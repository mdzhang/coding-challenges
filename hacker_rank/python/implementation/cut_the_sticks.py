n = int(raw_input())
sticks = map(lambda x: int(x), raw_input().split(' '))

print len(sticks)

while sticks:
    smallest = min(sticks)

    sticks = filter(lambda x: x > 0, map(lambda x: x - smallest, sticks))

    if sticks:
        print len(sticks)