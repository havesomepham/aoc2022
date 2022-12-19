import re
file = open("input.txt")

sensors = []
beacons = []  # beacon at index i is the closest to the sensor at index i
filled = set()
sb_set = set() # set of all beacons and sensors
checked_row = 2_000_000

lines = file.read().strip().split("\n")

for line in lines:
    line = re.split("[=,:]", line)
    sx = int(line[1])
    sy = int(line[3])
    bx = int(line[5])
    by = int(line[7])
    sensors.append((sx, sy))
    beacons.append((bx, by))

for p in sensors:
    sb_set.add(p)
for p in beacons:
    sb_set.add(p)


def distance(pa, pb):  # takes two 2-tuples
    return abs(pa[0] - pb[0]) + abs(pa[1] - pb[1])


def check(p):
    if p[1] == checked_row:
        if tuple(p) not in filled and tuple(p) not in sb_set:
            filled.add(tuple(p))


for i in range(len(sensors)):
    s = sensors[i]
    b = beacons[i]
    dist = distance(s, b)
    curr = [s[0], checked_row]
    check(curr)
    while distance(curr, s) < dist: # go right
        curr[0] += 1
        check(curr)
    curr = [s[0], checked_row]
    while distance(curr, s) < dist: # go left
        curr[0] -= 1
        check(curr)

print(len(filled))