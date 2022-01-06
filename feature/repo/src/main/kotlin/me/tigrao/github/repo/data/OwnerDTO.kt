package me.tigrao.github.repo.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal class OwnerDTO(
    val avatar_url: String,
    val login: String
)
