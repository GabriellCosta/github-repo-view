package me.tigrao.github.pull.data

import com.google.gson.annotations.SerializedName
import java.util.Date

internal class PullRequestResponseDTO(
    @SerializedName("title")
    val title: String,
    @SerializedName("body")
    val body: String,
    @SerializedName("created_at")
    val createdDate: Date
)