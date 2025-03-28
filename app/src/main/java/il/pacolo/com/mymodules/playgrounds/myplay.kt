package il.pacolo.com.mymodules.playgrounds

import android.icu.text.ListFormatter.Width


fun main() {

    val c = Shape.Circle(5.0)
    println(calculateArea(c)) // Output: 78.54 (approximately)

    val r = Shape.Rectangle(4.0, 6.0)
    println(calculateArea(r)) // Output: 24.0

    print(evenNumbers(listOf(3, 7, 2, 4, 6)))
}


// certification problems
//Task: Write a function that takes a nullable String and returns the length of the string. If the input is null, return -1.


fun getLength(input:String?): Int = input?.length ?: -1

//Task: Create a sealed class Shape with two subclasses:
//
//Circle(radius: Double)
//Rectangle(length: Double, width: Double)
//Write a function that takes a Shape and calculates its area.


sealed class Shape() {
    data class Circle( val radius:Double): Shape()
    data class Rectangle(val lenght:Double,val width: Double): Shape()
}

fun calculateArea(shape:Shape): Double {
    return when(shape) {
        is Shape.Circle ->  Math.PI * shape.radius * shape.radius
        is Shape.Rectangle ->  shape.lenght * shape.width
    }
}


//Task: Given a list of integers, write a function that returns only the even numbers, squared, and sorted in descending order.
fun evenNumbers(list:List<Int>): List<Int>  =  list.filter { it % 2 == 0 }.map{it*it}.sortedDescending()






