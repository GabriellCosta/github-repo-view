package dev.tigrao.state

interface StateEvent<out VALUE>

sealed class SealedStateEvent : StateEvent<Nothing>

object StartedEvent : StateEvent<Nothing>

data class ErrorEvent(val errorDataDTO: ErrorDataDTO) : StateEvent<Nothing>

data class SuccessEvent<RESULT>(val result: RESULT) : StateEvent<RESULT>

object FinishedEvent : StateEvent<Nothing>
