package com.farm.farmus_application.data.mypage.repository

import com.farm.farmus_application.data.mypage.remote.MyPageApi
import com.farm.farmus_application.domain.mypage.MyPageRepository
import javax.inject.Inject

class MyPageRepositoryImpl @Inject constructor(private val myPageApi: MyPageApi) : MyPageRepository {

}