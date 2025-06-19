## Objectives
- Working with Functions
- Working with Libraries and Gradle Modules
- Working with External Dependencies (Packages)
## Functions
### Introduction
A function is a reusable block of code designed to perform a specific task. Functions are fundamental to writing efficient and well-structured programs. By following the **DRY** (Don't Repeat Yourself) principle, functions help eliminate code duplication. They also make it easier to break down complex problems into smaller, manageable parts, resulting in code that is more organized, easier to understand, and simpler to maintain.
### Creating Functions
We create a function by following these steps:
1. Identify the code to reuse.
2. Choose a descriptive function name.
3. Decide what parameters (inputs) it needs.
4. Define what it should return (output).

In Kotlin, we define a function using the `fun` keyword, followed by the function name and parentheses `()`. The code to be executed is placed within curly braces `{}`.  
To call a function, we simply use its name followed by parentheses.
```
fun sayHello() {
    println("Hello, user!")
}

// Call the function
sayHello() // Output: Hello, user!
```
### Functions with Parameters and Return Values
We can make a function accept arguments by adding parameters inside the parentheses. In Kotlin, we must specify the type for each parameter by writing `: Type` after the parameter name.  
A function can also return a value. To do this, we specify the return type after the parameter list, preceded by a colon `:`. Inside the function, we use the `return` keyword to send back the result.
```
fun addTwoNumbers(num1: Int, num2: Int): Int {
    val result = num1 + num2
    return result
}

val a = addTwoNumbers(2, 3)
println(a) // Output: 5
```
#### Single-Expression Functions
For functions that consist of just a single expression, Kotlin provides a more concise syntax. We can omit the curly braces and the `return` keyword, and specify the body after an equals sign `=`. The return type is often inferred by the compiler.
```
fun multiply(a: Int, b: Int) = a * b

val result = multiply(4, 5)
println(result) // Output: 20
```
### Named and Default Arguments
#### Named Arguments
Kotlin allows us to specify the names of arguments when we call a function. This makes the code much more readable, especially for functions with many parameters, and allows us to change the order of arguments.
```
fun createUser(name: String, age: Int, city: String) {
    println("Creating user $name of age $age from $city.")
}

// Call with named arguments
createUser(age = 30, name = "Amina", city = "Algiers")
// Output: Creating user Amina of age 30 from Algiers.
```
#### Default Arguments
We can make parameters optional by providing them with default values. If an argument is not provided when the function is called, its default value will be used.
```
fun sayHello(name: String = "user") {
    println("Hello, $name!")
}

sayHello() // Uses the default value
sayHello(name = "Mohamed") // Overrides the default value
```
**Output:**
```
Hello, user!
Hello, Mohamed!
```
### Variable Scope
Scope defines where in a program a variable or function can be accessed. In Kotlin, the most relevant scopes for functions are global and local scope.
#### Global Scope
A variable or constant defined at the top level of a file (outside any function or class) has global scope. These are known as top-level properties. They can be accessed from anywhere within the same module.
#### Local Scope
A variable declared inside a function has local scope. It is only accessible within that function's curly braces `{}`.
```
fun addAndShow(num1: Int, num2: Int): Int {
    val localResult = num1 + num2 // localResult is a local variable
    return localResult
}

val a = addAndShow(num1 = 2, num2 = 3)
println(a)
// println(localResult) // Error: Unresolved reference 'localResult'
```
### Variadic Parameters (`vararg`)
We can design functions to accept a variable number of arguments of a specific type by marking a parameter with the vararg modifier. Inside the function, this parameter is treated as an Array of the specified type.
```
fun sayHello(vararg names: String) {
    for (name in names) {
        println("Hello, $name!")
    }
}

sayHello("Mohamed", "Ahmed", "Ali")
```
**Output:**
```
Hello, Mohamed!
Hello, Ahmed!
Hello, Ali!
```
### Higher-Order Functions
In Kotlin, functions are "first-class citizens," meaning they can be treated like any other value: stored in variables, passed as arguments, or returned from other functions. A function that takes another function as an argument or returns a function is called a higher-order function.
#### Passing a Function as an Argument
We can pass a function as an argument by defining a parameter with a function type. The syntax for a function type is `(ParameterTypes) -> ReturnType`. For example, `(String) -> Unit` represents a function that takes a `String` and returns nothing (`Unit` is Kotlin's equivalent of `void`).
```
fun greet(name: String) {
    println("Hello, $name!")
}

// A higher-order function that accepts a function
fun runCallback(callback: (String) -> Unit, value: String) {
    callback(value)
}

// Pass the 'greet' function as an argument
runCallback(::greet, "Kotlin") // Output: Hello, Kotlin!
```
We can also pass lambda expressions instead of named functions:
```
runCallback({ name -> println("Welcome, $name!") }, "JetBrains") // Output: Welcome, JetBrains!
```
Or use shorthand syntax when possible:
```
runCallback({ println("Hi, $it!") }, "Developers") // Output: Hi, Developers!
```
Even shorter with Kotlin’s trailing lambda syntax:
```
runCallback("World") { println("Hi, $it!") } // Output: Hi, World!
```
The code in {} will represent the function that we want pass as argument.  
Note: The `::` operator is used to get a reference to a named function.
#### Returning a Function
A function can also return another function. We specify this by using a function type as the return type.
```
fun add(a: Int, b: Int): Int = a + b
fun multiply(a: Int, b: Int): Int = a * b

// This function returns another function of type (Int, Int) -> Int
fun getOperation(type: String): (Int, Int) -> Int {
    return when (type) {
        "add" -> ::add
        "multiply" -> ::multiply
        else -> throw IllegalArgumentException("Unknown operation")
    }
}

val operation = getOperation("multiply")
println(operation(3, 4)) // Output: 12

val anotherOp = getOperation("add")
println(anotherOp(3, 4)) // Output: 7
```
### Recursive Functions
A recursive function is one that calls itself to solve a problem. This process continues until a base case (a stopping condition) is met.  
Let's use recursion to calculate a factorial:  
Let's suppose we want to create a Function that calculates the factorial of numbers. We know that:
- 0! is equal to 1
- 1! is equal to 1=1×0!
- 2! is equal to 2×1=2×1!
- 3! is equal to 3×2×1=3×2!
- 4! is equal to 4×3×2×1=4×3!
- 5! is equal to 5×4×3×2×1=5×4!
```
fun factorial(n: Int): Int {
    // Base case: if n is 0, the factorial is 1
    if (n == 0) {
        return 1
    }
    // Recursive step: n * factorial of (n-1)
    return n * factorial(n - 1)
}

println(factorial(5)) // Output: 120
```
In Kotlin, deep recursion can lead to a `StackOverflowError` because each recursive call adds a new frame to the call stack. However, if the recursion is in tail position, Kotlin can optimize it using the `tailrec` modifier, turning it into an efficient loop behind the scenes
```
// A tail-recursive version of factorial
tailrec fun factorialTailRec(n: Int, accumulator: Long = 1): Long {
    if (n == 0) {
        return accumulator
    }
    return factorialTailRec(n - 1, accumulator * n)
}
println(factorialTailRec(20)) // Can calculate large factorials without crashing
```
Kotlin’s `tailrec` modifier detects tail-recursive functions and converts them into loops, eliminating the need for additional stack frames.  
This:
```
return factorialTailRec(n - 1, accumulator * n)
```
gets transformed roughly into:
```
while (n != 0) {
	accumulator *= n     
	n-- 
} 
return accumulator
```
So no matter how big `n` is (up to the limits of data types), it won’t crash the program due to stack overflow.
### Lambda Expressions
A lambda expression (or "lambda") is an anonymous function a function without a name. Lambdas are extremely useful for passing short, one-time-use blocks of code to higher-order functions.  
The basic syntax for a lambda is: `{ parameters -> body }`
```
val sum: (Int, Int) -> Int = { a, b -> a + b }
println(sum(10, 5)) // Output: 15
```
Lambdas shine when used with collections or as arguments.
```
val operations: Map<String, (Int, Int) -> Int> = mapOf(
    "+" to { a, b -> a + b },
    "-" to { a, b -> a - b },
    "*" to { a, b -> a * b },
    "/" to { a, b -> a / b }
)

fun calculate(a: Int, b: Int, operator: String): String {
    val operation = operations[operator]
    return if (operation != null) {
        operation(a, b).toString()
    } else {
        "Unsupported operator"
    }
}

println(calculate(10, 5, "+")) // Output: 15
```
**Trailing Lambda Syntax:** If the last argument of a function is a lambda, you can place it _outside_ the parentheses. This is a very common and readable pattern in Kotlin.
```
val numbers = listOf(1, 2, 3)

// Standard syntax
numbers.forEach({ number -> println(number) })

// Trailing lambda syntax
numbers.forEach { number -> println(number) }
```
### Functional Programming Concepts
Functional programming is a paradigm that treats computation as the evaluation of mathematical functions and avoids changing-state and mutable data.
1. **Pure Functions**: Always produce the same output for the same input and have no side effects (they don't modify anything outside their own scope).
2. **Recursion**: Iteration is often achieved through recursion, as seen above.
3. **First-Class and Higher-Order Functions**: Functions are treated as values, can be passed as arguments, and can be returned from other functions.
4. **Referential Transparency**: An expression can be replaced with its corresponding value without changing the program's behavior.
5. **Immutability**: Preferring immutable data structures (`val`, `listOf`, `mapOf`) over mutable ones (`var`, `mutableListOf`) helps prevent side effects and makes code more predictable.
## Working with Libraries, Modules, and Dependencies in Kotlin
### Introduction
In the Kotlin and Java ecosystem, reusable code is packaged into libraries (typically as `.jar` or `.aar` files). These libraries are managed by a build automation tool, with Gradle being the standard for modern Kotlin projects. Modules and libraries help organize code into reusable components and create namespaces to prevent name collisions.
### Kotlin and Java Standard Libraries
Kotlin projects have access to two powerful sets of standard libraries out of the box:
- **Kotlin Standard Library (`kotlin-stdlib`)**: Provides Kotlin-specific functions and types, such as extensions for collections, coroutines support, and more. It is automatically included in every Kotlin project.
- **Java Development Kit (JDK)**: Since Kotlin is fully interoperable with Java, you can use all the rich libraries from the JDK for tasks like file I/O, networking, date/time manipulation, and math.

To use functionalities from these libraries, you must explicitly `import` them at the top of your file.
```
import kotlin.math.sqrt
import java.util.Date // Importing a class from the Java standard library

fun main() {
    println(sqrt(25.0)) // From Kotlin's math library

    val currentDate = Date() // From Java's utility library
    println(currentDate)
}
```
### Kotlin Projects with Gradle
A Kotlin project is typically managed by Gradle. Gradle handles everything from compiling your code and running tests to managing dependencies.
#### Creating a Kotlin Project
You can create a new Kotlin project using an IDE like IntelliJ IDEA or Android Studio, which will set up the Gradle structure for you. Alternatively, you can initialize a project from the command line:
```
# This command initializes a new Kotlin application project with Gradle
gradle init --type kotlin-application
```
#### Structure of a Gradle Project
A typical Gradle project has the following structure:
```
MyProject/
├── gradle/
├── src/
│   ├── main/
│   │   └── kotlin/
│   │       └── com/example/Main.kt  # Your source code
│   └── test/
│       └── kotlin/
│           └── com/example/AppTest.kt # Your test code
├── build.gradle.kts      # The main build script
└── settings.gradle.kts   # Project settings, defines modules
```
#### The `build.gradle.kts` file
This is the manifest file for your project, written in Kotlin Script (`.kts`). It tells Gradle how to build your project, what plugins to use, and what dependencies it needs.
```
// build.gradle.kts

// Defines the plugins needed for this project (e.g., for Kotlin on the JVM)
plugins {
    kotlin("jvm") version "1.9.23"
    application
}

// Specifies the main repository for finding dependencies
repositories {
    mavenCentral()
}

// Lists the external libraries your project depends on
dependencies {
    // Coroutines library from JetBrains
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")
    testImplementation(kotlin("test")) // A library for testing
}

application {
    mainClass.set("com.example.MainKt") // Specifies the main entry point
}
```
### Creating Local Modules (Gradle Sub-modules)
We can organize a large project into smaller, independent modules (also called sub-projects). This improves organization and build times.
1. **Define a new module** in your `settings.gradle.kts` file.
    ```
    // settings.gradle.kts
    rootProject.name = "my-app"
    include("my-custom-module") // Declare the module
    ```
2. **Create a folder** for the module (e.g., `my-custom-module/`) with its own `src` directory and `build.gradle.kts` file.
3. **Make the main project depend on the module.** Add it as a dependency in the main project's `build.gradle.kts`.
    ```
    // In my-app/build.gradle.kts
    dependencies {
        implementation(project(":my-custom-module")) // Depend on the local module
    }
    ```
4. Now you can `import` and use public code from `my-custom-module` in your main application.
    ```
    // In my-custom-module/src/main/kotlin/MyModule.kt
    package com.example.custom
    
    object MyModule {
        fun sayHello(name: String) {
            println("Hello from the module, $name!")
        }
    }
    
    // In my-app/src/main/kotlin/Main.kt
    import com.example.custom.MyModule
    
    fun main() {
        MyModule.sayHello("Ali")
    }
    ```
## Working with External Dependencies (Packages)
### Introduction

Gradle makes it easy to use third-party libraries (packages) from public repositories like Maven Central, Google's Maven Repository, or JitPack. These packages help you avoid reinventing the wheel by leveraging the work of the wider developer community for tasks like networking, JSON serialization, and more.
### Installing and Using a Third-Party Library
To use an external library, you simply add it to the `dependencies` block in your `build.gradle.kts` file. Gradle will automatically download it and make it available to your project.  
Let's add **Ktor**, a popular networking library for Kotlin.
1. **Add the dependency** in `build.gradle.kts`:
    ```
    // build.gradle.kts
    dependencies {
        // Ktor client for making HTTP requests
        val ktorVersion = "2.3.10"
        implementation("io.ktor:ktor-client-cio:$ktorVersion")
    }
    ```
2. **Sync your Gradle project.** Your IDE will prompt you to do this, or you can run `gradle build` from the command line. Gradle will download Ktor.
3. **Use the library** in your code by importing its modules.
    ```
    import io.ktor.client.*
    import io.ktor.client.engine.cio.*
    import io.ktor.client.request.*
    import io.ktor.client.statement.*
    import kotlinx.coroutines.runBlocking
    
    fun main() = runBlocking {
        val client = HttpClient(CIO)
        val response: HttpResponse = client.get("https://httpbin.org/get")
        println(response.bodyAsText())
        client.close()
    }
    ```
    This example makes a network request and prints the response. `runBlocking` is used here to run the asynchronous code.
## Tasks
### Task 1
Create a function `isPrime(number: Int): Boolean` that takes an integer and returns `true` if it is a prime number, and `false` otherwise.
### Task 2
Write a recursive function `decimalToBinary(n: Int): String` that takes a positive integer as input and returns its binary representation as a string.
### Task 3
Write a function that takes a number and another function as parameters, and applies the given function to the number.