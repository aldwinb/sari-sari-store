import sys


class TagalogDictionary:

    def sortWords(self, words):
        return sorted(words, key=self.__tagKey)

    def __tagKey(self, word):
        if len(word) <= 0:
            return -1
        key = ''
        for i in range(0, len(word)):
            c = word[i]
            if ord(c) >= ord('o'):
                key = key + chr(ord(c)+1)
            else:
                key = key + c
        key = key.replace('k', 'c').replace('ng', 'o')
        return key

if __name__ == '__main__':
    td = TagalogDictionary()
    for line in sys.stdin:
        line = line.replace('\n', '')
        testCase = line.split('\t')
        actual = ','.join(td.sortWords(testCase[0].split(',')))
        if actual != testCase[1]:
            print 'expected=[%s]\tactual=[%s]' % (testCase[1], actual)
