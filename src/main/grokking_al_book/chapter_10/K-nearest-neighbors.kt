package main.grokking_al_book.chapter_10

import kotlin.math.pow
import kotlin.math.sqrt


private fun `k-nearest neighbors`(data: MutableList<List<Category>>, testCategory: List<Category>) {

    var minimumDist = Double.POSITIVE_INFINITY
    var categoryIndex = -1
    var totalDist = 0.0
    for (item in data) {
        for (category in item.indices) {
            totalDist += euclideanDistance(item[category].value, testCategory[category].value)
        }
        if (totalDist < minimumDist) {
            minimumDist = totalDist
            categoryIndex = data.indexOf(item)
        }
        totalDist = 0.0
    }
    println("minimum distance length is $minimumDist")
    println("similar to category $categoryIndex")
}

data class Category(val name: String, val value: Double)

fun euclideanDistance(testItem: Double, categoryItem: Double): Double {
    return sqrt((testItem - categoryItem).pow(2))
}

// TODO: 19/03/2022 Regression 
fun main() {

    var data = mutableListOf(
            listOf(Category("Comedy", 3.0), Category("Action", 4.0), Category("Drama", 4.0), Category("Horror", 1.0), Category("Romance", 4.0)),
            listOf(Category("Comedy", 4.0), Category("Action", 3.0), Category("Drama", 5.0), Category("Horror", 1.0), Category("Romance", 5.0)),
            listOf(Category("Comedy", 2.0), Category("Action", 5.0), Category("Drama", 1.0), Category("Horror", 3.0), Category("Romance", 1.0)))

    val testItem = listOf(Category("Comedy", 1.0), Category("Action", 1.0), Category("Drama", 1.0), Category("Horror", 0.0), Category("Romance", 1.0))

    `k-nearest neighbors`(data, testItem)

}
