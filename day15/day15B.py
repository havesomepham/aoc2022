import re
file = open("input.txt")

sensors = []
beacons = []  # beacon at index i is the closest to the sensor at index i
distances = []  # distances between sensor at index i and beacon at index i


lines = file.read().strip().split("\n")


def distance(pa, pb):  # takes two 2-tuples
    return abs(pa[0] - pb[0]) + abs(pa[1] - pb[1])


for line in lines:
    line = re.split("[=,:]", line)
    sx = int(line[1])
    sy = int(line[3])
    bx = int(line[5])
    by = int(line[7])
    sensors.append((sx, sy))
    beacons.append((bx, by))
    distances.append(distance((sx, sy), (bx, by)))

pos_lines = []
neg_lines = []

for i, s in enumerate(sensors):
    d = distances[i]
    pos_lines.extend([s[0] - s[1] + d, s[0] - s[1] - d])
    neg_lines.extend([s[0] + s[1] + d, s[0] + s[1] - d])

pos = None
neg = None

for i in range(2 * len(sensors)):
    for j in range(i + 1, 2 * len(sensors)):
        a, b = pos_lines[i], pos_lines[j]

        if abs(a - b) == 2:
            pos = min(a, b) + 1

        a, b = neg_lines[i], neg_lines[j]

        if abs(a - b) == 2:
            neg = min(a, b) + 1

x, y = (pos + neg) // 2, (neg - pos) // 2

print(x,y)
print(x * 4_000_000 + y)
