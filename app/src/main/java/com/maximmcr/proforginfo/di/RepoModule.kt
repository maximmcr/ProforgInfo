package com.maximmcr.proforginfo.di

import com.maximmcr.proforginfo.data.Repository
import com.maximmcr.proforginfo.data.RepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Named

@Module
abstract class RepoModule {

    @Binds
    @Named("main")
    abstract fun repository(impl: RepositoryImpl) : Repository

//    @Binds
//    @Named("local")
//    abstract fun repository(impl: RepositoryImpl) : Repository
//
//    @Binds
//    @Named("firebase")
//    abstract fun repository(impl: RepositoryImpl) : Repository
}