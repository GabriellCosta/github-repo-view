package me.tigrao.aegis.network.ui

import android.arch.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.annotations.SerializedName
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import timber.log.Timber

class UiStateCallback<R>(val mutableLiveData: MutableLiveData<UiState<R>>) : RetryCallback<R>() {

    override fun onRetry() {
        mutableLiveData.postValue(UiLoading)
    }

    override fun onFailure(call: Call<R>, t: Throwable) {
        super.onFailure(call, t)
        Timber.e(t)
        mutableLiveData.postValue(UiError(ErrorData(t)))
    }

    override fun onResponse(call: Call<R>, response: Response<R>) {
        super.onResponse(call, response)
        val uiState = createUiState(response)

        mutableLiveData.postValue(uiState)
    }

    private fun createUiState(response: Response<R>): UiState<R> {
        return if (response.isSuccessful)
            createSuccessResponse(response)
        else
            UiError(createErrorData(response))
    }

    private fun createSuccessResponse(response: Response<R>): UiState<R> =
        if (response.body() != null)
            UiSuccess(response.body()!!)
        else
            UiSuccess(Unit as R)

    private fun createErrorData(response: Response<R>): ErrorData {

        val throwable = createExceptionMessage(response)

        Timber.d(throwable)

        return ErrorData(throwable)
    }

    private fun <R> createExceptionMessage(response: Response<R>): Exception {
        val message = getErrorBodyMessage(response.errorBody())

        return IllegalStateException(message?.message ?: response.message())
    }

    private fun getErrorBodyMessage(errorBody: ResponseBody?): ErrorBody? =
        errorBody?.run {
            try {
                Gson().fromJson<ErrorBody>(string(), ErrorBody::class.java)
            } catch (jsonSyntaxException: JsonSyntaxException) {
                null
            }
        }

    private class ErrorBody(
        @SerializedName("message")
        val message: String
    )
}
