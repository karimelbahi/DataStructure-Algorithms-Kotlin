package main.data_Structures_algorithms_in_kotlin_book

/**
 * normal way
 * */
/*class Box {
    var content: Any? = null
    fun put(content: Any?) {
        this.content = content
    }

    fun retrieve():Any? {
        return content
    }
    fun isEmpty():Boolean {
        return content == null
    }
}*/

/**
 * generic way
 * */
class Box<T> {
    var content: T? = null
    fun put(content: T?) {
        this.content = content
    }

    fun retrieve(): T? {
        return content
    }

    fun isEmpty(): Boolean {
        return content == null
    }
}

fun main() {

    val box = Box<Int>()
    box.put(4)

    val boolBox = Box<Boolean>()
    boolBox.put(true)
    boolBox.isEmpty()
}