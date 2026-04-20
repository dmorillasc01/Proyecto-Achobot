#!/bin/bash

puerto="/dev/ttyUSB0"

printf "\xA0\x01\x01\xA2" > $puerto