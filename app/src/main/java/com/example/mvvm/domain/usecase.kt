package com.example.mvvm.domain

import com.example.mvvm.data.ZipResponse
import com.example.mvvm.repository.ZipRepo
import com.example.mvvm.utils.FlowResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class usecase @Inject constructor(
    private val repository : ZipRepo
){
     suspend fun invoke(zip: String): Flow<FlowResult<ZipResponse>> = flow {
         emit(FlowResult.Loading)
         val response = repository.getZipDetails(zip)

         if(response.isSuccessful){
             val data = response.body()

             if(data!=null){
                 emit(FlowResult.Success(data))
             }else{
                 emit(FlowResult.Failure("Empty response",response.code()))
             }
         }else{
             emit(FlowResult.Failure("API Error",response.code()))
         }
     }.catch{
         emit(FlowResult.Failure(it.message,null))
     }.flowOn(Dispatchers.IO)
}