#!/usr/bin/env bash
###########################################################################
	#
	# Author kangjian
	# Date 2019/1/12 00:11
	# Version 1.0.0.1
	# start bigsql-spark-service
	#
	###########################################################################
	work_path=$(cd `dirname "$0"`; pwd)/..
	mkdir -p /tmp/Application
	service_pid=/tmp/Application/Application.pid
	function exit_with_usage(){
	echo "usage:"
	echo "\t--debug   # start debug moudle to start service"
	echo "\t--initdb    #init database for service"
	exit -1
	}

	while (("$#")); do
	    case $1 in
	        --debug)
	        JAVA_DEBUG_ARGV="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=8999"
	        ;;
	        --initdb)
	        INPUT_ARGV="initdb"
	        ;;
	        *)
	        exit_with_usage
	        ;;
	    esac
	done
	# check env (if it is nessery)
	if [[ -f "$service_pid" ]]; then
	    TARGET_ID="$(cat "$service_pid")"
	    if [[ $(ps -p "$TARGET_ID" -o comm=) =~ "java" ]]; then
	        echo "Service aleady running as process $TARGET_ID.  Stop it first."
	        exit 1
	    fi
	fi
	#set env
	JAVA_BASE_ARGV="-Xms3g -Xmx3g"

	# set class path
	export CLASSPATH=$work_path/conf/log4j.properties:$CLASSPATH
	export CLASSPATH=$work_path/lib/*:$CLASSPATH
	export CLASSPATH=$work_path/*:$CLASSPATH
	export CLASSPATH=$work_path/conf/:$CLASSPATH
	export CLASSPATH=$work_path/webapp/*:$CLASSPATH

	# start service
	nohup java  $JAVA_BASE_ARGV $JAVA_DEBUG_ARGV com.ziyue.website.httpserver.HttpServer $INPUT_ARGV >> nohup.out 2>&1 < /dev/null &
	pid=$!
	echo "service start whith pid $pid"
    mkdir -p /tmp/HttpServer
	echo $pid > /tmp/HttpServer/HttpServer.pid
