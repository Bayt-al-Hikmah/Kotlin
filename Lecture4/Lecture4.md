## Objectives
- Build a strong foundation in Object-Oriented Programming (OOP) with Kotlin.
- Learn to write robust code by handling errors and exceptions gracefully.
## Object-Oriented Programming (OOP)
### Introduction: 
Imagine we're building a complex application, like a game or a social media app. You could write all your code in one massive file, but it would quickly become chaotic and impossible to manage.  
Object-Oriented Programming (OOP) provides a powerful way to structure your code. The core idea is to organize your software around "objects," which are modeled after things in the real world.
- **Objects**: Think of an object as a self-contained entity. In a game, you might have a `Player` object, an `Enemy` object, and an `Item` object. In a banking app, you'd have a `Customer` object and a `BankAccount` object. Each object holds its own data and can perform specific actions.
- **Classes**: So, how do we create these objects? We use a **class**. A class is like a blueprint or a cookie cutter. The blueprint for a `Car` defines that all cars will have a color and a model, and that they can be driven. The cookie cutter defines the shape of a cookie. You can use that single cutter to make many individual cookies (objects), each a separate entity.
### The Anatomy of a Class: Attributes and Methods
Every class we define has two main components:
1. **Attributes (or Properties)**: These are the variables that belong to the class. They represent the data or characteristics of an object. For a `Person` class, attributes might be `name`, `age`, and `height`. They define the _state_ of an object.
2. **Methods (or Functions)**: These are the functions that belong to the class. They represent the actions or behaviors that an object can perform. For our `Person` class, methods might be `walk()`, `talk()`, or `greet()`. They define what the object can _do_.

Let's build a simple `Person` class step-by-step to see this in action.
```
// We define the blueprint for a Person using the 'class' keyword.
class Person {
    // 1. ATTRIBUTES (Properties)
    var name: String = "Sarah"
    var age: Int = 25

    // 2. METHODS (Functions)
    fun greet() {
        // Inside a method, we can use the object's attributes.
        println("Hello, my name is $name and I am $age years old.")
    }
}
```
### Creating and Using an Object (an "Instance")
Now that we have our `Person` blueprint, we can create actual `Person` objects from it. This process is called **instantiation**, and each object is an **instance** of the class.

```
fun main() {

    val person1 = Person()

    // We can access the object's attributes using the dot (.) operator.
    println(person1.name) // Output: Sarah

    // We can also call its methods.
    person1.greet() 

    // Let's create another, separate instance.
    val person2 = Person()
    person2.name = "John" 
    person2.age = 30

    person2.greet() // Output: Hello, my name is John and I am 30 years old.

    // Notice that person1 remains unchanged.
    person1.greet() // Output: Hello, my name is Sarah and I am 25 years old.
}
```
### Constructors: The Smart Way to Create Objects
In the example above, we created a `Person` and then set their name and age afterwards. This is a bit clumsy. What if we want to ensure every `Person` is created with a name and age right from the start? For this, we use a **constructor**.  
A constructor is a special method that runs automatically when we create an object, allowing us to initialize its attributes right away. In Kotlin, the most common way to do this is with a primary constructor, which is part of the class header.

```
// The primary constructor is defined here, in the parentheses.
// Using 'val' or 'var' inside the constructor is a shortcut to declare
// and initialize the attributes at the same time.
class Person(val name: String, var age: Int) {

    fun greet() {
        println("Hello, my name is $name and I am $age years old.")
    }
}

fun main() {
    // Now, we must provide the initial values when creating the object.
    val person = Person("Alice", 30)
    person.greet()
}
```
We can also add an initializer block (`init`) to the class. This code runs right after the object is created and its properties are initialized.
```
class Person(val name: String, var age: Int) {
    // This 'init' block runs every time a new Person object is created.
    init {
        println("A new person named $name was created!")
    }
}

fun main() {
    val p1 = Person("Alice", 30) // Prints the init message
    val p2 = Person("Bob", 25)   // Prints the init message again
}
```
### Properties, Getters, and Setters: Controlling Access to Data
We've seen that we can access properties like `person.name`. But how does this work? In Kotlin, properties are more powerful than simple variables.
#### The "Magic" Behind Properties
When we declare a property, Kotlin automatically generates accessor methods for you behind the scenes.
- For a `val` (read-only), Kotlin generates a getter. This method is called whenever you read the property's value.
- For a `var` (mutable), Kotlin generates a getter and a setter. The setter is called whenever you assign a new value to the property.

