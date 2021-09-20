package com.sairabanu.taskapp.model

data class UserListModel(
    val `data`: DataModel,
    val message: Any,
    val status: Boolean
)

data class DataModel(
    val has_more: Boolean,
    val users: ArrayList<UserModel>
)

data class UserModel(
    val image: String,
    val items: List<String>,
    val name: String
)

