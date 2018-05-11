package com.maximmcr.proforginfo.ui.login

import com.maximmcr.proforginfo.di.ActivityScoped
import dagger.Binds
import dagger.Module

@Module
abstract class LoginModule {

    @ActivityScoped
    @Binds
    abstract fun bindLoginPresenter(p: Presenter<Contract.View>): Contract.Presenter<Contract.View>

}