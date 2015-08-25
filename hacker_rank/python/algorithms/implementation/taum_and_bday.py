test_cases = int(raw_input())

for test_case in range(test_cases):

    black_count, white_count = map(lambda x: int(x), raw_input().split(' '))
    black_cost, white_cost, convert_cost = map(lambda x: int(x), raw_input().split(' '))

    min_cost = 0
    cost_per_white = -1
    cost_per_black = -1

    # whites
    if black_cost + convert_cost < white_cost:
        cost_per_white = black_cost + convert_cost
    else:
        cost_per_white = white_cost

    # blacks
    if white_cost + convert_cost < black_cost:
        cost_per_black = white_cost + convert_cost
    else:
        cost_per_black = black_cost

    min_cost = cost_per_white * white_count + cost_per_black * black_count

    print min_cost