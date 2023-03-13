package cinema

import java.io.IOException
import java.util.*

fun main() {


    println("Enter the number of rows:")
    var number_of_rows = readln().toInt()
    println("Enter the number of seats in each row:")
    var number_of_seats_each_row = readln().toInt()
    var list_cine =
        MutableList(number_of_rows) { MutableList(number_of_seats_each_row) { 'S' } }
    println("1. Show the seats\n2. Buy a ticket\n3. Statistics\n0. Exit\n")
    var income=0
    var seats = number_of_rows * number_of_seats_each_row
    var totalIncome=if (seats < 60) 10*seats else if (seats > 60 && number_of_rows % 2 == 0)
            (number_of_rows/2)*number_of_seats_each_row*8+((number_of_rows/2)*number_of_seats_each_row*10)
    else  ((number_of_rows/2)*number_of_seats_each_row*10)+(((number_of_rows/2)+1)*number_of_seats_each_row*8)
    fun printCinema() {

        println("Cinema:")
        println("  " + (1..number_of_seats_each_row).toList().joinToString(" "))
        var j = 1
        for (i in list_cine.indices) {
            println("$j ${list_cine[i].joinToString(" ")}")
            j++
        }
        println()
        println("1. Show the seats\n2. Buy a ticket\n3. Statistics\n0. Exit\n")

    }

    fun buySeat() {

        var wronganswer=true
        while (wronganswer){
            println("Enter a row number:")
            var row_number = readln().toInt()
            println("Enter a seat number in that row:")
            var number_of_seat = readln().toInt()
            var seats = number_of_rows * number_of_seats_each_row
        try {
            if (row_number > number_of_rows || number_of_seat > number_of_seats_each_row) {
                throw IOException("Wrong input!")
            } else if (list_cine[row_number - 1][number_of_seat - 1] == 'B') {
                throw Exception("That ticket has already been purchased!")
            }
            else {
                println(
                    "Ticket price:${
                        if (seats < 60) "$10" else if (seats > 60 && number_of_rows % 2 == 0 && number_of_rows / 2 <= row_number) "$8"
                        else if (seats > 60 && number_of_rows % 2 != 0 && row_number > number_of_rows / 2) "$8" else "$10"
                    }"
                )
                list_cine[row_number - 1][number_of_seat - 1] = 'B'
                income += if (seats < 60) 10 else if (seats > 60 && number_of_rows % 2 == 0 && number_of_rows / 2 <= row_number) 8
                else if (seats > 60 && number_of_rows % 2 != 0 && row_number > number_of_rows / 2) 8 else 10
                wronganswer=false
            }
        } catch (e: IOException) {
            println(e.message)

        } catch (e: Exception) {
            println(e.message)
        }
    }
        println("1. Show the seats\n2. Buy a ticket\n3. Statistics\n0. Exit\n")

    }

    fun statistics(){

        val purchased= list_cine.flatten().count { it == 'B' }
        val percentage=((purchased.toDouble()/list_cine.sumBy { it.size })*100)
        val formatPercentage = String.format("%.2f",percentage)

        println("Number of purchased tickets: $purchased")
        println("Percentage: $formatPercentage%")
        println("Current income: $$income")
        println("Total income: $$totalIncome")


        println("1. Show the seats\n2. Buy a ticket\n3. Statistics\n0. Exit\n")
    }
    var continues = true
    while (continues) {
        when (readln().toInt()) {
            1 -> printCinema()
            2 -> buySeat()
            3 -> statistics()
            0 -> continues = false
        }

    }

}