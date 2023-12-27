package com.farm.farmus_application.data.user.repository

import com.farm.farmus_application.data.user.remote.UserApi
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
import com.farm.farmus_application.domain.user.UserRepository
import com.farm.farmus_application.domain.user.entity.DeleteLikeFarmEntity
import com.farm.farmus_application.domain.user.entity.FindAccountEntity
import com.farm.farmus_application.domain.user.entity.FindPaswordEntity
import com.farm.farmus_application.domain.user.entity.LikeFarmEntity
import com.farm.farmus_application.domain.user.entity.LoginEntity
import com.farm.farmus_application.domain.user.entity.SignUpEntity
import com.farm.farmus_application.domain.user.entity.SignUpVerificationEntity
import com.farm.farmus_application.domain.user.entity.VerificationEntity
import com.farm.farmus_application.domain.user.entity.WithdrawalEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val userApi: UserApi) : UserRepository {
    override suspend fun signUp(signUpRequest: SignUpReq): Flow<BaseResult<SignUpEntity, SignUpRes>> {
        return flow {
            val response = userApi.postSignUp(signUpRequest)
            if (response.isSuccessful) {
                val body = response.body()
                emit(BaseResult.Success(SignUpEntity(body?.isSuccess ?: false)))
            } else {
                val type = object : TypeToken<SignUpRes>() {}.type
                val err = Gson().fromJson<SignUpRes>(response.errorBody()!!.charStream(), type)
                response.code().also { err.code = it }
                emit(BaseResult.Error(err))
            }
        }
    }

    override suspend fun login(loginRequest: LoginRequest): Flow<BaseResult<LoginEntity, LoginRes>> {
        return flow {
            val response = userApi.postLogin(loginRequest)
            if (response.isSuccessful) {
                val body = response.body()!!
                emit(
                    BaseResult.Success(
                        LoginEntity(
                            body.isSuccess, body.result?.accesstoken!!,
                            body.result.profile, body.result.status
                        )
                    )
                )
            } else {
                val type = object : TypeToken<LoginRes>() {}.type
                val err = Gson().fromJson<LoginRes>(response.errorBody()!!.charStream(), type)
                response.code().also { err.code = it }
                emit(BaseResult.Error(err))
            }
        }
    }

    override suspend fun signUpVerification(signUpVerificationRequest: SignUpVerificationReq): Flow<BaseResult<SignUpVerificationEntity, SignUpVerificationRes>> {
        return flow {
            val response = userApi.postUserSignupVerification(signUpVerificationRequest)
            if (response.isSuccessful) {
                val body = response.body()
                emit(BaseResult.Success(SignUpVerificationEntity(body?.result ?: false)))
            } else {
                val type = object : TypeToken<SignUpVerificationRes>() {}.type
                val err = Gson().fromJson<SignUpVerificationRes>(
                    response.errorBody()!!.charStream(),
                    type
                )
                response.code().also { err.code = it }
                emit(BaseResult.Error(err))
            }
        }
    }

    override suspend fun verification(verificationRequest: VerificationReq): Flow<BaseResult<VerificationEntity, VerificationRes>> {
        return flow {
            val response = userApi.postUserVerification(verificationRequest)
            if (response.isSuccessful) {
                val body = response.body()
                emit(
                    BaseResult.Success(
                        VerificationEntity(
                            body?.code!!,
                            body.email,
                            body.message,
                            body.name,
                            body.result
                        )
                    )
                )
            } else {
                val type = object : TypeToken<VerificationRes>() {}.type
                val err =
                    Gson().fromJson<VerificationRes>(response.errorBody()!!.charStream(), type)
                response.code().also { err.code = it }
                emit(BaseResult.Error(err))
            }
        }
    }

    override suspend fun findAccount(
        name: String,
        phone: String
    ): Flow<BaseResult<FindAccountEntity, FindAccountRes>> {
        return flow {
            val response = userApi.getUserFindAccount(name = name, phoneNumber = phone)
            if (response.isSuccessful) {
                val body = response.body()!!
                emit(
                    BaseResult.Success(
                        FindAccountEntity(
                            body.result, body.name, body.email,
                            body.status
                        )
                    )
                )
            } else {
                val type = object : TypeToken<FindAccountRes>() {}.type
                val err = Gson().fromJson<FindAccountRes>(response.errorBody()!!.charStream(), type)
                response.code().also { err.code = it }
                emit(BaseResult.Error(err))
            }
        }
    }

    override suspend fun findPassword(email: String): Flow<BaseResult<FindPaswordEntity, FindPasswordRes>> {
        return flow {
            val response = userApi.getUserFindPassword(email)
            if (response.isSuccessful) {
                val body = response.body()!!
                emit(BaseResult.Success(FindPaswordEntity(body.result, body.message)))
            } else {
                val type = object : TypeToken<FindPasswordRes>() {}.type
                val err =
                    Gson().fromJson<FindPasswordRes>(response.errorBody()!!.charStream(), type)
                response.code().also { err.code = it }
                emit(BaseResult.Error(err))
            }
        }
    }

    override suspend fun withdrawal(): Flow<BaseResult<WithdrawalEntity, WithdrawalRes>> {
        return flow {
            val response = userApi.deleteUserWithdrawal()
            if (response.isSuccessful) {
                val body = response.body()!!
                emit(BaseResult.Success(WithdrawalEntity(body.result)))
            } else {
                val type = object : TypeToken<WithdrawalRes>() {}.type
                val err = Gson().fromJson<WithdrawalRes>(response.errorBody()!!.charStream(), type)
                // response.code().also { err.code = it }
                emit(BaseResult.Error(err))
            }
        }
    }

    override suspend fun likeFarm(likeFarmRequest: LikeFarmReq): Flow<BaseResult<LikeFarmEntity, LikeFarmRes>> {
        return flow {
            val response = userApi.postUserLikeFarm(likeFarmRequest)
            if (response.isSuccessful) {
                val body = response.body()!!
                emit(BaseResult.Success(LikeFarmEntity(body.isSuccess)))
            } else {
                val type = object : TypeToken<LikeFarmRes>() {}.type
                val err = Gson().fromJson<LikeFarmRes>(response.errorBody()!!.charStream(), type)
                // response.code().also { err.code = it }
                emit(BaseResult.Error(err))
            }
        }
    }

    override suspend fun deleteLikeFarm(
        email: String,
        farmId: Int
    ): Flow<BaseResult<DeleteLikeFarmEntity, DeleteLikeFarmRes>> {
        return flow {
            val response = userApi.deleteUserLikeFarm(email, farmId)
            if (response.isSuccessful) {
                val body = response.body()!!
                emit(BaseResult.Success(DeleteLikeFarmEntity(body.result, body.message)))
            } else {
                val type = object : TypeToken<DeleteLikeFarmRes>() {}.type
                val err = Gson().fromJson<DeleteLikeFarmRes>(response.errorBody()!!.charStream(), type)
                // response.code().also { err.code = it }
                emit(BaseResult.Error(err))
            }
        }
    }


}