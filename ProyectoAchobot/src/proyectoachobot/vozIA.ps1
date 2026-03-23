# Configuración de UTF-8
chcp 65001 > $null
[Console]::InputEncoding  = [System.Text.UTF8Encoding]::new()
[Console]::OutputEncoding = [System.Text.UTF8Encoding]::new()
$OutputEncoding           = [System.Text.Encoding]::UTF8

# Configuración del puerto serie y luces del muñeco
[Byte[]] $poweron  = 0xA0, 0x01, 0x01, 0xA2
[Byte[]] $poweroff = 0xA0, 0x01, 0x00, 0xA1
$ssd = New-Object System.IO.Ports.SerialPort COM7,9600,None,8,One

function EncenderLuces {
    $ssd.Open()
    $ssd.Write($poweron,0,$poweron.Count)
    $ssd.Close()
}

function ApagarLuces {
    $ssd.Open()
    $ssd.Write($poweroff,0,$poweroff.Count)
    $ssd.Close()
}

# Encendemos las luces del muñeco
EncenderLuces

# Configuración del reconocimiento de voz en español
Add-Type -AssemblyName System.Speech
$culture = New-Object System.Globalization.CultureInfo("es-ES")
$rec = New-Object System.Speech.Recognition.SpeechRecognitionEngine($culture)
$rec.SetInputToDefaultAudioDevice()
$rec.LoadGrammar((New-Object System.Speech.Recognition.DictationGrammar))

Write-Host "Habla ahora (espera 1-2 s de silencio para terminar)..."
$result = $rec.Recognize()  # Captura una frase hasta silencio
$rec.Dispose()

if ($result) {
    $pregunta = $result.Text
    Write-Host "Tu pregunta: $pregunta"
} else {
    Write-Host "No se detectó voz. Saliendo..."
    ApagarLuces
    exit
}

# Configuración de la API de OpenAI
$uri = "https://api.openai.com/v1/chat/completions"
$apiKey = "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"  # ⚠ Reemplaza por tu nueva API key
$headers = @{
    "Authorization" = "Bearer $apiKey"
    "Content-Type"  = "application/json; charset=utf-8"
}

# Construcción manual del JSON para evitar errores 400
$bodyJson = @"
{
  "model": "gpt-3.5-turbo",
  "messages": [
    {"role": "system", "content": "Habla siempre en español, de manera clara y amistosa."},
    {"role": "user", "content": "$pregunta"}
  ]
}
"@

# Convertimos a bytes UTF-8
$bytesBody = [System.Text.Encoding]::UTF8.GetBytes($bodyJson)

# Llamada a la API
try {
    $resp = Invoke-WebRequest -Uri $uri -Method Post -Headers $headers -Body $bytesBody
} catch {
    Write-Host "Error al llamar a la API: $($_.Exception.Message)"
    ApagarLuces
    exit
}

# Fuerza la lectura UTF-8
$bytes = $resp.RawContentStream.ToArray()
$jsonTexto = [System.Text.Encoding]::UTF8.GetString($bytes)
$obj = $jsonTexto | ConvertFrom-Json
$texto = $obj.choices[0].message.content

# Mostrar y hablar la respuesta de la IA en español
Write-Host "`nRespuesta de la IA:`n$texto"
$speaker = New-Object System.Speech.Synthesis.SpeechSynthesizer

# Selecciona una voz en español instalada (Windows)
# Ejemplos: "Microsoft Helena Desktop", "Microsoft Pablo Desktop"
$speaker.SelectVoice("Microsoft Helena Desktop")
$speaker.Speak($texto)

# Apagamos las luces del muñeco al terminar
ApagarLuces