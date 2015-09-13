# https://hr-testcases-us-east-1.s3.amazonaws.com/8965/output08.txt?AWSAccessKeyId=AKIAJAMR4KJHHUS76CYQ&Expires=1442199532&Signature=uUCmvMCgeg%2FtiW0F9vI%2BXovfCnQ%3D&response-content-type=text%2Fplain
# https://hr-testcases-us-east-1.s3.amazonaws.com/8965/input08.txt?AWSAccessKeyId=AKIAJAMR4KJHHUS76CYQ&Expires=1442199528&Signature=6P%2FUrovgkLpPXUiaqXlfGlm%2F7HQ%3D&response-content-type=text%2Fplain
def read_matrix(rows, cols):
    matrix = []

    for i in range(rows):
        row = map(lambda x: int(x), raw_input().split(' '))
        matrix.append(row)

    return matrix

def make_matrix(rows, cols):
    matrix = []

    for i in range(rows):
        row = [0] * cols
        matrix.append(row)

    return matrix

# iterate over the x, y coordinates of a square ring
# in a clockwise fashion starting from the top left
def iter_ring_coords(x_min, x_max, y_min, y_max):
    # top row, left to right
    x = x_min
    y = y_min

    for y in range(y_min, y_max + 1):
        yield x, y

    # rightmost column, top to bottom
    y = y_max

    for x in range(x_min + 1, x_max + 1):
        yield x, y

    # bottom row, right to left
    x = x_max

    for y in range(y_max - 1, y_min - 1, -1):
        yield x, y

    # leftmost column, bottom to top
    y = y_min

    for x in range(x_max - 1, x_min, -1):
        yield x, y

def find_coords_after_rotations(coords, x_bounds, y_bounds, rotations):
    rows = x_bounds[1] - x_bounds[0] + 1
    cols = y_bounds[1] - y_bounds[0] + 1

    # how many rotations bring us back to the same cell?
    full_rotation = rows * 2 + cols * 2 - 4
    rotations = rotations % full_rotation

    for i in range(rotations):
        coords = find_coords_after_rotation(coords, x_bounds, y_bounds)

    return coords

def find_coords_after_rotation(coords, x_bounds, y_bounds):
    x, y = coords
    x_min, x_max = x_bounds
    y_min, y_max = y_bounds

    # rightmost column
    if y == y_max:
        # go one cell up
        if x > x_min:
            return x - 1, y
        # top right corner; go one cell left
        else:
            return x, y - 1
    # top row
    elif x == x_min:
        # go one cell left
        if y > y_min:
            return x, y - 1
        # top left corner; go one cell down
        else:
            return x + 1, y
    # leftmost column
    elif y == y_min:
        # go one cell down
        if x < x_max:
            return x + 1, y
        # bottom left corner; go one cell right
        else:
            return x, y + 1
    # bottom row
    elif x == x_max:
        # go one cell right
        if y < y_max:
            return x, y + 1
        # bottom right corner; go one cell up
        else:
            return x - 1, y
    else:
        # print 'coords ({}, {})'.format(x, y)
        # print 'x_bounds ({}, {})'.format(x_min, x_max)
        # print 'y_bounds ({}, {})'.format(y_min, y_max)
        raise ValueError("Bad coords")

def rotate_matrix(matrix, rotations, rows, cols):
    rings = min(rows, cols) / 2
    new_matrix = make_matrix(rows, cols)

    x_max = rows
    y_max = cols
    x_min = -1
    y_min = -1

    for r in range(rings):
        x_max -= 1
        y_max -= 1
        x_min += 1
        y_min += 1

        x_bounds = [x_min, x_max]
        y_bounds = [y_min, y_max]

        for x, y in iter_ring_coords(x_min, x_max, y_min, y_max):
            coords = [x, y]
            x_new, y_new = find_coords_after_rotations(coords, x_bounds, y_bounds, rotations)
            new_matrix[x_new][y_new] = matrix[x][y]

    return new_matrix

def print_matrix(matrix):
    s = ''

    for i in range(len(matrix)):
        s += ' '.join(map(lambda x: str(x), matrix[i])) + '\n'

    print s.strip()

def main():
    rows, cols, rotations = map(lambda x: int(x), raw_input().split(' '))
    matrix = read_matrix(rows, cols)
    matrix = rotate_matrix(matrix, rotations, rows, cols)
    print_matrix(matrix)

main()