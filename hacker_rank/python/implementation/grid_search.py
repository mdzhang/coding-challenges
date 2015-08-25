test_cases = int(raw_input())

def check_for_pattern_at_coords(grid, pattern, gx, gy):
    for px in range(len(pattern)):
        for py in range(len(pattern[0])):

            if grid[gx + px][gy + py] != pattern[px][py]:
                return False

    return True

def search_for_pattern(grid, pattern):

    for gx in range(len(grid) - len(pattern) + 1):
        for gy in range(len(grid[0]) - len(pattern[0]) + 1):

            if grid[gx][gy] == pattern[0][0]:
                if check_for_pattern_at_coords(grid, pattern, gx, gy):
                    return True

    return False

for test_case in range(test_cases):
    grid = []
    pattern = []

    g_rows, g_cols = map(lambda x: int(x), raw_input().split(' '))

    for r in range(g_rows):
        row = map(lambda x: int(x), list(raw_input()))
        grid.append(row)

    p_rows, p_cols = map(lambda x: int(x), raw_input().split(' '))

    for r in range(p_rows):
        row = map(lambda x: int(x), list(raw_input()))
        pattern.append(row)

    has_pattern = search_for_pattern(grid, pattern)

    if has_pattern:
        print 'YES'
    else:
        print 'NO'
