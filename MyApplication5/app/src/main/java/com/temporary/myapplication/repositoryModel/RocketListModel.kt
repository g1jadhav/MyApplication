package com.temporary.myapplication.repositoryModel

import com.temporary.myapplication.interfaces.HandleRepositoryResponse
import com.temporary.myapplication.network.ApiInterface
import com.temporary.myapplication.pojoModel.RocketListResponseModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class RocketListModel(
    val handleRepositoryResponse: HandleRepositoryResponse,
    private val mApiInterface: ApiInterface
) {

    private var disposable: Disposable? = null

    fun getRocketListResponse() {
        disposable = mApiInterface.getRocketList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object :
                DisposableSingleObserver<Response<List<RocketListResponseModel.Main>>>() {
                override fun onSuccess(response: Response<List<RocketListResponseModel.Main>>) {
                    disposable?.dispose()
                    handleRepositoryResponse.success(response)
                }

                override fun onError(e: Throwable) {
                    disposable?.dispose()
                    handleRepositoryResponse.error(e)
                }
            })
    }
}