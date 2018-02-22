die() {  
  echo >&2 "$@"
  exit 1
}

if [ -f $(brew --prefix)/etc/bash_completion ]; then
  . $(brew --prefix)/etc/bash_completion
fi

# COLORS!!!
export CLICOLOR=1
export LSCOLORS=ExGxBxDxCxEgEdxbxgxcxd

export GITAWAREPROMPT="$HOME/.bash/git-aware-prompt"
source "${GITAWAREPROMPT}/main.sh"

export PS1="\W \[$txtcyn\]\$git_branch\[$txtred\]\$git_dirty\[$txtrst\] [\u 🍕 ] $ "
export SUDO_PS1="\[$bakred\]\W [\u]\[$txtrst\] $ "
export JAVA_HOME=$(/usr/libexec/java_home)
export GOPATH="$HOME/Projects/tests/golang"
export PYTHONUSERBASE="$HOME/.local"
export VAULT_ADDR=https://prod.vault.conde.io:443
export CLASSPATH=/Users/abarredo/Projects/personal/sari-sari-store/practice/java/com/leetcode

alias ll="ls -la"
alias ping="ping -c 4"
alias vaultauth="vault auth -method=ldap username=abarredo"

# add to $PATH
if [ `echo $PATH | egrep '/usr/local/go' | wc -l` -eq 0 ] ; then
  export PATH=$PATH:/usr/local/go/bin:$GOPATH/bin
fi

if [ `echo $PATH | egrep $HOME'/.local/bin' | wc -l` -eq 0 ] ; then
  export PATH=$PATH:$HOME/.local/bin
fi

stty -ixon

# Docker shortcuts
alias dockerid="docker-id.sh"
alias yesdock="dockerid -a=true"
alias nodock="dockerid -a=false"

docker_rm_exited() {
  docker rm -f $(docker ps -f status=exited -q)
 }
docker_rmi_dangling() {
  docker rmi -f $(docker images -f dangling=true -q)
 }

# git shortcuts
git_merge() {
  if [ "$#" -gt "0" ]; then
    mergee=${2:-$(git rev-parse --abbrev-ref HEAD)}
    git checkout $1
    git pull
    if git merge --no-commit ${mergee}; then
        git commit
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
