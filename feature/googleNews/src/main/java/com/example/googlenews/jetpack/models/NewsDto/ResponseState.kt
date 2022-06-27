package com.example.googlenews.jetpack.models.NewsDto

sealed class ResponseState<T>(val data:T?=null,val errorMsg:String?=null) {
class Success<T>(data: T?):ResponseState<T>(data)
class Error<T>(msg: String?):ResponseState<T>(errorMsg = msg)
class Loading<T>():ResponseState<T>()
class Empty<T>():ResponseState<T>()

}