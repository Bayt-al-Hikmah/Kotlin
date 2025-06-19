## Objectives:

- Introduction to Programming Languages
- Introduction to Kotlin
- User Input and Output
## Introduction to Programming Languages
Programming languages are formal languages that provide a structured way for humans to give instructions to a computer. Instead of writing instructions directly in binary code (sequences of 1s and 0s), which is tedious and error-prone for complex tasks, we use programming languages with a more human-readable syntax. This abstraction simplifies software development, making code easier to write, read, and maintain.  
However, a computer's processor can only execute instructions in its native machine code. To bridge this gap, we use a special program called a **compiler** or an **interpreter**. The compiler checks the source code for syntax errors and then translates the entire program into machine code that the CPU can execute directly. An interpreter, on the other hand, translates and executes the code line by line. Kotlin primarily uses a compiler to achieve high performance.
## Introduction to Kotlin
### Introduction
Kotlin is a modern, statically typed programming language developed by JetBrains. It is designed to be concise, safe, and fully interoperable with Java and its ecosystem. While it's the official language for Android development, Kotlin is a versatile, multiplatform language used for building applications for server-side, web front-end, and even desktop and iOS (via Kotlin Multiplatform).  
It is known for its pragmatic design, which focuses on eliminating boilerplate code and preventing common programming errors, such as null pointer exceptions. Its clear and expressive syntax makes it an excellent choice for both beginners and experienced developers.  
Kotlin source code files have the `.kt` extension.
### The Kotlin Toolchain
To write, compile, and run Kotlin applications, we need the Kotlin toolchain. This includes the Kotlin compiler (`kotlinc`), the standard library, and build automation tools like Gradle or Maven, which help manage project dependencies and streamline the build process.  
The easiest way to get started is by installing an Integrated Development Environment (IDE) like IntelliJ IDEA (also from JetBrains) or Android Studio for Android development. These IDEs come bundled with everything we need.  
For command-line development, we can install the standalone Kotlin compiler. Once installed, we can verify the setup by opening our terminal or command prompt and running:
```
kotlinc -version
```
### Running our First Program
In Kotlin, the execution of a program starts at the `main` function. This is the designated entry point for all Kotlin applications.  
Here’s the classic "Hello, world!" program in Kotlin:
```
fun main() {
    println("Hello, world!")
}
```
In this code, `fun main()` defines the main function, and `println()` is a standard library function that prints the string "Hello, world!" to the console, followed by a new line.  
To run this program, save the code in a file named `Hello.kt`. Then, open our terminal, navigate to the directory containing the file, and use the following commands to compile and run it:
1. **Compile the code using `kotlinc`:** This creates a `.jar` (Java Archive) file.
    ```
    kotlinc Hello.kt -include-runtime -d Hello.jar
    ```
2. **Run the compiled program using the `kotlin` command or `java`:**    
    ```
    kotlin Hello.jar
    // Or using the java command
    // java -jar Hello.jar
    ```
### Variables and Constants
In Kotlin, we use variables and constants to store and manage data in our programs.  
- Use `val` (for value) to declare a read-only or immutable variable (a constant). Its value cannot be changed once assigned.
- Use `var` (for variable) to declare a mutable variable. Its value can be reassigned.

