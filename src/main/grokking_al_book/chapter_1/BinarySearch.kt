package main.grokking_al_book.chapter_1


private fun binarySearch(list: List<Int>, searchItem: Int): Int {
    val sortedList = list.sorted()
    var start = 0
    var end = sortedList.size - 1
    while (start <= end) {
        val center = (start + end)/2
        val guess = sortedList[center]
        when {
            guess == searchItem -> return center
            guess > searchItem -> {
                end = center - 1
            }
            else -> {
                start = center + 1
            }
        }
    }
    return -1
}


private fun recursiveBinarySearch(list: IntArray, item: Int, start: Int, end: Int = list.size - 1): Int? {

    // base case
    if (start > end)
        return null

    val mid = (start + end) / 2
    val guess: Int = list[mid]
    if (guess == item) return mid

    // recursive case
    return if (guess > item) {
        recursiveBinarySearch(list, item, start, mid - 1)
    } else {
        recursiveBinarySearch(list, item, mid + 1, end)
    }
}
fun main() {
    val myList = listOf(1, 3, 5, 7, 9)
    val myList2 = intArrayOf(1, 3, 5, 7, 9)

    println("Search item is at position : ${binarySearch(myList,7) }")
    println("Search item is at position : ${recursiveBinarySearch(myList2 ,3, 0) }")
}