We don't see this code, which is why Kotlin is so clean. `println(person.name)` calls the getter, and `person.name = "new name"` calls the setter.
#### Custom Getters and Setters
Sometimes, you need more control. You might want to run some logic when a property is read or changed. In these cases, you can define your own custom getter or setter.
- A **custom getter** is useful for calculating a property's value on the fly.
- A **custom setter** is great for validating a new value before it's assigned.

Inside a custom accessor, you use the special identifier `field` to refer to the property's backing fieldthe hidden variable that actually stores the value. In a setter, the new value being assigned is available as `value`.  
Let's look at an example:
```
class User(val firstName: String, val lastName: String) {

    // 1. A custom GETTER
    // This property doesn't store any data itself. It's calculated
    // every time it's accessed.
    val fullName: String
        get() = "$firstName $lastName"

    // 2. A custom SETTER
    var age: Int = 0
        set(value) { // 'value' is the new value being assigned
            if (value >= 0) {
                // 'field' refers to the backing field of 'age'
                field = value
            } else {
                println("Age cannot be negative. Setting age to 0.")
                field = 0
            }
        }
}

fun main() {
    val user = User("John", "Doe")

    // Accessing fullName calls the custom getter
    println("Welcome, ${user.fullName}!") // Output: Welcome, John Doe!

    // Assigning to 'age' calls the custom setter
    user.age = 25
    println("User's age: ${user.age}") // Output: User's age: 25

    user.age = -5 // Calls the setter again with validation logic
    // Output: Age cannot be negative. Setting age to 0.
    println("User's age: ${user.age}") // Output: User's age: 0
}
```
### Encapsulation: Protecting Our Data
Encapsulation is one of the core principles of OOP. It means bundling the data (attributes) and the methods that work on that data within one unit (the class), and controlling access to that data.  
Why is this important? Imagine a `BankAccount` class. You wouldn't want external code to be able to just set the balance to any number they want, like a negative value! You want to control _how_ the balance is modified.  
In Kotlin, you use visibility modifiers to achieve this. The most common one is `private`. A `private` member can only be accessed from _inside_ the same class.
```
class BankAccount(val accountNumber: String, initialBalance: Double) {
    // By making this property 'private', we hide it from the outside world.
    // It can only be used within the BankAccount class.
    private var balance: Double = initialBalance

    // This is a public method that allows controlled modification.
    fun deposit(amount: Double) {
        if (amount > 0) {
            balance += amount
            println("Deposited $amount. New balance is $balance")
        } else {
            println("Cannot deposit a negative amount.")
        }
    }

    // This public method allows controlled access to the balance.
    fun getBalance(): Double {
        return balance
    }
}

fun main() {
    val account = BankAccount("12345", 100.0)

    // account.balance = 5000.0 // ERROR! 'balance' is private and cannot be accessed.

    
    account.deposit(50.0)      
    account.deposit(-20.0)     
    println(account.getBalance()) 
}
```
### Static Members (Companion Objects)
In Kotlin, if we need a method or property that is tied to a class rather than an instance of the class, you place it inside a **`companion object`**. This is Kotlin's equivalent of the `static` keyword in other languages.
```
class MathUtils {
    companion object {
        const val PI = 3.14159 // A compile-time constant

        fun square(number: Double): Double {
            return number * number
        }
    }
}

fun main() {
    println(MathUtils.PI)          // Accessing the constant directly through the class name
    println(MathUtils.square(5.0)) // Calling the method directly through the class name
}
```
### Inheritance: Reusing Code and Building Hierarchies
Inheritance allows you to create a new class that is based on an existing class. The new class (the child or subclass) inherits the attributes and methods of the existing class (the parent or superclass). This creates an "is-a" relationship (e.g., a `Dog` _is a_ `Pet`).  
To allow a class to be inherited from, we must mark it as `open`. By default, classes in Kotlin are `final` (cannot be inherited). 
```
// This class is 'open', so other classes can inherit from it.
open class Pet(val name: String) {
    // This method is also 'open' so it can be changed by subclasses.
    open fun speak() {
        println("$name makes a generic pet sound.")
    }
}

// Dog inherits from Pet. It gets the 'name' attribute and 'speak' method.
class Dog(name: String) : Pet(name) { // We must call the parent constructor
    // We 'override' the parent's speak method to provide a specific implementation.
    override fun speak() {
        println("$name says Woof!")
    }
}

// Cat also inherits from Pet.
class Cat(name: String) : Pet(name) {
    override fun speak() {
        println("$name says Meow!")
    }
}

fun main() {
    val myDog = Dog("Buddy")
    val myCat = Cat("Whiskers")

    myDog.speak() // Output: Buddy says Woof!
    myCat.speak() // Output: Whiskers says Meow!
}
```
If we want to change the functionality of a parent method in a child class, we override it using the `override` keyword followed by the method name , but before doing that we should make sure that the method is set as open in the parent Class.  
To call the parent class's implementation of a method from an overridden method, you use the `super` keyword.

