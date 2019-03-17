#!/bin/bash
FONT_END_PAHT=httpServer/src/main/resources
VERSION_SET_PATH=common/src/main/resources/version


# env check
which javac > /dev/null
if [[ $? != 0 ]];then
    echo "check your java env"
fi

echo "build-time:["`date`"]  version:["`git rev-parse --short HEAD`"]" > $VERSION_SET_PATH
cd $(cd `dirname "$0"`; pwd)

function exit_with_usage(){
    echo "usage: sh build.sh"
    echo -e "\t --test"
    echo -e "\t --compile"
    echo -e "\t --build"
}

function build(){
    # http module
    cd httpServer/src/main/resources && npm install  && npm run build && rm -rf node_modules 
    cd -
    mvn clean package -DskipTests
    rm -rf output; mkdir output
    mkdir -p output/httpServer
    mkdir -p output/master
    mkdir -p output/worker
    mkdir -p output/sso
    # set httpServer
    cd httpServer/target
    mv conf lib bin httpServer-*.jar ../../output/httpServer && cd -
    echo "http server done"
    # set master
    cd master/target
    mv conf lib bin master-*.jar ../../output/master && cd -
    echo "master done"
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
