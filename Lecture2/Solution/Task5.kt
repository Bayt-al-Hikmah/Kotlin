fun main() {
    println("Enter number to calculate factorial")
    var number = readLine()!!.toInt()
    var fact = 1
    while(number > 1){
        fact *= number
        number--
    }
    println(fact)
    println("Enter number 1")
    var n = readLine()!!.toInt()
    println("Enter number 2")
    var m = readLine()!!.toInt()
    var prime = true

    for (i in n..m){
        if (i==2){
            println(i)
        }
        else{
            prime = true
            for (j in 2..i-1){
                if(i % j ==0){
                    prime = false
                    break
                }
            }
            if (prime){
                println(i)
            }

        }
    }
    println("Enter n:")
    val num = readLine()!!.toInt()
    var j = 0
    do {
        if(j % 2 == 0){
            println("$j")
        }

        j++
    } while (j <= num)


}