package com.example.study

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/***
 * retrofit网络请求封装，未加okhttp拦截器和请求头，可以自行加上
 * */
class HttpClent private constructor(){

  companion object{
    val instance: HttpClent by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED){
      HttpClent()
    }
  }
  var retrofit: Retrofit? = null;

  fun Http():Api?{
    retrofit = Retrofit.Builder().baseUrl("https://gank.io")
      .client(OkHttpClient())

      .addConverterFactory(GsonConverterFactory.create())
      .build()
    return retrofit?.create(Api::class.java)
  }

}