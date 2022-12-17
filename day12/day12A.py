file = open("input.txt")
points = []
startpoint = None
endpoint = None


class Point:
    def __init__(self, i, j, z):
        self.i = i
        self.j = j
        self.z = None
        self.is_endpoint = False
        self.explored = False
        self.valid_neighbors = []  # misnomer since being a valid neighbor is not symmetric
        self.steps = 0

        if (z == "S"):
            self.z = ord("a")
        elif (z == "E"):
            self.z = ord("z")
            self.is_endpoint = True
        else:
            self.z = ord(z)

rows = list(file.read().split("\n"))

# Initialize array of Points
for i in range(len(rows)):
    point_array_for_row = []
    for j in range(len(rows[i])):
        obj = Point(i, j, rows[i][j])
        point_array_for_row.append(obj)
        if (rows[i][j] == "S"):
            startpoint = obj
        elif (rows[i][j] == "E"):
            endpoint = obj
    points.append(point_array_for_row)


def neighbor_check(current_point, next_point) -> bool:
    if (current_point.z >= next_point.z - 1):
        return True
    else:
        return False


# Generate valid_neighbors
for row in points:
    for point in row:
        max_i = len(points) - 1
        max_j = len(row) - 1
        if (point.i + 1 <= max_i):
            if (neighbor_check(point, points[point.i+1][point.j])):
                point.valid_neighbors.append(points[point.i+1][point.j])
        if (point.i - 1 >= 0):
            if (neighbor_check(point, points[point.i-1][point.j])):
                point.valid_neighbors.append(points[point.i-1][point.j])
        if (point.j + 1 <= max_j):
            if (neighbor_check(point, points[point.i][point.j+1])):
                point.valid_neighbors.append(points[point.i][point.j+1])
        if (point.j - 1 >= 0):
            if (neighbor_check(point, points[point.i][point.j-1])):
                point.valid_neighbors.append(points[point.i][point.j-1])

# BFS
queue = []
startpoint.explored = True
startpoint.steps = 0
queue.append(startpoint)
while (queue):
    v = queue.pop(0)
    if (v.is_endpoint == True):
        continue
    for n in v.valid_neighbors:
        if (not n.explored):
            n.explored = True
            n.steps = v.steps + 1
            queue.append(n)
print(endpoint.steps)
