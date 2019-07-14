package com.vinayshenoy.pagingplayground.data

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import io.reactivex.Completable

@Entity
data class Employee(

    @PrimaryKey(autoGenerate = true)
    val id: Long = -1L,

    val name: String,

    val field: String,

    val seniority: String,

    val position: String,

    val skills: String,

    val title: String
) {

  @Dao
  interface RoomDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insert(employees: List<Employee>): Completable
  }
}
