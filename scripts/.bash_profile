die() {  
  echo >&2 "$@"
  exit 1
}

export GITAWAREPROMPT="$HOME/.bash/git-aware-prompt"
source "${GITAWAREPROMPT}/main.sh"

export PS1="\W \[$txtcyn\]\$git_branch\[$txtred\]\$git_dirty\[$txtrst\] [\u 🍕 ] $ "
export SUDO_PS1="\[$bakred\]\W [\u]\[$txtrst\] $ "

alias ll="ls -la"
# alias ec2="ssh-to-AWS-EC2.sh"
alias ping="ping -c 4"

export JAVA_HOME=$(/usr/libexec/java_home)

# shortcuts
export GOPATH="$HOME/Projects/tests/golang"
export PYTHONUSERBASE="$HOME/.local"

# add to $PATH

if [ `echo $PATH | egrep '/usr/local/go' | wc -l` -eq 0 ] ; then
  export PATH=$PATH:/usr/local/go/bin:$GOPATH/bin
fi

if [ `echo $PATH | egrep $HOME'/.local/bin' | wc -l` -eq 0 ] ; then
  export PATH=$PATH:$HOME/.local/bin
fi

export CLASSPATH=/Users/aldwin.barredo/Projects/sari-sari-store/java

stty -ixon

# Docker shortcuts
alias dockerid="docker-id.sh"
alias yesdock="dockerid -a=true"
alias nodock="dockerid -a=false"

docker_remove_dangling () {
  docker rmi -f $(docker images -f 'dangling=true' | egrep -v "IMAGE" | 
  awk '{ print $3 }')
}

# git shortcuts
git_merge() {
  if [ "$#" -eq "2" ]; then
    git checkout $1
    git pull
    if git merge --no-ff $2; then
      git push --no-verify
    else
      echo "Merge failed."
    fi
  else
    echo "Invalid number of arguments."
  fi
}

git_delete_branch() {
  if [ "$#" -eq "1" ]; then 
    if [ "$(git ls-remote | egrep $1 | wc -l)" -eq "1" ]; then
      git push --no-verify --delete origin $1 
    fi
    if [ "$(git branch | egrep $1 | wc -l)" -eq "1" ]; then
      git branch -D $1
    fi
  else
    echo "Invalid number of arguments."
  fi
}
