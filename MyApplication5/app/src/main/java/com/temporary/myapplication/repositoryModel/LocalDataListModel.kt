package com.temporary.myapplication.repositoryModel

import android.content.Context
import com.temporary.myapplication.interfaces.HandleLocalDataBaseResponse
import com.temporary.myapplication.pojoModel.RocketListResponseModel
import com.temporary.myapplication.roomdatabase.AppDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LocalDataListModel(
    private val context: Context,
    private val handleLocalDataBaseResponse: HandleLocalDataBaseResponse
) {
    private lateinit var responseList: ArrayList<RocketListResponseModel.Main>

    fun getLocalData() {

        GlobalScope.launch(Dispatchers.IO) {

            val listDao = AppDataBase.getInstance(context).mRocketListDao()
            val list = listDao.getAllRocketList()

            responseList = ArrayList()

            for (i in list!!.indices) {

                val main = RocketListResponseModel.Main()
                val height = RocketListResponseModel.Height()
                val diameter = RocketListResponseModel.Diameter()

                main.id = list[i].id[i].id
                main.name = list[i].name[i].name
                main.country = list[i].country[i].country
                main.stages = list[i].engineCount[i].engineCount.toInt()
                main.active = list[i].activeStatus[i].activeStatus
                main.costPerLaunch = list[i].costPerLaunch[i].costPerLaunch.toInt()
                main.successRatePct = list[i].successRate[i].successRate.toInt()
                main.description = list[i].desc[i].desc
                main.wikipedia = list[i].wikiLink[i].wikiLink
                main.height = height
                main.diameter = diameter
                main.height!!.feet = list[i].height[i].height.toFloat()
                main.diameter!!.feet = list[i].diameter[i].diameter.toFloat()
                main.flickrImages = list[i].images[i].images

                responseList.add(main)
            }

            withContext(Dispatchers.Main) {
                handleLocalDataBaseResponse.setData(responseList)
            }
        }
    }
}