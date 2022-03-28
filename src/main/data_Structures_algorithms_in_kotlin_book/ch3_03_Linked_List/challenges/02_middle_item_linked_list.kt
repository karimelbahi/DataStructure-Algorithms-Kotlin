package main.data_Structures_algorithms_in_kotlin_book.ch3_03_Linked_List.challenges

import main.data_Structures_algorithms_in_kotlin_book.ch3_03_Linked_List.Node
import main.data_Structures_algorithms_in_kotlin_book.ch3_03_Linked_List.mutableCollection.LinkedListMutableCollection


/*
Given a linked list, find the middle node of the list. For example:
1 -> 2 -> 3 -> 4
// middle is 3
1 -> 2 -> 3
// middle is 2
 */


fun <T : Any> LinkedListMutableCollection<T>.getMiddle(): Node<T>? {
    var slow = this.nodeAt(0)
    var fast = this.nodeAt(0)

    while (fast != null) {
        fast = fast.next
        if (fast != null) {
            fast = fast.next
            slow = slow?.next
        }
    }
    return slow
}


fun main() {
    val list = LinkedListMutableCollection<Int>()
    list.add(1)
    list.add(2)
    list.add(3)
    list.add(4)
    list.add(5)
    println(list)
    println(list.getMiddle()?.value)
}