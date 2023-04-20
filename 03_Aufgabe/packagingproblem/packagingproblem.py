#!/usr/bin/python
#
# There are no tricks, just pure logic, so good luck and don't give up.
#
# This is a task called "Packaging Problem". The task is to pack a number of
# boxes into one container. The boxes have different sizes and the container
# has a fixed size. The task is to find the optimal solution, which means
# that the container is filled with all boxes and the boxes will not overlap.
#
# 1. There is one big "blue" box with a 7 * 8 grid called "container"
# 2. There are 6 different sized "green" boxes with the following sizes:
#    2.1. 1 * 4
#    2.2. 1 * 8
#    2.3. 2 * 2
#    2.4. 2 * 3
#    2.5. 2 * 5
#    2.6. 4 * 6
#
# THE QUESTION: Is it possible to pack all boxes into the container?
#
#

from constraint import Problem, AllDifferentConstraint

def solve():
    
    # define the variables
    boxes = ["1x4", "1x8", "2x2", "2x3", "2x5", "4x6"]
    
    problem = Problem()
    
    # define the domains
    problem.addVariables(boxes, range(1, 8))
    
    # define the constraints
    problem.addConstraint(AllDifferentConstraint())
    
    solution = problem.getSolution()
    return solution

def printContainer(solution):
    endline = "+---+---+---+---+---+---+---+"
    
    print(endline)
    for i in range(1, 9):
        for box in solution:
            if solution[box] == i:
                print("| " + box + " ", end="")
    
def showSolution(solution):
    print("Solution:")
    
    #printContainer(solution)
    for box in solution:
        print(box + ": " + str(solution[box]))
        
def main():
    
    try:
        solution = solve()
        showSolution(solution)
    except Exception as e:
        print("ERROR: Not solvable solution found!")
    
    
if __name__ == "__main__":
    main()