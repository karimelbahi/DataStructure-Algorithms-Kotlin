package main.data_Structures_algorithms_in_kotlin_book.ch4_04_Stack.challenges
import main.data_Structures_algorithms_in_kotlin_book.ch4_04_Stack.StackImpl


/*:
 # Stack Challenges
 ## Challenge 2:
    Check for balanced parentheses.
    Given a string, check if there are `(` and `)` characters,
    and return `true` if the parentheses in the string are balanced.
    For example:
    h((e))llo(world)() // balanced parentheses
    (hello world // unbalanced parentheses
 */

fun String.checkParentheses(): Boolean {

    val stack = StackImpl<Char>()
    for (char in this) {
        when (char) {
            '(' -> stack.push(char)
            ')' -> if (stack.isEmpty) {
                return false
            } else {
                stack.pop()
            }
        }
    }

    return stack.isEmpty
}

fun main() {

    println("h((e))llo(world)()".checkParentheses())
    println("(hello world".checkParentheses())
}