import math

input_str = list(raw_input())

sqrt = math.sqrt(len(input_str))

rows = int(math.floor(sqrt))
cols = int(math.ceil(sqrt))

if rows * cols < len(input_str):
    rows += 1

matrix = []
i = 0

for r in range(rows):
    row = []

    for c in range(cols):
        if i < len(input_str):
            row.append(input_str[i])
            i += 1

    matrix.append(row)

output = []

for c in range(cols):
    for r in range(rows):

        if len(matrix[r]) > c:
            output.append(matrix[r][c])

    output.append(' ')

print ''.join(output)

