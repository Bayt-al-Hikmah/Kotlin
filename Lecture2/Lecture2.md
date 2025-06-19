## Objectives
- Working with Strings, Arrays, and Maps
- Understanding Null Safety and Handling Nulls
- Using Comparison and Logical Operators
- Implementing Conditional Statements
- Working with Loops
## Working with Strings, Arrays, and Maps
### Strings
We use strings to store text values like names, messages, or any kind of written data. Strings are created by wrapping text in double quotes (`"`):
```
val name = "Alice"
val greeting = "Hello, world!"
```
Kotlin strings are powerful and come with a rich set of built-in properties and functions to manipulate text easily.
#### Accessing Characters in a String
In Kotlin, we can access individual characters in a string using indexed access, just like with an array. The index starts at `0`.  
We can also use the `.first()` and `.last()` functions to get the first and last characters.
```
val word = "Kotlin"

println(word[0]) // Output: K
println(word[3]) // Output: l

if (word.isNotEmpty()) {
    println("First character: ${word.first()}") // Output: K
    println("Last character: ${word.last()}") // Output: n
} else {
    println("The string is empty")
}
```
#### Accessing Substrings
We can extract a part of a string using functions like `substring()`, `take()`, and `drop()`.  
**Using `substring()` with a range:**
```
val word = "Programming"
val sub = word.substring(0, 4) // From index 0 up to (but not including) 4
println(sub) // Output: Prog
```
**Using `take()` and `takeLast()`:**
```
val word = "Programming"

val prefixFive = word.take(5)
println(prefixFive) // Prints "Progr" (first 5 characters)

val suffixFour = word.takeLast(4)
println(suffixFour) // Prints "ming" (last 4 characters)
```
#### String Properties and Functions

##### `.length`
Returns the number of characters in the string:
```
val name = "Alice"
println(name.length) // Output: 5
```
##### `.uppercase()` and `.lowercase()`
Convert a string to all uppercase or lowercase:
```
println("hello".uppercase()) // Output: HELLO
println("WORLD".lowercase()) // Output: world
```
##### `.contains()`
Checks if a string contains a certain substring:
```
val sentence = "Kotlin is fun"
println(sentence.contains("fun"))    // Output: true
println(sentence.contains("boring")) // Output: false
```
##### String Concatenation (`+`) and Templates
Adds two strings together:
```
val greeting = "Hello, " + "Alice"
println(greeting) // Output: Hello, Alice
```
We can also use string templates, which are highly recommended for readability and performance. Use the `$` symbol to embed expressions inside a string.
```
val name = "Bob"
println("Hello, $name") // Output: Hello, Bob
println("Next year, $name will be ${25 + 1} years old.") // Output: Next year, Bob will be 26 years old.
```
##### `.replace()`
Replaces parts of a string:
```
val text = "I like Java"
println(text.replace("Java", "Kotlin")) // Output: I like Kotlin
```
##### `.trim()`
Removes whitespace from the beginning and end of a string:
```
val input = "   hello   "
println(input.trim()) // Prints "hello"
```
##### `.split()`
Splits a string into a list of substrings, based on a delimiter:
```
val words = "apple,banana,orange"
val parts = words.split(',')
print(parts) // Output: [apple, banana, orange]
```
### Lists
In Kotlin, a `List` is an ordered collection of items. Lists are incredibly common and flexible. The key feature of a list is that its size can change dynamically if it is a mutable list.  
There are two main types of lists:
- `List`: An **immutable** list. Once created, we cannot add, remove, or change its elements.
- `MutableList`: A **mutable** list, which allows us to add, remove, and update elements after it has been created.

