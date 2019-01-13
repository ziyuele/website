#!/bin/bash

# env check
which javac > /dev/null
if [[ $? != 0 ]];then
    echo "check your java env"
fi
cd $(cd `dirname "$0"`; pwd)

function exit_with_usage(){
    echo "usage: sh build.sh"
    echo "\t --tgz"
    echo "\t --test"
    echo "\t --compile"
    echo "\t --build"
}

function make_tgz(){
    mvn clean package -DSkiptests
    rm -rf output; mkdir output
    cd target
    jar_name=`ls website*.jar`
    tgz_name=${jar_name%.*}
    echo "make tgz doing..."
    tar zcf "$tgz_name".tgz conf lib bin webapp website-*.jar
    mv "$tgz_name".tgz ../output
    echo "done"
}

function build(){
    mvn clean package -DSkiptests
    rm -rf output; mkdir output
    cd target
    mv conf lib bin webapp website-*.jar  ../output/
    echo "done"
}

if [[ $# != 1 ]];then
    exit_with_usage
    exit -1
fi

while (("$#")); do
    case $1 in
        --build)
        build
        ;;
        --tgz)
        make_tgz
        ;;
        --test)
        mvn clean test
        echo "all test done,check it in target file"
        exit 0
        ;;
        --compile)
        mvn clean compile
        echo "compile done, check it in target file"
        exit 0
        ;;
        --help)
        exit_with_usage
        exit 0
        ;;
        *)
        exit_with_usage
        exit -1
        ;;
    esac
    shift
done
