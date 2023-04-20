#!/usr/bin/python
#
# There are no tricks, just pure logic, so good luck and don't give up.
#
# 1. There are 16 states, 4 colours(maybe 3)
# 2. Each state has to be coloured with one of the four colours
# 3. No two neighbouring states cant have the same colour
#
# THE QUESTION: Which colour gets assigned to which state?
#
# HINTS
#
# 1.  Baden-Württemberg has a border to Hessen, Bayern and Rheinland-Pfalz.
# 2.  Bayern has a border to Baden-Württemberg, Hessen, Thüringen and Sachsen.
# 3.  Berlin has a border to Brandenburg.
# 4.  Brandenburg has a border to Berlin, Mecklenburg-Vorpommern, Niedersachsen, Sachsen-Anhalt and Sachsen.
# 5.  Bremen has a border to Niedersachsen.
# 6.  Hamburg has a border to Niedersachsen and Schleswig-Holstein.
# 7.  Hessen has a border to Baden-Württemberg, Bayern, Rheinland-Pfalz, Nordrhein-Westfalen, Thüringen and Niedersachsen.
# 8.  Mecklenburg-Vorpommern has a border to Brandenburg, Niedersachsen and Schleswig-Holstein.
# 9.  Niedersachsen has a border to Bremen, Hamburg, Nordrhein-Westfalen, Mecklenburg-Vorpommern, Sachsen-Anhalt, Schleswig-Holstein, Thüringen and Hessen.
# 10. Nordrhein-Westfalen has a border to Hessen, Niedersachsen, Rheinland-Pfalz.
# 11. Rheinland-Pfalz has a border to Baden-Württemberg, Hessen, Nordrhein-Westfalen, Saarland.
# 12. Saarland has a border to Rheinland-Pfalz.
# 13. Sachsen has a border to Bayern, Thüringen, Sachsen-Anhalt, Brandenburg.
# 14. Sachsen-Anhalt has a border to Sachsen, Thüringen, Niedersachsen, Brandenburg.
# 15. Schleswig-Holstein has a border to Hamburg, Mecklenburg-Vorpommern, Niedersachsen.
# 16. Thüringen has a border to Sachsen, Sachsen-Anhalt, Niedersachsen, Hessen and Bayern.
#

from constraint import Problem, AllDifferentConstraint
import geopandas as gpd
import matplotlib.pyplot as plt