For most day-to-day programming tasks, lists (especially `MutableList`) are more commonly used than arrays due to their flexibility.
```
// An immutable list (read-only)
val immutableColors: List<String> = listOf("red", "green", "blue")

// A mutable list (can be changed)
val mutableColors: MutableList<String> = mutableListOf("red", "green", "blue")

// Lists can hold items of mixed types if you specify the type as `Any`
val mixed: List<Any> = listOf("hello", 42, true)
```
#### Accessing Elements in a List
We access list elements using **index numbers**, starting from `0`.
```
val colors = listOf("red", "green", "blue")
println(colors[0]) // Prints "red"

// Using the .get() function
println(colors.get(1)) // Prints "green"
```
If we try to access an index that doesn’t exist, Kotlin will throw an `IndexOutOfBoundsException`.
#### Accessing Sublists (Slices)
We can extract a portion of a list using `slice()`:
```
val numbers = listOf(10, 20, 30, 40, 50)
val subList = numbers.slice(1..3)
println(subList) // Prints [20, 30, 40]
```
#### List Functions
##### `.size` or `.count()`
Returns the number of elements:
```
val colors = listOf("red", "green", "blue")
println(colors.size) // Prints 3
```
##### `.add()` (For `MutableList` only)
Adds an element. We can add to the end or at a specific index.
```
val colors = mutableListOf("red", "green", "blue")
colors.add("yellow")        // Adds "yellow" to the end
colors.add(0, "black") // Inserts "black" at the beginning
println(colors) // Prints [black, red, green, blue, yellow]
```
##### `.removeAt()` (For `MutableList` only)
Removes the element at the given index.
```
val colors = mutableListOf("red", "green", "blue")
val firstColor = colors.removeAt(0)
println(firstColor) // Prints "red"
println(colors)     // Prints [green, blue]
```
##### `.removeLast()` (For `MutableList` only)
Removes and returns the last element.
```
val colors = mutableListOf("red", "green", "blue")
val lastColor = colors.removeLast()
println(lastColor) // Prints "blue"
println(colors)    // Prints [red, green]
```
##### `.removeAll()` (For `MutableList` only)
Removes all occurrences that satisfy a condition.
```
val arr = mutableListOf(1, 2, 3, 2, 4)
arr.removeAll { it == 2 } // `it` refers to the current element
println(arr) // Prints [1, 3, 4]
```
##### `.clear()` (For `MutableList` only)
Empties the list.
```
val arr = mutableListOf(1, 2, 3, 4)
arr.clear()
println(arr) // Prints []
```
##### `.contains()`
Checks if the list contains a value:
```
val colors = listOf("red", "green", "blue")
println(colors.contains("green")) // Prints true
```
##### `.reversed()`
Returns a new list with the order reversed:
```
val colors = listOf("red", "green", "blue")
val reversed = colors.reversed()
println(reversed) // Prints [blue, green, red]
```
##### `.sort()` / `.sorted()`
Sorts the list. `.sort()` modifies a `MutableList` in place, while `.sorted()` returns a new, sorted list and can be used on any list.
```
// Sorting a mutable list in-place
val numbers = mutableListOf(3, 1, 5, 2)
numbers.sort()
println(numbers) // Prints [1, 2, 3, 5]

// Getting a new, sorted list from any list
val otherNumbers = listOf(3, 1, 5, 2)
val sortedNumbers = otherNumbers.sorted()
println(sortedNumbers) // Prints [1, 2, 3, 5]
```
##### `.joinToString()`
Combines list elements into a single string:
```
val colors = listOf("red", "green", "blue")
println(colors.joinToString(separator = ", ")) // Prints "red, green, blue"
```
### Arrays
In Kotlin, an `Array` is an ordered collection of items with a fixed size. Once an array is created, we cannot change its size (i.e., we cannot add or remove elements). We can, however, change the value of an element at a specific index.  
Arrays are useful when we know exactly how many items we need to store and for performance-critical code, especially with primitive types.  
There are two kinds of arrays:
- `Array<T>`: For objects of a specific type (e.g., `Array<String>`).
- Primitive Type Arrays: Specialized, high-performance arrays for primitive types like `IntArray`, `DoubleArray`, `BooleanArray`, etc.
```
// An array of strings
val colors: Array<String> = arrayOf("red", "green", "blue")

// A specialized array for integers
val numbers: IntArray = intArrayOf(1, 2, 3, 4, 5)
```
#### Accessing and Modifying Elements
We access and modify array elements using index notation.
```
val colors = arrayOf("red", "green", "blue")

// Accessing elements
println(colors[0]) // Prints "red"

// Modifying an element
colors[0] = "purple"
println(colors[0]) // Prints "purple"
```
We can also use the `.get()` and `.set()` functions:
```
val names = arrayOf("Alice", "Bob")
println(names.get(1)) // Prints "Bob"

names.set(1, "Charlie")
println(names[1])     // Prints "Charlie"
```
Remember, doing this is not possible because arrays have a fixed size:
```
// colors.add("yellow") // COMPILE ERROR! No such function for Array
```
#### Array Properties and Functions
##### `.size`
Returns the number of elements in the array.
```
val colors = arrayOf("red", "green", "blue")
println(colors.size) // Prints 3
```
##### `.sort()`
Sorts the array in-place.
```
val numbers = arrayOf(5, 3, 8, 1)
numbers.sort()
println(numbers.joinToString()) // Prints "1, 3, 5, 8"
```
##### `.sortedArray()`
Returns a new, sorted array, leaving the original unchanged.
```
val numbers = intArrayOf(5, 3, 8, 1)
val sortedNumbers = numbers.sortedArray()
println(numbers.joinToString())       // Prints "5, 3, 8, 1"
println(sortedNumbers.joinToString()) // Prints "1, 3, 5, 8"
```
##### `.contains()`
Checks if the array contains a specific value.
```
val numbers = arrayOf(5, 3, 8, 1)
println(numbers.contains(8)) // Prints true
```
##### `.sliceArray()`
Extracts a portion of an array into a new array.
```
val numbers = intArrayOf(10, 20, 30, 40, 50)
val slice = numbers.sliceArray(1..3)
```
### Sets
A set is an unordered collection of **unique** values of a single type. Sets are useful when we want to ensure no duplicates are stored.
```
val colors: Set<String> = setOf("red", "green", "blue")
val numbers: Set<Int> = setOf(1, 2, 3, 4, 5)
```
#### Adding and Removing Elements
We must use a `MutableSet` to modify a set after creation. Use `.add()` to add an element (duplicates are ignored) and `.remove()` to remove an element.
```
val colors = mutableSetOf("red", "green", "blue")
colors.add("yellow")
println(colors) // Output: [red, green, blue, yellow] (order is not guaranteed)

colors.add("red") // Already exists, set remains unchanged
println(colors) // Output: [red, green, blue, yellow]

colors.remove("green")
println(colors) // Output: [red, blue, yellow]
```
#### Set Methods and Operations
Sets support properties like `.size`, `.contains()`, and `.isEmpty`. They also support mathematical operations:
##### Union
Combines two sets.
```
val a = setOf(1, 2, 3)
val b = setOf(3, 4, 5)
println(a.union(b)) // Output: [1, 2, 3, 4, 5]
```
##### Intersection
Finds common elements between two sets.
```
val a = setOf(1, 2, 3)
val b = setOf(3, 4, 5)
println(a.intersect(b)) // Output: [3]
```
##### Subtract
Finds elements in one set that are not in the other.
```
val a = setOf(1, 2, 3)
val b = setOf(3, 4, 5)
println(a.subtract(b)) // Output: [1, 2]
```
### Maps
We use maps (known as dictionaries in other languages) to store **key-value pairs**. Each unique key maps to a specific value. Maps are excellent for representing objects with properties, settings, or data that needs quick lookup by a unique identifier.  
Maps are written using `mapOf()` with key-value pairs created using the `to` infix function.
```
// Immutable Map
val person: Map<String, Any> = mapOf(
    "name" to "Alice",
    "age" to 30
)

// Mutable Map
val mutablePerson = mutableMapOf(
    "name" to "Bob",
    "age" to 25
)
```
#### Accessing Map Values
To access a value, use the corresponding key within square brackets `[]`.
```
val person: Map<String, Any> = mapOf("name" to "Alice", "age" to 30)

val name = person["name"] // Type is Any?
println("Name = $name")   // Prints "Name = Alice"
```
In Kotlin, when we look up a value in a map, the result is **nullable** (e.g., `String?`). That’s because the key we use might not exist. We will discuss null safety in detail in the next section. For now, we can use the Elvis operator `?:` to provide a default value.
```
val person: Map<String, Any> = mapOf("name" to "Alice", "age" to 30)

println(person["age"] ?: 0)         // Prints 30
println(person["email"] ?: "No email") // Prints "No email"
```
#### Adding or Updating Entries
We can add new key-value pairs or update existing ones in a `MutableMap` by using index assignment:
```
val person = mutableMapOf("name" to "Alice", "age" to 30)

person["email"] = "alice@example.com" // Adds new entry
person["age"] = 31                    // Updates existing entry
println(person) // Prints {name=Alice, age=31, email=alice@example.com}
```
#### Map Properties and Functions
##### `.keys` and `.values`
Get a collection of all keys or values.
```
val person = mapOf("name" to "Alice", "age" to 30)

println(person.keys)   // Prints [name, age]
println(person.values) // Prints [Alice, 30]
```
##### `.remove()`
Removes a key-value pair from a `MutableMap`:
```
val person = mutableMapOf("name" to "Alice", "age" to 30, "email" to "alice@example.com")
person.remove("email")
println(person) // Prints {name=Alice, age=30}
```
##### `.clear()`
Empties the entire `MutableMap`.
```
val person = mutableMapOf("name" to "Alice", "age" to 30)
person.clear()
println(person) // Prints {}
```
##### `.count()` or `.size`
Returns the number of key-value pairs.
```
val person = mapOf("name" to "Alice", "age" to 30)
println(person.count()) // Output: 2
```
##### `.isEmpty()`
Checks if the map is empty.
```
val person = mapOf("name" to "Alice", "age" to 30)
println(person.isEmpty()) // Output: false
```
##### `containsKey()` or `in` operator
Check if a key exists:
```
val person = mapOf("name" to "Alice", "age" to 30)
println(person.containsKey("name")) // Prints true
println("age" in person)            // A more idiomatic way, prints true
```
##### `containsValue()`
To check if a map's values contain a specific item:
```
val person = mapOf("name" to "Alice", "age" to 30)
println(person.containsValue("Alice")) // Prints true
println(person.containsValue(99))      // Prints false
```
##### Merging Maps
We can combine two maps using the `+` operator, which creates a new map. Values from the second map will overwrite values for overlapping keys.
```
val defaults = mapOf("font" to "Arial", "size" to 12)
val settings = mapOf("size" to 14, "color" to "blue")

val merged = defaults + settings
println(merged) // Prints {font=Arial, size=14, color=blue}

// For mutable maps, you can use putAll
val mutableDefaults = mutableMapOf("font" to "Arial", "size" to 12)
mutableDefaults.putAll(settings)
println(mutableDefaults) // Prints {font=Arial, size=14, color=blue}
```
## Null Safety and Handling Nulls
One of Kotlin's most important features is null safety, which is designed to eliminate the `NullPointerException` (NPE) from our code. The type system distinguishes between references that can hold `null` (nullable references) and those that cannot (non-null references).
#### Nullable (`?`) and Non-Nullable Types
By default, variables in Kotlin cannot hold `null` values. If we try to assign `null` to a regular variable, our code will not compile.
```
var name: String = "Kotlin"
// name = null // Compilation Error!
```
To allow a variable to hold `null`, we must explicitly declare it as **nullable** by appending a `?` to its type.
```
var name: String? = "Kotlin"
name = null // This is OK
```
#### Safe Calls (`?.`)
The safe call operator allows us to access properties or call functions on a nullable object. If the object is not `null`, it behaves like a normal call. If the object is `null`, the expression evaluates to `null` without throwing an error.
```
val name: String? = null
println(name?.length) // Output: null

val anotherName: String? = "Alice"
println(anotherName?.uppercase()) // Output: ALICE
```
#### The Elvis Operator (`?:`)
Often, we want to provide a default value when a nullable reference is `null`. The Elvis operator `?:` is perfect for this. It returns the expression on its left if it's not `null`, otherwise it returns the expression on its right.
```
val name: String? = null
val length = name?.length ?: 0 // If name is null, use 0 as the length
println(length) // Output: 0

val displayName = name ?: "Guest"
println(displayName) // Output: Guest
```
#### The Not-Null Assertion Operator (`!!`)
The `!!` operator converts any nullable type to its non-nullable version. If the value is `null` at runtime, it will throw a `NullPointerException`. This operator should be used with extreme caution, only when we are 100% certain that the value will not be `null`.   
A common place to see this is with `readLine()`, which reads input from the console and returns a `String?`.
```
print("Enter your name: ")
// val name = readLine() // name is of type String?

// UNSAFE: This will crash if the user doesn't enter anything.
// val nameLength = readLine()!!.length 
// println("Your name has $nameLength characters.")

// SAFE way with Elvis operator:
val name = readLine() ?: "" // Provide an empty string as a default
println("Your name has ${name.length} characters.")
```
#### Safe Calls with `let` 
To execute a block of code only if a value is not `null`, we can use a safe call with the `let` scope function. 
```
val name: String? = "Alice"

// The code inside the let block only executes if name is not null.
// Inside the block, `it` refers to the non-null value.
name?.let {
    println("The name is $it")
    println("It has ${it.length} characters.")
}

val nullName: String? = null
nullName?.let {
    // This code will NOT execute.
    println("This will not be printed.")
}
```
## Comparison and Logical Operators
### Comparison Operators
Comparison operators compare two values and return a `Boolean` result (`true` or `false`).
- `>` (Greater than): `5 > 4` is `true`.
- `<` (Less than): `4 < 5` is `true`.
- `==` (Equal to): `4 == 4` is `true`.
- `!=` (Not equal to): `1 != 0` is `true`.
- `>=` (Greater than or equal to): `2 >= 2` is `true`.
- `<=` (Less than or equal to): `2 <= 3` is `true`.
```
println(5 > 4)         // Prints true
println("hi" == "hello") // Prints false
println(2 >= 2)        // Prints true
```
### Logical Operators
Logical operators combine multiple boolean expressions.
#### `||` (OR)
Returns `true` if at least one of the conditions is true.
```
println(true || false)  // Prints true
println(false || false) // Prints false
```
#### `&&` (AND)
Returns `true` only if all conditions are true.
```
println(true && true)   // Prints true
println(true && false)  // Prints false
```
#### `!` (NOT)
Reverses the logical state.

