# IMPORTANTE:
# Revoca la API key que pegaste antes y crea una nueva.
# Guarda este .ps1 en UTF-8 con BOM si puedes.

chcp 65001 > $null
[Console]::InputEncoding  = [System.Text.UTF8Encoding]::new()
[Console]::OutputEncoding = [System.Text.UTF8Encoding]::new()
$OutputEncoding           = [System.Text.UTF8Encoding]::new()

$pregunta = Read-Host "Pregúntame lo que quieras"

$uri = "https://api.openai.com/v1/chat/completions"
$apiKey = "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"  # Asegúrate de reemplazar 'sk-code' con tu clave de API real

$headers = @{
    "Authorization" = "Bearer $apiKey"
    "Content-Type"  = "application/json; charset=utf-8"
}

$body = @{
    model = "gpt-3.5-turbo"
    messages = @(
        @{
            role    = "user"
            content = $pregunta
        }
    )
} | ConvertTo-Json -Depth 10

# Pedimos la respuesta RAW
$resp = Invoke-WebRequest `
    -Uri $uri `
    -Method Post `
    -Headers $headers `
    -Body $body `
    -UseBasicParsing

# Fuerza la lectura como UTF-8
$bytes = $resp.RawContentStream.ToArray()
$jsonTexto = [System.Text.Encoding]::UTF8.GetString($bytes)

# Convierte JSON a objeto
$obj = $jsonTexto | ConvertFrom-Json

$texto = $obj.choices[0].message.content

Write-Host ""
Write-Host "Respuesta:"
Write-Host $texto

Add-Type -AssemblyName System.Speech
$speaker = New-Object System.Speech.Synthesis.SpeechSynthesizer
$speaker.Speak($texto)