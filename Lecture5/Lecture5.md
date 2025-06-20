## Objectives
- Kotlin Advanced Concepts
- Data Classes and Scope Functions
- Working with Files
## Advanced Concepts
### Generics:
Imagine we need to create a box. First, we create a `BoxForApples`. Then you need one for books, so we create a `BoxForBooks`. This is repetitive and inefficient. What if we could create a single, versatile `Box` blueprint that could hold any type of item?  
That's exactly what Generics allows us to do. It's a way to create classes, interfaces, and functions that can work with any data type. We use angle brackets `<T>` to denote a generic "Type" parameter. Think of `T` as a placeholder that will be replaced by a real type (like `String`, `Int`, or our own `Book` class) when it's used.
```
// 1. A Generic Class
// This Box class can hold an item of any type 'T'.
class Box<T>(var content: T) {

    fun peek(): T {
        return content
    }

    fun replace(newContent: T) {
        this.content = newContent
        println("The box now contains: $newContent")
    }
}

// 2. A Generic Function
// This function can create a Box for any kind of item.
fun <T> createBoxFor(item: T): Box<T> {
    return Box(item)
}

fun main() {
    // We create a Box that specifically holds a String.
    // Kotlin infers that T is String.
    val stringBox = Box("Hello, Kotlin!")
    println(stringBox.peek()) // Output: Hello, Kotlin!
    stringBox.replace("Generics are cool!") // Output: The box now contains: Generics are cool!

    // Now let's create a Box for an Int using our generic function.
    // Here, T becomes Int.
    val intBox = createBoxFor(123)
    println(intBox.peek()) // Output: 123
}
```
Generics allow us to write flexible and reusable code, avoiding duplication while maintaining type safety. The compiler ensures that our `stringBox` can only ever hold strings, preventing us from accidentally putting an integer in it.
### Delegation:
Inheritance is great, but it's not always the best solution. It creates a rigid "is-a" relationship. What if we want our class to have a certain ability, but we don't want to inherit from a whole class just for that? What if you want to "borrow" the functionality from another object?  
This is where delegation comes in. It's a design pattern where an object, instead of performing a task itself, delegates that task to another helper object. It's a "has-a" relationship that promotes flexibility. 
In Kotlin, this is a first-class language feature supported by the `by` keyword. It's like saying, "For this part of my job, I'm going to have this other object handle it completely."  
Let's imagine we have an interface for counting. Then we create a class that is an expert at counting. Finally, we create a `BookCollection` that needs to count books, but we don't want to rewrite the counting logic. We can delegate the counting ability to our expert counter.
```
// 1. The "ability" we want to delegate.
interface Counter {
    fun count(): Int
}

// 2. The "expert" helper class that implements the ability.
class BasicCounter(private val items: List<Any>) : Counter {
    override fun count(): Int {
        return items.size
    }
}

class BookCollection(private val books: List<String>) : Counter by BasicCounter(books) {
    // We don't need to write an implementation for count() here!
    // The 'by BasicCounter(books)' part handles it for us.
    
    fun firstBook(): String? {
        return books.firstOrNull()
    }
}

fun main() {
    val myBooks = BookCollection(listOf("The Hobbit", "1984", "Dune"))
    
    // We can call count() directly on myBooks.
    // Internally, it's calling the count() method of the BasicCounter instance.
    println("Number of books: ${myBooks.count()}") // Output: Number of books: 3
    
    println("First book: ${myBooks.firstBook()}") // Output: First book: The Hobbit
}
```
Delegation is a powerful alternative to inheritance for code reuse, leading to more flexible and less coupled designs.
### Coroutines:
Imagine a chef in a kitchen. If they were a synchronous program, they would put a pot of water on the stove and stare at it until it boils before doing anything else. The whole kitchen would be "blocked."  
An asynchronous chef, however, puts the water on to boil and then immediately starts chopping vegetables. When the water boils, they are notified and can switch back to that task. This is the core idea of asynchronous programming: performing long-running tasks without blocking the main program, keeping everything responsive.  
This is crucial for modern apps. You don't want your app to freeze while it's downloading a file or waiting for a database query.  
Kotlin's solution to this is Coroutines. They are a way to write asynchronous code that looks and reads just like regular, sequential code. They are like very lightweight threads, and they make complex async operations simple.
- **`suspend`**: This keyword marks a function as one that can be paused and resumed later. A `suspend` function can call other `suspend` functions without blocking the main thread.
- **`launch`**: This is a coroutine builder. It starts a new coroutine that runs in the background—a "fire and forget" job.
- **`async`**: This is another coroutine builder that starts a new coroutine and allows you to get a result back in the future (via a `Deferred` value).