```
class SmartDog(name: String, age: Int) : Pet(name, age) {
    override fun speak() {
        super.speak() // Calls the speak() method from the Pet class
        println("$name also says Woof!")
    }
}

fun main() {
    val smartDog = SmartDog("Rex", 4)
    smartDog.speak()
    // Output:
    // Rex makes a sound.
    // Rex also says Woof!
}
```
### Polymorphism: One Action, Many Forms
Polymorphism, which means "many forms," is a powerful concept that works with inheritance. It allows us to treat objects of different subclasses as if they were all objects of the parent class. This lets us write more general and flexible code.
```
fun main() {

    val pets: List<Pet> = listOf(
        Dog("Rex"),
        Cat("Luna")
    )
    for (pet in pets) {
        pet.speak()
    }
}
// Output:
// Rex says Woof!
// Luna says Meow!
```
### Abstract Classes and Interfaces
#### Abstract Classes: Partial Blueprints
An abstract class is a class that cannot be created on its own. It's a partial blueprint that defines some common structure and behavior for its subclasses, but it leaves some parts for the subclasses to implement.  
Think of it as a "build-it-yourself" kit with some parts pre-assembled.
```
abstract class Shape {
    // A regular method that all shapes will inherit.
    fun description() {
        println("I am a shape.")
    }

    // An abstract method. It has no implementation here.
    // Any subclass MUST provide an implementation for this method.
    abstract fun area(): Double
}

class Circle(val radius: Double) : Shape() {
    // We must override and implement the abstract 'area' method.
    override fun area(): Double {
        return Math.PI * radius * radius
    }
}
```
#### Interfaces: A Contract of Abilities
An interface is a contract. It defines a set of methods and properties that a class must implement if it "signs" the contract. An interface can't have its own state (attributes), but it can define abstract methods or methods with a default implementation.  
A class can inherit from only one parent class, but it can implement many interfaces. Think of them as "skills" or "abilities" a class can have.  
```
// Anyone implementing this interface MUST be able to drive.
interface Drivable {
    fun drive()
}

// Anyone implementing this interface CAN fly.
interface Flyable {
    fun fly()
}

// A Car can be driven.
class Car : Drivable {
    override fun drive() {
        println("Driving the car.")
    }
}

// A Superhero can be driven (in their car) and can also fly.
class Superhero : Drivable, Flyable {
    override fun drive() {
        println("Driving the super-mobile.")
    }

    override fun fly() {
        println("Flying to the rescue!")
    }
}
```
#### Sealed Classes
A sealed class is used to represent a restricted class hierarchy. All subclasses of a sealed class are known at compile time. This is incredibly useful with `when` expressions, as the compiler can check if you've covered all possible cases.
```
sealed class Result {
    data class Success(val data: String) : Result()
    data class Error(val message: String) : Result()
    object Loading : Result()
}

fun handleResult(result: Result) {
    when (result) {
        is Result.Success -> println("Success: ${result.data}")
        is Result.Error -> println("Error: ${result.message}")
        Result.Loading -> println("Loading...")
    }
}
```
### Operator Overloading
Operator overloading is the act of making basic operators like `+`, `-`, `*`, `==`, and others work with our own classes. By default, Kotlin doesn't know how to use these operators with custom objects. But by defining special functions in our class, we can tell Kotlin what these operators should do.  
Let’s say we’re working with a `FoodPortion` class. We want to be able to **add** two portions together or **compare** if they are equal. Instead of creating separate methods like `add` or `isEqual`, we can overload the `+` and `==` operators to keep our code clean and natural.  
In Kotlin, we use the `operator` keyword to tell the compiler that we are overloading a standard operator.
```
class FoodPortion(val grams: Int) {

    // Overload + operator
    operator fun plus(other: FoodPortion): FoodPortion {
        return FoodPortion(this.grams + other.grams)
    }

    // Override equals to compare based on grams
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is FoodPortion) return false
        return this.grams == other.grams
    }

    // Always override hashCode when overriding equals
    override fun hashCode(): Int {
        return grams
    }

    // Override toString for readable output
    override fun toString(): String {
        return "${grams}g"
    }
}

fun main() {
    val portion1 = FoodPortion(50)
    val portion2 = FoodPortion(30)
    val total = portion1 + portion2 // This works due to the 'plus' operator

    println("Total: $total") // Output: Total: 80g
    println(portion1 == portion2) // Output: false

    val portion3 = FoodPortion(50)
    println(portion1 == portion3) // Output: true (because we overrode equals)
}
```
The operator ``===`` checks whether two references point to the exact same object in memory this is called referential equality.
The `this` variable represents the first operand (the object on the left side of the `+`). It refers to the current instance of the class on which the function is being called. We use `this` when we want to access the properties or methods of the current object, especially when both operands are involved in an operation.
The `other` variable represents the second operand (the object on the right side of the `+`). We include it as a parameter for binary operators that require two operands.  
Here’s a small list of some common operators and the functions we need to define to overload them in Kotlin:

