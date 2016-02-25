import scala.collection.mutable

/**************
 * Arrays
 *************/
// Arrays funktionieren vom Prinzip her genauso wie wir sie aus Java kennen, nur dass sie in Scala
// ein eigenständiges Objekt sind.
// Zunächst erzeugen wir ein neues Arrayobjekt eines Typs und einer festen Länge.

val array = new Array[Int](10)

// Zugriff erlangen wir anders wie in Java nicht mit eckigen Klammern, sondern mit runden Klammern.
// Auch hier kennt Scala keinen separaten Zugriffsoperator for Arrays. Stattdessen wird hier die
// im Objekt liegende `apply` und `update`  Methode aufgerufen. `apply` und `update` sind ein magic names.
// Wir können diese Methode egal an welchem Objekt statt `instance.apply(34)` direkt mit
// `instance(34)` aufrufen. Dieses Pattern werden wir desöfteren wiedersehen.
array(0) = 42
array.update(1, 43)

// Ein illegaler Zugriff außerhalb des Wertebereichs mündet in einer ArrayIndexOutOfBoundsException
//array(11) = 44 <- ArrayIndexOutOfBoundsException

// Alternativ kann man auch ein vorausgefülltes Array definieren. Hier bedienen wir uns dem companion
// objects von Array, welche wir später kennen lernen werden. Kurz gesagt ist es der statische Nachbar der
// Klasse Array, wiederum mit einer `apply` Methode.
val newArray = Array(1, 2, 3, 4, 5)


/**************
 * Listen
 *************/
// Listen sind der dynamischer Nachbar des Arrays und können beliebig wachsen und auch schrumpfen. Anders
// als in vielen anderen Sprachen ist die Standardliste in Scala immutable. Das heißt, jede Operation, die
// ich auf einer Liste vorneme, erzeugt wiederum eine neue Liste

// Listen können auch hier mit einem companion object erzeugt werden.
val list: List[Int] = List(1, 2, 3, 4, 5, 6)
// Zugriff erlangen wir wieder mit der gekürzt geschriebenen `apply` Methode
list(4)
// Die Methoden der Liste heißen ein wenig seltsam, sollen aber eher einen Operatorcharakter haben.
// Daher schreibt man sie auch eher ohne Punkt und Klammern.
val addList_1 = 0 :: list     // Legt die 0 an den Anfang der neuen Liste
val addList_2 = list :+ 7     // Legt die 7 an das Ende der neuen Liste

// Mit der ::: Methode lassen sich zwei Listen konkatenieren.
val concatList = List(-2, -1, 0) ::: list

// Ein paar nützliche Methoden sind `head` und `tail`.
// `head` liefert das erste Element einer Liste. `tail` die Liste ohne das erste Element.
val head = list.head  // 1
val tail = list.tail  // (2, 3, 4, 5, 6)

// Darüber hinaus verfügt List über diverse Transformationsmethoden, die
// über Lambdaausdrücke gesteuert werden.
list.filter(i => i > 3)       // Filtert alle Elemente raus, die nicht auf den Filter passen
list.map(i => i + 2)          // Transformiert jedes Element der Liste
list.takeWhile(i => i > 4)    // Liefert so lange Elemente von links nach rechts, bis der Lambdaausdruck true ergibt.
list.foldLeft(0)((sum, element) => sum + element)   // "Klappt" die Liste zusammen


//flatMap
val nestedList = List(List("Hallo", "Welt", "."), List("Hello", "world", "."))
nestedList.flatten

val mappedList = nestedList.map(l => l.map(s => s.length))
mappedList.flatten

nestedList.flatMap(l => l.map(s => s.length))
// Beside the immutable collections there is a whole package of mutable counterparts.
// e.g. the mutable ListBuffer
val listBuffer  = mutable.ListBuffer[String]()
listBuffer += "hallo"
listBuffer += "welt"
"!" +=: listBuffer
println(listBuffer.toList)


import scala.collection.mutable

/**************
 * Maps
 **************/
// Immutable Map
// Each entry is a Tuple2 with a key and value
// We can define an entry directly as tuple or with the implicit arrow notation
val firstMap = Map("one" -> "hallo", "two" -> "welt")
firstMap("one")

// Concatenations
val a_map = firstMap + ("three" -> "foo")
val b_map = a_map - "three"

// Mutable counterpart
val mutableMap: mutable.Map[String, Int]  = mutable.Map()
mutableMap += ("foo" -> 1) += ("bar" -> 2)
val newMutableMap = mutableMap + ("baz" -> 3)

// Outlook:
// If you ask how such statements
//   "key" -> 123, or 1 to 5
// are possible. These are implicit conversions. Like the autoboxing known from java, scala allows own definitions
// of these conversions. So in the above example, the String "key" is implicit converted to an object of the class
// ArrowAssoc of some type A. This object has the '->' method, which takes a value of some type B and returns a
// tuple of type A, B.
//
// For more information how to write your own conversions see section 7.






