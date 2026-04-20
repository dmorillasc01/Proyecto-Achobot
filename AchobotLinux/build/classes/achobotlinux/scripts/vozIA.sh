#!/bin/bash

# API OpenAI
API_KEY="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"

puerto="/dev/ttyUSB0"

encender() {
echo -ne '\xA0\x01\x01\xA2' > $puerto
}

apagar() {
echo -ne '\xA0\x01\x00\xA1' > $puerto
}

parpadeo() {
    while true; do
        encender
        sleep 0.4
        apagar
        sleep 0.4
    done
}

# Info del sistema
INFO=$(cat "$(dirname "$0")/info.txt")

# Voz (Linux usa espeak o festival)
say() {
  spd-say -l es -r -20  -t male2 -i 80 -w  "$1"
}

encender

say "Estoy listo para escucharte, háblame"

apagar
encender

# Pregunta (manual en terminal)
arecord -f cd -t wav -d 4 audio.wav

RESPONSE=$(curl -s https://api.openai.com/v1/audio/transcriptions \
-H "Authorization: Bearer $API_KEY" \
-F file=@audio.wav \
-F model=whisper-1)

PREGUNTA=$(echo "$RESPONSE" | jq -r '.text')

parpadeo &
PID_PARPADEO=$!



JSON=$(jq -n \
--arg info "$INFO" \
--arg pregunta "$PREGUNTA" \
'{
model: "gpt-3.5-turbo",
max_tokens: 60,
messages: [
{role: "system", content: $info},
{role: "user", content: $pregunta}
]
}')

RESPONSE=$(curl -s https://api.openai.com/v1/chat/completions \
-H "Authorization: Bearer $API_KEY" \
-H "Content-Type: application/json" \
-d "$JSON")

# Extraer respuesta (requiere jq)
TEXT=$(echo "$RESPONSE" | jq -r '.choices[0].message.content // "ERROR"')

kill $PID_PARPADEO 2>/dev/null
encender

echo "Respuesta: $TEXT"
say "$TEXT"

sleep 0.5
apagar
