package com.example.dandelion.data

import kotlinx.coroutines.flow.Flow

interface LogsRepository {
    fun getAllLogsStream(): Flow<List<DayLog>>

    fun getLogStream(id: Int): Flow<DayLog?>

    suspend fun insertLog(dayLog: DayLog)

    suspend fun deleteLog(dayLog: DayLog)

    suspend fun updateLog(dayLog: DayLog)
}