# Obtener ruta del escritorio
$desktop = [Environment]::GetFolderPath("Desktop")

# Nombre del acceso directo
$shortcutPath = Join-Path $desktop "AchobotAPP.lnk"

# Ruta base (donde está este script)
$basePath = $PSScriptRoot

# Archivo .jar e icono
$jar = Join-Path $basePath "../ProyectoAchobot.jar"
$icon = Join-Path $basePath "../iconoAchobot.ico"

# Comprobar si existe
if (-Not (Test-Path $shortcutPath)) {

    $WshShell = New-Object -ComObject WScript.Shell
    $shortcut = $WshShell.CreateShortcut($shortcutPath)

    # Ejecutar Java directamente
    $shortcut.TargetPath = "javaw"
    $shortcut.Arguments = "-jar `"$jar`""
    $shortcut.WorkingDirectory = $basePath
    $shortcut.IconLocation = $icon

    $shortcut.Save()

    Write-Host "Acceso directo creado en el escritorio"
}
else {
    Write-Host "El acceso directo ya existe"
}