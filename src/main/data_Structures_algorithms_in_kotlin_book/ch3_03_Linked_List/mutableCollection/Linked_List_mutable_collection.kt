package main.data_Structures_algorithms_in_kotlin_book.ch3_03_Linked_List.mutableCollection

import main.data_Structures_algorithms_in_kotlin_book.ch3_03_Linked_List.Node


class LinkedListMutableCollection<T : Any> : Iterable<T>, Collection<T>,
    MutableIterable<T>, MutableCollection<T> {

    private var head: Node<T>? = null
    private var tail: Node<T>? = null

//    override val size: Int
//        get() = TODO("Not yet implemented")

    override var size = 0
        private set

    fun push(value: T) {
        head = Node(
            value = value,
            next = head
        ) // make current note pointer to the head then pass it th the head to be the first item
        if (tail == null) {
            tail = head
        }
        size++
    }

    fun pushChain(value: T): LinkedListMutableCollection<T> {
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

    fun appendChain(value: T): LinkedListMutableCollection<T> {
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

    fun insertChain(value: T, afterNode: Node<T>): LinkedListMutableCollection<T> {
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

    override fun isEmpty(): Boolean {
        return size == 0
    }

    override fun add(element: T): Boolean {
        append(element)
        return true
    }

    override fun addAll(elements: Collection<T>): Boolean {
        for (element in elements) {
            append(element)
        }
        return true
    }

    override fun clear() {
        head = null
        tail = null
        size = 0
    }

    override fun iterator(): MutableIterator<T> {
        return LinkedListMutableCollectionIterator(this)
    }

    override fun remove(element: T): Boolean {
// 1
        val iterator = iterator()
// 2
        while (iterator.hasNext()) {
            val item = iterator.next()
// 3
            if (item == element) {
                iterator.remove()
                return true
            }
        }
// 4
        return false
    }

    // The return value of removeAll is true if any elements were removed.
    override fun removeAll(elements: Collection<T>): Boolean {
        var result = false
        for (item in elements) {
            result = remove(item) || result
        }
        return result
    }

    override fun retainAll(elements: Collection<T>): Boolean {
        var result = false
        val iterator = this.iterator()
        while (iterator.hasNext()) {
            val item = iterator.next()
            if (!elements.contains(item)) {
                iterator.remove()
                result = true
            }
        }
        return result
    }

    override fun toString(): String {
        if (isEmpty()) {
            return "Empty List"
        } else {
            return head.toString()
        }
    }

}

fun main() {

    println("removing last elements")
    val list: MutableCollection<Int> = LinkedListMutableCollection()
    list.add(3)
    list.add(2)
    list.add(1)
    println(list)
    list.remove(1)
    println(list)


    println("retaining elements")
    val list2: MutableCollection<Int> = LinkedListMutableCollection()
    list2.add(3)
    list2.add(2)
    list2.add(1)
    list2.add(4)
    list2.add(5)
    println(list2)
    list2.retainAll(listOf(3, 4, 5))
    println(list2)

    println("remove all elements")
    val list3: MutableCollection<Int> = LinkedListMutableCollection()
    list3.add(3)
    list3.add(2)
    list3.add(1)
    list3.add(4)
    list3.add(5)
    println(list3)
    list3.removeAll(listOf(3, 4, 5))
    println(list3.toString())


}