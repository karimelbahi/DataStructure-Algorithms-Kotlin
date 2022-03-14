package main.grokking_al_book.chapter_5

val voted:HashMap<String,Boolean> = HashMap<String, Boolean>()

fun checkVoter(name: String) {

    if(!voted.containsKey(name)){
        voted.put(name, true)
        println("let them vote!")
    } else {
        println("kick them out!")
    }

}

private val cachedSites: MutableMap<String, String> = HashMap()

private fun cachedSites(url: String): String? {
    return if (cachedSites.containsKey(url)) {
        println("get data from cache")
        cachedSites[url]
    } else {
        println("get data from server")
        val data = getDataFromServer(url)
        cachedSites[url] = data
        data
    }
}

private fun votedPersons() {
    checkVoter("tom")
    checkVoter("mike")
    checkVoter("mike")
}

private fun getCashedFromServer() {
    println(cachedSites("facebook"))
    println(cachedSites("youtube"))
    println(cachedSites("facebook"))
    println(cachedSites("youtube"))
}

private fun priceOfGroceries() {

    val book: MutableMap<String, Double> = HashMap()

    // an apple costs 67 cents
    book["apple"] = 0.67
    book["app"] = 0.66
    // milk costs $1.49
    book["milk"] = 1.49
    book["avocado"] = 1.49
    println(book) // {apple=0.67, app=0.67, avocado=1.49, milk=1.49}
}

fun getDataFromServer(url : String):String{
    return "data $url"
}

fun main(args: Array<String>) {

    /**
     * checkVoter
     * */
    votedPersons()

    println("------------")
    /**
     * check categories prices
     *  */
    priceOfGroceries()

    println("------------")

    /**
     * cachedSites
     * */
    getCashedFromServer()
}


