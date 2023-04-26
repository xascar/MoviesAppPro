package dev.xascar.moviesapppro.utils

import dev.xascar.network_sdk.model.MoviesResponse
import dev.xascar.network_sdk.utils.MovieCategory
import dev.xascar.network_sdk.utils.toLowerCase
import retrofit2.Response

//todo Call boilerplate code in this function
//fun makeRetrofitCall(
//
//
//suspendFunction: () -> Unit
//success: () -> Unit,
//error: (Any) -> Unit,
//){
//    try{
//
//        val response = localService.getMoviesDynamic(
//            MovieCategory.POPULAR.toLowerCase(),
//            page
//        )
//        suspendFunction.invoke(){
//            if(response.isSuccessful){
//                response.body()?.let {
//                    success(ResultState.Success(it.results.mapToDomainMovie()))
//                } ?: throw NullResponseException()
//            }else{
//                throw UnsuccessfulResponseException(response.errorBody()?.string()) //Exception(response.errorBody()?.string())
//            }
//        }
//
//
//    }catch (e: Exception){
//        error(ResultState.Error(e))
//    }
//}