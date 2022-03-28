package main.data_Structures_algorithms_in_kotlin_book.ch3_03_Linked_List.iterable

import main.data_Structures_algorithms_in_kotlin_book.ch3_03_Linked_List.Node

class LinkedListIterable<T : Any> : Iterable<T>{
    private var head: Node<T>? = null
    private var tail: Node<T>? = null
    var size = 0
    fun isEmpty(): Boolean {
        return size == 0
    }

    override fun toString(): String {
        if (isEmpty()) {
            return "Empty list"
        } else {
            return head.toString()
        }
    }

    fun push(value: T) {
        head = Node(value = value, next = head) // make current note pointer to the head then pass it th the head to be the first item
        if (tail == null) {
            tail = head
        }
        size++
    }

    fun pushChain(value: T): LinkedListIterable<T> {
        head = Node(value = value, next = head)
        if (tail == null) {
            tail = head
        }
        size++
        return this
    }

    fun append(value: T) {
// 1
        if (isEmpty()) {
            push(value)
            return
        }
// 2
        tail?.next = Node(value = value)
// 3
        tail = tail?.next
        size++
    }

    fun appendChain(value: T): LinkedListIterable<T> {
// 1
        if (isEmpty()) {
            push(value)
            return this
        }
// 2
        tail?.next = Node(value = value)
// 3
        tail = tail?.next
        size++

        return this
    }

    fun nodeAt(index: Int): Node<T>? {
// 1
        var currentNode = head
        var currentIndex = 0
// 2
        while (currentNode != null && currentIndex < index) {
            currentNode = currentNode.next
            currentIndex++
        }
        return currentNode
    }

    fun insert(value: T, afterNode: Node<T>): Node<T> {
// 1
        if (tail == afterNode) {
            append(value)
            return tail!!
        }
// 2
        val newNode = Node(value = value, next = afterNode.next)
// 3
        afterNode.next = newNode
        size++
        return newNode
    }

    fun insertChain(value: T, afterNode: Node<T>): LinkedListIterable<T> {
// 1
        if (tail == afterNode) {
            append(value)
            return this
        }
// 2
        val newNode = Node(value = value, next = afterNode.next)
// 3
        afterNode.next = newNode
        size++
        return this
    }

    fun pop(): T? {
        if (!isEmpty()) size--
        val result = head?.value
        head = head?.next
        if (isEmpty()) {
            tail = null
        }
        return result
    }

    fun removeLast(): T? {
// 1
        val head = head ?: return null
// 2
        if (head.next == null) return pop()
// 3
        size--
// 4
        var prev = head
        var current = head
        var next = current.next
        while (next != null) {
            prev = current
            current = next
            next = current.next
        }
// 5
        prev.next = null
        tail = prev
        return current.value
    }

    fun removeAfter(node: Node<T>): T? {
        val result = node.next?.value
        if (node.next == tail) {
            tail = node
        }
        if (node.next != null) { // because this will be the end node and we cannot remove any node after it cause of there are no one
            size--
        }
        node.next = node.next?.next
        return result
    }


    // Iterating through elements
    override fun iterator(): Iterator<T> {
        return LinkedListIterator(this)

    }

}

fun main() {

    val list = LinkedListIterable<Int>()
    list.push(5)
    list.push(4)
    list.push(3)
    list.push(2)
    list.push(1)

    println("list before removing $list")
    list.removeAfter(list.nodeAt(1)!!)
    list.removeLast()
    println("list after removing $list")

    for (item in list) {
        println("Double: ${item * 2}")
    }

}