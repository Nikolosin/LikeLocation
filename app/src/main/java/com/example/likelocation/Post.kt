package com.example.likelocation

data class Post (
    val data: String,
    val name: String,
    val text: String,
    var itemLike: Int,
    var itemComm: Int,
    var itemShare: Int,
    var activeLike: Boolean = false,
    var activeComm: Boolean = false,
    var activeShare: Boolean = false,
    var address: String = "",
    var gps: Pair<Double, Double> = Pair(0.0, 0.0)

    ){
}