package main.grokking_al_book.chapter_3


private fun recursiveOnNum(num: Int) {
    if (num == 0) {
        println()
        return
    }
    else {
        print("$num " )
        recursiveOnNum(num - 1)
    }
}

private fun recursiveFactorial(num: Int): Int {

    if (num == 1) {
        return 1
    }
    return num * recursiveFactorial(num - 1)

}

private fun recursiveSum(list: List<Int>): Int {

    if (list.isEmpty())
        return 0
    if (list.size == 1) {
        return list[0]
    }
    return list[0] + recursiveSum(list.subList(1, list.size))

}

private fun recursiveCount(list: List<Int>): Int {

    if (list.isEmpty())
        return 0

    if (list.size == 1) {
        return 1
    }
    return 1 + recursiveCount(list.subList(1, list.size))

}

private fun recursiveMaxim(list: IntArray): Int {

    if (list.isEmpty()) return 0

    if (list.size == 1) return list[1]

    if (list.size == 2) return if (list[0] > list[1]) list[0] else list[1]

    val max = recursiveMaxim(list.copyOfRange(1, list.size))
    return if (list[0] < max) max else list[0]
}

fun main() {
    val myList = listOf(1, 3, 5, 7, 9)
    val myList2 = intArrayOf(1, 3, 5, 7, 9, 2, 5, 4)

    recursiveOnNum(10)
    println(recursiveFactorial(5))
    println(recursiveCount(myList))
    println(recursiveMaxim(myList2))
    println(recursiveSum(myList))

}