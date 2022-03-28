package main.data_Structures_algorithms_in_kotlin_book.ch3_03_Linked_List.iterable

import main.data_Structures_algorithms_in_kotlin_book.ch3_03_Linked_List.Node


class LinkedListIterator<T : Any>(private val list: LinkedListIterable<T>) : Iterator<T> {
    private var index = 0
    private var lastNode: Node<T>? = null

    override fun hasNext(): Boolean {
        return index < list.size
    }

    override fun next(): T {
        // 1
        if (index >= list.size) throw IndexOutOfBoundsException()
// 2
        /** If this is the first iteration, there is no lastNode set, so you take the first node of
            the list. After the first iteration, you can get the next property of the last node to
            find the next one.*/
        lastNode = if (index == 0) {
            list.nodeAt(0)
        } else {
            lastNode?.next
        }
// 3
        index++
        return lastNode!!.value
    }


}