```
println(!true)  // Prints false
println(!false) // Prints true
```

#### Truth Table

| A     | B     | A && B | A \|\| B | !A    |
| ----- | ----- | ------ | -------- | ----- |
| true  | true  | true   | true     | false |
| true  | false | false  | true     | false |
| false | true  | false  | true     | true  |
| false | false | false  | false    | true  |
## Conditional Statements
Conditional statements are fundamental programming constructs that enable our code to make decisions and execute different actions based on specific conditions. They control program flow by evaluating whether certain criteria are met, allowing for dynamic behavior that responds to different situations, inputs, or values.
### `if`, `else`, and `else if` Statement
#### Single Condition with `if`
When we need to execute code only when a condition is true:
```
if (condition) {
    // Code executes only if condition evaluates to true
}
```
**Example:**
```
val age = 18

if (age >= 18) {
    println("You are an adult.")
}
// Output when age is 18: "You are an adult."
```
#### Alternative Path with `if-else`
When we want to handle both true and false cases differently:
```
if (condition) {
    // Code for true case
} else {
    // Code for false case
}
```
**Example:**
```
val isRaining = false

if (isRaining) {
    println("Bring an umbrella.")
} else {
    println("No umbrella needed today.")
}
// Output: "No umbrella needed today."
```
#### Multiple Conditions with `else if`
When we need to check several possible conditions in sequence:

