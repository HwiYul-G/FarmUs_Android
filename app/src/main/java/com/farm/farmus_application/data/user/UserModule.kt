package com.farm.farmus_application.data.user

import com.farm.farmus_application.data.common.NetworkModule
import com.farm.farmus_application.data.user.remote.UserApi
import com.farm.farmus_application.data.user.repository.UserRepositoryImpl
import com.farm.farmus_application.domain.user.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class UserModule {

    @Singleton
    @Provides
    fun provideUserApi(retrofit: Retrofit) : UserApi {
        return retrofit.create(UserApi::class.java)
    }

    @Singleton
    @Provides
    fun provideUserRepository(userApi: UserApi) : UserRepository {
        return UserRepositoryImpl(userApi)
    }

}