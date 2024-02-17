package com.example.myapplication.dsa.sort

fun heapSort(arr: IntArray) {
    val n = arr.size

    // Build max heap
    for (i in n / 2 - 1 downTo 0) {
        heapify(arr, n, i)
    }

    // Heap sort
    for (i in n - 1 downTo 0) {
        val temp = arr[0]
        arr[0] = arr[i]
        arr[i] = temp

        // Heapify root element
        heapify(arr, i, 0)
    }
}

fun heapify(arr: IntArray, n: Int, i: Int) {
    // Find largest among root, left child and right child
    var largest = i
    val l = 2 * i + 1
    val r = 2 * i + 2
    if (l < n && arr[l] > arr[largest]) largest = l
    if (r < n && arr[r] > arr[largest]) largest = r

    // Swap and continue heapifying if root is not largest
    if (largest != i) {
        val swap = arr[i]
        arr[i] = arr[largest]
        arr[largest] = swap
        heapify(arr, n, largest)
    }
}

fun main() {
    val arr = intArrayOf(1, 12, 9, 5, 6, 10)
    heapSort(arr)
    println("Sorted array is")
    println(arr.contentToString())
}
