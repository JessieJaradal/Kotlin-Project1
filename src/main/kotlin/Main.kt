data class Order(val coffee: String, var count: Int)

const val VANILLA_COFFEE = "Vanilla Coffee"
const val CHOCOLATE_COFFEE = "Chocolate Coffee"
const val CINNAMON_COFFEE = "Cinnamon Coffee"
const val PEPPERMINT_COFFEE = "Peppermint Coffee"
const val ICE_COFFEE = "Ice Coffee"

val coffeePrice = mapOf(
    VANILLA_COFFEE to 200,
    CHOCOLATE_COFFEE to 171,
    CINNAMON_COFFEE to 191,
    PEPPERMINT_COFFEE to 181,
    ICE_COFFEE to 141,
)

val orders = mutableListOf<Order>()
var totalSales = 0
var totalOrders = 0
var averageSalePerOrder = 0
var takingOrder = true

fun main() {
    display()

    while (takingOrder) {
        print("Please select your order: ")
        val userInput = readln()

        when (userInput.toInt()) {
            0 -> {
                println("")
                println("Receipt:")
                println("Orders List:")
                for (order in orders) {
                    val totalPrice = order.count * coffeePrice[order.coffee]!!
                    println("${order.coffee}(x${order.count}) - ₱$totalPrice")
                }
                println("Total:           ₱$totalSales")
                println(".............................")
                generateReport()
                takingOrder = false
            }
            in 1..5 -> {
                processOrder(userInput.toInt())
            }
            else -> println("Error! Please input the correct number in the menu!")
        }
    }
}

fun processOrder(userOrder: Int) {
    val coffee = when (userOrder) {
        1 -> "Vanilla Coffee"
        2 -> "Chocolate Coffee"
        3 -> "Cinnamon Coffee"
        4 -> "Peppermint Coffee"
        5 -> "Ice Coffee"
        else -> null
    }

    if (coffee != null) {
        var order = orders.find { it.coffee == coffee }
        if (order == null) {
            order = Order(coffee, 0)
            orders.add(order)
        }

        order.count++
        totalSales += coffeePrice[coffee]!!
        totalOrders++
        averageSalePerOrder = totalSales / totalOrders
    } else {
        println("Error! Please input the correct number in the menu!")
    }
}

fun display() {
    println("!______ Welcome to Coffee-king Caffe ______!")
    println("Coffee-king Menu:")
    coffeePrice.forEach { (coffee, price) ->
        println("${coffeePrice.keys.indexOf(coffee) + 1} - $coffee: ₱$price")
    }
    println("0 - To Finalize your order")
    println("---------------------------")
    println("Please input the number of the coffee you want to order!")
}
fun generateReport() {
    println("Sales Report:")
    println("Total Sales:  $totalSales")
    println("Total Orders: $totalOrders")
    println("Average Sale Per Order: $averageSalePerOrder")
    println("")
    println("Thank you! Please enjoy your Coffee.")
}