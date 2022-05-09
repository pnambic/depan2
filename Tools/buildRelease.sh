#! /bin/bash
# For each file, output the name and then the contents
set -o nounset
set -o errexit

function buildRelease {
  mvn -f depan-release-master/ clean install
}

function usage {
  cat >&2 <<HELP
Usage: $(basename "$0") [ opts | files.. ]
Build DepAn Release
  --help: this message
HELP
}

function main {

  while [ $# -gt 0 ]; do
    case $1 in
    --help)
      usage
      exit 0;;
    *)
      # die
      exit 1;;
    esac
  done

  buildRelease
}

main "$@"
