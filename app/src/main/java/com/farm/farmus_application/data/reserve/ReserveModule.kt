package com.farm.farmus_application.data.reserve

import com.farm.farmus_application.data.common.NetworkModule
import com.farm.farmus_application.data.reserve.remote.ReserveApi
import com.farm.farmus_application.data.reserve.repository.ReserveRepositoryImpl
import com.farm.farmus_application.domain.reserve.ReserveRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class ReserveModule {

    @Singleton
    @Provides
    fun provideReserveApi(retrofit: Retrofit) : ReserveApi {
        return retrofit.create(ReserveApi::class.java)
    }

    @Singleton
    @Provides
    fun provideReserveRepository(reserveApi: ReserveApi) : ReserveRepository {
        return ReserveRepositoryImpl(reserveApi)
    }
}