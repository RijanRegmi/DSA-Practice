# Write a function to delete an integer at a specific position in the array.

def delete_at_position(arr, position):
    if position < 0 or position >= len(arr):
        print("Error")
    else:
        removed_element = arr.pop(position)
        print(f"Element {removed_element} deleted from position {position}.")


arr = [10, 20, 30, 40, 50]
delete_at_position(arr, 2)
print(arr)

