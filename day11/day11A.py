file = open("input.txt")

blocks = list(file.read().split("\n\n"))
monkeys = []

class Monkey:
    def __init__(self, items, operation, test) -> None:
        self.items = items # List of ints
        self.operation = operation # String
        self.test = test # List of 3 ints, [mod, true, false]
        self.inspections = 0

    def __str__(self) -> str:
        return f"Items: {self.items}, Operation: {self.operation}, Test: {self.test}"

    def execute_operation(self, val):
        tokens = self.operation.split()
        if (tokens[1] == "+"):
            return val + int(tokens[2])
        elif (tokens[1] == "*"):
            if (tokens[2] == "old"):
                return val ** 2
            else:
                return val * int(tokens[2])


for block in blocks:
    lines = block.split("\n")
    items = list((map(int, lines[1].split(maxsplit=2)[2].split(","))))
    operation = lines[2].split(maxsplit=3)[3]
    test = [int(lines[3].split()[-1]), int(lines[4].split()[-1]),
            int(lines[5].split()[-1])]
    monkeys.append(Monkey(items, operation, test))

for i in range(20):
    for monkey in monkeys:
        while (monkey.items):
            monkey.inspections += 1
            monkey.items[0] = monkey.execute_operation(monkey.items[0]) // 3
            if (monkey.items[0] % monkey.test[0] == 0):
                monkeys[monkey.test[1]].items.append(monkey.items[0])
            else:
                monkeys[monkey.test[2]].items.append(monkey.items[0])
            monkey.items.pop(0)

inspections = []
for monkey in monkeys:
    inspections.append(monkey.inspections)
inspections.sort()
print(inspections[-1] * inspections[-2])


