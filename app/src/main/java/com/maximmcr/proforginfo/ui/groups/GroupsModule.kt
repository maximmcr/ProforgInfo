package com.maximmcr.proforginfo.ui.groups

import com.maximmcr.proforginfo.di.ActivityScoped
import dagger.Binds
import dagger.Module

@Module
abstract class GroupsModule {

    @ActivityScoped
    @Binds
    abstract fun bindPresenter(p: Presenter<Contract.View>): Contract.Presenter<Contract.View>

}