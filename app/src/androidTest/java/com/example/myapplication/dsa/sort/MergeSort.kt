package com.example.myapplication.dsa.sort

private fun merge(data: IntArray, p: Int, q: Int, r: Int) {
    val n1 = q - p + 1
    val n2 = r - q
    val arr1 = IntArray(n1)
    val arr2 = IntArray(n2)
    for (i in 0 until n1) {
        arr1[i] = data[p + i]
    }
    for (j in 0 until n2) {
        arr2[j] = data[q + 1 + j]
    }
    var i = 0
    var j = 0
    var k = p
    while (i < n1 && j < n2) {
        if (arr1[i] <= arr2[j]) {
            data[k] = arr1[i]
            i++
        } else {
            data[k] = arr2[j]
            j++
        }
        k++
    }
    while (i < n1) {
        data[k] = arr1[i]
        i++
        k++
    }
    while (j < n2) {
        data[k] = arr2[j]
        j++
        k++
    }
}

fun mergeSort(data: IntArray, l: Int, r: Int) {
    if (l < r) {
        val m = (l + r) / 2
        mergeSort(data, l, m)
        mergeSort(data, m + 1, r)
        merge(data, l, m, r)
    }
}

fun main() {
    val data = intArrayOf(20, -12, 10, 15, 22, 2)
    mergeSort(data, 0, data.lastIndex)
    println(data.contentToString())
}
