package main.data_Structures_algorithms_in_kotlin_book.ch3_03_Linked_List

fun main() {

    val node1 = Node(value = 1)
    val node2 = Node(value = 2)
    val node3 = Node(value = 3)
    node1.next = node2
    node2.next = node3
    println(node1)

}