n, t = map(lambda x: int(x), raw_input().split(' '))
lane = map(lambda x: int(x), raw_input().split(' '))

for i in range(t):
    entry, exit = map(lambda x: int(x), raw_input().split(' '))

    segment = lane[entry: exit + 1]
    print min(segment)