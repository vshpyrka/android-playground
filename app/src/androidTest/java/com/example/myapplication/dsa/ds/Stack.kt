package com.example.myapplication.dsa.ds

class Stack<T> {

    class Node<T>(
        val value: T,
        var next: Node<T>? = null
    )

    private var top: Node<T>? = null

    fun put(value: T) {
        val newNode = Node(value)
        val currentTop = top
        if (currentTop == null) {
            top = newNode
        } else {
            newNode.next = currentTop
            top = newNode
        }
    }

    fun pop(): T {
        val currentTop = top
        if (currentTop == null) {
            throw IllegalStateException()
        } else {
            val value = currentTop.value
            val next = currentTop.next
            if (next != null) {
                top = next
            }
            return value
        }
    }
}

fun main() {
    val stack = Stack<Int>()
    stack.put(5)
    stack.put(4)
    stack.pop()
    println(stack)
}
