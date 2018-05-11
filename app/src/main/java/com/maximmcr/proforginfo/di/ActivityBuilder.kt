package com.maximmcr.proforginfo.di

import com.maximmcr.proforginfo.ui.groups.GroupsActivity
import com.maximmcr.proforginfo.ui.groups.GroupsModule
import com.maximmcr.proforginfo.ui.login.LoginActivity
import com.maximmcr.proforginfo.ui.login.LoginModule
import dagger.Module
import dagger.android.ContributesAndroidInjector
import javax.inject.Scope

@Module
abstract class ActivityBuilder {

    @ActivityScoped
    @ContributesAndroidInjector(modules = [GroupsModule::class])
    abstract fun bindGroupsActivity(): GroupsActivity

    @ActivityScoped
    @ContributesAndroidInjector(modules = [LoginModule::class])
    abstract fun bindLoginActivity(): LoginActivity

}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ActivityScoped