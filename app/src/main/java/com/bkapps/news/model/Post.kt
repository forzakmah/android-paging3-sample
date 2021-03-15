package com.bkapps.news.model

import com.google.gson.annotations.SerializedName

data class Post(
    @field:SerializedName("userId") val userId: Long = 0,
    @field:SerializedName("id") val id: Long = 0,
    @field:SerializedName("title") val title: String = "",
    @field:SerializedName("body") var body: String = "",
    @Transient
    @field:SerializedName("image") val image: String = "https://picsum.photos/400"
)


