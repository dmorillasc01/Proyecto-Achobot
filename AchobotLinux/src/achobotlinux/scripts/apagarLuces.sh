#!/bin/bash

puerto="/dev/ttyUSB0"

printf "\xA0\x01\x00\xA1" > $puerto