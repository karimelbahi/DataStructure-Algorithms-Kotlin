package main.data_Structures_algorithms_in_kotlin_book.ch1_01_kotlin_and_kotlin_standard_library

fun noSideEffectList(names: List<String>) {
    println(names)
    // names.add("Joker") // not available

}

fun sideEffectList(names: MutableList<String>) {
    names.add("Joker")
}

fun mutableVsReadOnly() {
    val people = mutableListOf("Brian", "Stanley", "Ringo")
    noSideEffectList(people) // [Brian, Stanley, Ringo]
    sideEffectList(people)   // Adds a Joker to the list
    noSideEffectList(people) // [Brian, Stanley, Ringo, Joker]
}

fun main() {
    val scores = mutableMapOf("Eric" to 9, "Mark" to 12, "Wayne" to 1)

    scores["Andrew"] = 0
    println(scores.keys)
    println(scores.values)
}
