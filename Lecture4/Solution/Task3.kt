class Calculator(){
    companion object{fun divide(a:Int, b:Int):Int {
        if (b == 0){
            throw ArithmeticException("Error: Cannot divide by zero.")
        }
        return a / b
    }
    }
}
fun main() {
    try{
        println("10 / 2 = ${Calculator.divide(10,2)}")
        println("10 / 0 = ${Calculator.divide(10,0)}")
    }catch (e:ArithmeticException){
        println("Error: ${e.message}")
    }
}
