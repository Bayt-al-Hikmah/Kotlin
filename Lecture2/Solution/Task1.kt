fun main() {
    val word = "Developer"
    println("first character: ${word.first()}")
    println("last character: ${word.last()}")
    println("uppercase: ${word.uppercase()}")
    val phrase = "I love Python"
    println(phrase.replace("Python","Kotlin"))
    val sentence = "Learning Kotlin is fun"
    println("sentence contain fun: ${sentence.contains("fun")}")
    println("number of characters in sentence: ${sentence.length}")

}