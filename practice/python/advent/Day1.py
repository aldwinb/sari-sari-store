#!/usr/bin/env python

import sys


def count_parens(parens):
    if not parens or len(parens) == 0:
        return 0

    count = 0
    for p in parens:
        if p == '(':
            count = count+1
        else:
            count = count-1

    return count


def main():
    args = sys.argv[1:]
    with open(args[0]) as f:
        for line in f:
            print (count_parens(line))


if __name__ == '__main__':
    main()
