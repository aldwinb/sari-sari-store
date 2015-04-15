import sys


class StripePainter:

    __cost = []

    def minStrokes(self, stripes):
        if len(stripes) <= 1:
            return len(stripes)
        for i in range(len(stripes)):
            self.__cost.append(sys.maxint)
        return self.__cut(stripes, 0)
        # return self.__getMinStrokes(stripes)

    # BECBBDDEEBABDCADEAAEABCACBDBEECDEDEACACCBEDABEDADD
    # B__BB____B_B_________B___B_B____________B___B_____ = 1
    # B__BBDDEEB_B__A__AA_ABC_CB_BEE__E_E_____B___B_D_DD = 7
    # B__BBDDEEB_B__A__AA_ABC_CB_BEE__E_E_C_CCB___B_D_DD = 8
    def __cut(self, stripes, j):
        if len(stripes)-j <= 1:
            return len(stripes)-j
        if self.__cost[j] != sys.maxint:
            return self.__cost[j]
        min = sys.maxint
        for i in range(j+1, len(stripes)):
            # stroke = stripes[i:]
            # print 'cut = %s %s' % (stripes[:i], stroke)
            left = self.__getMinStrokes(stripes[j:i])
            right = self.__cut(stripes, i)
            # print 'i = %s, cut_l = %s, cnt_l = %s, cut_r = %s, cnt_r = %s' \
            #     % (i, stripes[j:i], left,  stroke, right)
            c = left+right
            if (min > c):
                min = c
        c = self.__getMinStrokes(stripes[j:])
        if (min > c):
            min = c
        # print 'cost = %s' % min
        self.__cost[j] = min
        return self.__cost[j]

    def __getMinStrokes(self, stripes):
        # print "stripes = %s" % stripes
        stroke = stripes[0]
        new_stroke = -1
        c = 1
        for i in range(1, len(stripes)):
            if stroke != stripes[i] and new_stroke == -1:
                new_stroke = i
            elif stroke == stripes[i] and new_stroke != -1:
                c = c+self.__getMinStrokes(stripes[new_stroke:i])
                # print "count = %s" % c
                new_stroke = -1

        if new_stroke != -1:
            c = c+self.__getMinStrokes(stripes[new_stroke:i+1])
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
