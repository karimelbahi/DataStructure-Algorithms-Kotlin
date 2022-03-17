package main.grokking_al_book.chapter_8

private fun greedySearch(statesNeeded: MutableSet<String>, stations: Map<String, Set<String>>): MutableSet<String> {
    val finalStations: MutableSet<String> = HashSet()
    while (statesNeeded.isNotEmpty()) {
        var bestStation: String? = null
        var statesCovered: Set<String?> = HashSet()
        for ((key, value) in stations) {
            val covered: MutableSet<String?> = HashSet(statesNeeded)
            covered.retainAll(value)
            if (covered.size > statesCovered.size) {
                bestStation = key
                statesCovered = covered
            }
            statesNeeded.removeIf { o: String? ->
                statesCovered.contains(o)
            }
            if (bestStation != null) {
                finalStations.add(bestStation)
            }
        }
    }
    return finalStations
}


fun main() {
    // Covering Stations Problem

    val statesNeeded: MutableSet<String> = HashSet(listOf("mt", "wa", "or", "id", "nv", "ut", "ca", "az"))
//    val stations: MutableMap<String, Set<String>> = LinkedHashMap()
//    stations["kone"] = HashSet(listOf("id", "nv", "ut"))
//    stations["ktwo"] = HashSet(listOf("wa", "id", "mt"))
//    stations["kthree"] = HashSet(listOf("or", "nv", "ca"))
//    stations["kfour"] = HashSet(listOf("nv", "ut"))
//    stations["kfive"] = HashSet(listOf("ca", "az"))

    val stations = mapOf(
            "kone" to setOf("id", "nv", "ut"),
            "ktwo" to setOf("wa", "id", "mt"),
            "kthree" to setOf("or", "nv", "ca"),
            "kfour" to setOf("nv", "ut"),
            "kfive" to setOf("ca", "az")
    )

    val finalStations: MutableSet<String> = greedySearch(statesNeeded, stations)
    println(finalStations) // [ktwo, kone, kthree, kfive]
}

