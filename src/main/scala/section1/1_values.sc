/************************
 * OBJECTS und REFERENCES
 ************************/
// Zunächst einmal sind alle Werte in Scala Objekte. Auch Integers, Booleans etc.
// welche in anderen Sprachen lediglich als Primitive definiert sind.
2.toString
true.hashCode()


// Da alle Wete auch Objekte sind, kennt Scala nur sehr wenig native Operatoren.
// Stattdessen sind 'normale' Additionsoperatoren wie '+' bei Integers lediglich
// Methoden eben dieser Klasse.
2.+(2)


// Da diese Form doch etwas hakelig ist, erlaubt Scala Punkte und Klammern weg zu lassen.
// Der Compiler schafft es in den meisten Fällen die Reihenfolge der Argumente aufzulösen
2 + 2
2 equals 5


// Value equality und reference equality ist in Scala genau andersrum wie in Java.
var l = List(1, 2, 3)
var ll = List(1, 2, 3)

l == ll   // Macht einen Wertevergleich
l eq ll   // Macht einen Referenzvergleich


/*****************
 * VALs und VARs
 *****************/
// Eine benannte Variable lässt sich mit 'var', dem Namen der Variable plus dem Typ
// nach einem Doppelpunkt definieren. Eine Wertinitialisierung ist dabei erforderlich.
// Variablen sind, wie der Name bereits sagt, mutable und können im Laufe andere Werte annehmen.
var y: String = "This is a var"
y = "Another value"


// Im Gegensatz zu variables, definieren values eine immutable reference, äquivalent zu final
// Variablen in Java. Best practise ist hier: So viel vals wie möglich, so viel vars wie nötig.
// Vor allem wenn man im nebenläufigen Umfeld unterwegs ist, was bei Scala schnell passieren kann.
val x: String = "hello world"


// Wie wir bereits weiter oben gesehen haben, erlaubt uns der Scala Compiler manche Ausdrücke
// zu verkürzen. Bei vals und vars können wir den Typ weg lassen. Trotzdem haben die Bezeichner
// einen Typ. Der Compiler ermittelt den Typ implizit über den Initialisierungswert.
val xx = "hello other world"