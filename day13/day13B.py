file = open("input.txt")

pairs = file.read().strip().split("\n\n")


# 1 if right order, -1 if not, and 0 if inconclusive
def compare(a, b):
    if isinstance(a, list) and isinstance(b, int):
        b = [b]

    if isinstance(a, int) and isinstance(b, list):
        a = [a]

    if isinstance(a, int) and isinstance(b, int):
        if a > b:
            return -1
        elif a < b:
            return 1
        else:
            return 0

    if isinstance(a, list) and isinstance(b, list):
        i = 0
        while i < len(a) and i < len(b):
            result = compare(a[i], b[i])
            if result != 0:
                return result
            i += 1

        if i == len(a) == len(b):
            return 0
        elif i == len(a):
            return 1
        else:
            return -1


blocks = [[[2]], [[6]]]
for pair in pairs:
    a, b = map(eval, pair.split("\n"))
    blocks.append(a)
    blocks.append(b)

# Bubble Sort
n = len(blocks)
for i in range(n):
    for j in range(n - i - 1):
        if compare(blocks[j], blocks[j+1]) == -1:
            blocks[j], blocks[j+1] = blocks[j+1], blocks[j]

div1 = 0
div2 = 0
for i in range(len(blocks)):
    if (blocks[i] == eval("[[2]]")):
        div1 = i + 1
    elif (blocks[i] == eval("[[6]]")):
        div2 = i + 1

print(div1 * div2)