| Operator | Function to Define               | Description           |
| :------- | :------------------------------- | :-------------------- |
| `+`      | `fun plus(other)`                | Addition              |
| `-`      | `fun minus(other)`               | Subtraction           |
| `*`      | `fun times(other)`               | Multiplication        |
| `/`      | `fun div(other)`                 | Division              |
| `%`      | `fun rem(other)` or `mod(other)` | Remainder (Modulo)    |
| `==`     | `fun equals(other)`              | Equality comparison   |
| `!=`     | Not directly overloaded*         | Inequality            |
| `<`      | `fun compareTo(other)`           | Less than             |
| `<=`     | `fun compareTo(other)`           | Less than or equal    |
| `>`      | `fun compareTo(other)`           | Greater than          |
| `>=`     | `fun compareTo(other)`           | Greater than or equal |
In Kotlin, if we correctly override the `equals` function , the `!=` operator will automatically work as its logical opposite. The comparison operators (`<`, `<=`, `>`, `>=`) are all handled by implementing the `compareTo` function, which should return a negative integer, zero, or a positive integer if `this` object is less than, equal to, or greater than `other`, respectively.
#### Comparing Objects with `compareTo`
We've seen how `==` checks for equality. But what if we want to know if one object is greater than or less than another? For numbers, this is easy (`5 > 3`). For our custom classes, Kotlin doesn't inherently know what makes one `FoodPortion` "greater than" another.  
This is where the `compareTo` function comes in. By implementing it, we define a "natural order" for our objects. Once we do this, Kotlin allows us to use all the familiar comparison operators: `>`, `<`, `>=`, and `<=`.  
To do this, our class needs to implement the `Comparable` interface. Think of this as a "contract" where our class promises to provide a `compareTo` function.  
##### How `compareTo` Works
The `compareTo` function's job is to compare the current object (`this`) with another object (`other`) and return an integer that signals the result:
- **A negative number:** if `this` object is less than the `other` object.
- **Zero:** if `this` object is equal to the `other` object.
- **A positive number:** if `this` object is greater than the `other` object.

Let's teach our `FoodPortion` class how to compare itself to another `FoodPortion`. The most logical way to compare them is by their weight in grams.
```
class FoodPortion(val grams: Int) : Comparable<FoodPortion> {
    operator fun plus(other: FoodPortion): FoodPortion {
        return FoodPortion(this.grams + other.grams)
    }

    override operator fun compareTo(other: FoodPortion): Int {
        return if (this.grams < other.grams) {
            -1
        } else if (this.grams > other.grams) {
            1
        } else {
            0
        }
    }

    override fun toString(): String {
        return "${grams}g"
    }
}


fun main() {
    val smallPortion = FoodPortion(100)
    val largePortion = FoodPortion(250)
    val mediumPortion = FoodPortion(100) // Same size as smallPortion

    println("Is largePortion > smallPortion?")
    println(largePortion > smallPortion) // Output: true

    println("\nIs smallPortion < largePortion?")
    println(smallPortion < largePortion) // Output: true

    println("\nIs smallPortion >= mediumPortion?")
    println(smallPortion >= mediumPortion) // Output: true

}
```
## Error Handling
### Introduction: When Things Go Wrong
No matter how carefully we write our code, errors can happen. An exception is an error that occurs while your program is running that disrupts its normal flow. For example:
- Trying to divide a number by zero.
- Trying to convert the text "hello" into a number.
- Trying to access an item in a list that doesn't exist.

