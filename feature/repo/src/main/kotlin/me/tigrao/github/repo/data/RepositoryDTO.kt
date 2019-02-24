package me.tigrao.github.repo.data

import com.google.gson.annotations.SerializedName

internal class RepositoryDTO(
    @SerializedName("id")
    val id: Long,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("description")
    val description: String?,
    @SerializedName("owner")
    val owner: OwnerDTO,
    @SerializedName("forks_count")
    val forksCount: Int,
    @SerializedName("stargazers_count")
    val stargazersCount: Int
)
