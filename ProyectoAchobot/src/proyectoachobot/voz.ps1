param(
    [string]$texto
)

# Enciende luces
$ssd.open()
$ssd.write($poweron,0,$poweron.count)
$ssd.close()

# Sonido
$player = New-Object System.Media.SoundPlayer "$PSScriptRoot/sonidojake.wav"
$player.Play()

Start-Sleep -Seconds 0.75

# Voz
[void][System.Reflection.Assembly]::LoadWithPartialName("System.Speech") 
$speaker = [System.Speech.Synthesis.SpeechSynthesizer]::new() 
$speaker.SelectVoice("Microsoft Helena Desktop") 
$speaker.Speak($texto)  # <- usamos la variable

# Apaga luces
$ssd.open()
$ssd.write($poweroff,0,$poweroff.count)
$ssd.close()