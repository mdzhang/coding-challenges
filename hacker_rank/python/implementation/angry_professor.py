test_cases = int(raw_input())

for i in range(test_cases):
    n, k = map(lambda x: int(x), raw_input().split(' '))
    arrival_times = map(lambda x: int(x), raw_input().split(' '))

    late_arrivals = len(filter(lambda x: x > 0, arrival_times))

    if late_arrivals > (n - k):
        print 'YES'
    else:
        print 'NO'