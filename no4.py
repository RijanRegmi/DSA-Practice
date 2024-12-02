# Write a function to search for a specific integer in the array and return its index if found.

def search_in_array(arr, element):
    if element in arr:
        index = arr.index(element)
        print(f"Element {element} found at index {index}.")
        return index
    else:
        print(f"Element {element} not found in the array.")
        return -1

arr = [10, 20, 30, 40, 50]
search_in_array(arr, 30)
search_in_array(arr, 60)
