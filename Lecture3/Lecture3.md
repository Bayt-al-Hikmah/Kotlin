## Objectives
- Working with Functions
- Working with Dependencies 
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

## Working with Dependencies 
### Introduction
So far, all our programs have been represented by single files. However, Kotlin allows us to divide a project into multiple files, defining specific functionality in each one. We can then use these functionalities by importing the functions into our main file. When compiling, Kotlin generates bytecode files for the main file as well as all other files in the project.
To organize that Kotlin use packages
### Packages
A **package** in Kotlin is a way to organize related classes, functions, and interfaces. It helps prevent naming conflicts and can control visibility across files. To define a package, we use the `package` keyword at the very top of a Kotlin file.  
Let's create a specialized package for mathematical operations. We'll create a folder named `tools` and inside it, a file named `MathHelper.kt`.
```kotlin
package tools

fun square(number: Int): Int {
   return number * number
}
```
#### Using the package
If we want to use this class in another file (outside the `tools` folder), we must import it using the `import` keyword. Kotlin uses the full package name + class name to locate it.
```kotlin
import tools.square

fun main() {
    // Using a static-like method from the package
    val result = square(5)
    println("Square is: $result")
}
```
We can also import all classes/functions from a package using a wildcard:
```
import tools.*
```
#### Sub-packages in Kotlin
Packages can be nested using subfolders to create a hierarchical structure. This is useful in larger projects. For example, we might have this folder structure:
```
src/
 └─ com/
     └─ example/
         └─ tools/
             └─ MathHelper.kt
```
In this case, the package name reflects the folder structure:
```
// File: src/com/example/tools/MathHelper.kt
package com.example.tools

class MathHelper {

    companion object {
        fun square(number: Int): Int {
            return number * number
        }
    }

    fun printMessage() {
        println("Math helper in nested package is running.")
    }
}
```
To use this class in another file outside ``com.example.tools``, we import it with the full package path:
```
import com.example.tools.MathHelper

fun main() {
    val result = MathHelper.square(7)
    println("Square is: $result")

    val helper = MathHelper()
    helper.printMessage()
}
```
The problem with this approach is that if we want to share functionality with other developers, they would need all the Kotlin files in the project. To simplify distribution, we can package all classes into a single JAR file, which is the standard JVM way to bundle multiple classes into one archived file. This allows other developers to use our functionality without having to manage individual Kotlin files.
### The Standard Unit in Kotlin: JAR Files
In Kotlin (running on the JVM), applications are packaged into **JAR files** (Java ARchives). A JAR is essentially a compressed ZIP file that contains everything needed to run the application. A typical JAR includes:
- **Compiled Code**  All `.class` files generated from your Kotlin code, organized by package.
- **Resources** Images, configuration files, scripts, and other assets.
- **Metadata** A manifest file that can specify the entry point of the application (the class containing the `main` function).
#### Creating a JAR File in Kotlin
To create a JAR file, we first compile Kotlin code into JVM bytecode and then package it into a single archive.

**Compile Kotlin source code:**
```
kotlinc src -d out
```
- `src` is the folder containing your `.kt` files.
- `-d out` specifies the destination directory for the compiled `.class` files.
**Package into a JAR:**
```
jar cf app.jar -C out .
```
- `c` create a new archive
- `f` the name of the JAR file (`app.jar`)
- `-C out .` change to the `out` directory and include all compiled files

#### Creating an Executable JAR
If our Kotlin application has a `main` function, you can make the JAR executable by adding a manifest file:
**manifest.txt**
```
Main-Class: com.example.MainKt
```
**Create executable JAR:**
```
jar cfm app.jar manifest.txt -C out .
```
- `m` includes the manifest file.
- After this, you can run your application directly:

```
java -jar app.jar
```
## Including External JARs
Kotlin projects often depend on other JAR files. You can include them during compilation using the `-cp` (classpath) flag:
```
kotlinc -cp "lib/*" -d out src/*.kt
```
- `"lib/*"` includes all JAR files in the `lib` directory.

At runtime, we can also add external libraries to the classpath:
```
java -cp "lib/*:out" com.example.MainKt
```
- `lib/*` loads all external libraries.
- `out` contains your compiled Kotlin classes.
### The Modern Problem: Dependency Hell

While JAR files solve the packaging problem, they create **dependency management issues**. If your project uses libraries that themselves have dependencies, manually downloading and linking all JARs becomes tedious and error-prone this is known as **Dependency Hell**.
**Solution:** Use a build tool like **Gradle** or **Maven**. These tools:
- Automatically download required libraries from central repositories.
- Resolve **transitive dependencies** (dependencies of your dependencies).
- Simplify compilation, packaging, and running of Kotlin applications.


In modern Kotlin development, **Gradle** is the standard tool for building projects, managing JARs, and handling dependencies efficiently.

