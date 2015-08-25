n = int(raw_input())
chars = list(raw_input())
k = int(raw_input())

lower_case_ascii = range(97, 123)
upper_case_ascii = range(65, 91)

for i in range(len(chars)):
    c = chars[i]
    charset = None

    if c.istitle():
        charset = upper_case_ascii
    else:
        charset = lower_case_ascii


    try:
        o = charset.index(ord(c))
        o = (o + k) % 26
        chars[i] = chr(charset[o])
    except:
        continue


print ''.join(chars)