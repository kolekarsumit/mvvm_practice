package com.example.mvvm.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm.data.ZipResponse
import com.example.mvvm.domain.usecase
import com.example.mvvm.utils.FlowResponse
import com.example.mvvm.utils.FlowResult
import com.example.mvvm.utils.doOnFailure
import com.example.mvvm.utils.doOnLoading
import com.example.mvvm.utils.doOnSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ZipViewModel @Inject constructor(
    private val getZipUseCase: usecase
) : ViewModel(){
    private val _zipResponseLiveData = MutableLiveData<FlowResponse<ZipResponse>>()
    val zipResponseLiveData : LiveData<FlowResponse<ZipResponse>> = _zipResponseLiveData

    fun getZIpDetails(zip: String){

        executeUseCase(
            { getZipUseCase.invoke(zip)},
            _zipResponseLiveData
        )
    }

    private fun <T> executeUseCase(
        useCase: suspend () -> Flow<FlowResult<T>>,
        liveData: MutableLiveData<FlowResponse<T>>
    ){
        viewModelScope.launch {
            useCase.invoke()
                .doOnLoading {
                    liveData.postValue(FlowResponse.loading())
                }
                .doOnSuccess {
                    liveData.postValue(FlowResponse.success(it))
                }
                .doOnFailure {
                    liveData.postValue(FlowResponse.error(it ?: "Unknown Error"))
                }
                .collect{}
        }
    }
}