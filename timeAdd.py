
def timeAdd():
    total = 0
    print('Enter times(type "end" to end operation):')
    while True:
        time = input()
        if time == 'end':
            break
        mins = int(time[0:2])*60
        secs = int(time[2:4])
        total += mins + secs
    totalMin = total//60
    totalSecs = total%60
    return  str(totalMin) + ' mins and ' + str(totalSecs) + ' seconds '

print(timeAdd())
