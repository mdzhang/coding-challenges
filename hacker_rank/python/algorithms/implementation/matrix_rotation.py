def find_index_after_rotation(x, y):





rows, cols, rotations = map(lambda x: int(x), raw_input().split(' '))

matrix = []

for r in range(rows):
    row = map(lambda x: int(x), raw_input().split(' '))
    matrix.append(row)
