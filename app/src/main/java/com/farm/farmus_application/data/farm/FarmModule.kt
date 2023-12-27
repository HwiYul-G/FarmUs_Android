package com.farm.farmus_application.data.farm

import com.farm.farmus_application.data.common.NetworkModule
import com.farm.farmus_application.data.farm.remote.FarmApi
import com.farm.farmus_application.data.reserve.remote.ReserveApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class FarmModule {

    @Singleton
    @Provides
    fun provideReserveApi(retrofit: Retrofit) : FarmApi {
        return retrofit.create(FarmApi::class.java)
    }
//
//    @Singleton
//    @Provides
//    fun provideUserRepository(farmApi: FarmApi) : FarmRepository {
//        return UserRepositoryImpl(farmApi)
//    }
}