```
val city = "Algiers"         // Immutable
var currentTemperature = 25.5  // Mutable
```
Kotlin is a statically typed language with powerful type inference. The compiler can automatically deduce the type of a variable from the value assigned to it. This provides type safety without the need for explicit type declarations. However, we can declare the type explicitly if needed:
```
val country: String = "Algeria"
var population: Int = 43000000
```
Once a variable's type is inferred or declared, we cannot assign a value of a different type to it.  
#### Null Safety and Nullable Types
A common pitfall in many programming languages is the "null pointer exception" (or `NullPointerException` in Java), which occurs when we try to access a member of a null reference. Kotlin's type system is designed to eliminate this problem.  
By default, variables in Kotlin cannot hold `null` values. If we try to assign `null` to a non-nullable variable, our code will fail to compile.  
To allow a variable to hold `null`, we must explicitly declare it as a nullable type by appending a `?` to the type name.
```
var score: Int? // This can hold an Int or null
score = 88
score = null    // This is valid
```
This feature forces us to handle the possibility of `null` safely, preventing runtime crashes and making our code more robust.
### Data Types
#### Numbers
Kotlin provides a set of built-in types for numbers. These are similar to Java's primitive types but are objects in Kotlin.
- **Integer types:** `Byte`, `Short`, `Int`, `Long` (for whole numbers of different sizes).
- **Floating-point types:** `Float`, `Double` (for numbers with a fractional part).
```
val age: Int = 30
val price: Double = 19.99
```
Standard arithmetic operators are available:
- `+` (addition)
- `-` (subtraction)
- `*` (multiplication)
- `/` (division)
- `%` (remainder/modulo)
```
val total = 10 + 5
val half = 20.0 / 2.0 // Division of doubles results in a double
```
#### Character
The `Char` type in Kotlin represents a single character. A `Char` literal is specified with single quotes.
```
val firstLetter: Char = 'A'
val anEmoji: Char = '😊' // Kotlin supports Unicode
val punctuation: Char = '!'
```
#### Strings
A `String` is a sequence of characters, like `"Hello"`. Strings are immutable; once created, their content cannot be changed.
```
val name = "Alice"
val greeting = "Hello, world!"
```
Kotlin offers a powerful feature called string templates (or interpolation) to embed expressions directly into a string. We use the `$` prefix for variables and `${}` for more complex expressions.
```
val firstName = "Alice"
val lastName = "Joly"
val greet = "Hello, ${firstName} ${lastName}!" // "Hello, Alice Joly!"
```
The `String` type also provides many useful properties and functions, like `.length`, `.uppercase()`, `.lowercase()`, and `.contains()`.
#### Booleans
The `Boolean` type represents logical values: `true` or `false`. It is fundamental for conditional logic and control flow.
```
val isLoggedIn = true
val hasPermission = false
```
Booleans are used with `if` statements, `when` expressions, loops, and logical operators (`&&` for AND, `||` for OR, `!` for NOT).
#### Collections
Kotlin provides a rich standard library for grouping and managing collections of data.
##### Lists
A `List` is an ordered collection of items of the same type. Elements are accessed by their zero-based index. Kotlin distinguishes between read-only and mutable lists.
- `List`: Read-only. We cannot add or remove elements after creation.
- `MutableList`: Can be modified (add, remove, or change elements).

```
// Read-only list
val colors: List<String> = listOf("red", "green", "blue")
println(colors[0]) // "red"

// Mutable list
val mutableColors: MutableList<String> = mutableListOf("red", "green")
mutableColors.add("blue") // Now ["red", "green", "blue"]
```
##### Maps
A `Map` stores key-value pairs. Keys are unique, and each key maps to exactly one value.
- `Map`: Read-only.
- `MutableMap`: Can be modified.
```
// Read-only map
val scores: Map<String, Int> = mapOf(
    "Ali" to 90,
    "Sara" to 85
)
println(scores["Ali"]) // 90

// Mutable map
val mutableScores = mutableMapOf("Ali" to 90)
mutableScores["Sara"] = 85
```
##### Sets
A `Set` is an unordered collection of unique elements. It is useful when we want to ensure that an item appears only once.
- `Set`: Read-only.
- `MutableSet`: Can be modified.
```
val uniqueNumbers: MutableSet<Int> = mutableSetOf(1, 2, 3)
uniqueNumbers.add(2) // The set remains {1, 2, 3} because elements are unique
uniqueNumbers.add(4) // The set becomes {1, 2, 3, 4}
```
#### Enums and Sealed Classes
Enumerations, or `enum class`, provide a way to define a type that has a restricted set of possible values. This makes code clearer and safer.
##### Basic Enums
```
enum class CompassDirection {
    NORTH, SOUTH, EAST, WEST
}

var directionToHead = CompassDirection.WEST
directionToHead = CompassDirection.EAST
```
##### Enums with Properties
Kotlin enums can have constructors and properties, allowing us to associate data with each enum constant.
```
enum class Planet(val order: Int) {
    MERCURY(1),
    VENUS(2),
    EARTH(3),
    MARS(4); // Semicolon is required if there are members

    fun description() = "Planet ${this.name} is number $order from the sun."
}

val earthPosition = Planet.EARTH.order // earthPosition is 3
println(Planet.EARTH.description())  // "Planet EARTH is number 3 from the sun."
```
#### The `Any` Type
In Kotlin, `Any` is the root of the class hierarchy. Every Kotlin class has `Any` as a superclass. It is similar to Swift's `Any` or Java's `Object`. Using `Any` should be done carefully, as we lose type safety.
```
var something: Any = "Hello"
something = 42
```
To use a variable of type `Any`, we typically need to check its type and perform a **smart cast** or an explicit cast.

