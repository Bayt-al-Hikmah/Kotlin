fun main() {
    println("Enter radius ")
    val input = readln()
    val radius:Double? = input.toDoubleOrNull()
    if (radius == null) {
        println("ERROR: Invalid radius")
    }else{
        val area = radius * radius * 3.14
        println("The area is $area")
    }

}