If we don't handle these exceptions, our program will crash. Kotlin provides a safe way to deal with them.
### Handling Exceptions with `try-catch`
The `try-catch` block is our primary tool for handling exceptions.
- **`try`**: You put the "risky" code that might cause an exception inside this block.
- **`catch`**: If an exception _does_ happen in the `try` block, the code inside the `catch` block is executed. This is your "plan B."
- **`finally`**: This is an optional block. The code inside `finally` is **always** executed, whether an exception occurred or not. It's perfect for cleanup code, like closing a file.
```
fun main() {
    try {
        println("Please enter a number:")
        val number = readln().toInt()
        println("You entered the number $number.")
    } catch (e: NumberFormatException) {
        // This block only runs if the user enters text that is not a number.
        println("That was not a valid number! Please try again.")
        println("Error details: ${e.message}")
    } finally {
        // This block always runs.
        println("Finished the operation.")
    }
}
```
### Throwing Your Own Exceptions
Sometimes, we need to signal an error in your own code. You can do this with the `throw` keyword. This is useful for enforcing rules in your program.
```
fun withdraw(balance: Double, amount: Double) {
    if (amount > balance) {
        // We create and throw an exception to signal an invalid operation.
        throw IllegalArgumentException("You don't have enough funds.")
    }
    println("Withdrawal successful.")
}

fun main() {
    try {
        withdraw(balance = 100.0, amount = 150.0)
    } catch (e: IllegalArgumentException) {
        println("Error: ${e.message}") // Output: Error: You don't have enough funds.
    }
}
```
### Creating Custom Exceptions
While Kotlin provides many built-in exception types, sometimes it's clearer to create our own to represent specific error conditions in our application. We can define our own exception class by simply inheriting from the `Exception` class. This makes your error handling code more specific and readable.
```
// Define our own custom exception class.
class InsufficientFundsException(message: String) : Exception(message)

fun withdrawCustom(balance: Double, amount: Double) {
    if (amount > balance) {
        // Now we throw our custom exception.
        throw InsufficientFundsException("You don't have enough funds to withdraw $$amount.")
    }
    println("Withdrawal successful. New balance: $${balance - amount}")
}

fun main() {
    val balance = 200.0
    val amountToWithdraw = 250.0
    
    try {
        withdrawCustom(balance, amountToWithdraw)
    } catch (e: InsufficientFundsException) {
        // We can specifically catch our custom exception.
        println("Transaction failed: ${e.message}") 
    }
}
```
## Tasks
### Task 1:
Create a `Book` class that has the following properties:
- `title` (String, read-only)
- `author` (String, read-only)
- `isAvailable` (Boolean, mutable, with a default value of `true`)

The class should have a method:
- `summary()`: Prints a one-line summary of the book, like "`The Hobbit` by J.R.R. Tolkien."

**Instructions:**
1. Create the class with a primary constructor for the `title` and `author`.
2. Create an instance of the `Book` class.
3. Call its `summary()` method.
4. Change its availability to `false` and print a message like "The book is now checked out."
### Task 2:
1. Create an `open` class named `Vehicle` with:
    - A read-only property `brand` (String).
    - An `open` method `startEngine()` that prints "`The vehicle's engine starts.`"
2. Create a `Motorcycle` class that inherits from `Vehicle` and:
    - Overrides the `startEngine()` method to print "`The Motorcycle's engine roars to life!`"
3. In `main`, create an instance of `Motorcycle` and call its `startEngine()` method.
### Task 3:
1. Create a `Calculator` class with a single method: `divide(a: Int, b: Int): Int`.
2. This method should:
    - Return the result of `a / b`.
    - Throw an `ArithmeticException` with the message "Error: Cannot divide by zero." if `b` is `0`.
3. In your `main` function, use a `try-catch` block to call the `divide` method. Call it once with `10, 2` and once with `10, 0`. Print the result if successful, or the error message if it fails.