```
if (condition1) {
    // First case
} else if (condition2) {
    // Second case
} else {
    // Default case
}
```
**Example:**
```
val score = 85

if (score >= 90) {
    println("Excellent performance")
} else if (score >= 80) {
    println("Good job")
} else {
    println("Room for improvement")
}
// Output: "Good job"

```
### The `when` Statement
`when` is Kotlin’s powerful and flexible alternative to the `switch` statement. It allows us to execute different actions based on the value of a variable.  
To use it, place the variable in parentheses after the `when` keyword, then define the possible values inside curly braces `{}`, each followed by `->` and the corresponding expression to execute.
```
val day = "Monday"

when (day) {
    "Monday" -> println("Weekday 1")
    "Tuesday" -> println("Weekday 2")
    "Saturday", "Sunday" -> println("Weekend!")
    else -> println("Unknown day")
}
// Output: "Weekday 1"
```
`when` must be exhaustive when used as an expression (all possible paths must be covered). The `else` branch handles any values not explicitly matched.  
We can also use `when` without an argument to check arbitrary boolean expressions.
```
val temperature = 25
when {
    temperature > 30 -> println("It's hot!")
    temperature in 20..30 -> println("It's warm.") // `in` checks a range
    else -> println("It's cold.")
}
// Output: It's warm.
```
## Loops
Loops help us repeat code multiple times without rewriting it.
### `while` Loop
A `while` loop keeps running as long as a condition is true.
```
var count = 1
while (count <= 5) {
    println("Count is $count")
    count++
}
```
**Output:**
```
Count is 1
Count is 2
Count is 3
Count is 4
Count is 5
```
### `do-while` Loop
A `do-while` loop executes its block of code at least once, and then continues to repeat as long as its condition is true.
```
var count = 1
do {
    println("Count is $count")
    count++
} while (count <= 5)
```
This produces the same output as the `while` loop example.
### `for` Loop
A `for` loop iterates over anything that provides an iterator, such as a range, an array, a list, or a map.
```
// Looping through a range
for (i in 1..3) { // '..' creates an inclusive range
    println("Iteration $i")
}
// Output:
// Iteration 1
// Iteration 2
// Iteration 3
```
Looping through a list:
```
val fruits = listOf("apple", "banana", "cherry")
for (fruit in fruits) {
    println(fruit)
}
// Output:
// apple
// banana
// cherry
```
And with maps:
```
val person = mapOf("name" to "Alice", "age" to 30)
for ((key, value) in person) {
    println("$key: $value")
}
// Output:
// name: Alice
// age: 30
```
#### Ranges in Kotlin

