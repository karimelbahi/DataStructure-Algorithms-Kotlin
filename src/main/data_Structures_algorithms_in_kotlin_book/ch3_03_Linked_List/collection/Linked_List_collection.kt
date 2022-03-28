package main.data_Structures_algorithms_in_kotlin_book.ch3_03_Linked_List.collection

import main.data_Structures_algorithms_in_kotlin_book.ch3_03_Linked_List.Node

class LinkedListCollection<T : Any> : Iterable<T>, Collection<T> {
    private var head: Node<T>? = null
    private var tail: Node<T>? = null

    override var size = 0

    override fun isEmpty(): Boolean {
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

    fun pushChain(value: T): LinkedListCollection<T> {
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

    fun appendChain(value: T): LinkedListCollection<T> {
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

    fun insertChain(value: T, afterNode: Node<T>): LinkedListCollection<T> {
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
        return LinkedListCollectionIterator(this)

    }

    override fun contains(element: T): Boolean {
        for (item in this) {
            if (item == element) return true
        }
        return false
    }


    override fun containsAll(elements: Collection<T>): Boolean {
        for (searched in elements) {
            if (!contains(searched)) return false
        }
        return true
    }

}

fun main() {

    val list = LinkedListCollection<Int>()
    list.push(5)
    list.push(4)
    list.push(3)
    list.push(2)
    list.push(1)
    println("Before removing at particular index: $list")
    val index = 1
    val node = list.nodeAt(index - 1)!!
    val removedAfterValue = list.removeAfter(node)
    println("After removing at index $index: $list")
    println("Removed value: $removedAfterValue")

    println("--------------------")

    for (item in list) {
        println("Double: ${item * 2}")
    }

    println("--------------------")

    println("current list -> ${list}")

    println("is the list contains 3 -> ${list.contains(3)}")

    val list2 = LinkedListCollection<Int>()
    list2.push(5)
    list2.push(4)
    println("is the list contains 4, 5 -> ${list.containsAll(list2)}")

}