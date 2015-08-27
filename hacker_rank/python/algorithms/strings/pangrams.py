sentence = raw_input().lower().replace(' ', '')

sorted_ascii = set(sorted(map(lambda x: ord(x) , list(sentence))))
target_ascii = set(range(97, 123))

is_pangram = sorted_ascii & target_ascii == target_ascii

if is_pangram:
    print 'pangram'
else:
    print 'not pangram'