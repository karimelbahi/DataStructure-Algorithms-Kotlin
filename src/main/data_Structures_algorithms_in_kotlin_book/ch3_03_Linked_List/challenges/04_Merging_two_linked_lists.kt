package main.data_Structures_algorithms_in_kotlin_book.ch3_03_Linked_List.challenges

import main.data_Structures_algorithms_in_kotlin_book.ch3_03_Linked_List.Node
import main.data_Structures_algorithms_in_kotlin_book.ch3_03_Linked_List.mutableCollection.LinkedListMutableCollection


/*
Create a function that takes two sorted linked lists and merges them into a single
sorted linked list
Your goal is to return a new linked list that contains the nodes from two lists in
sorted order. You may assume they are both sorted in ascending order. For example:
// list1
1 -> 4 -> 10 -> 11
// list2
-1 -> 2 -> 3 -> 6
// merged list
-1 -> 1 -> 2 -> 3 -> 4 -> 6 -> 10 -> 11
 */

fun <T : Comparable<T>> LinkedListMutableCollection<T>.mergeSorted(
    otherList: LinkedListMutableCollection<T>
): LinkedListMutableCollection<T> {
    if (this.isEmpty()) return otherList
    if (otherList.isEmpty()) return this
    val result = LinkedListMutableCollection<T>()

    var left = nodeAt(0) // equal this.nodeAt(0)
    var right = otherList.nodeAt(0)

    while (left != null && right != null) {
        if (left.value < right.value) {
            left = append(result, left)
        } else {
            right = append(result, right)
        }
    }

    while (left != null) {
        left = append(result, left)
    }

    while (right != null) {
        right = append(result, right)
    }

    return result
}

private fun <T : Comparable<T>> append(
    result: LinkedListMutableCollection<T>,
    node: Node<T>
): Node<T>? {
    result.append(node.value)
    return node.next
}


fun main() {

    /** all of the lists has to be sorted*/
    val list = LinkedListMutableCollection<Int>()
    list.add(1)
    list.add(2)
    list.add(3)
    list.add(5)
    list.add(6)
    val other = LinkedListMutableCollection<Int>()
    other.add(-1)
    other.add(0)
    other.add(2)
    other.add(4)
    other.add(7)
    println("Left: $list")
    println("Right: $other")
    println("Merged: ${list.mergeSorted(other)}")
}