- `1..5` creates a **closed range** including both start and end values: `1, 2, 3, 4, 5`.
- `1 until 5` creates a **half-open range** that includes the start but excludes the end value: `1, 2, 3, 4`.
- `5 downTo 1` creates a range that counts down: `5, 4, 3, 2, 1`.
- `1..10 step 2` creates a range with a step: `1, 3, 5, 7, 9`.
### `break` and `continue` Keywords
- `break`: Stops the loop entirely.
- `continue`: Skips the rest of the current iteration and jumps to the next one.

**Example with `continue`:**
```
for (i in 1..5) {
    if (i == 3) {
        continue // Skips printing 3
    }
    println(i)
}
// Output:
// 1
// 2
// 4
// 5
```
**Example with `break`:**
```
for (i in 1..5) {
    if (i == 4) {
        break // Stops the loop when i is 4
    }
    println(i)
}
// Output:
// 1
// 2
// 3
```

## Tasks

### Task 1
- Create a string variable named `word` with the value `"Developer"`.
    - Print the first and last characters using indexing.
    - Convert the word to uppercase and print it.
- Create a string variable named `phrase` with the text `"I love Python"`.
    - Replace "Python" with "Kotlin" and print the result.
- Create a string variable named `sentence` with the text `"Learning Kotlin is fun"`.
    - Print `true` if "fun" is in the sentence.
    - Print the number of characters in the sentence.
