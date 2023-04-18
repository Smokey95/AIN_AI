# Aufgabe 3

## Arbeiten mit der python constraint library

Zur Lösung der Aufgabe wird die python constraint library benötigt. Diese kann mit dem Befehl `pip install python-constraint` installiert werden.

### Erstellen eines CSP

Ein CSP kann einfach als `Problem` erstellt werden, nach der definition der Constraint (siehe unten) kann mittels `Problem.getSolution()` eine Lösung gefunden werden.

    def solve() {
        problem = Problem()
        ...
        return problem.getSolution()
    }
    
**Ein CSP ist in der Regel festgelegt durch:**
  - eine Menge von Variablen
  - eine Menge von Domänen/ Wertebereiche (Werte, die die Variablen annehmen können)
  - eine Menge von Constraints (Bedingungen, die die Variablen erfüllen müssen)

<br>

### Erstellen von Variablen

Ein CSP besteht aus einer Menge von Variablen. Diese können mit dem Befehl `problem.addVariable()` hinzugefügt werden. Die Variablen werden dabei mit einem Namen versehen, der später zur Identifikation verwendet werden kann. Die Variablen können auch mit einem Wertebereich versehen werden, der die möglichen Werte der Variablen beschreibt. Dieser Wertebereich kann als Liste von Werten angegeben werden. Die Variablen können auch ohne Wertebereich angelegt werden, in diesem Fall muss der Wertebereich später mit dem Befehl `problem.addDomains()` hinzugefügt werden.

    problem.addVariable("var1", [1, 2, 3])
    
    problem.addVariable("var2")
    problem.addDomains({"var2": [1, 2, 3]})
    
**var1** und **var2** sind nun Variablen, die im CSP enthalten sind. **var1** und **var2** haben einen Wertebereich von 1, 2 und 3

Ein weniger abstraktes Beispiel wären Lehrer welche in verschiedenen Klassenzimmern unterrichten. Die Variablen wären hier die Lehrer, die Domänen die Klassenzimmer.

    lehrer = ["Herr Müller", "Frau Schmidt", "Herr Meier"]
    fächer = ["Mathe", "Deutsch", "Englisch"]
    problem.addVariable(lehrer, for i in range(1, 4))
    problem.addVariable(fächer, for i in range(1, 4))
    
Den Lehrern und den Klassenzimmern werden hier jeweils die Räume (Wertebereich) 1 bis 3 zugeordnet.