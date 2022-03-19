package main.grokking_al_book.chapter_6


import java.util.*
import kotlin.collections.HashMap


private val graph: MutableMap<String, List<String>> = HashMap()

private fun search(name: String): Boolean {

    val searchQueue: Queue<String> = ArrayDeque(graph[name])
    // This list save which people you've searched before.
    val searched = HashSet<String>()

    while (searchQueue.isNotEmpty()) {
        val person = searchQueue.poll()
        // Only if you haven't already searched them
        if (!searched.contains(person)) {
            if (isSellerPerson(person)) {
                println("$person is a mango seller!")
            } else {
                graph[person]?.let { searchQueue.addAll(it) }
                // Marks this person as searched
                searched.add(person)
            }
        }
    }
    return false
}

private fun isSellerPerson(name: String): Boolean {
    return name.endsWith("m")
}

typealias Graph<V> = MutableMap<V, List<V>>

fun <V> Graph<V>.breadthFirstSearch(key: V, isSearched: (V) -> Boolean): Boolean {

    val queue: Deque<V> = LinkedList()

    // add first node to the searched list
    this[key]?.let { queue += it }
    val searched = HashSet<V>()

    while (queue.isNotEmpty()) {
        val value = queue.pop()
        if (!searched.contains(value))
            if (isSearched(value)) {
                println("value $value is here!")
                return true
            } else {
                // add all neighbors of the current node
                this[value]?.let { queue += it }
                // add current node to the searched list
                searched.add(value)
            }
    }
    return false
}

data class Person(
        val name: String,
        val isSellerMango: Boolean = false
) {
    override fun equals(other: Any?): Boolean =
            if (other is Person) other.name == name
            else false

    override fun hashCode(): Int {
        return name.length
    }
}



fun main() {

    // shortest path

    // simple way
    graph["you"] = listOf("alice", "bob", "claire")
    graph["bob"] = listOf("anuj", "peggy")
    graph["alice"] = listOf("peggy")
    graph["claire"] = listOf("thom", "jonny")
    graph["anuj"] = emptyList()
    graph["peggy"] = emptyList()
    graph["thom"] = emptyList()
    graph["jonny"] = emptyList()
    search("you")

    // Generic way
    val graph: Graph<Person> = HashMap()
    (graph as java.util.HashMap<Person, List<Person>>).apply {
        put(Person("John"), listOf(Person("Sergey"), Person("Viktoria")))
        put(Person("Viktoria"), listOf(Person("Sergey"), Person("Phara")))
        put(Person("Phara"), listOf(Person("Sergey"), Person("Thrall"), Person("Xul"), Person("Juncart", true)))
    }

    println(graph.breadthFirstSearch(Person("John"), Person::isSellerMango))
}