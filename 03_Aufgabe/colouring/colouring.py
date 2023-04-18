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
# 1. Baden-Württemberg has a border to Hessen, Bayern and Rheinland-Pfalz.
# 2. Bayern has a border to Baden-Württemberg, Hessen, Thüringen and Sachsen.
# 3. Berlin has a border to Brandenburg.
# 4. Brandenburg has a border to Berlin, Mecklenburg-Vorpommern, Niedersachsen, Sachsen-Anhalt and Sachsen.
# 5. Bremen has a border to Niedersachsen.
# 6. Hamburg has a border to Niedersachsen and Schleswig-Holstein.
# 7. Hessen has a border to Baden-Württemberg, Bayern, Rheinland-Pfalz, Nordrhein-Westfalen, Thüringen and Niedersachsen.
# 8. Mecklenburg-Vorpommern has a border to Brandenburg, Niedersachsen and Schleswig-Holstein.
# 9. Niedersachsen has a border to Bremen, Hamburg, Nordrhein-Westfalen, Mecklenburg-Vorpommern, Sachsen-Anhalt, Schleswig-Holstein, Thüringen and Hessen.
# 10. Nordrhein-Westfalen has a border to Hessen, Niedersachsen, Rheinland-Pfalz.
# 11. Rheinland-Pfalz has a border to Baden-Württemberg, Hessen, Nordrhein-Westfalen, Saarland.
# 12. Saarland has a border to Rheinland-Pfalz.
# 13. Sachsen has a border to Bayern, Thüringen, Sachsen-Anhalt, Brandenburg.
# 14. Sachsen-Anhalt has a border to Sachsen, Thüringen, Niedersachsen, Brandenburg.
# 15. Schleswig-Holstein has a border to Hamburg, Mecklenburg-Vorpommern, Niedersachsen.
# 16. Thüringen has a border to Sachsen, Sachsen-Anhalt, Niedersachsen, Hessen and Bayern.
#

from constraint import Problem, AllDifferentConstraint
