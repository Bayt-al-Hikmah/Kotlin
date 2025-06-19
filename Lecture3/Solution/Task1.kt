fun main() {
    println("Enter number to check:")
    val num =readln().toInt()
    println("$num is prime: ${isPrime(num)}")
}

fun isPrime(number: Int): Boolean{
    if (number == 2) return true
    for (i in 2..number){
        if (number % i == 0) return false
    }
    return true
}