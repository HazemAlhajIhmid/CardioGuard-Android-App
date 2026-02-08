package com.cardioguard.heartdisease.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cardioguard.heartdisease.models.PredictionRequest
import com.cardioguard.heartdisease.models.PredictionResponse
import com.cardioguard.heartdisease.network.RetrofitClient
import kotlinx.coroutines.launch

class PredictionViewModel : ViewModel() {

    private val _predictionResult = MutableLiveData<PredictionResponse?>()
    val predictionResult: LiveData<PredictionResponse?> = _predictionResult

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    fun predict(request: PredictionRequest) {
        _isLoading.value = true
        _error.value = null

        viewModelScope.launch {
            try {
                val response = RetrofitClient.instance.getPrediction(request)
                _predictionResult.postValue(response)
            } catch (e: Exception) {
                _error.postValue("Failed to get prediction: ${e.message}")
                _predictionResult.postValue(null)
            } finally {
                _isLoading.postValue(false)
            }
        }
    }
}
