package com.example.study

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/***
 * activity的基类，databingding+viewmodel+协程的一个mvvm例子，model层因为基本是网络请求，所有我这里没有写model
 * 其实实现方式很简单，创建一个model类，直接将viewmodel中的getUserInfo方法丢进去，在进行网络请求就搞定了
 * */
abstract class BaseActivity<VDB: ViewDataBinding> : AppCompatActivity() {

  lateinit var ui: VDB
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    ui = DataBindingUtil.setContentView(this, resId)
    ui.lifecycleOwner = this
    initView()

  }

  abstract fun initView()

  abstract var resId : Int;
}