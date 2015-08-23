n = int(raw_input())

for i in range(1, n + 1):
    step = ' ' * (n - i) + '#' * i
    print step
