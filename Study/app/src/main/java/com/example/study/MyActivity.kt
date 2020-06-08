package com.example.study

import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.study.databinding.ActivityMyBinding
import com.example.study.utils.Click

/**
 * 我的个人请求页面（测试协程）
 * */
class MyActivity(override var resId: Int = R.layout.activity_my) : BaseActivity<ActivityMyBinding>(), Click {

  override fun initView() {
    var vm = ViewModelProvider(this)[MyViewModel::class.java]
    vm.name = "旺仔牛奶"
    ui.vm = vm
    ui.clicker = this

  }

  override fun onClick(v: View?) {
    when (v) {
      ui.myOne -> {
        //全局的协程封装。需要手动处理关闭问题
//        ui.vm?.getData()
        //使用了viewmodel字节的viewmodel协程，无需处理关闭的问题
        ui.vm?.getScoreData()
      }
    }
  }

}