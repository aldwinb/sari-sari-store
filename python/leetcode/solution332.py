#!/usr/bin/env python

import sys

class Graph(object):

    def __init__(self, tickets):
        self.vertices = {}
        for ticket in tickets:
            self.__add(ticket)

    def __add(self, ticket):
        if ticket[0] not in self.vertices.keys():
            self.vertices[ticket[0]] = []
        self.vertices[ticket[0]].append(ticket[1])

    def getAdj(self, v):
        if v in self.vertices.keys():
            return self.vertices[v]
        return None


class Solution(object):

    visited = []

    def findItinerary(self, tickets):
        """
        :type tickets: List[List[str]]
        :rtype: List[str]
        """
        g = Graph(tickets)
        list = []
        self.dfs(g, "JFK", list)
        return list

    def dfs(self, g, v, list):
        list.append(v)
        if v in self.visited:
            return

        self.visited.append(v)

        adj = g.getAdj(v)
        if not adj:
            return

        for a in sorted(adj):
            self.dfs(g, a, list)


def main():
    args = sys.argv[1:]
    tix = []
    for arg in args:
        tix.append(arg.split(','))
    itinerary = Solution().findItinerary(tix)
    print ','.join(itinerary)

if __name__ == '__main__':
    main()