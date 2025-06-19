fun main() {
    // 1. Create a mutable map representing a person
    val person = mutableMapOf(
        "name" to "John",
        "age" to 30,
        "city" to "New York"
    )
    

    println("Name: ${person["name"]}")
    person["occupation"] = "Software Developer"
    println("Keys: ${person.keys}")
    println("Contains 'age' key: ${"age" in person}")
    val hobbiesMap = mapOf("hobbies" to listOf("reading", "coding"))
    person.putAll(hobbiesMap)
    person.remove("city")
    println("Final person map: $person")
}