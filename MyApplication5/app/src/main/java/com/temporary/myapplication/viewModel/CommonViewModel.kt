package com.temporary.myapplication.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class CommonViewModel: ViewModel() {
    val mIsLoading: MutableLiveData<Boolean> = MutableLiveData()
    val throwableMutableLiveData: MutableLiveData<Throwable> = MutableLiveData()
}