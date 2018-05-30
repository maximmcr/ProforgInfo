package com.maximmcr.proforginfo.ui.dashboard.user

import com.maximmcr.proforginfo.di.FragmentScoped
import dagger.Binds
import dagger.Module

@Module
abstract class UserModule {

    @FragmentScoped
    @Binds
    abstract fun bindPresenter(p: Presenter<Contract.View>): Contract.Presenter<Contract.View>

}