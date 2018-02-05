class Solution:
    
    def multiply(self, num1, num2):
        """
        :type num1: str
        :type num2: str
        :rtype: str
        """
        
        final_product = []
        for i in (len(num1)-1, 0):
            for j in (len(num2)-1, 0):
                i_digit = int(i)
                j_digit = int(j)
                product = i_digit*j_digit
                self.__add_product(product, final_product)
                
        return ''.join(final_product)
    
    def __add_product(self, product, final_product, pos):
        # product will be at most a 2-digit integer
        
        pass
    
s = Solution()
test_cases = [('1', '1')]
for num1, num2 in test_cases:
    print ('{} * {} = {}'.format(num1, num2, s.multiply(num1, num2)))
