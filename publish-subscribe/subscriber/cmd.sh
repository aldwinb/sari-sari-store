#!/usr/bin/env sh

# Do tasks here that are required for your Docker development environment to
# run (e.g. install requirements)
# 
pip install --upgrade -r /app/requirements.txt

export PYTHONPATH="/app"

# start a terminal inside the Docker container
#python3 /app/contentfilterer/app.py
sh