Let's see a conceptual example:
```
import kotlinx.coroutines.*

// This is a suspend function. It can be paused.
// Imagine this takes 2 seconds to complete.
suspend fun fetchUserData(userId: String): String {
    println("Fetching data for $userId...")
    delay(2000) // 'delay' is a special non-blocking suspend function.
    return "User data for $userId"
}

fun main() = runBlocking { // runBlocking starts a coroutine for our main function
    println("Main program started.")

    // 'launch' starts a background task.
    // The main program continues immediately.
    launch {
        val userData = fetchUserData("user123")
        println(userData)
    }

    println("Main program continues while user data is being fetched...")
    println("Main program finished.")
    
    // The program will wait here until the launched coroutine is done.
}
// --- Expected Output Order ---
// Main program started.
// Main program continues while user data is being fetched...
// Main program finished.
// Fetching data for user12asdf...  (This appears after a brief moment)
// User data for user123        (This appears after 2 seconds)
```
Coroutines simplify asynchronous code, helping you build fast, responsive applications without falling into the complexities of traditional multithreading.
## Data Classes and Scope Functions
### Data Classes
In Kotlin, some features are designed specifically to make your code more concise, expressive, and fluent. Data classes and scope functions are prime examples. They work together beautifully to manage data and operations on it.  
A **`data class`** is a class designed purely to hold data. When you declare a class with the `data` keyword, the compiler automatically generates useful methods for you under the hood:
- `equals()`: for structural equality comparison (`==`).
- `hashCode()`: required when you override `equals`.
- `toString()`: for a readable string representation.
- `copy()`: to create a copy of an instance, optionally changing some properties.
- `componentN()`: functions that correspond to the properties in their order of declaration.


```
data class Song(val title: String, val artist: String, var playCount: Int = 0)
```
This one line gives you a full-featured, data-holding class.  
### Scope Functions
**Scope functions** are functions that execute a block of code within the context of an object. They provide a temporary "scope" where you can access the object without its name. This leads to cleaner, more readable code. There are five main scope functions: `let`, `run`, `with`, `apply`, and `also`.
They differ in two key ways:
1. **How you refer to the context object**: Either as `this` or as `it`.
2. **What they return**: Either the _context object_ itself or the _result of the lambda expression_.

