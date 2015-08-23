num = raw_input()

def factorial(n):
    if n <= 2:
        return n
    else:
        return factorial_r(n, n - 1)

def factorial_r(m, n):
    if n <= 2:
        return m * n
    else:
        return factorial_r(m * n, n - 1)

print factorial(int(num))