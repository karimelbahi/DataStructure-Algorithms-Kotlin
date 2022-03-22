package main.data_Structures_algorithms_in_kotlin_book.ch1_01_kotlin_and_kotlin_standard_library

fun printCarLet(car: Car?) {
    val isCoupe = car?.let {
        (it.doors <= 2)
    }
    if (isCoupe == true) {
        println("Coupes ar awesome Let")
    }
}

fun printCarRun(car: Car?) {
    val isCoupe = car?.run {
        (this.doors <= 2)
    }
    if (isCoupe == true) {
        println("Coupes ar awesome Run")
    }
}

fun printCarAlso(car: Car?) {
    car?.also {
        it.doors = 4
    }.let {
        if (it?.doors != null && it.doors <= 2) {
            println("Coupes ar awesome Also")
        }
    }
}

fun printCarApply(car: Car?) {
    car?.apply {
        doors = 4
    }.let {
        if (it?.doors != null && it.doors <= 2) {
            println("Coupes ar awesome Apply")
        }
    }
}

fun main() {

    /** let and run for transformation , and also and run for manipulation */
    val car = Car("test", 2)

    printCarLet(car)
    println(car)

    printCarRun(car)
    println(car)

    printCarAlso(car)
    println(car)

    printCarApply(car)
    println(car)


}