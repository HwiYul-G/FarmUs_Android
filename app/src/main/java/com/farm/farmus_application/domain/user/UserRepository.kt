package com.farm.farmus_application.domain.user


import com.farm.farmus_application.data.user.remote.dto.findaccount.FindAccountRes
import com.farm.farmus_application.data.user.remote.dto.findpassword.FindPasswordRes
import com.farm.farmus_application.data.user.remote.dto.likes.DeleteLikeFarmRes
import com.farm.farmus_application.data.user.remote.dto.likes.LikeFarmReq
import com.farm.farmus_application.data.user.remote.dto.likes.LikeFarmRes
import com.farm.farmus_application.data.user.remote.dto.login.LoginRequest
import com.farm.farmus_application.data.user.remote.dto.login.LoginRes
import com.farm.farmus_application.data.user.remote.dto.signup.SignUpReq
import com.farm.farmus_application.data.user.remote.dto.signup.SignUpRes
import com.farm.farmus_application.data.user.remote.dto.signup_verification.SignUpVerificationReq
import com.farm.farmus_application.data.user.remote.dto.signup_verification.SignUpVerificationRes
import com.farm.farmus_application.data.user.remote.dto.verification.VerificationReq
import com.farm.farmus_application.data.user.remote.dto.verification.VerificationRes
import com.farm.farmus_application.data.user.remote.dto.withdrawal.WithdrawalRes
import com.farm.farmus_application.domain.BaseResult
import com.farm.farmus_application.domain.user.entity.DeleteLikeFarmEntity
import com.farm.farmus_application.domain.user.entity.FindAccountEntity
import com.farm.farmus_application.domain.user.entity.FindPaswordEntity
import com.farm.farmus_application.domain.user.entity.LikeFarmEntity
import com.farm.farmus_application.domain.user.entity.LoginEntity
import com.farm.farmus_application.domain.user.entity.SignUpEntity
import com.farm.farmus_application.domain.user.entity.SignUpVerificationEntity
import com.farm.farmus_application.domain.user.entity.VerificationEntity
import com.farm.farmus_application.domain.user.entity.WithdrawalEntity
import kotlinx.coroutines.flow.Flow

// suspend fun 함수명(get타입의 경우 그냥 파람으로 넘김, data영역의 dto를 이용)
// : Flow<BaseResult< domain 영역의 entity, data 영역의 >>
interface UserRepository {

    suspend fun signUp(signUpRequest : SignUpReq) : Flow<BaseResult<SignUpEntity, SignUpRes>>

    suspend fun login(loginRequest : LoginRequest) : Flow<BaseResult<LoginEntity, LoginRes>>

    suspend fun signUpVerification(signUpVerificationRequest : SignUpVerificationReq) : Flow<BaseResult<SignUpVerificationEntity, SignUpVerificationRes>>

    suspend fun verification(verificationRequest : VerificationReq) : Flow<BaseResult<VerificationEntity, VerificationRes>>

    suspend fun findAccount(name : String, phone : String) : Flow<BaseResult<FindAccountEntity, FindAccountRes>>

    suspend fun findPassword(email : String) : Flow<BaseResult<FindPaswordEntity, FindPasswordRes>>

    suspend fun withdrawal() : Flow<BaseResult<WithdrawalEntity, WithdrawalRes>>

    suspend fun likeFarm(likeFarmRequest : LikeFarmReq) : Flow<BaseResult<LikeFarmEntity, LikeFarmRes>>

    suspend fun deleteLikeFarm(email : String, farmId : Int) : Flow<BaseResult<DeleteLikeFarmEntity, DeleteLikeFarmRes>>

}