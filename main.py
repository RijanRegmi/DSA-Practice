student_list1 = ['tim', 'drake', 'ashish', 'shubham']

student_list2 = ['andrew', 'chris', 'harshit', 'lary','shubham',"Rijan","Helan","a","b","c"]

def checkStudent(student_list2):
    for student in student_list2:
        if student == "chris":
            print('Available')
        else:
            print("Not Available")
        
        
checkStudent(student_list2)




def displayStudent(student_list2):
    print(student_list2[5])

displayStudent(student_list2)



student_list2 = ['andrew', 'chris', 'harshit', 'lary','shubham',"Rijan","Helan","a","b","c"] #O(1)

def randomFunction(student_list2):
    first = student_list2[0]#O(1)
    total = 0#O(1)
    new_list = []#O(1)

    for student in student_list2:
        total += 1#O(n)
        new_list.append(student) #O(n)

    print(new_list)#O(1)
    return total#O(1)

print(randomFunction(student_list2)) #O(6 + 2n) => #O(n)




# Rulr 5 - remove all non-dominants

num_list = [1,2,3,4,5,6,7]

def randomFunction(num_list):
    total = 0 #O(1)
    all_integer  =True #O(1)

    for num in num_list:
        print(num) #O(n)

    for num1 in num_list:
        for num2 in num_list:
            print(num1,num2) #O(n^2)
            total += 1 #O(n^2)
        
    msg = "Rulr 5 - remove all non-dominants" #O(1)
    return total #O(1)

print(randomFunction(num_list)) #O(4 + n + 2n^2)





