#!/bin/bash

##########################
# Script Achobot en Bash #
# Diego Morillas Criado  #
##########################

# FUNCIONES

## Función 1 -- Encender las luces
opc1() {
    printf "\xA0\x01\x01\xA2" > $puerto
}

## Función 2 -- Apagar las luces
opc2() {
    printf "\xA0\x01\x00\xA1" > $puerto
}

## Función 3 -- Parpadeo de luces
opc3 () {
    clear

    read -p "Indique la cantidad de veces que desea que parpadeen las luces: " parpadeo
    echo ""
    read -p "Ahora indique la velocidad a la que desea que parpadee (en segundos): " velocidad
    for (( i=0; i<$parpadeo; i++ ))
    do
        opc1
         sleep $velocidad
        opc2
         sleep $velocidad
    done
}

## Función 4 -- Texto a Voz
opc4 () {

    read -p "¿Qué quiéres que diga Achobot? : " texto
    echo ""

    aplay sonidoachobot.wav &> /dev/null
    opc1
    spd-say -l es -t male3 -r 10 -w "$texto"
    opc2
}


# Variables
opcionmenu=1000;
puerto="/dev/ttyUSB0"

# MENU
while [ $opcionmenu -ne 0 ]
do 
    clear
    echo "¡Bienvenido al menú de ACHOBOT!"
    echo ""
    echo " _______________________________"
    echo "| 1. Encender las luces.........|"
    echo "| 2. Apagar las luces...........|"
    echo "| 3. Parpadeo de luces..........|"
    echo "| 4. Texto a Voz................|"
    echo "| 0. Salir del programa.........|"
    echo "|_______________________________|"
    echo "|"
    read -p "Introduce la opción que desee: " opcionmenu

    case "$opcionmenu" in
        "0")
            echo "Saliendo del programa..."
            echo ""
         ;;
        "1") 
            opc1
            echo "Encendiendo luces..."
            echo ""
            read -p "Presiona una tecla para volver al menú..."
         ;;
        "2")
            opc2
            echo "Apagando luces..."
            echo ""
            read -p "Presiona una tecla para volver al menú..."
         ;;
        "3")
            opc3
            read -p "Presiona una tecla para volver al menú..."
         ;;
        "4")
            opc4
            read -p "Presiona una tecla para volver al menú..."
         ;;
        *)
            echo "Opción no válida"
         ;;
    esac
done


