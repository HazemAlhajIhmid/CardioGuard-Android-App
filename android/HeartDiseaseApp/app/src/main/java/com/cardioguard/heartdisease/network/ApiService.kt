package com.cardioguard.heartdisease.network

import com.cardioguard.heartdisease.models.PredictionRequest
import com.cardioguard.heartdisease.models.PredictionResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("api/Prediction/predict")
    suspend fun getPrediction(@Body request: PredictionRequest): PredictionResponse

    companion object {
        // Direct backend endpoint
        const val BASE_URL = "https://cardio-guard-api-prod-b2a0cfdbe9czbkgx.norwayeast-01.azurewebsites.net/"
    }
}
