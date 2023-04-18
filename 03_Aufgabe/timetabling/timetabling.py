#!/usr/bin/python
#
# There are no tricks, just pure logic, so good luck and don't give up.
#
# 1. In a there are four rooms, four people and four subjects.
# 2. In each room only one person teaches exactly one subject
#
# THE QUESTION: Who teaches where and what?
#
# HINTS
#
# 1. Mr. Maier never teaches in room 4.
# 2. Mr. Müller teaches German.
# 3. Mr. Schmid and Mr. Müller dont teach in neighbouring rooms.
# 4. Mrs. Huber teaches Math.
# 5. Physics always gets thaught in room 4.
# 6. German and English dont get thaught in room 1.
#

# how do you define a constraint?
# does it has to be in a normalform?

from constraint import Problem, AllDifferentConstraint


def solve():
    problem = Problem()
    for i in range(1, 5):
        problem.addVariable("subject%d" %
                            i, ["german", "english", "physics", "maths"])
        problem.addVariable("room%d" %
                            i, ["one", "two", "three", "four"])
        problem.addVariable("teacher%d" %
                            i, ["maier", "mueller", "huber", "schmid"])

    problem.addConstraint(
        AllDifferentConstraint(), ["subject%d" % i for i in range(1, 5)])
    problem.addConstraint(
        AllDifferentConstraint(), ["teacher%d" % i for i in range(1, 5)])

    # Hint 1: Mr. Maier never teaches in room 4.
    # problem.addConstraint(
    #     lambda teacher, room: teacher != "maier" or room != "four"
    # )

    # Hint 2: Mr. Müller teaches German.
    # problem.addConstraint(
    #     lambda teacher, subject: teacher == "mueller" and subject == "german")

    # Hint 3: Mr. Schmid and Mr. Müller dont teach in neighbouring rooms.
    # problem.addConstraint(lambda teachera, teacherb, rooma, roomb: teachera != "mueller" or teacherb != "schmid" or rooma !=
    #                      roomb-1 or rooma != roomb+1, ("teacher%d" % 2, "teacher%d" % 4, "room%d" % 2, "room%d" % 4))

    # Hint 4: Mrs. Huber teaches Math.
    # problem.addConstraint(
    #     lambda teacher, subject: teacher == "huber" and subject == "maths")

    # # Hint 5: Physics always gets thaught in room 4.
    # problem.addConstraint(
    #     lambda subject, room: subject == "physics" and room == "four")

    # # Hint 6: German and English dont get thaught in room 1.
    # problem.addConstraint(
    #     lambda subject, room: subject != "german" or subject != "english" and room != "one")

    solution = problem.getSolution()
    return solution


def showSolution(solution):
    print("Solution:")
    for i in range(1, 5):
        print(
            "Teacher %s teaches %s in room %s"
            % (solution["teacher%d" % i], solution["subject%d" % i], solution["room%d" % i])
        )


def main():
    showSolution(solve())


if __name__ == "__main__":
    main()
