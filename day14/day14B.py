import time
start_time = time.time()
file = open("input.txt")

rock_paths = file.read().strip().split("\n")
rock_points = []
for i in range(len(rock_paths)):
    path = rock_paths[i].split(" -> ")
    rock_points.append(path)

max_x = 0
max_y = 0
min_x = 100_000
for path in rock_points:
    for point in path:
        x,y = point.split(",")
        if max_x < int(x):
            max_x = int(x)
        if max_y < int(y):
            max_y = int(y)
        if min_x > int(x):
            min_x = int(x)

buffer = 1000 # buffer needs to be "big enough"
max_y += 2
max_x = min_x + 2 * buffer
min_x = buffer # idk why this works
char_array = [["."] * (max_x + 1 - min_x) for _ in range(max_y + 1)]

for path in rock_points:
    for i in range(len(path) - 1):
        x1 , y1 = map(int, path[i].split(","))
        x2 , y2 = map(int, path[i+1].split(","))
        # print(f"point1: ({x1 - min_x}, {y1})")
        # print(f"point2: ({x2 - min_x}, {y2})")
        if x1 == x2:
            if y1 < y2:
                for y in range(y1, y2 + 1):
                    char_array[y][x1 - min_x] = "#"
            else:
                for y in range(y2, y1 + 1):
                    char_array[y][x1 - min_x] = "#"
        elif y1 == y2:
            if x1 < x2:
                for x in range(x1 - min_x, x2 + 1 - min_x):
                    char_array[y1][x] = "#"
            else:
                for x in range(x2 - min_x, x1 + 1 - min_x):
                    char_array[y1][x] = "#"

for x in range(0, max_x - min_x + 1):
    char_array[max_y][x] = "#"
sand_point = [500, 0]
total_sand = 0
curr_x = 500
curr_y = 0

char_array[curr_y][curr_x - min_x] = "o"
output_file = open("output.txt", "w")


while True:
    if char_array[curr_y + 1][curr_x - min_x] == ".": # down
        char_array[curr_y][curr_x - min_x] = "."
        curr_y += 1
        char_array[curr_y][curr_x - min_x] = "o"
    elif char_array[curr_y + 1][curr_x - min_x - 1] == ".":
        char_array[curr_y][curr_x - min_x] = "."
        curr_y += 1
        curr_x -= 1
        char_array[curr_y][curr_x - min_x] = "o"
    elif char_array[curr_y + 1][curr_x - min_x + 1] == ".":
        char_array[curr_y][curr_x - min_x] = "."
        curr_y += 1
        curr_x += 1
        char_array[curr_y][curr_x - min_x] = "o"
    else:
        if curr_x == 500 and curr_y == 0:
            total_sand += 1
            break
        # print(curr_x, curr_y)
        curr_x = 500
        curr_y = 0
        total_sand += 1
        output_file.seek(0)
        output_file.truncate()

print(total_sand)
for line in char_array:
            outputline = ""
            for char in line:
                outputline += char
            outputline += "\n"
            output_file.write(outputline)

end_time = time.time()
print(end_time - start_time)