package com.example.testmovie.source.api

import android.util.Log
import com.example.testmovie.R
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import retrofit2.HttpException


suspend fun <T: Any> makeNetworkCall(
    call: suspend () -> T
): ApiResponseState<T>{
        return withContext(Dispatchers.IO){
            try {
                ApiResponseState.Success(call())
            }catch (e: HttpException){
                try {
                    val errorBody = e.response()?.errorBody()
                    val jsonErrorBody = errorBody?.charStream()?.readText()?.let { JSONObject(it) }
                    val defaultResponse = Gson().fromJson(jsonErrorBody.toString(), DefaultResponse::class.java)
                    ApiResponseState.Error(R.string.error_api_connection, defaultResponse.status_message)
                }catch (e:Exception){
                    ApiResponseState.Error(R.string.error_api_connection)
                }
            }catch (e: Exception){
                Log.d("API_MOVIE", "${e.cause?.message}")
                ApiResponseState.Error(R.string.error_connection)
            }
        }
}