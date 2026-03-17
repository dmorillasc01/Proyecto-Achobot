##############################################
### Este es el script de la luz de Achobot   #
### Hecho por: Diego Morillas Criado 2ºSMR   #
### Grupo: 5 | Sistemas Operativos en Red    #
##############################################

# FUNCIONES

## Encender la luz

Function opc1 {

$ssd.open()
$ssd.write($poweron,0,$poweron.count)
$ssd.close()

}


## Apagar la luz

Function opc2 {

$ssd.open()
$ssd.write($poweroff,0,$poweroff.count)
$ssd.close()

}

## Parpadeo

Function opc3 {

[int]$i = 1

    for ( $i; $i -le 5; $i++) {
    
    $ssd.open()
    $ssd.write($poweron,0,$poweron.count)
    $ssd.close()

    Start-Sleep 1

    $ssd.open()
    $ssd.write($poweroff,0,$poweroff.count)
    $ssd.close()
    }
}


## Voz a texto

Function opc4 {

# Enciende las luces
opc1

$player = New-Object System.Media.SoundPlayer "C:\Users\alumno\Documents\_jake\sonidojake.wav"
$player.Play()

#Cargar los espacios de nombres System.Speech que contienen los tipos que admiten el reconocimiento de voz 
[void][System.Reflection.Assembly]::LoadWithPartialName("System.Speech
 ") 
#Crear un objeto de tipo motor de síntesis de voz 
$speaker = [System.Speech.Synthesis.SpeechSynthesizer]::new() 
#Cambiar la voz actual del motor de síntesis de voz 
$speaker.SelectVoice("Microsoft Helena Desktop") 
#Pronunciar el texto 
$speaker.Speak("A ver guapetes, la verdad que este ordenador es completamente inútil. En mi opinión para lo único que sirve es para ofimática. Pero si no es capaz de soportar mi flou, que se vaya a la guapetepapelera.") 

# Apaga las luces
opc2

}

Function opc5 {

# Enciende las luces
opc1

$player = New-Object System.Media.SoundPlayer "C:\Users\alumno\Documents\_jake\sonidojake.wav"
$player.Play()

# Requiere Windows con paquete de reconocimiento "Español (España)"
Add-Type -AssemblyName System.Speech
$culture = New-Object System.Globalization.CultureInfo("es-ES")
$rec = New-Object System.Speech.Recognition.SpeechRecognitionEngine($culture)
$rec.SetInputToDefaultAudioDevice()
$rec.LoadGrammar((New-Object System.Speech.Recognition.DictationGrammar))

Write-Host "Habla y guarda 1-2 s de silencio..."

$result = $rec.Recognize() # una sola frase hasta silencio

if ($result) {
    $texto = $result.Text
    Write-Host "Texto: $texto"
}

else {
    Write-Host "No se ha reconocido nada."
}

$rec.Dispose()

# Apaga las luces
opc2

# Enciende las luces
opc1

#Cargar los espacios de nombres System.Speech que contienen los tipos que admiten el reconocimiento de voz 
[void][System.Reflection.Assembly]::LoadWithPartialName("System.Speech
 ") 
#Crear un objeto de tipo motor de síntesis de voz 
$speaker = [System.Speech.Synthesis.SpeechSynthesizer]::new() 
#Cambiar la voz actual del motor de síntesis de voz 
$speaker.SelectVoice("Microsoft Helena Desktop") 
#Pronunciar el texto 
$speaker.Speak($texto) 

# Apaga las luces
opc2


}

Function opc6 {

# Enciende las luces
opc1

$player = New-Object System.Media.SoundPlayer "C:\Users\alumno\Documents\_jake\sonidojake.wav"
$player.Play()

# Requiere Windows con paquete de reconocimiento "Español (España)"
Add-Type -AssemblyName System.Speech
$culture = New-Object System.Globalization.CultureInfo("es-ES")
$rec = New-Object System.Speech.Recognition.SpeechRecognitionEngine($culture)
$rec.SetInputToDefaultAudioDevice()
$rec.LoadGrammar((New-Object System.Speech.Recognition.DictationGrammar))

Write-Host "Habla y guarda 1-2 s de silencio..."

$result = $rec.Recognize() # una sola frase hasta silencio

if ($result) {
    $texto = $result.Text
    Write-Host "Texto: $texto"
}

else {
    Write-Host "No se ha reconocido nada."
}

$rec.Dispose()

# Apaga las luces
opc2

# Configuración de la solicitud
$uri = "https://api.openai.com/v1/chat/completions"

# Esta es la clave de API real (CUIDADO ES LA CARLOS APIKEY)
$apiKey = "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"
$headers = @{
"Authorization" = "Bearer $apiKey"
"Content-Type" = "application/json; charset=utf-8"
}

# Cuerpo de la solicitud
$body = @{
model = "gpt-3.5-turbo"
max_tokens = 60
messages = @(
@{

# Aquí te digo el rol que vas a tener

role = "system"
content = "$info"
},


@{

# Aquí te hago la pregunta que vas a contestar
role = "user"
content = $texto
}
)
}
# Convertir el cuerpo a JSON con codificación UTF-8
$jsonBody = $body | ConvertTo-Json -Depth 10 -Compress
$bytes = [System.Text.Encoding]::UTF8.GetBytes($jsonBody)
$stream = [System.IO.MemoryStream]::new($bytes)

# Realizar la solicitud
$response = Invoke-RestMethod -Uri $uri -Method Post -Headers $headers -Body $stream

# Convertir la respuesta a UTF-8
$decodedResponse = [System.Text.Encoding]::UTF8.GetString([System.Text.Encoding]::Default.GetBytes($response.choices.message.content))

# Muestro por pantalla el resultado de la respuesta
Write-Host $decodedResponse

# Enciende las luces
opc1

#Cargar los espacios de nombres System.Speech que contienen los tipos que admiten el reconocimiento de voz 
[void][System.Reflection.Assembly]::LoadWithPartialName("System.Speech
 ") 
#Crear un objeto de tipo motor de síntesis de voz 
$speaker = [System.Speech.Synthesis.SpeechSynthesizer]::new() 
#Cambiar la voz actual del motor de síntesis de voz 
$speaker.SelectVoice("Microsoft Helena Desktop") 
#Pronunciar el texto 
$speaker.Speak($decodedResponse) 

# Apaga las luces
opc2

}


# VARIABLES

[Byte[]] $poweron = 0xA0, 0x01, 0x01, 0xA2
[Byte[]] $poweroff = 0xA0, 0x01, 0x00, 0xA1
$ssd= new-object system.io.ports.serialport COM6,9600,none,8,one

[int]$opcionmenu = 0

$info = Get-Content "C:\Users\alumno\Documents\_jake\comportamiento.txt"

# MENU

While ( $opcionmenu -lt 4) {
cls
Write-Host "¡Bienvenido a la configuración de la luz!" -ForegroundColor cyan
Write-Host " _________________________________ "
Write-Host "|1. Encender las luces------------|"
Write-Host "|2. Apagar las luces--------------|"
Write-Host "|3. Parpadeo----------------------|"
Write-Host "|4. Texto a voz-------------------|"
Write-Host "|5. Micrófono---------------------|"
Write-Host "|6. IA----------------------------|"
Write-Host "|7. Salir-------------------------|"
Write-Host "|_________________________________| Creado por: Diego Morillas Criado / Grupo 05"
Write-Host "|"
[string]$opcionmenu = Read-Host  "|Elija la opción que desee "

    switch ($opcionmenu) {

        1  { opc1 }
        2  { opc2 }
        3  { opc3 }
        4  { opc4 }
        5  { opc5 }
        6  { opc6 }
        7  { Write-Host ""
             Write-Host "Saliendo del script..." -ForegroundColor DarkGray; exit }
        
        default { Write-Host "Parece que ha habido un problema con la elección..." -ForegroundColor Red }

    }

    Write-Host ""
    Read-Host "Pulse para volver al menú "
    $opcionmenu = 0

}