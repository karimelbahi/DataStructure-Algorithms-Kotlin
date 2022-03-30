package main.data_Structures_algorithms_in_kotlin_book.ch4_04_Stack.challenges

import main.data_Structures_algorithms_in_kotlin_book.ch4_04_Stack.StackImpl

/*:
 # Stack Challenges
 ## Challenge 1:
    Print a linked list in reverse without using recursion.
    Given a linked list, print the nodes in reverse order.
    You should not use recursion to solve this problem.
 */

fun <T : Any> ArrayList<T>.printInReverse() {
    val stack = StackImpl<T>()
    for (node in this) {
        stack.push(node)
    }

    var node = stack.pop()
    while (node != null) {
        println(node)
        node = stack.pop()
    }
}


fun main() {

    val stack = arrayListOf(1,2,3,4,5,6)
    stack.printInReverse()
}