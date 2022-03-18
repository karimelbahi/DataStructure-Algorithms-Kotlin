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


}