```
val a = 4.5
val b: Any = 5.5

// Smart casting inside an 'if' check
if (b is Double) {
    val c = a + b // b is automatically cast to Double here
    println(c)    // 10.0
}

// Safe casting using 'as?'
val c = a + (b as? Double ?: 0.0) // Safely cast, or use 0.0 as a default
println(c) // 10.0
```

### Type Conversion
Kotlin does not perform automatic type conversions between different numeric types (e.g., `Int` to `Double`). We must perform conversions explicitly using functions like `toDouble()`, `toInt()`, etc.
#### Converting to Numbers
We can convert a string to a number. Since the conversion can fail (e.g., `"hello".toInt()`), Kotlin provides functions that return `null` on failure.
```
val numberString = "42"
val number = numberString.toInt() // 42

val invalidString = "hello"
val invalidNumber = invalidString.toIntOrNull() // Returns null

val priceString = "3.14"
val price = priceString.toDoubleOrNull() // Returns 3.14
```
#### Converting to String
We can convert almost any value to a `String` using the `.toString()` method or by using it in a string template.
```
val message = 100.toString()          // "100"
val status = true.toString()          // "true"
val greeting = "The value is $price"
```
### Comments
Comments are parts of the code that are ignored by the compiler. They are used to add explanations and notes for human readers.
#### Single-line Comments
Use `//` for comments that fit on a single line.
```
// This is a single-line comment
println("Hello, world!") // This prints a greeting
```
#### Multi-line Comments
Use `/* ... */` for comments that span multiple lines.
```
/*
This is a multi-line comment.
It is useful for explaining
more complex logic or documenting a function.
*/
```

## User Input and Output
Interacting with the user is a fundamental part of most applications. Kotlin provides straightforward functions for console output and input.
### Output
The `print()` and `println()` functions are used to display output on the console.
- `println()` prints its arguments and adds a newline character at the end.
- `print()` prints its arguments without adding a newline.
```
println("Hello!") // Prints "Hello!" and moves to the next line
print("Hi")      // Prints "Hi"
print(" there!") // Prints " there!" on the same line. Output: Hi there!
```
#### Escape Sequences
Escape sequences are special character combinations starting with a backslash (`\`) used inside strings.

| Escape Sequence | Meaning      | Example                  |
| :-------------- | :----------- | :----------------------- |
| `\n`            | Newline      | `"Line 1\nLine 2"`       |
| `\t`            | Tab          | `"Name:\tAlice"`         |
| `\\`            | Backslash    | `"C:\\Users\\Alice"`     |
| `\"`            | Double quote | `"She said, \"Hello!\""` |

```
println("She said, \"Welcome!\"\nLet’s start learning Kotlin.")
```
**Output:**
```
She said, "Welcome!"
Let’s start learning Kotlin.
```
### Input
To read a line of input from the user in a console application, we use the `readln()` function, introduced in Kotlin 1.6. It's an improvement over the older `readLine()` because it signals the end of input by throwing an exception instead of returning `null`, which simplifies input handling.
```
print("What's your name? ")
val name = readln()
println("Nice to meet you, $name!")
```
The `readln()` function reads the entire line of text entered by the user and returns it as a `String`.
#### Getting Numeric Input
The `readln()` function always returns a `String`. To use this input as a number, we must convert it. It's best practice to use functions like `toIntOrNull()` or `toDoubleOrNull()` to handle cases where the user enters invalid text.  
This approach gracefully handles bad input and prevents the program from crashing.
```
println("Enter your age:")
val ageInput = readln()
val age = ageInput.toIntOrNull() // Returns Int or null

if (age != null) {
    println("In 5 years, you’ll be ${age + 5} years old!")
} else {
    println("That's not a valid number.")
}
```
## Tasks
### Task 1
Write a program that reads the radius of a circle from the user and then displays its surface area. 
### Task 2
Develop a temperature converter that converts from Celsius to Fahrenheit. 