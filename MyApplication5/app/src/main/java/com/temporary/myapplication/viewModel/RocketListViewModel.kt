package com.temporary.myapplication.viewModel

import android.content.Context
import android.os.Looper
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.temporary.myapplication.customClasses.Constant
import com.temporary.myapplication.interfaces.HandleLocalDataBaseResponse
import com.temporary.myapplication.interfaces.HandleRepositoryResponse
import com.temporary.myapplication.network.ApiInterface
import com.temporary.myapplication.pojoModel.RocketListResponseModel
import com.temporary.myapplication.repositoryModel.LocalDataListModel
import com.temporary.myapplication.repositoryModel.RocketListModel
import com.temporary.myapplication.roomdatabase.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class RocketListViewModel constructor(private val mApiInterface: ApiInterface) : CommonViewModel(),
    HandleRepositoryResponse, HandleLocalDataBaseResponse {

    private lateinit var mContext: Context

    val mRocketList: MutableLiveData<List<RocketListResponseModel.Main>> = MutableLiveData()
    private lateinit var mRocketListModel: RocketListModel


    private lateinit var mLocalDataListModel: LocalDataListModel

    fun getRocketList(
        context: Context
    ) {
        this.mContext = context
        mIsLoading.postValue(true)

        mRocketListModel = RocketListModel(this, mApiInterface)
        mRocketListModel.getRocketListResponse()
    }

    fun getSavedRocketList(
        context: Context
    ) {
        this.mContext = context
        mIsLoading.postValue(true)

        mLocalDataListModel = LocalDataListModel(context, this)
        mLocalDataListModel.getLocalData()
    }

    override fun success(response: Response<*>) {
        mIsLoading.postValue(false)
        if (response.isSuccessful) {

            val list = response.body() as List<RocketListResponseModel.Main>
            mRocketList.postValue(list)

            viewModelScope.launch(Dispatchers.IO) {

                val images = ArrayList<Images>()
                val id = ArrayList<Id>()
                val names = ArrayList<Name>()
                val country = ArrayList<Country>()
                val engineCount = ArrayList<EngineCount>()
                val activeStatus = ArrayList<ActiveStatus>()
                val costPerLaunch = ArrayList<CostPerLaunch>()
                val successRate = ArrayList<SuccessRate>()
                val desc = ArrayList<Desc>()
                val wikiLink = ArrayList<WikiLink>()
                val height = ArrayList<Height>()
                val diameter = ArrayList<Diameter>()

                for (i in list.indices) {

                    id.add(Id(list[i].id.toString()))
                    names.add(Name(list[i].name.toString()))
                    country.add(Country(list[i].country.toString()))
                    engineCount.add(EngineCount(list[i].stages.toString()))
                    activeStatus.add(ActiveStatus(list[i].active))
                    costPerLaunch.add(CostPerLaunch(list[i].costPerLaunch.toString()))
                    successRate.add(SuccessRate(list[i].successRatePct.toString()))
                    desc.add(Desc(list[i].description.toString()))
                    wikiLink.add(WikiLink(list[i].wikipedia.toString()))
                    height.add(Height(list[i].height!!.feet.toString()))
                    diameter.add(Diameter(list[i].diameter!!.feet.toString()))

                    images.add(Images(list[i].flickrImages))

                    val rocketListDataEntity = RocketListDataEntity(
                        0, id, names, country, engineCount,
                        activeStatus, costPerLaunch, successRate,
                        desc, wikiLink, height, diameter, images
                    )

                    val listDao = AppDataBase.getInstance(mContext).mRocketListDao()
                    listDao.insertRocketList(rocketListDataEntity)

                    Constant.dataIsStored(mContext, true)
                }
            }

        } else {
            throwableMutableLiveData.postValue(NullPointerException())
        }
    }

    override fun error(e: Throwable) {
        android.os.Handler(Looper.getMainLooper()).post {
            mIsLoading.postValue(false)
            throwableMutableLiveData.postValue(e)
        }
    }

    override fun setData(response: List<RocketListResponseModel.Main>) {
        mIsLoading.postValue(false)
        mRocketList.postValue(response)
    }
}

