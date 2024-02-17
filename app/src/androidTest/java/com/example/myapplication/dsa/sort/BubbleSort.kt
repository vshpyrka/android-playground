package com.example.myapplication.dsa.sort

private fun bubbleSort(array: IntArray) {
    val size = array.size

    for (i in 0 until size - 1) {

        // exclude already sorted item (i)
        for (j in 0 until size - 1 - i) {

            // If left > right, replaces
            if (array[j] > array[j + 1]) {

                val temp = array[j]
                array[j] = array[j + 1]
                array[j + 1] = temp
            }
        }
    }
}

fun main() {
    val data = intArrayOf(-2, 45, 0, 11, -9)
    bubbleSort(data)
    println(data.contentToString())
}
