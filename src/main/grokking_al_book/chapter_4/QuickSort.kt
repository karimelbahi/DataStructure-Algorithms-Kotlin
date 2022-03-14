package main.grokking_al_book.chapter_4

import kotlin.random.Random

// TODO take the pivot strategy as a parameter
fun quickSort(numbers: IntArray, lowIndex: Int = 0, highIndex: Int = numbers.size - 1): IntArray {
    if (highIndex - lowIndex < 1) {
        return numbers
    }
    var pivotIndex = lowIndex + Random.nextInt(highIndex - lowIndex)
    val pivotValue = numbers[pivotIndex]
    numbers.swap(pivotIndex, highIndex)
    var left = lowIndex
    var right = highIndex - 1

    while (left <= right) {
        if (numbers[left] >= pivotValue && numbers[right] <= pivotValue) {
            numbers.swap(left, right)
            left++
            right--
        } else {
            if (numbers[left] < pivotValue) {
                left++
            }
            if (numbers[right] > pivotValue) {
                right--
            }
        }
    }
    numbers.swap(left, highIndex)
    pivotIndex = left

    quickSort(numbers, lowIndex, pivotIndex - 1)
    quickSort(numbers, pivotIndex + 1, highIndex)
    return numbers
}

private fun IntArray.swap(index1: Int, index2: Int) {
    val temp = this[index1]
    this[index1] = this[index2]
    this[index2] = temp

}

// TODO take the pivot strategy as a parameter
fun recursiveQuickSort(list: List<Int>): List<Int> {

    // base case, arrays with 0 or 1 element are already "sorted"
    if (list.size <= 1) return list

    val pivot = list[list.size / 2]
    val equal = list.filter { it == pivot }
    val less = list.filter { it < pivot }
    val greater = list.filter { it > pivot }

    // recursive case
    return recursiveQuickSort(less) + equal + recursiveQuickSort(greater)
}

fun main() {
    val myList = listOf(1, 19, 2, 25, 0, 5, 7, 9)
    val myList2 = intArrayOf(10, 0, 2, 5, 1, 8, 23, 31, 21, 93, 213, 231, 341, 10, 20, 5, 516, 132, 643)

    println(quickSort(myList2,0).toList())
    print(recursiveQuickSort(myList))


}