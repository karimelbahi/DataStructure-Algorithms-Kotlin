package main.data_Structures_algorithms_in_kotlin_book.ch3_03_Linked_List.mutableIterable

import main.data_Structures_algorithms_in_kotlin_book.ch3_03_Linked_List.Node

class LinkedListMutableIterable<T : Any> : Iterable<T> , MutableIterator<T>{
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

    fun pushChain(value: T): LinkedListMutableIterable<T> {
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

    fun appendChain(value: T): LinkedListMutableIterable<T> {
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

    fun insertChain(value: T, afterNode: Node<T>): LinkedListMutableIterable<T> {
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
        return LinkedListMutableIterator(this)

    }

    // Mutating while iterating
    override fun hasNext(): Boolean {
        TODO("Not yet implemented")
    }

    override fun next(): T {
        TODO("Not yet implemented")
    }

    override fun remove() {
        TODO("Not yet implemented")
    }


}

fun main() {

/*
    val list = LinkedListMutableIterable<Int>()
    list.push(3)
    list.push(2)
    list.push(1)

    list.pushChain(30).pushChain(20).push(10)

    list.append(400)
    list.append(500)
    list.append(600)

    list.appendChain(7000).appendChain(8000)
    println(list)

    val list2 = LinkedListMutableIterable<Int>()
    list2.push(3)
    list2.push(2)
    list2.push(1)
    println("Before inserting: $list2")
    var middleNode = list2.nodeAt(1)!!
    for (i in 1..3) {
        middleNode = list2.insert(-1 * i, middleNode)
    }
    println("After inserting: $list2")

    list2.insertChain(100, middleNode).insertChain(5, middleNode)
    println("After Chain inserting: $list2")

    val poppedValue = list2.pop()
    println("After popping list2: $list2")
    println("Popped value: $poppedValue")

    var removedValue = list2.removeLast()
    println("After removing last2 node: $list2")
    println("Removed value: $removedValue")


    val index = 2
    val node = list2.nodeAt(index - 1)!!
    removedValue = list2.removeAfter(node)
    println("After removing at index $index: $list2")
    println("Removed value: $removedValue")
*/

    val list = LinkedListMutableIterable<Int>()
    list.push(3)
    list.push(2)
    list.push(1)
    println(list)
    for (item in list) {
        println("Double: ${item * 2}")
    }

}