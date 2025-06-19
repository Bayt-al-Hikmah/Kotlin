fun main() {
    println("Enter temperature: ")
    val input = readln()
    val temperatureC:Double? = input.toDoubleOrNull()
    if (temperatureC != null) {
        val temperatureF = temperatureC * 9 / 5 + 32
        println("$temperatureC C° = ${temperatureF} F°")
    }else{
        println("Error: Enter valid temperature")
    }

}