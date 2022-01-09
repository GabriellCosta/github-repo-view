package me.tigrao.github.repo.domain

import deb.tigrao.github.infra.api.ResultDomainError
import me.tigrao.github.repo.domain.model.RepositoryErrorModel

internal class FetchRepositoryErrorMapper {

    fun mapFrom(from: ResultDomainError): RepositoryErrorModel {
        return when (from) {
            is ResultDomainError.GenericError -> RepositoryErrorModel.GenericError
            is ResultDomainError.NetworkError -> {
                if (from.httpCode == 403) {
                    RepositoryErrorModel.MaxOfRequestReach
                } else {
                    RepositoryErrorModel.GenericError
                }
            }
            ResultDomainError.UnknownError -> RepositoryErrorModel.GenericError
        }
    }
}
