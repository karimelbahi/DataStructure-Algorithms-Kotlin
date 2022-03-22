package main.data_Structures_algorithms_in_kotlin_book.ch1_01_kotlin_and_kotlin_standard_library


data class Car(val name: String, var doors: Int = 4) {
    fun drive() {}
}

fun main() {
    var car: Car? = null

    car = Car("Mercedes-Benz")
    println(car.name)
    println(car.doors)
    println(car.drive())
    println(car?.drive())

    // if the car object is null return Porsche car
    val realCar: Car = car ?: Car("Porsche")
    println(realCar.name)
    println(realCar.doors)
    println(realCar!!.drive())

}