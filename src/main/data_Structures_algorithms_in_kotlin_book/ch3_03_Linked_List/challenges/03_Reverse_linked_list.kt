package main.data_Structures_algorithms_in_kotlin_book.ch3_03_Linked_List.challenges

import main.data_Structures_algorithms_in_kotlin_book.ch3_03_Linked_List.Node
import main.data_Structures_algorithms_in_kotlin_book.ch3_03_Linked_List.mutableCollection.LinkedListMutableCollection


/*
To reverse a list is to manipulate the nodes so that the nodes of the list are linked in
the opposite direction. For example:
// before
1 -> 2 -> 3
// after
3 -> 2 -> 1
 */

fun <T : Any> addInReverse(list: LinkedListMutableCollection<T>, node: Node<T>) {
    val next = node.next
    if (next != null) {
        addInReverse(list, next)
    }
    list.append(node.value)
}

fun <T : Any> reverse(list: LinkedListMutableCollection<T>, node: Node<T>) {
    val next = node.next
    if (next != null) {
        addInReverse(list, next)
    }
    node.next = node
}


fun <T : Any> LinkedListMutableCollection<T>.reversed(): LinkedListMutableCollection<T> {
    val result = LinkedListMutableCollection<T>()
    val head = this.nodeAt(0)
    if (head != null) {
        addInReverse(result, head)
    }
    return result
}

fun <T : Any> LinkedListMutableCollection<T>.reverse(): LinkedListMutableCollection<T> {
    val result = LinkedListMutableCollection<T>()
    val head = this.nodeAt(0)
    if (head != null) {
        reverse(this, head)
    }
    return result
}

fun main() {
    val list = LinkedListMutableCollection<Int>()
    list.add(1)
    list.add(2)
    list.add(3)
    list.add(4)
    list.add(5)
    println("Original: $list")
    println("Reversed: ${list.reversed()}")
    for (item in list)
        print(item)
}