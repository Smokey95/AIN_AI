#!/usr/bin/python
#
# There are no tricks, just pure logic, so good luck and don't give up.
#
# 1. There are four rooms, four people and four subjects.
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

    subject = ["german", "english", "physics", "maths"]
    teacher = ["maier", "mueller", "huber", "schmid"]

    problem = Problem()
    problem.addVariables(
        subject, range(1, 5))
    problem.addVariables(
        teacher, range(1, 5))

    problem.addConstraint(
        AllDifferentConstraint(), subject)
    problem.addConstraint(
        AllDifferentConstraint(), teacher)

    # Hint 1: Mr. Maier never teaches in room 4.
    problem.addConstraint(
        lambda maier: maier != 4, ["maier"])

    # Hint 2: Mr. Müller teaches German.
    problem.addConstraint(
        lambda mueller, german: mueller == german, ["mueller", "german"])

    # Hint 3: Mr. Schmid and Mr. Müller dont teach in neighbouring rooms.
    problem.addConstraint(
        lambda mueller, schmid: abs(mueller - schmid) != 1, ["mueller", "schmid"])

    # Hint 4: Mrs. Huber teaches Math.
    problem.addConstraint(
        lambda huber, maths: huber == maths, ["huber", "maths"])

    # Hint 5: Physics always gets thaught in room 4.
    problem.addConstraint(
        lambda physics: physics == 4, ["physics"])

    # Hint 6: German and English dont get thaught in room 1.
    problem.addConstraint(
        lambda german, english: german != 1 and english != 1, ["german", "english"])

    solution = problem.getSolution()
    return solution


def showSolution(solution):
    print("Solution:")
    for i in range(1, 5):
        if solution["maier"] == i:
            if solution["german"] == i:
                print(
                    "Teacher Maier teaches german in room %s" % (i))
            if solution["english"] == i:
                print(
                    "Teacher Maier teaches english in room %s" % (i))
            if solution["maths"] == i:
                print(
                    "Teacher Maier teaches maths in room %s" % (i))
            if solution["physics"] == i:
                print(
                    "Teacher Maier teaches physics in room %s" % (i))
        if solution["mueller"] == i:
            if solution["german"] == i:
                print(
                    "Teacher Mueller teaches german in room %s" % (i))
            if solution["english"] == i:
                print(
                    "Teacher Mueller teaches english in room %s" % (i))
            if solution["maths"] == i:
                print(
                    "Teacher Mueller teaches maths in room %s" % (i))
            if solution["physics"] == i:
                print(
                    "Teacher Mueller teaches physics in room %s" % (i))
        if solution["huber"] == i:
            if solution["german"] == i:
                print(
                    "Teacher Huber teaches german in room %s" % (i))
            if solution["english"] == i:
                print(
                    "Teacher Huber teaches english in room %s" % (i))
            if solution["maths"] == i:
                print(
                    "Teacher Huber teaches maths in room %s" % (i))
            if solution["physics"] == i:
                print(
                    "Teacher Huber teaches physics in room %s" % (i))
        if solution["schmid"] == i:
            if solution["german"] == i:
                print(
                    "Teacher Schmid teaches german in room %s" % (i))
            if solution["english"] == i:
                print(
                    "Teacher Schmid teaches english in room %s" % (i))
            if solution["maths"] == i:
                print(
                    "Teacher Schmid teaches maths in room %s" % (i))
            if solution["physics"] == i:
                print(
                    "Teacher Schmid teaches physics in room %s" % (i))


def main():
    showSolution(solve())


if __name__ == "__main__":
    main()
