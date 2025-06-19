open class Vehicle(val brand: String) {
    open fun startEngine(){
        println("The vehicle's engine starts.")
    }
}
class Motorcycle(brand: String) : Vehicle(brand) {
    override fun startEngine(){
        println("The Motorcycle's engine roars to life!")
    }
}
fun main() {
    val car = Motorcycle("Car")
    car.startEngine()
}