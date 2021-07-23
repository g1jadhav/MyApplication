package com.temporary.myapplication.di.component

import com.temporary.myapplication.di.module.RetrofitModule
import com.temporary.myapplication.viewModel.RocketListVMFactory
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RetrofitModule::class])
interface RetrofitComponent {
    fun getRocketListVMFactory(): RocketListVMFactory
}