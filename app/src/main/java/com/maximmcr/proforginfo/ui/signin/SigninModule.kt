package com.maximmcr.proforginfo.ui.signin

import com.maximmcr.proforginfo.di.ActivityScoped
import dagger.Binds
import dagger.Module

@Module
abstract class SigninModule {
    @ActivityScoped
    @Binds
    abstract fun bindPresenter(p: Presenter<Contract.View>): Contract.Presenter<Contract.View>
}