package main.data_Structures_algorithms_in_kotlin_book.ch1_01_kotlin_and_kotlin_standard_library

/**
 * normal way
 * */
class Box {
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
}

/**
 * generic way
 * */
class BoxGeneric<T> {
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

    /** normal way
     * This could work, but there’s one drawback: Once you put something into the box,
     * you lose the knowledge of the object’s type since you had to use the Any type to story
     * any kind of object.
     * */
    val box = Box()
    box.put(4)
    println(box.retrieve())

    val boolBox = Box()
    boolBox.put(true)
    println(boolBox.isEmpty())

    println("-------------")

    /** generic way*/
    val boxG = BoxGeneric<Int>()
    boxG.put(4)
    println(boxG.retrieve())

    val boolBoxG = BoxGeneric<Boolean>()
    boolBoxG.put(true)
    println(boolBoxG.isEmpty())
}