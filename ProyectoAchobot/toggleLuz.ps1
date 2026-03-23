# Definir comandos
[Byte[]] $poweron  = 0xA0, 0x01, 0x01, 0xA2
[Byte[]] $poweroff = 0xA0, 0x01, 0x00, 0xA1

# Puerto serie
$portName = "COM6"
$baudRate = 9600

# Variable de estado (0 = apagado, 1 = encendido)
$global:estadoLuz = 0

function Toggle-Luz {
    $ssd = New-Object System.IO.Ports.SerialPort $portName, $baudRate, 'None', 8, 'One'
    $ssd.Open()
    
    if ($global:estadoLuz -eq 0) {
        # Encender luz
        $ssd.Write($poweron,0,$poweron.count)
        $global:estadoLuz = 1
        Write-Host "Luz encendida"
    } else {
        # Apagar luz
        $ssd.Write($poweroff,0,$poweroff.count)
        $global:estadoLuz = 0
        Write-Host "Luz apagada"
    }
    
    $ssd.Close()
}

# Ejecutar toggle
Toggle-Luz