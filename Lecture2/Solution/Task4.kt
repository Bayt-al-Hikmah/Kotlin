fun main() {
    val number = 9
    if (number % 2 == 0) {
        println("even number")
    }else{
        println("odd number")
    }
    
    val temperature = 32.5
    if(temperature < 20){
        println("Bring a jacket")
    }else if(temperature <= 30){
        println("T-shirt weather")
    }else {
        println("Wear shorts")
    }

}