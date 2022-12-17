file = open("input.txt")
points = []
startpoints = []
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

    def __str__(self) -> str:
        return f"point ({self.i}, {self.j}, {self.z})"


rows = list(file.read().split("\n"))

# Initialize array of Points
for i in range(len(rows)):
    point_array_for_row = []
    for j in range(len(rows[i])):
        obj = Point(i, j, rows[i][j])
        point_array_for_row.append(obj)
        if (rows[i][j] == "E"):
            endpoint = obj
    points.append(point_array_for_row)

# Initialize startingpoints
for row in points:
    for point in row:
        if (point.z == ord("a")):
            startpoints.append(point)


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


def bfs(startpoint) -> int:
    queue = []
    startpoint.explored = True
    queue.append(startpoint)
    tests = 0
    while (queue):
        v = queue.pop(0)
        tests += 1
        if (v.is_endpoint == True):
            continue
        for n in v.valid_neighbors:
            if (not n.explored):
                n.explored = True
                n.steps = v.steps + 1
                queue.append(n)
    return endpoint.steps


bfs_values = []
for point in startpoints:
    bfs_values.append(bfs(point))
    # cleanup
    for row in points:
        for point in row:
            point.steps = 0
            point.explored = False

bfs_values.sort()
for val in bfs_values:
    if (val == 0):  # if the endpoint is unreachable, bfs() will return 0
        continue
    print(val)
    break
