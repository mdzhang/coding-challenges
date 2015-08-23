n = int(raw_input())

difference = 0
i = 0

for i in range(n):
    row = map(lambda x: int(x), raw_input().split(' '))

    major = row[i]
    minor = row[n - i - 1]

    difference = difference + major - minor

    i += 1

print abs(difference)