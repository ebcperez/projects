cont = True
total = 0

while cont:
    time = input()
    if time == 'end':
        break
    mins = int(time[0:2])*60
    secs = int(time[2:4])
    total += mins + secs
print(total/60)
