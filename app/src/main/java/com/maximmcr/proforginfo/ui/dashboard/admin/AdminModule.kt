package com.maximmcr.proforginfo.ui.dashboard.admin

import com.maximmcr.proforginfo.di.FragmentScoped
import dagger.Binds
import dagger.Module

@Module
abstract class AdminModule {

    @FragmentScoped
    @Binds
    abstract fun bindPresenter(p: Presenter<Contract.View>): Contract.Presenter<Contract.View>

}