package com.vinayshenoy.pagingplayground.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Employee::class],
    version = 1,
    exportSchema = false
)
abstract class EmployeeDatabase : RoomDatabase() {

  companion object {
    fun inMemory(context: Context): EmployeeDatabase {
      return Room
          .inMemoryDatabaseBuilder(context, EmployeeDatabase::class.java)
          .build()
    }
  }

  abstract fun employeeDao(): Employee.RoomDao
}
