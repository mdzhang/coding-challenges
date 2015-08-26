number_to_string_list = [
    'zero',
    'one',
    'two',
    'three',
    'four',
    'five',
    'six',
    'seven',
    'eight',
    'nine',
    'ten',
    'eleven',
    'twelve',
    'thirteen',
    'fourteen',
    'fifteen',
    'sixteen',
    'seventeen',
    'eighteen',
    'nineteen'
]

tens_to_string_map = {
    20: 'twenty',
    30: 'thirty',
    40: 'forty',
    50: 'fifty'
}

def number_to_string(mins):
    if mins < 20:
        return number_to_string_list[mins]
    else:
        tens = (mins / 10) * 10
        ones = mins - tens

        if ones != 0:
            return tens_to_string_map[tens] + ' ' + number_to_string_list[ones]
        else:
            return tens_to_string_map[tens]

def time_to_string(hour, mins):

    if mins > 30:
        hour = (hour + 1) % 12
        if hour == 0:
            hour = 12

    hour = number_to_string_list[hour]

    if mins == 0:
        return hour + " o'clock"
    if mins == 1:
        return 'one minute past ' + hour
    elif mins == 15:
        return 'quarter past ' + hour
    elif mins == 30:
        return 'half past ' + hour
    elif mins == 45:
        return 'quarter to ' + hour
    elif mins < 30:
        return number_to_string(mins) + ' minutes past ' + hour
    elif mins == 59:
        return 'one minute to ' + hour
    elif mins > 30:
        mins = 60 - mins
        return number_to_string(mins) + ' minutes to ' + hour


hour = int(raw_input())
mins = int(raw_input())

print time_to_string(hour, mins)
