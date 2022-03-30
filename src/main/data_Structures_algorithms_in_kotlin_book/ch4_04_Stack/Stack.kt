package main.data_Structures_algorithms_in_kotlin_book.ch4_04_Stack

interface Stack<Element> {
    val count: Int
        get
    val isEmpty: Boolean
        get() = count == 0

    fun push(element: Element)
    fun pop(): Element?
    fun peek(): Element?
}