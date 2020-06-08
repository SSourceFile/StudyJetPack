package com.example.study

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

/*
* 首页
* */
class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    pa_fg.setOnClickListener(object : View.OnClickListener {
      override fun onClick(v: View?) {
        var intent = Intent(this@MainActivity, MyActivity::class.java)
        startActivity(intent)
      }
    })
  }
}
