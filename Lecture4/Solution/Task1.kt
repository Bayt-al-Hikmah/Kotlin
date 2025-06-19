class Book(val title: String, val author: String,var isAvailable:Boolean = true){
    fun summary(){
        println("$title by $author")
    }
}
fun main() {
    val mybook = Book("The Hobbit",  "J.R.R. Tolkien")
    mybook.summary()
    mybook.isAvailable = false
    println("The book is now checked out.")
}
