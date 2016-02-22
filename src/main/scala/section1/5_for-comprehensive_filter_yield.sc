/****
 * FOR COMPREHENSION
 *****/
// Definieren wir uns zunächst einmal eine kleine Int-Liste, die wir für die Beispiele nehmen werden.
val list = List(2, 4, 5, 8, 9)


/** Simple Iteration */
// Die for comprehension ist weit mehr als eine for Schleife in imperativen Sprachen.
// In Scala ist sie vielseitig einsetzbar und wird auch gerne als das schweizer Taschenmesser in Scala
// beschrieben.

// Wollen wir die obere Liste lediglich iterieren und jedes Element ausgeben, so können wir die Schleife
// auf diese Weise benutzen:
//
//               Liste
//               |
//          Zuweisungoperator (In dem Fall ein ntiver syntaktischer Operator, keine Methode der Objekte)
//          |    |
//   Iterator    |
//   |      |    |
for(element <- list) {
  println(element) // 2, 4, 5, 8, 9
}


// Die For comprehension kennt keine klassische Iteration über einen extra definierten Zähler. Lediglich foreach
// Ausdrücke sind möglich. Um dennoch eine 'Laufvariable' zu benutzen, existiert die Range Struktur, welche
// einen from-to - Ausdruck ermöglich. In dem Beispiel eine Range von 0 bis 10 in 2er Schritten.
// Neben Range gibt es noch eine Vielzahl weitere iterierbarer Strukturen.

for(element <- Range(0, 10, 2)) {
  println(element) // 0, 2, 4, 6, 8, 10
}


/** Filtering */
// Nun kommen wir zu einigen schönen Features, welche die for comprehension von normalen for Schleifen unterscheidet.

// Mit Filterausdrücken kann die Schleife aus einer iterierten Liste direkt Element aussortieren.
// Dafür schreibt man ein if, gefolgt einem boolschen Ausdruck direkt hinter den Iteratorausdruck.
// In diesem Beispiel iterieren wir wieder über die oben definierte Liste, filtern aber alle Elemente heraus,
// die nicht ganzzahlig durch 2 teilbar sind:
for(element <- list if element % 2 == 0) {
  println(element) // 2, 4, 8
}

// Filter können auch kombiniert werden. Hierfür steigen wir auf eine andere Schreibform um, wobei die einzelnen
// Ausdrücke einfach untereinander geschrieben werden. Man beachte hier die genutzten curly braces.
// In diesem Beispiel geben wir nur alle Element aus, die ganzzahlig durch 2 teilbar sind und größer als 2 sind:
for {
  element <- list
  if element % 2 == 0
  if element > 2
} {
  println(element) // 4, 8
}


/** Nested iterations */
// We can also nest iterations in one statement.
// The following statement loops over the list and for each list element over the split(' ') result array.
val names = List("Max Mustermann", "Hans Dampf")

for {
  name <- names
  word <- name.split(" ")
} {
  println(word) // "Max", "Mustermann", "Hans", "Dampf"
}


/** Nested iterations und Filter kombiniert. */
// We can also adding a filter statement for each iterator.
// Hint: If using the for with ( - braces, you have to add a semicolon after each statement.
//       Using for with curly braces, the statements are interpreted as code block.
for {
  name <- names
  if name.startsWith("H")   // Nur noch "Hans Dampf"
  word <- name.split(" ")
  if word.length > 4        // Nur noch "Dampf"
} {
  println(word) // "Dampf"
}


/** Yielding */
// For each nested iteration, the yield block is executed. The result in this example is a list of the word length.
val wordLength: List[Int] =
  for {
    name <- names
    word <- name.split(" ")
  } yield {
    word.length
  }
