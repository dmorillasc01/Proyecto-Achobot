# Cambiar la consola a UTF-8 para mostrar correctamente caracteres especiales
chcp 65001
[Console]::OutputEncoding = [System.Text.Encoding]::UTF8

# Configuración de la solicitud
$uri = "https://api.openai.com/v1/chat/completions"
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
            role = "system"
            content = "Contesta como una mascota"
        },
        @{
            role = "user"
            content = "Hola, ¿qué tal estas?"
        }
    )
}

# Convertir el cuerpo a JSON con codificación UTF-8
$jsonBody = $body | ConvertTo-Json -Depth 10 -Compress
$bytes = [System.Text.Encoding]::UTF8.GetBytes($jsonBody)
$stream = [System.IO.MemoryStream]::new($bytes)

# Realizar la solicitud
$response = Invoke-RestMethod -Uri $uri -Method Post -Headers $headers -Body $stream

# Mostrar la respuesta correctamente
Write-Host $response.choices[0].message.content