|Function|Context Object|Return Value|Use Case|
|:--|:--|:--|:--|
|`let`|`it`|Lambda result|Working with nullable objects; chaining|
|`run`|`this`|Lambda result|Object initialization and computing a result|
|`with`|`this`|Lambda result|Grouping calls on a non-nullable object|
|`apply`|`this`|**Context object**|Object configuration ("apply these settings")|
|`also`|`it`|**Context object**|Additional actions/side effects ("also do this")|
#### `let` (Context: `it`, Returns: Lambda Result)
`let` is perfect for executing code on a nullable object only if it is not `null`. Inside the `let` block, the object is referred to as `it`.
```
fun main() {
    val book: Book? = Book("1984", "George Orwell")
    
    // The let block only executes if 'book' is not null.
    val summary = book?.let {
        println("Accessing the book...")
        it.isAvailable = false // 'it' refers to the book object
        it.summary() // Call the summary method
        "The book has been checked out." // This is the return value
    }
    
    println(summary)
    
    val nullBook: Book? = null
    val nullSummary = nullBook?.let {
        // This code will NOT run
        "This won't be returned"
    }
    println(nullSummary) // Output: null
}
```
#### `run` (Context: `this`, Returns: Lambda Result)
`run` is similar to `let`, but it refers to the context object as `this`. This makes it feel like you are calling methods from inside the class itself. It's great for when you need to initialize an object and then compute some result from it.
```
fun main() {
    val book = Book("Dune", "Frank Herbert")

    val bookReport = book.run {
        // Here, 'this' refers to the book object.
        // We can access properties and methods directly.
        isAvailable = false
        val status = if (isAvailable) "Available" else "Checked out"
        
        // The last expression is the return value.
        "Report for '$title' by $author. Status: $status"
    }
    
    println(bookReport)
    // Output: Report for 'Dune' by Frank Herbert. Status: Checked out
}
```
#### `with` (Context: `this`, Returns: Lambda Result)
`with` is very similar to `run`, but it's a regular function, not an extension. You pass the object as an argument. It’s useful for grouping a sequence of calls on the same object without having to repeat its name.
```
fun main() {
    val book = Book("The Lord of the Rings", "J.R.R. Tolkien")
    
    // 'with' is not a great choice for nullable objects as it would require a separate null check.
    val result = with(book) {
        println("Configuring '$title'...")
        isAvailable = true
        // Return a custom string
        "The book by $author is now available."
    }
    
    println(result)
}
```
#### `apply` (Context: `this`, Returns: Context Object)
`apply` is the ultimate object configuration tool. You access the object as `this`, and it conveniently returns the object itself. This makes it perfect for chaining calls, like when using the Builder pattern. The main purpose is to "apply" a configuration to an object.
```
fun main() {
    // Create and configure a book in one single, fluent expression.
    val newBook = Book("Brave New World", "Aldous Huxley").apply {
        // 'this' is the Book instance.
        isAvailable = false
        // No need for a return statement; 'apply' returns the book itself.
    }
    
    println("Created a new book:")
    newBook.summary() // We can immediately use the configured object.
    println("Is it available? ${newBook.isAvailable}")
}
```
#### `also` (Context: `it`, Returns: Context Object)
`also` is for performing side-effects or additional actions that don't affect the object itself, like logging or debugging. You access the object as `it`, and it returns the object itself, just like `apply`. The name says it all: "do this to the object, and _also_ do this other thing."
```
fun main() {
    val books = mutableListOf<Book>()

    // Chaining example: create a book, log it, and then add it to a list.
    val bookToAdd = Book("Fahrenheit 451", "Ray Bradbury")
        .also { 
            // 'it' refers to the book.
            println("Preparing to add book: ${it.title}") 
        }
        .apply {
            // Use apply to change the object's properties
            isAvailable = true
        }

    books.add(bookToAdd)
    println("Book added successfully.")
}
```
## Working With Files
Reading from and writing to files is a fundamental task in many applications. Kotlin makes this process safe and concise, especially by providing functions that automatically manage resources (like closing the file when you're done).  
Let's create a simple log file for our book management system.
### Writing to a File
The easiest way to write text to a file is using the `writeText()` extension function on the `File` class. If the file doesn't exist, it will be created. If it does exist, its content will be overwritten. To add to a file, you can use `appendText()`.
```
import java.io.File

fun main() {
    val logFile = File("book_log.txt")

    // writeText overwrites the file completely.
    logFile.writeText("--- Book Activity Log ---\n")
    
    // appendText adds to the end of the file.
    val book1 = Book("The Hobbit", "J.R.R. Tolkien")
    logFile.appendText("Checked out: '${book1.title}' on ${java.time.LocalDate.now()}\n")
    
    val book2 = Book("1984", "George Orwell")
    logFile.appendText("Returned: '${book2.title}' on ${java.time.LocalDate.now()}\n")
    
    println("Log file has been written to 'book_log.txt'")
}
```
### Reading from a File
Kotlin provides excellent, safe ways to read files. The `useLines` function is one of the best. It opens the file, gives you a sequence of all the lines, and most importantly automatically closes the file for you when it's done, even if an error occurs. This prevents resource leaks.
```
import java.io.File

fun main() {
    val logFile = File("book_log.txt")

    // First, let's make sure the file exists to avoid an error.
    if (logFile.exists()) {
        println("--- Reading Log File ---")
        
        // 'useLines' handles opening and closing the file for us.
        // It's efficient because it reads the lines lazily.
        logFile.useLines { lines ->
            lines.forEach { line ->
                println(line)
            }
        }
        
    } else {
        println("Log file not found.")
    }
}
```
## Final Project
Using everything we’ve learned during this course, create a Kotlin project that solves a problem you face in your daily life.  
Think about tasks you often repeat, information you want to organize, or anything you wish could be automated then build a solution using Kotlin!