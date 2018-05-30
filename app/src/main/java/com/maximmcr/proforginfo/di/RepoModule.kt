package com.maximmcr.proforginfo.di

import android.content.Context
import com.firebase.ui.auth.AuthUI
import com.google.android.gms.flags.impl.SharedPreferencesFactory.getSharedPreferences
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.maximmcr.proforginfo.data.PreferenceRepository
import com.maximmcr.proforginfo.data.RemoteGroupsRepo
import com.maximmcr.proforginfo.data.RemoteUsersRepo
import com.maximmcr.proforginfo.data.foreign.RemoteGroupsRepoImpl
import com.maximmcr.proforginfo.data.foreign.RemoteUsersRepoImpl
import com.maximmcr.proforginfo.data.local.model.MyObjectBox
import com.maximmcr.proforginfo.data.settings.PreferenceRepoImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class RepoModule {

    @Module
    companion object {
        @JvmStatic
        @Provides
        @Singleton
        fun provideDb(context: Context) = MyObjectBox.builder().androidContext(context).build()

        @JvmStatic
        @Provides
        @Singleton
        fun provideFirestore() = FirebaseFirestore.getInstance()

        @JvmStatic
        @Provides
        @Singleton
        fun provideAuthUI() = AuthUI.getInstance()

        @JvmStatic
        @Provides
        @Singleton
        fun provideAuthProviders() = listOf(
                AuthUI.IdpConfig.EmailBuilder().build(),
                AuthUI.IdpConfig.GoogleBuilder().build()
        )

        @JvmStatic
        @Provides
        @Singleton
        fun provideFirebaseAuth() = FirebaseAuth.getInstance()

        @JvmStatic
        @Provides
        @Singleton
        fun provideSharedPrefs(context: Context) = getSharedPreferences(context)
    }

    @Singleton
    @Binds
    abstract fun bindRemoteUsersRepo(repo: RemoteUsersRepoImpl): RemoteUsersRepo

    @Singleton
    @Binds
    abstract fun bindRemoteGroupsRepo(repo: RemoteGroupsRepoImpl): RemoteGroupsRepo

    @Singleton
    @Binds
    abstract fun bindPreferencesRepo(repo: PreferenceRepoImpl): PreferenceRepository

}