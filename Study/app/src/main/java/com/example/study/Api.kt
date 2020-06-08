package com.example.study

import retrofit2.http.GET

/**
 * api
 * */
interface Api {

  @GET("/api/v2/banners")
  suspend fun getData(): BaseBean<List<ImageData>>?
}