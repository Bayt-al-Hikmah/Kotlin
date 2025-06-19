fun main() {
    val colors = mutableListOf("red", "green", "blue")
    println("First color: ${colors.first()}")
    println("Last color: ${colors.last()}")
    colors.add("yellow")
    colors.add(0,"black")
    colors.removeLast()
    println("$colors")
    println("green in our List: ${colors.contains("green")}")
    val numbers = listOf(5,3,8,1)
    println("Ordered numbers: ${numbers.sorted()}")

}