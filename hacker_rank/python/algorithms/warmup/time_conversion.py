import re

ampm_time = raw_input()

regex = r'(\d{2}):(\d{2}):(\d{2})(AM|PM)'
m = re.match(regex, ampm_time)

hour = m.group(1)
mins = m.group(2)
secs = m.group(3)
ampm = m.group(4)

if ampm == 'PM' and hour != '12':
    hour = str(int(hour) + 12)

    if hour < 10:
        hour = '0' + hour

    if hour == '24':
        hour = '00'

if ampm == 'AM' and hour == '12':
    hour = '00'

print ':'.join([hour, mins, secs])