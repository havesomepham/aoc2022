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


ans = 0
for i, block in enumerate(pairs):
    a, b = map(eval, block.split("\n"))
    if compare(a, b) == 1:
        ans += i + 1

print(ans)
