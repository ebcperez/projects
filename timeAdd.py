cont = True
total = 0
print('Enter times')
while cont:
    time = input()
    if time == 'end':
        cont = False
    mins = int(time[0:2])*60
    secs = int(time[2:4])
    total += mins + secs
print(total//60, 'and', total%60,'seconds')
