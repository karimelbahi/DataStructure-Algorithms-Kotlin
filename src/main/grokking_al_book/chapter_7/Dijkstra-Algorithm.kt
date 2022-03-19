package main.grokking_al_book.chapter_7


import java.util.*

// the graph
private val graph: MutableMap<String, MutableMap<String, Double>> = HashMap()
private val processed: MutableList<String> = ArrayList()

private fun findLowestCostNode(costs: Map<String, Double>): String? {

    var newCost = Double.POSITIVE_INFINITY
    var newCostNode: String? = null

    // Go through each node
    for ((key, currentCost) in costs) {
        // If it's the lowest cost so far and hasn't been processed yet...
        if (!processed.contains(key) && currentCost < newCost) {
            // ... set it as the new lowest-cost node.
            newCost = currentCost
            newCostNode = key
        }
    }
    return newCostNode
}

private fun dijkstra(costs: MutableMap<String, Double>, parents: MutableMap<String, String?>) {

    // lowest const starting from the beginning node
    var node = findLowestCostNode(costs)

    // iterate on the other nodes (till find the end which it's nodes is null)
    while (node != null) {

        // get cost of the selected node (node with lowest cost)
        val cost: Double? = costs[node]
        // Go through all the neighbors of this node
        val neighbors: Map<String, Double>? = graph[node]
        for (n in neighbors!!.keys) {
            val newCost = cost!! + (neighbors[n] ?: error(""))
            // If it's cheaper to get to this neighbor by going through this node
            if (costs[n]!! > newCost) {
                // ... update the cost for this node
                costs[n] = newCost
                // This node becomes the new parent for this neighbor.
                parents[n] = node
            }
        }
        // Mark the node as processed
        processed.add(node)

        // Find the next node to process, and loop
        node = findLowestCostNode(costs)
    }
}

fun main() {

    // shortest path with minimum cost
    // graph of the paths table
    graph["start"] = HashMap()
    graph["start"]?.set("a", 6.0)
    graph["start"]?.set("b", 2.0)
    graph["a"] = HashMap()
    graph["a"]?.set("fin", 1.0)
    graph["b"] = HashMap()
    graph["b"]?.set("a", 3.0)
    graph["b"]?.set("fin", 5.0)
    graph["fin"] = HashMap()

    // The costs table
    val costs: MutableMap<String, Double> = HashMap()
    costs["a"] = 6.0
    costs["b"] = 2.0
    costs["fin"] = Double.POSITIVE_INFINITY

    // the parents table
    val parents: MutableMap<String, String?> = HashMap()
    parents["a"] = "start"
    parents["b"] = "start"
    parents["fin"] = null

    dijkstra(costs, parents)
    println("Cost from the start to each node:")
    println(costs) // { a: 5, b: 2, fin: 6 }
}


