package com.example.myapplication.dsa.sort

fun selectionSort(arr: IntArray) {
    val size = arr.size
    for (i in 0 until size -1) {
        var minIdx = i
        for (j in i + 1 until size) {
            if (arr[j] < arr[minIdx]) {
                minIdx = j
            }
        }
        val temp = arr[i]
        arr[i] = arr[minIdx]
        arr[minIdx] = temp
    }
}

fun main() {
    val data = intArrayOf(20, -12, 10, 15, 22, 2)
    selectionSort(data)
    println(data.contentToString())
}
