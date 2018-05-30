package com.maximmcr.proforginfo.di

import com.maximmcr.proforginfo.ui.dashboard.DashboardActivity
import com.maximmcr.proforginfo.ui.dashboard.DashboardModule
import com.maximmcr.proforginfo.ui.groups.GroupsActivity
import com.maximmcr.proforginfo.ui.groups.GroupsModule
import com.maximmcr.proforginfo.ui.login.LoginActivity
import com.maximmcr.proforginfo.ui.login.LoginModule
import com.maximmcr.proforginfo.ui.signin.SigninActivity
import com.maximmcr.proforginfo.ui.signin.SigninModule
import dagger.Module
import dagger.android.ContributesAndroidInjector
import javax.inject.Scope

@Module
abstract class ActivityBuilder {

    @ActivityScoped
    @ContributesAndroidInjector(modules = [LoginModule::class])
    abstract fun bindLoginActivity(): LoginActivity

    @ActivityScoped
    @ContributesAndroidInjector(modules = [SigninModule::class])
    abstract fun bindSigninActivity(): SigninActivity

    @ActivityScoped
    @ContributesAndroidInjector(modules = [GroupsModule::class])
    abstract fun bindGroupsActivity(): GroupsActivity

    @ActivityScoped
    @ContributesAndroidInjector(modules = [DashboardModule::class])
    abstract fun bindDashActivity(): DashboardActivity

}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ActivityScoped

@Scope
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CONSTRUCTOR, AnnotationTarget.FUNCTION, AnnotationTarget.CLASS)
annotation class FragmentScoped