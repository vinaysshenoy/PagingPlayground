package com.vinayshenoy.pagingplayground

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vinayshenoy.pagingplayground.infiniteintegers.InfiniteIntegerFragment

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    if (savedInstanceState == null) {
      supportFragmentManager
          .beginTransaction()
          .add(R.id.content, InfiniteIntegerFragment())
          .commit()
    }
  }
}
