package com.example.myapplication.dsa.sort

fun insertionSort(arr: IntArray) {

    val size = arr.size
    for (step in 1 until size) {
        val key = arr[step]
        var j = step - 1

        while (j >= 0 && key < arr[j]) {
            arr[j + 1] = arr[j]
            --j
        }
        arr[j + 1] = key
    }
}

fun main() {
    val data = intArrayOf(9, 5, 1, 4, 3)
    insertionSort(data)
    println(data.contentToString())
}
