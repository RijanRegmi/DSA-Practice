# Write a function to insert a new integer at a specific position in the array

def insert_at_position(arr, element, position):
    if position < 0 or position > len(arr):
        print("Error")
    else:
        arr.insert(position, element)
        print(f"{element} inserted at position {position}.")

arr = [10, 20, 30, 40, 50]
insert_at_position(arr, 25, 2)
print(arr)
