#!/usr/bin/env bash

for((i=3;i<16;i++))
do
    #echo "Or(a=a[$i],b=b[$i],out=out[$i]);"
    # j=$((i-1))
    # k=$((j-1))
    # echo "Or(a=or$j$k,b=in[$i],out=or$i$j);"
#      echo "And(a=sela,b=a[$i],out=as$i);"
#     echo "And(a=selb,b=b[$i],out=bs$i);"
#     echo "And(a=selc,b=c[$i],out=cs$i);"
#     echo "And(a=seld,b=d[$i],out=ds$i);"
#     echo ""
#    echo "Or(a=as$i,b=bs$i,out=abs$i);"
#     echo "Or(a=cs$i,b=abs$i,out=cbs$i);"
#     echo "Or(a=ds$i,b=cbs$i,out=out[$i]);"
    last=$((i-1))
    echo "HalfAdder(a=int[$i],b=carry$last,sum=out[$i],carry=carry$i);"

#echo "b[$i]=sela"


done