### Task 2
- Create a mutable list of colors: `["red", "green", "blue"]`.
    - Print the first and last elements.
    - Add "yellow" to the end of the list.
    - Insert "black" at the beginning.
    - Remove and print the last element.
    - Check if "green" exists in the list and print the result.
- Sort the list `[5, 3, 8, 1]` in ascending order and print it.
### Task 3
- Create a mutable map representing a person with a name, age, and city.
    - Print the name using the key.
    - Add a new key-value pair for "occupation".
    - Print all the keys of the map.
    - Check if the map contains the key `age` and print the result.
    - Merge it with another map: `{ "hobbies" to listOf("reading", "coding") }`.
    - Delete the city information from the map.
### Task 4
- Write an `if-else` expression that checks if a number is even or odd and prints the result.
- Write a program that recommends clothing based on temperature using a `when` statement:
    - Above 30°C: "Wear shorts"
    - 20-30°C: "T-shirt weather"
    - Below 20°C: "Bring a jacket"

### Task 5
- Create a program that asks the user to enter a number and calculates its factorial using a `while` loop. (Handle the input as a nullable string).
- Ask the user for two numbers and display all prime numbers between them (inclusive) using a `for` loop.
- Ask the user to enter a number `n`, and use a `do-while` loop to print all even numbers from 0 up to `n`.