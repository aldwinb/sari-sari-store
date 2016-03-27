#!/usr/bin/env bash

# install Docker shortcuts
if ! egrep 'docker_rm_exited\('; then
  echo -e "docker_rm_exited() {\n"\
  " docker rm -f \$(docker ps -f status=exited -q)\n"\
  "}" >> /home/vagrant/.profile
fi

if ! egrep 'docker_rmi_dangling\('; then
  echo -e "docker_rmi_dangling() {\n"\
  " docker rmi -f \$(docker images -f dangling=true -q)\n"\
  "}" >> /home/vagrant/.profile
fi