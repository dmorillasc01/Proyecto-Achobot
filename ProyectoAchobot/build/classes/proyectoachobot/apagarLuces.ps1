[Byte[]] $poweron = 0xA0, 0x01, 0x01, 0xA2
[Byte[]] $poweroff = 0xA0, 0x01, 0x00, 0xA1
$ssd= new-object system.io.ports.serialport COM7,9600,none,8,one

$ssd.open()
$ssd.write($poweroff,0,$poweroff.count)
$ssd.close()