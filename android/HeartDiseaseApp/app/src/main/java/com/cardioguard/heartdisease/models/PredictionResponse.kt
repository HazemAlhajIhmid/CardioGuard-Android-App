package com.cardioguard.heartdisease.models

data class PredictionResponse(
    val knn: ModelResult,
    val naiveBayes: ModelResult,
    val decisionTree: ModelResult,
    val ensemble: EnsembleResult
)

data class ModelResult(
    val prediction: Boolean,
    val confidence: Double,
    val accuracy: Int,
    val precision: Double, // Added
    val recall: Double,    // Added
    val f1_score: Double   // Added
)

data class EnsembleResult(
    val prediction: Boolean,
    val riskLevel: String,
    val riskScore: Double
)
