package main.grokking_al_book.chapter_2

private fun ArrayList<Int>.selectionSort(): ArrayList<Int> {
    val sortedArray = arrayListOf<Int>()
    for (i in 0 until size) {
        val smallest = getSmallest()
        sortedArray += this[smallest]
        removeAt(smallest)
    }
    return sortedArray
}

private fun ArrayList<Int>.getSmallest(): Int {
    var smallest = this[0]
    var index = 0
    for (i in 1 until size) if (this[i] < smallest) {
        smallest = this[i]
        index = i
    }
    return index
}


// Utility function to swap values at two indices in the array
private fun IntArray.swap(firstItem: Int, secondItem: Int) {
    val temp = this[firstItem]
    this[firstItem] = this[secondItem]
    this[secondItem] = temp
}



fun recursiveSelectionSort(arr: IntArray, start: Int, end: Int) {

    var min = start
    for (item in start + 1 until end) {

        if (arr[item] < arr[min]) { // if `arr[item]` is less, then it is the new minimum
            min = item // update the index of minimum element
        }
    }

    // swap the minimum element in sub array `arr[i…n-1]` with `arr[i]`
    arr.swap(min, start)
    if (start + 1 < end) {
        recursiveSelectionSort(arr, start + 1, end)
    }
}

fun main(args: Array<String>) {

    /**
     * Difference between List and Array types in Kotlin
     * https://www.tutorialspoint.com/difference-between-list-and-array-types-in-kotlin
     * */
    val array = arrayListOf(10, 0, 2, 5, 1, 8, 23, 31, 21, 93, 213, 231, 341, 10, 20, 5, 516, 132, 643)
    val array2 = intArrayOf(10, 0, 2, 5, 1, 8, 23, 31, 21, 93, 213, 231, 341, 10, 20, 5, 516, 132, 643)
    println("Sorted lis: ${array.selectionSort()}")

    recursiveSelectionSort(array2, 0, array2.size);

    // print the sorted array
    println("Sorted lis: ${array2.contentToString()}")
}