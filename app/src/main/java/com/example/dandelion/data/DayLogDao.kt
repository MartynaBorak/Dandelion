package com.example.dandelion.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface DayLogDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(dayLog: DayLog)

    @Update
    suspend fun update(dayLog: DayLog)

    @Delete
    suspend fun delete(dayLog: DayLog)

    @Query("SELECT * from dayLog WHERE id = :id")
    fun getLog(id: Int): Flow<DayLog>

    @Query("SELECT * from dayLog ORDER BY id DESC")
    fun getAllLogs(): Flow<List<DayLog>>
}