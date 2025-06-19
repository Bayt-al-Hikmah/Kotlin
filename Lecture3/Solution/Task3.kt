fun main() {
    println("Enter number:")
    val n = readln().toInt()
    println("$n written on binary as ${runner(n,::decimalToBinaryTailRec)}")


    println("Enter number2:")
    val m = readln().toInt()
    println("$m is even: ${runner(m,{n -> n % 2 == 0})}")


    println("Enter number3:")
    val num = readln().toInt()
    val fact = runner(n){
        var n = 1
        for (i in 1..it){
            n *= i
        }
        n
    }
    
    println("factorial of $num is: $fact")
}


fun runner(n:Int, func: (Int)-> Any):Any{
    return func(n)
}


tailrec fun decimalToBinaryTailRec(n: Int, accumulator: Long = 1,result: Long=0): Long {
    if (n == 0) return result

    return decimalToBinaryTailRec(n /2, accumulator * 10,result + n % 2 * accumulator)
}
