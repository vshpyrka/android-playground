package com.example.myapplication.dsa.tree

data class Node(
    val value: Int,
    val left: Node? = null,
    val right: Node? = null,
)

fun inOrder(node: Node) {
    node.left?.run { inOrder(this) }
    println(node.value)
    node.right?.run { inOrder(this) }
}

fun preOrder(node: Node) {
    println(node.value)
    node.left?.run { inOrder(this) }
    node.right?.run { inOrder(this) }
}

fun postOrder(node: Node) {
    node.left?.run { inOrder(this) }
    node.right?.run { inOrder(this) }
    println(node.value)
}

fun main() {

    val root = Node(
        value = 1,
        left = Node(
            value = 12,
            left = Node(5),
            right = Node(6)
        ),
        right = Node(
            value = 9
        )
    )


    println("InOrder")
    inOrder(root)
    println("PreOrder")
    preOrder(root)
    println("PostOrder")
    postOrder(root)
}
