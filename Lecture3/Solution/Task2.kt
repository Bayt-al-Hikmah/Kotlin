fun main() {
    println("Enter number:")
    val n = readln().toInt()
    println("$n written on binary as ${decimalToBinary(n)}")
    println("Enter number2:")
    val m = readln().toInt()
    println("$m written on binary as ${decimalToBinaryTailRec(m)}")
}

fun decimalToBinary(n:Int): Long{
    if (n == 0) return 0
    return  decimalToBinary(n/2 ) * 10 + (n % 2)
}
tailrec fun decimalToBinaryTailRec(n: Int, accumulator: Long = 1,result: Long=0): Long {
    if (n == 0) return result

    return decimalToBinaryTailRec(n /2, accumulator * 10,result + n % 2 * accumulator)
}
