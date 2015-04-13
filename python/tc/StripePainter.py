import sys


class StripePainter:

    def minStrokes(self, stripes):
        if len(stripes) <= 1:
            return len(stripes)
        return self.__getMin(stripes)

    def __getMin(self, stripes):
        print "stripes = %s" % stripes
        stroke = stripes[0]
        new_stroke = -1
        c = 1
        for i in range(1, len(stripes)):
            if stroke != stripes[i] and new_stroke == -1:
                new_stroke = i
            elif stroke == stripes[i] and new_stroke != -1:
                c = c+self.__getMin(stripes[new_stroke:i])
                print "count = %s" % c
                new_stroke = -1

        if new_stroke != -1:
            c = c+self.__getMin(stripes[new_stroke:i+1])
        print "count = %s" % c
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
