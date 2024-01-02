package com.farm.farmus_application.domain.mypage

import com.farm.farmus_application.data.mypage.remote.dto.EditInfoNameReq
import com.farm.farmus_application.data.mypage.remote.dto.EditInfoNicknameReq
import com.farm.farmus_application.data.mypage.remote.dto.EditInfoPasswordReq
import com.farm.farmus_application.data.mypage.remote.dto.EditInfoPhoneNumberReq
import com.farm.farmus_application.data.mypage.remote.dto.MyPageNameRes
import com.farm.farmus_application.data.mypage.remote.dto.MyPageNickNameRes
import com.farm.farmus_application.data.mypage.remote.dto.MyPagePasswordRes
import com.farm.farmus_application.data.mypage.remote.dto.MyPagePhoneNumberRes
import com.farm.farmus_application.data.mypage.remote.dto.MyPageProfileImageRes
import com.farm.farmus_application.domain.BaseResult
import com.farm.farmus_application.domain.mypage.entity.MyPageNameEntity
import com.farm.farmus_application.domain.mypage.entity.MyPageNickNameEntity
import com.farm.farmus_application.domain.mypage.entity.MyPagePasswordEntity
import com.farm.farmus_application.domain.mypage.entity.MyPagePhoneNumberEntity
import com.farm.farmus_application.domain.mypage.entity.MyPageProfileImageEntity
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody


interface MyPageRepository {

    suspend fun patchEditInfoNickname(
        email: String,
        editNickNameRequest: EditInfoNicknameReq
    ): Flow<BaseResult<MyPageNickNameEntity, MyPageNickNameRes>>

    suspend fun patchEditInfoName(
        email: String,
        editInfoNameRequest: EditInfoNameReq
    ): Flow<BaseResult<MyPageNameEntity, MyPageNameRes>>

    suspend fun patchEditInfoPassword(
        email: String,
        editInfoPasswordRequest: EditInfoPasswordReq
    ): Flow<BaseResult<MyPagePasswordEntity, MyPagePasswordRes>>

    suspend fun patchEditInfoPhoneNumber(
        email: String,
        editInfoPhoneNumberRequest: EditInfoPhoneNumberReq
    ): Flow<BaseResult<MyPagePhoneNumberEntity, MyPagePhoneNumberRes>>

    suspend fun patchEditInfoProfileImg(
        email: String,
        file: MultipartBody.Part
    ): Flow<BaseResult<MyPageProfileImageEntity, MyPageProfileImageRes>>
}