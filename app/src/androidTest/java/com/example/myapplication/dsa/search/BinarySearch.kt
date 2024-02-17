package com.example.myapplication.dsa.search

fun binarySearch(data: IntArray, i: Int): Int {
    var l = 0
    var r = data.lastIndex
    while (l <= r) {
        val m = l + (r - l) / 2
        if (i == data[m]) {
            return m
        }
        if (data[m] < i) {
            l = m + 1
        } else if (data[m] > i) {
            r = m - 1
        }
    }
    return -1
}

fun main() {
    val data = intArrayOf(-12, 2, 10, 15, 20, 22)
    val position = binarySearch(data, 15)
    println("Position $position")
}
