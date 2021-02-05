package com.example.fynddemoproject.network.data


import com.google.gson.annotations.SerializedName

data class RequestTokenResponse(
    @SerializedName("expires_at")
    var expiresAt: String,
    @SerializedName("request_token")
    var requestToken: String,
    @SerializedName("success")
    var success: Boolean
)