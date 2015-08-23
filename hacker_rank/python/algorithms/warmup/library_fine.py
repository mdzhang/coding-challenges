actual = raw_input()
expected = raw_input()

fine = 0

a_day, a_mo, a_year = actual.split(' ')
e_day, e_mo, e_year = expected.split(' ')

if a_year < e_year:
    fine = 0
elif a_year > e_year:
    fine = 10000
elif a_mo < e_mo:
    fine = 0
elif a_mo > e_mo:
    mos_late = int(a_mo) - int(e_mo)
    fine = 500 * mos_late
elif a_day > e_day:
    days_late = int(a_day) - int(e_day)
    fine = 15 * days_late

print fine