### Gradle
Gradle is a modern build automation tool widely used in the JVM ecosystem. While historically associated with Groovy, Gradle now strongly supports the Kotlin DSL (`build.gradle.kts`). This offers excellent IDE support (autocompletion, refactoring, and type safety) compared to the dynamic Groovy DSL or Maven's XML.   
It is the standard build tool for Android and server-side Kotlin development.
#### Installing Gradle
 Gradle does not come pre-installed with the JDK. To install it, first download the **Binary-only zip** from the official [Gradle website](https://gradle.org/install/). Then extract it to a folder:
- **Windows:** `C:\Program Files\Gradle`
- **Linux/Mac:** `/opt/gradle`

Finally, add the `bin` folder to your system’s **PATH**:
- **Linux/Mac:** Add `export PATH=/opt/gradle/bin:$PATH` to `.bashrc` or `.zshrc`.
- **Windows:** Edit System Environment Variables → Path → Add `C:\Program Files\Gradle\gradle-x.x\bin`.

Verify the installation by running:
```
gradle -v
```
#### Setting The Configuration
Gradle uses a file named `build.gradle.kts` to define project configuration. This file must sit at the **root** of our project.  
Here is a typical configuration for a Kotlin application:
```
plugins {
    kotlin("jvm") version "1.9.22" // Applies the Kotlin JVM plugin
    application                    // Applies the Application plugin
}

group = "com.example"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.google.code.gson:gson:2.10.1")
    testImplementation(kotlin("test")) // Standard Kotlin testing library
}

application {
    mainClass.set("com.example.MainKt") // Points to the compiled class
}
```
Here is how the configuration works (note the parentheses and quotes, which are required in Kotlin):
- **`plugins`**:
    - `kotlin("jvm")`: Adds capabilities to compile Kotlin code.
    - `application`: Helps run the app as an executable.
- **`group` & `version`**: Identifies the project artifacts.
- **`repositories`**: Tells Gradle where to look for dependencies (`mavenCentral()` is the standard).
- **`dependencies`**:
    - `implementation(...)`: Libraries required for production code.
    - `testImplementation(...)`: Libraries needed only for testing.
- **`application`**: Configures the entry point.
    - _Note:_ If our `main` function is a top-level function in a file named `Main.kt`, the compiled class name is usually `MainKt`.
#### The Standard Directory Layout
Gradle projects follow this foldes structure:
```
my-app/
├── build.gradle.kts      (Project configuration in Kotlin DSL)
└── src/
    ├── main/
    │   ├── kotlin/       (Application source code)
    │   └── resources/    (Non-code resources)
    └── test/
        ├── kotlin/       (Test source code)
        └── resources/    (Test-specific resources)
```
- **`src/main/kotlin`** – Gradle compiles Kotlin code here.
- **`src/test/kotlin`** – Gradle compiles test scripts here.
#### Building The Project
Gradle use tasks to compile our project, we can excute those tasks as following:

**Compile**
```
gradle compileKotlin
```
This task compiles code in `src/main/kotlin` and stores `.class` files in `build/classes/kotlin/main`.

**Running Tests**
```
gradle test
```
This compiles the code and tests, then executes them using the configured test framework (e.g., JUnit or Kotlin Test).

**Packaging**
```
gradle jar
```
Packages the compiled code into a JAR file located in `build/libs`.

**Cleaning the Project**
```
gradle clean
```
Deletes the `build` folder. Combine it with packaging for a fresh build:
```
gradle clean jar
```
#### Running Executable JAR
We can run our Kotlin project directly without manually creating a JAR using:
```
gradle run
```
This requires the `application` plugin and the `mainClass` property set in `build.gradle.kts`.

If we want to generate a runnable JAR (where we can run `java -jar app.jar`), we must tell the `jar` task about our main class in the manifest. In Kotlin DSL, it looks like this:
```
tasks.jar {
    manifest {
        attributes["Main-Class"] = "com.example.MainKt"
    }
}
```
After building, run it with:
```
kotlin build/libs/my-app-1.0.0.jar
```
### The Gradle Wrapper
The Gradle Wrapper allows a project to run without a global Gradle installation, ensuring the exact correct version of Gradle is used.
#### Setting The Gradle Wrapper
Generate the wrapper files by running:

```
gradle wrapper
```
This creates:
```
gradlew         (Unix script)
gradlew.bat     (Windows script)
gradle/wrapper/ (Configuration files)
```
To initialize a new Kotlin project from scratch (which creates the wrapper automatically):
```
gradle init
```
Select Kotlin as the implementation language and Kotlin as the build script DSL when prompted.
#### Working With The Gradle Wrapper
Always use the wrapper scripts instead of the global command:

**Linux/Mac:**
```
./gradlew build
./gradlew run
```

**Windows:**
```
.\gradlew build
.\gradlew run
```
#### Installing and Using a Third-Party Library
To use an external library in Kotlin, we add it to your build.gradle.kts dependencies and then import it in our code. For example, to use Ktor for HTTP requests:

We add the dependency in build.gradle.kts:
```
dependencies {
    val ktorVersion = "2.3.10"
    implementation("io.ktor:ktor-client-cio:$ktorVersion")
}
```

Once the dependency is added, we can use the library in our Kotlin code. For example, using Ktor to make an HTTP request:
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
After building the project, Gradle will automatically download the library and include it in our project’s JAR file, making it ready to use.
## Tasks
### Task 1
Create a function `isPrime(number: Int): Boolean` that takes an integer and returns `true` if it is a prime number, and `false` otherwise.
### Task 2
Write a recursive function `decimalToBinary(n: Int): String` that takes a positive integer as input and returns its binary representation as a string.
### Task 3
Write a function that takes a number and another function as parameters, and applies the given function to the number.
