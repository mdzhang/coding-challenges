n = int(raw_input())

nums = map(lambda x: int(x), raw_input().split(' '))

positives = len(filter(lambda x: x > 0, nums))
negatives = len(filter(lambda x: x < 0, nums))
zeros = len(filter(lambda x: x == 0, nums))

n = float(n)

print "%.3f" % round(positives / n, 3)
print "%.3f" % round(negatives / n, 3)
print "%.3f" % round(zeros / n, 3)