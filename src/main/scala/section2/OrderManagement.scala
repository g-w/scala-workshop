package section2

import org.joda.time.DateTime

object OrderManagement {

  /**
    * Eine kleine Typhierachie für Zahlungsinformationen.
    *
    * Eine `sealed` Klasse lässt sich nur in der eigenen .scala-Datei ableiten.
    * So ist gewährleistet, dass die Vererbungsmenge immer eindeutig ist. Beim pattern
    * matching kann so bereits der Compiler entscheiden, ob alle Fälle wirklich abgedeckt sind.
    */
  sealed abstract class Payment
  case class DebitCard(accountNr: String) extends Payment
  case class CreditCard(cardNumber: String, expirationDate: DateTime) extends Payment

  /**
    * Weitere Klassen, mit denen wir eine Bestellung modelliern können. Es gilt:
    *
    * [Order] -----(1)->  [Payment]
    *      \
    *       -----(0,*)->  [LineItem] ---(1)->  [Product]
    */
  case class Product(name: String, price: Double)
  case class LineItem(product: Product, count: Int)

  case class Order(id: Int, date: DateTime, payment: Payment, items: List[LineItem] = List()) {
    def addLineItem(item: LineItem): Order = copy(items = item :: items)
  }



  /**
    * Dieses Repository ist ein statisches Objekt, mit einer festen Anzahl an `Order`s, die uns als
    * Basis für die Tests dienen soll.
    */
  object OrderRepository {
    /**
      * Zunächst definieren wir vier Produkte
      */
    val apple = Product("Apple", 1.00)
    val milk = Product("Milk", 2.00)
    val beerBox = Product("beer box", 14.00)
    val chips = Product("chips", 4.00)

    /**
      * Und zwei Kreditkarten.
      */
    val creditCard1 = CreditCard("0987654321", DateTime.parse("2017-08-15"))
    val creditCard2 = CreditCard("0987654321", DateTime.parse("2015-10-01"))

    /**
      * This is the base repository. It is immutable and only an example for our tests.
      */
    val repository = Map(
      1 -> Order(1, DateTime.parse("2015-10-01"), DebitCard("1234567"), List(LineItem(chips, 5), LineItem(beerBox, 2))),
      2 -> Order(2, DateTime.parse("2015-12-26"), creditCard1, List(LineItem(apple, 3), LineItem(milk, 1))),
      3 -> Order(3, DateTime.parse("2015-09-13"), DebitCard("54321"), List(LineItem(chips, 5))),
      4 -> Order(4, DateTime.parse("2015-12-01"), creditCard2, List(LineItem(milk, 2), LineItem(beerBox, 1), LineItem(apple, 10))),
      5 -> Order(5, DateTime.parse("2015-12-23"), DebitCard("4711"), List(LineItem(apple, 2)))
    )

    /**
      * The repo has a method t find an order by its Id. It returns an `Option` of an `Order`.
      * So, if the id exists, the method will return a `Some` with the found order.
      * If the id does not exist, the method will return a `None`.
      * @param id
      * @return
      */
    def findOrder(id: Int): Option[Order] = repository.get(id)

    /**
      * Returns a list of all `Order`s in the repository.
      * @return
      */
    def findAll(): List[Order] = repository.values.toList
  }



  /**
    * Gibt an, ob eine Order, anhand der `orderId`, eine gültige Zahlungsoption hat. Gültig sind
    * `CreditCard`s und `DebitCard`s. Mögliche Sonderfälle sind durch die Tests definiert.
    *
    * @param orderId
    * @return
    */
  def hasValidPayment(orderId: Int): Boolean = ???

  /**
    * Gibt an, ob eine Order nur von einer Person über 18 Jahren getätigt werden kann.
    * Bier ist in unserem Fall zum Beispiel erst ab 18 erhältlich.
    *
    * @param orderId
    * @return
    */
  def onlyAdultPurchase(orderId: Int): Boolean = ???

  /**
    * Berechnet die Gesamtkosten einer Order. Das wir hier mit `Double` Rundungsfehler eingehen, ignorieren wir
    * mal absichtlich.
    *
    * @param orderId
    * @return
    */
  def orderPrice(orderId: Int): Option[Double] = ???

  /**
    * Berechnet den Wert des Weihnachtsgeschäft. Zum Weihnachtsgeschäft zählen alle Bestellungen die
    * vom 01.12.2015 bis einschließlich 24.12.2015 getätigt wurden.
    * @return
    */
  def christmasBusiness(): Double = ???
}
