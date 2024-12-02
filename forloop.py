import time
time.time()


timrstamp1 = time.time()

number = 100
total = 0

for value in range(1, number + 1):
    total = total + value
    print("Sum is", total)

timrstamp2 = time.time()
print(timrstamp2 - timrstamp1)