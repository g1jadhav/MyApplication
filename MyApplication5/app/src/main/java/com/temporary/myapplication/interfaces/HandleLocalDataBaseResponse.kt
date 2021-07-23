package com.temporary.myapplication.interfaces

import com.temporary.myapplication.pojoModel.RocketListResponseModel

interface HandleLocalDataBaseResponse {
    fun setData(response: List<RocketListResponseModel.Main>)
}