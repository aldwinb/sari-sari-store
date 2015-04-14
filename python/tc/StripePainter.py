import sys


class StripePainter:

    __cost = []

    def minStrokes(self, stripes):
        if len(stripes) <= 1:
            return len(stripes)
        for i in range(len(stripes)):
            self.__cost.append(sys.maxint)
        return self.__cut(stripes, 0)
        # return self.__getMin(stripes)

    def __cut(self, stripes, i):
        if len(stripes) <= 1:
            return len(stripes)
        if self.__cost[i] != sys.maxint:
            return self.__cost[i]
        min = sys.maxint
        for i in range(1, len(stripes)):
            stroke = stripes[i:]
            # print 'cut = %s %s' % (stripes[:i], stroke)
            left = self.__getMin(stripes[:i])
            right = self.__cut(stroke, i)
            print 'cut_l = %s, cnt_l = %s, cut_r = %s, cnt_r = %s' \
                % (stripes[:i], left,  stroke, right)
            c = left+right
            if (min > c):
                min = c
        self.__cost[i] = c
        return self.__cost[i]

    def __getMin(self, stripes):
        # print "stripes = %s" % stripes
        stroke = stripes[0]
        new_stroke = -1
        c = 1
        for i in range(1, len(stripes)):
            if stroke != stripes[i] and new_stroke == -1:
                new_stroke = i
            elif stroke == stripes[i] and new_stroke != -1:
                c = c+self.__getMin(stripes[new_stroke:i])
                # print "count = %s" % c
                new_stroke = -1

        if new_stroke != -1:
            c = c+self.__getMin(stripes[new_stroke:i+1])
        # print "count = %s" % c
        return c

if __name__ == '__main__':
    sp = StripePainter()
    for line in sys.stdin:
        # line = line.replace('\n','')
        # line2 = ''
        # for i in range(1, len(line)+1):
        #     line2 = line2 + line[-i]
        # print sp.minStrokes(line2)
        print sp.minStrokes(line.replace('\n', ''))
