#!/usr/bin/env bash

for((i=0;i<16;i++))
do
  echo "SymbolTable.putSymbol(\"R$i\",$i);";
done