package com.maximmcr.proforginfo.ui.dashboard

import com.maximmcr.proforginfo.di.ActivityScoped
import com.maximmcr.proforginfo.di.FragmentScoped
import com.maximmcr.proforginfo.ui.dashboard.admin.AdminFragment
import com.maximmcr.proforginfo.ui.dashboard.admin.AdminModule
import com.maximmcr.proforginfo.ui.dashboard.user.UserFragment
import com.maximmcr.proforginfo.ui.dashboard.user.UserModule
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class DashboardModule {

    @ActivityScoped
    @Binds
    abstract fun bindPresenter(p: Presenter<Contract.View>): Contract.Presenter<Contract.View>

    @FragmentScoped
    @ContributesAndroidInjector(modules = [UserModule::class])
    abstract fun bindUserFragment(): UserFragment

    @FragmentScoped
    @ContributesAndroidInjector(modules = [AdminModule::class])
    abstract fun bindAdminFragment(): AdminFragment

}