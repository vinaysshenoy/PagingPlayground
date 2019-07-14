package com.vinayshenoy.pagingplayground

import android.app.Application
import com.vinayshenoy.pagingplayground.data.EmployeeDatabase

class TheApplication : Application() {

  companion object {
    private lateinit var INSTANCE: TheApplication

    fun get() = INSTANCE
  }

  private val employeeDatabase by lazy { EmployeeDatabase.inMemory(this) }

  val employeeDao by lazy(LazyThreadSafetyMode.NONE) { employeeDatabase.employeeDao() }

  override fun onCreate() {
    super.onCreate()
    INSTANCE = this
  }
}