def solve(limit):
    
    # define the variables
    states = ["Baden-Württemberg", 
              "Bayern", 
              "Berlin", 
              "Brandenburg", 
              "Bremen", 
              "Hamburg", 
              "Hessen", 
              "Mecklenburg-Vorpommern", 
              "Niedersachsen", 
              "Nordrhein-Westfalen", 
              "Rheinland-Pfalz", 
              "Saarland", 
              "Sachsen", 
              "Sachsen-Anhalt", 
              "Schleswig-Holstein", 
              "Thüringen",
              "Mallorca"]
    
    problem = Problem()
    
    # define the domains
    problem.addVariables(states, range(1, limit + 1))
    
    # define the constraints
    
    # Hint 1
    problem.addConstraint(
        lambda state_bw, state_h, state_b, state_rp: state_bw != state_h and state_bw != state_b and state_bw != state_rp, 
        ("Baden-Württemberg", "Hessen", "Bayern", "Rheinland-Pfalz"))
    
    # Hint 2
    problem.addConstraint(
        lambda state_b, state_bw, state_h, state_th, state_s: state_b != state_bw and state_b != state_h and state_b != state_th and state_b != state_s, 
        ("Bayern", "Baden-Württemberg", "Hessen", "Thüringen", "Sachsen"))
    
    # Hint 3
    problem.addConstraint(
        lambda state_b, state_br: state_b != state_br,
        ("Berlin", "Brandenburg"))
    
    # Hint 4
    problem.addConstraint(
        lambda state_b, state_br, state_m, state_n, state_sa, state_s: state_b != state_br and state_b != state_m and state_b != state_n and state_b != state_sa and state_b != state_s,
        ("Brandenburg", "Berlin", "Mecklenburg-Vorpommern", "Niedersachsen", "Sachsen-Anhalt", "Sachsen"))
    
    # Hint 5
    problem.addConstraint(
        lambda state_b, state_n: state_b != state_n,
        ("Bremen", "Niedersachsen"))
    
    # Hint 6
    problem.addConstraint(
        lambda state_h, state_n, state_sh: state_h != state_n and state_h != state_sh,
        ("Hamburg", "Niedersachsen", "Schleswig-Holstein"))
    
    # Hint 7
    problem.addConstraint(
        lambda state_h, state_bw, state_b, state_rp, state_nr, state_th, state_n: state_h != state_bw and state_h != state_b and state_h != state_rp and state_h != state_nr and state_h != state_th and state_h != state_n,
        ("Hessen", "Baden-Württemberg", "Bayern", "Rheinland-Pfalz", "Nordrhein-Westfalen", "Thüringen", "Niedersachsen"))
    
    # Hint 8
    problem.addConstraint(
        lambda state_m, state_br, state_n, state_sh: state_m != state_br and state_m != state_n and state_m != state_sh,
        ("Mecklenburg-Vorpommern", "Brandenburg", "Niedersachsen", "Schleswig-Holstein"))
    
    # Hint 9
    problem.addConstraint(
        lambda state_n, state_b, state_h, state_nr, state_m, state_sa, state_sh, state_th, state_hs: state_n != state_b and state_n != state_h and state_n != state_nr and state_n != state_m and state_n != state_sa and state_n != state_sh and state_n != state_th and state_n != state_hs,
        ("Niedersachsen", "Bremen", "Hamburg", "Nordrhein-Westfalen", "Mecklenburg-Vorpommern", "Sachsen-Anhalt", "Schleswig-Holstein", "Thüringen", "Hessen"))
    
    # Hint 10
    problem.addConstraint(
        lambda state_nr, state_h, state_n, state_rp: state_nr != state_h and state_nr != state_n and state_nr != state_rp,
        ("Nordrhein-Westfalen", "Hessen", "Niedersachsen", "Rheinland-Pfalz"))
    
    # Hint 11
    problem.addConstraint(
        lambda state_rp, state_bw, state_h, state_nr, state_sa: state_rp != state_bw and state_rp != state_h and state_rp != state_nr and state_rp != state_sa,
        ("Rheinland-Pfalz", "Baden-Württemberg", "Hessen", "Nordrhein-Westfalen", "Saarland"))
    
    # Hint 12
    problem.addConstraint(
        lambda state_sa, state_rp: state_sa != state_rp,
        ("Saarland", "Rheinland-Pfalz"))
    
    # Hint 13
    problem.addConstraint(
        lambda state_s, state_b, state_th, state_sa, state_br: state_s != state_b and state_s != state_th and state_s != state_sa and state_s != state_br,
        ("Sachsen", "Bayern", "Thüringen", "Saarland", "Brandenburg"))
    
    # Hint 14
    problem.addConstraint(
        lambda state_sa, state_n, state_th, state_s: state_sa != state_n and state_sa != state_th and state_sa != state_s,
        ("Sachsen-Anhalt", "Niedersachsen", "Thüringen", "Sachsen"))
    
    # Hint 15
    problem.addConstraint(
        lambda state_sh, state_h, state_n, state_m: state_sh != state_h and state_sh != state_n and state_sh != state_m,
        ("Schleswig-Holstein", "Hamburg", "Niedersachsen", "Mecklenburg-Vorpommern"))
    
    # Hint 16
    problem.addConstraint(
        lambda state_th, state_b, state_h, state_n, state_s, state_sa: state_th != state_b and state_th != state_h and state_th != state_n and state_th != state_s and state_th != state_sa,
        ("Thüringen", "Bayern", "Hessen", "Niedersachsen", "Sachsen", "Sachsen-Anhalt"))
    
    # Hint 17
    problem.addConstraint(
        lambda state_malle, state_b: state_malle != state_b,
        ("Mallorca", "Berlin"))
    
    solution = problem.getSolution()
    return solution
    
def showSolution(solution):
    print("Solution:")
    
    german_map = gpd.read_file("data/DEU_adm1.shp")
    
    for state in solution:
        print(state + ": " + str(solution[state]))
        german_map.loc[german_map.NAME_1 == state, 'color'] = solution[state]
    
    german_map.plot(column='color', cmap='plasma')
    plt.show()
    
def main():
    
    print('Enter color count:')
    range = int(input())
    
    try:
        solution = solve(range)
        showSolution(solution)
    except Exception as e:
        print("ERROR: Not solvable with only " + str(range) + " colors.")
    
    
if __name__ == "__main__":
    main()