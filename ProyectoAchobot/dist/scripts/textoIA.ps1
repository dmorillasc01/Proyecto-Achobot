param(
    [string]$texto
)

[Byte[]] $poweron  = 0xA0, 0x01, 0x01, 0xA2
[Byte[]] $poweroff = 0xA0, 0x01, 0x00, 0xA1

# Método 1: WMI
$puerto = Get-WmiObject Win32_SerialPort |
    Where-Object { $_.Name -match "CH340|Arduino|FTDI" } |
    Select-Object -First 1 -ExpandProperty DeviceID

# Método 2: fallback con GetPortNames si WMI no devuelve nada
if (-not $puerto) {
    $todosPuertos = [System.IO.Ports.SerialPort]::GetPortNames()
    # Filtrar COM1 y COM2, elegir el primero restante
    $puerto = $todosPuertos | Where-Object { ($_ -match "COM") -and ([int]($_.Substring(3)) -gt 2) } | Select-Object -First 1
}

# Si sigue sin encontrar → error
if (-not $puerto) {
    Write-Error "No se encontró ningún puerto COM USB disponible"
    exit
}
$ssd = New-Object System.IO.Ports.SerialPort $puerto,9600,None,8,One

[string]$info = Get-Content "$PSScriptRoot\info.txt"

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

# Función para parpadear ojos
function ParpadearLuces {
    param([int]$tiempoMilisegundos = 500)

    while ($true) {
        $ssd.Open()
        $ssd.Write($poweron,0,$poweron.Count)
        $ssd.Close()
        Start-Sleep -Milliseconds $tiempoMilisegundos

        $ssd.Open()
        $ssd.Write($poweroff,0,$poweroff.Count)
        $ssd.Close()
        Start-Sleep -Milliseconds $tiempoMilisegundos
    }
}

# Encendemos las luces del muñeco
EncenderLuces

# Voz
[void][System.Reflection.Assembly]::LoadWithPartialName("System.Speech") 
$speaker = [System.Speech.Synthesis.SpeechSynthesizer]::new() 
$speaker.SelectVoice("Microsoft Helena Desktop") 
$speaker.Speak("Chacho, he recibido tu mensaje, espera que piense...")  # <- usamos la variable

# Arrancamos el parpadeo en un Job separado
$jobParpadeo = Start-Job -ScriptBlock {
    param($puerto,$poweron,$poweroff)
    $ssd = New-Object System.IO.Ports.SerialPort $puerto,9600,None,8,One
    while ($true) {
        $ssd.Open(); $ssd.Write($poweron,0,$poweron.Count); $ssd.Close()
        Start-Sleep -Milliseconds 400
        $ssd.Open(); $ssd.Write($poweroff,0,$poweroff.Count); $ssd.Close()
        Start-Sleep -Milliseconds 400
    }
} -ArgumentList $puerto,$poweron,$poweroff

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
  "max_tokens": 60,
  "messages": [
    {"role": "system", "content": "$info"},
    {"role": "user", "content": "$texto"}
  ]
}
"@

# Convertimos a bytes UTF-8
$bytesBody = [System.Text.Encoding]::UTF8.GetBytes($bodyJson)

# Llamada a la API
try {
    $resp = Invoke-WebRequest -Uri $uri -Method Post -Headers $headers -Body $bytesBody -UseBasicParsing
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

# Detenemos el parpadeo
Stop-Job $jobParpadeo
Remove-Job $jobParpadeo

EncenderLuces

# Selecciona una voz en español instalada (Windows)
# Ejemplos: "Microsoft Helena Desktop", "Microsoft Pablo Desktop"
$speaker.SelectVoice("Microsoft Helena Desktop")
$speaker.Speak($texto)

# Apagamos las luces del muñeco al terminar
ApagarLuces