package com.david.paging3implementation.data.response

import com.google.gson.annotations.SerializedName

data class InfoResponse(
    @SerializedName("count") val count: Int,
    @SerializedName("pages") val page: Int,
    @SerializedName("next") val nextPage: String?,
    @SerializedName("prev") val previewPage: String?,
)
