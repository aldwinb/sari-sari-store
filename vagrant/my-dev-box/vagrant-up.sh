#!/usr/bin/env bash

# attempts graceful exit
die () {
  echo >&2 "$@"
  exit 1
}

cmd="vagrant up"
if [ "${1+defined}" ] && [ "$1" = "-r" ]; then
  cmd="vagrant reload"
  shift
fi

# vagrant up / reload
if $cmd $@; then
    # if successful, start rsync as background process
    vagrant gatling-rsync-auto &> /tmp/my-dev-box-1-rsync.log &
fi
