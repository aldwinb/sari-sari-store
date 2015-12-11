#!/usr/bin/env python

import sys

tag_hash = {}

def count_and_tag(code):
    if not code or len(code) == 0:
        return ''

    if len(code) == 1:
        tag_hash[code] = 2
        return 2

    if code in tag_hash.keys():
        return tag_hash[code]

    ctr = 1
    prev = code[0]
    rem = code[1:]
    res = ''
    count = 1
    for c in rem:
        if c != prev:

            res = str.format('{res}{ctr}{prev}', res=res, ctr=ctr, prev=prev)
            prev = c
            ctr = 1
        else:
            ctr = ctr+1

    res = str.format('{res}{ctr}{prev}', res=res, ctr=ctr, prev=prev)
    return res


def main():
    args = sys.argv[1:]
    print (args[0])
    pat = args[0]
    loop = args[1]
    for i in range(1, int(loop)):
        pat = count_and_tag(pat)
        print (pat)

    # with open(args[0]) as f:
    #     for line in f:



if __name__ == '__main__':
    main()
