package section2

import java.util.Calendar

/********************
 * COMPANION OBJECTS
 *******************/

// Möchte man nun ein statisches Element für alle Instanzen einer Klasse einführen,
// so bietet Scala die Möglichkeit eines companion objects.

/**
  *
  * @param colour
  * @param age
  */
class Car(var colour: String, age: Int) {
  private var fuel: Double = 40

  def drive(km : Int) = {
    fuel = fuel - (km / 100 * 5.5)
  }

  def refuel(liter: Double) = {
    require(liter + fuel <= 40, "Tank zu voll")
    fuel = fuel + liter
  }

  private def crashWith(other: Car) {
    this.explodeAirbags()
    other.explodeAirbags()
  }

  private def explodeAirbags() = {
    println("PUFFF!")
  }
}

/**
  *
  */
object Car {
  def startTraffic(cars: Car*) = {
    if(cars.size >= 2) {
      cars(0).crashWith(cars(1))
    }
  }

  def apply(color: String, age: Int): Car = {
    new Car(color, age)
  }
}



object Main {
  def main(args: Array[String]) = {
    val firstCar = Car("blue", 3)
    val secondCar = Car("red", 5)

    Car.startTraffic(firstCar, secondCar)
  }
}




