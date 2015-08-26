import math

low = int(raw_input())
high = int(raw_input())

kaprekar_numbers = []

def list_split(digits, longer_left):
    l = float(len(digits))

    a = int(math.floor(l / 2))
    b = int(math.ceil(l / 2))

    l = int(l)

    left = []
    right = []

    if longer_left:
        left = digits[0:b]
        right = digits[b:l]
    else:
        left = digits[0:a]
        right = digits[a:l]

    return [left, right]

def is_kaprekar(split, n):
    left = int(''.join(map(lambda x: str(x), split[0])))
    right = int(''.join(map(lambda x: str(x), split[1])))

    if left == 0 or right == 0:
        return False
    elif left + right == n:
        return True

    return False

for n in range(low, high + 1):
    square = n ** 2

    # there are faster ways to pull out digits using / 10 and %s, but meh
    digits = map(lambda x: int(x), list(str(square)))

    if len(digits) > 1:

        # odd number digit numbers can be split in 2 ways e.g. 121 => 12 + 1 or 1 + 21
        if len(digits) % 2 != 0:

            left_split = list_split(digits, True)
            right_split = list_split(digits, False)

            if is_kaprekar(right_split, n):
                kaprekar_numbers.append(n)
            elif is_kaprekar(left_split, n):
                kaprekar_numbers.append(n)
        else:
            split = list_split(digits, True)

            if is_kaprekar(split, n):
                kaprekar_numbers.append(n)

    else:
        if n == square:
            kaprekar_numbers.append(n)

if kaprekar_numbers:
    print ' '.join(map(lambda x: str(x), kaprekar_numbers))
else:
    print 'INVALID RANGE'