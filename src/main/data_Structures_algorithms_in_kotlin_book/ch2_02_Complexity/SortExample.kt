package main.data_Structures_algorithms_in_kotlin_book.ch2_02_Complexity

fun main() {

    printSorted(listOf(1,2,5,3,4,9,8,7))
}

fun printSorted(numbers: List<Int>) {
// 1
    if (numbers.isEmpty()) return
// 2
    var currentCount = 0
    var minValue = Int.MIN_VALUE
// 3
    for (value in numbers) {
        if (value == minValue) {
            println(value)
            currentCount += 1
        }
    }
    while (currentCount < numbers.size) {
// 4
        var currentValue = numbers.maxOrNull()!!
        for (value in numbers) {
            if (value < currentValue && value > minValue) {
                currentValue = value
            }
        }
// 5
        for (value in numbers) {
            if (value == currentValue) {
                println(value)
                currentCount += 1
            }
        }
// 6
        minValue = currentValue
    }
}