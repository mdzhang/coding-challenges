n = int(raw_input())

matrix = []

for i in range(n):
    row = map(lambda x: int(x), list(raw_input()))
    matrix.append(row)

for row in range(1, n - 1):
    for col in range(1, n - 1):
        cell = matrix[row][col]

        should_mark = True

        edges = [ [-1, 0], [0, 1], [1, 0], [0, -1] ]

        for x, y in edges:
            n_row = row + x
            n_col = col + y

            if matrix[n_row][n_col] >= cell:
                should_mark = False

        if should_mark:
            matrix[row][col] = 'X'

for row in range(n):
    print ''.join(map(lambda x: str(x), matrix[row]))