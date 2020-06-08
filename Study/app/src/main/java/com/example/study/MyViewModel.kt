package com.example.study

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import retrofit2.Response

/**
 * viewmodel类。可以将协程封装在baseviewmode中，对viewmode的协程如果使用了livedata就不需要封装直接塞数据
 * livedata是个好玩意
 * */
class MyViewModel : ViewModel() {

  var urlData = MutableLiveData<String>().apply { value = "点我看美女" }
  var name: String = "哇哈"
  var job: Job? = null

  //使用全局的协程，需要自己手动取消协程
  fun getData() {
    job?.cancel()
    job = Http.instance.executeRequest<BaseBean<List<ImageData>>>(request = {
      getUserInfo()
    }, onSuccess = {
      Log.e("+++++", it?.data?.get(0)?.image ?: "")
    }, onFailer = {
      Log.e("+++", "错误了" + it.message)
    })
  }

  /**
   * 可以将这个方法丢到model层，这样就实现了mvvm的方式了
   * */
  suspend fun getUserInfo(): BaseBean<List<ImageData>>? {
    val result: BaseBean<List<ImageData>>? = HttpClent.instance.Http()?.getData()
    return result
  }

  override fun onCleared() {
    super.onCleared()
    //跟随viewmodel一起销毁
    job?.cancel()
  }

  //viewmodel的协程，已经处理好了取消操作
  fun getScoreData() {
    viewModelScope.launch {
      withContext(Dispatchers.IO) {
        var data = getUserInfo()
        urlData.postValue(data?.data?.get(0)?.image ?: "")
      }
    }
  }
}