package com.maxx.nordvaest.data.local

data class NavigationItem(var id: Int, var title: String, var menuIcon: Int)

data class HomeItem(var icon : Int, var showIcon : Boolean, var showStarFlag : Boolean, var position : Int)

data class Issue(var title : String, var upVote : String, var downVote : String, var time :String)