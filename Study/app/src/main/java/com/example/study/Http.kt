package com.example.study

import android.util.Log
import kotlinx.coroutines.*
import java.lang.Exception
/**
 * 对协程进行单例封装
 * */
class Http private constructor(){

  companion object{
    val instance: Http by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED){
      Http()
    }
  }
  val TAG: String =  "HTTP===="
  fun<T> executeRequest(request:suspend()-> T?, onSuccess:(T)->Unit={}, onFailer:(Throwable)->Unit={}): Job{
    return GlobalScope.launch {
      try {
        val res: T? = withContext(Dispatchers.IO){
          request()
        }
        res?.let { onSuccess(it) }
      }catch (e:CancellationException){
        Log.e(TAG, "取消网络请求")
      }catch (e: Exception){
        onFailer(e)
      }
    }
  }

}