package com.farm.farmus_application.domain.farm

import com.farm.farmus_application.domain.BaseResult
import kotlinx.coroutines.flow.Flow


// suspend fun 함수명(get타입의 경우 그냥 파람으로 넘김, data영역의 dto를 이용)
// : Flow<BaseResult< domain 영역의 entity, data 영역의 >>
interface FarmRepository {

    suspend fun getFarmList() : Flow<BaseResult<>>

    suspend fun
}