package main.grokking_al_book.chapter_9


private var maxCommonSubstring: Int = 0
fun `longest common subsequence examble`(wordA: String, wordB: String, wordC: String) {

    printResult(longestCommonSubsequence(wordA, wordB))

    println("max common subsequence is $maxCommonSubsequence")

//    expected
//    [0, 0, 0, 1]
//    [0, 1, 0, 0]
//    [0, 0, 2, 0]
//    [0, 0, 0, 3]
    println("-------------")

    maxCommonSubsequence = 0

    printResult(longestCommonSubsequence(wordA, wordC))
    println("max common subsequence is $maxCommonSubsequence")

//    expected
//    [0, 0, 0, 0, 0]
//    [0, 1, 0, 0, 0]
//    [0, 0, 2, 0, 0]
//    [0, 0, 0, 0, 0]
}

fun longestCommonSubsequence(wordA: String, wordB: String): Array<IntArray> {

    val cell = Array(wordA.length + 1) { IntArray(wordB.length + 1) }

    for (i in 0..wordA.length) {
        for (j in 0..wordB.length) {
            // The letters match
            if (i == 0 || j == 0)
                cell[i][j] = 0
            else if (wordA[i - 1] == wordB[j - 1]) {
                cell[i][j] = cell[i - 1][j - 1] + 1
                if (cell[i][j] > maxCommonSubsequence)
                    maxCommonSubsequence = cell[i][j]
            } else {
                // The letters don't match.
                cell[i][j] = cell[i - 1][j].coerceAtLeast(cell[i][j - 1])
                if (cell[i][j] > maxCommonSubsequence)
                    maxCommonSubsequence = cell[i][j]
            }
        }
    }
    return cell
}

private var maxCommonSubsequence: Int = 0
private fun `longest common substring examble`(wordA: String, wordB: String, wordC: String) {

    val cell = longestCommonSubstring(wordA, wordB)
    printResult(cell)
    println("max common substring is $maxCommonSubstring")
//      expected
//      [0, 0, 0, 1]
//      [0, 1, 1, 1]
//      [0, 1, 2, 2]
//      [0, 1, 2, 3]

    println("-------------")

    maxCommonSubstring = 0
    val cell2 = longestCommonSubstring(wordA, wordC)
    printResult(cell2)
    println("max common substring is $maxCommonSubstring")

//      expected
//    [0, 0, 0, 0, 0]
//    [0, 1, 1, 1, 1]
//    [0, 1, 2, 2, 2]
//    [0, 1, 2, 2, 2]


}

private fun longestCommonSubstring(wordA: String, wordB: String): Array<IntArray> {
    val cell = Array(wordA.length) { IntArray(wordB.length) }
    for (i in wordA.indices) {
        for (j in wordB.indices) {
            // The letters match
            if (wordA[i] == wordB[j]) {
                if (i > 0 && j > 0) {
                    cell[i][j] = cell[i - 1][j - 1] + 1
                    if (cell[i][j] > maxCommonSubstring)
                        maxCommonSubstring = cell[i][j]
                } else {
                    cell[i][j] = 1
                }
            }
        }
    }
    return cell
}

private fun printResult(arr: Array<IntArray>) {
    for (row in arr) {
        println(row.contentToString())
    }
}


private fun `knapsack problem solution`() {
    val guitar = Item("Guitar", 1500, 1)
    val stereo = Item("Stereo", 3000, 4)
    val laptop = Item("Laptop", 2000, 3)
    val iphone = Item("Iphone", 2000, 1)

    val water = Item("Water", 10, 3)
    val book = Item("Book", 3, 1)
    val food = Item("Food", 9, 2)
    val jacket = Item("Jacket", 5, 2)
    val camera = Item("Camera", 6, 1)

    println(knapsack(listOf(guitar, stereo, laptop), 4).items)
    println(knapsack(listOf(stereo, laptop, guitar), 4).items)// to verify that if we change the order, result should stay the same
    println(knapsack(listOf(guitar, stereo, laptop, iphone), 4).items)
    println(knapsack(listOf(guitar, stereo, laptop, iphone), 5).items)
    println(knapsack(listOf(water, book, food, jacket, camera), 6).items)
}

data class Item(val name: String, val value: Int, val spaceTaken: Int)
data class KnapsackSolution(val items: List<Item>, val totalValue: Int) : Comparable<KnapsackSolution> {
    operator fun plus(rhs: KnapsackSolution): KnapsackSolution {
        return KnapsackSolution(items + rhs.items, totalValue + rhs.totalValue)
    }

    override operator fun compareTo(other: KnapsackSolution): Int {
        return totalValue.compareTo(other.totalValue)
    }
}
private typealias KnapsackGrid = List<List<KnapsackSolution>>

private fun KnapsackGrid.get(row: Int, column: Int): KnapsackSolution {
    return this.getOrElse(row) {
        emptyList()
    }.getOrElse(column) {
        EMPTY_KNAPSACK
    }
}

private val EMPTY_KNAPSACK = KnapsackSolution(emptyList(), 0)
fun knapsack(items: List<Item>, knapsackCapacity: Int): KnapsackSolution {
    val solutions = mutableListOf<List<KnapsackSolution>>()

    for (i in items.indices) {
        val item = items[i]
        val row = mutableListOf<KnapsackSolution>()
        solutions.add(row)
        for (capacity in 0 until knapsackCapacity) {
            row.add(findMax(solutions, item, i, capacity)) //i => row of the graph, capacity => column of the graph
        }
    }

    return solutions.last().last() // return last object of last list (last KnapsackSolution of the grid)
}

private fun findMax(solutions: KnapsackGrid, item: Item, row: Int, column: Int): KnapsackSolution {
    val previousMax = solutions.get(row - 1, column) // first part of the formula

    val spaceTaken = item.spaceTaken
    val capacity = column + 1 // there are a relation between the capacity an the column, it is capacity always equal column plus one (check it from the figure)
    val current = if (spaceTaken <= capacity) { // second part of the formula
        KnapsackSolution(listOf(item), item.value) + solutions.get(row - 1, (capacity - spaceTaken - 1))  // full capacity - space taken for the current item -1 (-1 because always the wright is more than the column by one)
    } else {
        EMPTY_KNAPSACK
    }

    return maxOf(previousMax, current) // apply the formula (compare between first and second rule)
}

fun main() {

    val wordA = "hish"
    val wordB = "fish"
    val wordC = "vista"

//    val wordA = "cbdadcbda"
//    val wordB = "babcbad"
//    val wordC = "babcbad"

    `longest common substring examble`(wordA, wordB, wordC)

    println("-------------")

    `longest common subsequence examble`(wordA, wordB, wordC)

    println("-------------")

    `knapsack problem solution`()

}