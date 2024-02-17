package com.example.myapplication.dsa.search

fun sentinelSearch(data: IntArray, value: Int): Int {
    val last = data[data.lastIndex]
    var i = 0
    while (data[i] != value) {
        i++
    }
    return if (i < data.lastIndex || last == value) {
        i
    } else {
        -1
    }
}

fun main() {
    val data = intArrayOf(-12, 2, 10, 15, 20, 22)
    val position = sentinelSearch(data, 15)
    println("Position $position")
}
