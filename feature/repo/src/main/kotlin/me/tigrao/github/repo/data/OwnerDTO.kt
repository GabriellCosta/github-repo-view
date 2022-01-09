package me.tigrao.github.repo.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal class OwnerDTO(
    @Json(name = "avatar_url")
    val avatar: String,
    val login: String
)
