package com.farm.farmus_application.data.mypage

import com.farm.farmus_application.data.common.NetworkModule
import com.farm.farmus_application.data.mypage.remote.MyPageApi
import com.farm.farmus_application.data.reserve.remote.ReserveApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class MyPageModule {

    @Singleton
    @Provides
    fun provideReserveApi(retrofit: Retrofit) : MyPageApi {
        return retrofit.create(MyPageApi::class.java)
    }
//
//    @Singleton
//    @Provides
//    fun provideUserRepository(myPageApi: MyPageApi) : MyPageRepository {
//        return UserRepositoryImpl(myPageApi)
//    }
}