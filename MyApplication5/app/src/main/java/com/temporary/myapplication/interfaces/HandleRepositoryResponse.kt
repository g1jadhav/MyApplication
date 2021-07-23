package com.temporary.myapplication.interfaces

import retrofit2.Response

interface HandleRepositoryResponse {
    fun success(response: Response<*>)
    fun error(e: Throwable)
}