package com.example.myapplication.dsa.search

fun ternarySearch(data: IntArray, value: Int, l: Int, r: Int): Int {
    return if (r >= l) {
        val mid1 = l + (r - l) / 3
        val mid2 = r - (r - l) / 3
        if (data[mid1] == value) {
            return mid1
        } else if (data[mid2] == value) {
            return mid2
        } else {
            if (value < data[mid1]) {
                ternarySearch(data, value, l, mid1 - 1)
            } else if (value > data[mid2]) {
                ternarySearch(data, value, mid2 + 1, r)
            } else {
                ternarySearch(data, value, mid1 + 1, mid2 - 1)
            }
        }
    } else {
        -1
    }
}

fun main() {
    val data = intArrayOf(-12, 2, 10, 15, 20, 22)
    val position = ternarySearch(data, 15, 0, data.lastIndex)
    println("Position $position")
}

