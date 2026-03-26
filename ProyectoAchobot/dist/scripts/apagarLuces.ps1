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

$ssd= new-object system.io.ports.serialport $puerto,9600,none,8,one

$ssd.open()
$ssd.write($poweroff,0,$poweroff.count)
$ssd.close()