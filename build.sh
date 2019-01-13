#!/bin/bash

# env check
which javac > /dev/null
if [[ $? != 0 ]];then
    echo "check your java env"
fi
cd $(cd `dirname "$0"`; pwd)

function exit_with_usage(){
    echo "usage: sh build.sh"
    echo "\t --test"
    echo "\t --compile"
    echo "\t --build"
}

function build(){
    mvn clean package -DSkiptests
    rm -rf output; mkdir output
    mkdir -p output/httpServer
    mkdir -p output/master
    mkdir -p output/worker
    # set httpServer
    cd httpServer/target
    mv conf lib bin httpServer-*.jar ../../output/httpServer
    echo "http server done"
    # other moudle is not build yet
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
