#!/bin/bash
for (( c=0; c <= 4; c++ ))
do
	java -cp ./lib_antlr/antlr.jar:bin/ Transformer.Main student.c 1 3 $c
done
exit 0
