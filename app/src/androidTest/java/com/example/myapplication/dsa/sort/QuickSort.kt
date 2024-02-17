package com.example.myapplication.dsa.sort

// method to find the partition position
fun partition(array: IntArray, low: Int, high: Int): Int {

    // choose the rightmost element as pivot
    val pivot = array[high]

    // pointer for greater element
    var i = low - 1

    // traverse through all elements
    // compare each element with pivot
    for (j in low until high) {
        if (array[j] <= pivot) {

            // if element smaller than pivot is found
            // swap it with the greater element pointed by i
            i++

            // swapping element at i with element at j
            val temp = array[i]
            array[i] = array[j]
            array[j] = temp
        }
    }

    // swap the pivot element with the greater element specified by i
    val temp = array[i + 1]
    array[i + 1] = array[high]
    array[high] = temp

    // return the position from where partition is done
    return i + 1
}

fun quickSort(array: IntArray, low: Int, high: Int) {
    if (low < high) {

        // find pivot element such that
        // elements smaller than pivot are on the left
        // elements greater than pivot are on the right
        val pi = partition(array, low, high)

        // recursive call on the left of `pivot
        quickSort(array, low, pi - 1)

        // recursive call on the right of pivot
        quickSort(array, pi + 1, high)
    }
}

fun main() {
    val data = intArrayOf(-5, 20, -12, 10, 15, -22, 2)
    quickSort(data, 0, data.lastIndex)
    println(data.contentToString())
}
