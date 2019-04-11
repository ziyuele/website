#!/usr/bin/env bash
bin_path=$(cd `dirname "$0"`; pwd)/..
service_pid=$bin_path/.fileServer.pid

if [ -f "$service_pid" ]; then
    TARGET_ID="$(cat "$service_pid")"
    if [[ $(ps -p "$TARGET_ID" -o comm=) =~ "java" ]]; then
        echo "Stopping service"
        kill -9 "$TARGET_ID" && rm -f "$service_pid"
        exit 0
    else
        echo "no service to stop..."
        exit -1
    fi
else
    echo "no service to stop..."
    exit -1
fi
