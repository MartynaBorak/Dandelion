package com.example.dandelion.data

import kotlinx.coroutines.flow.Flow

class OfflineLogsRepository(private val logDao: DayLogDao) : LogsRepository{
    override fun getAllLogsStream(): Flow<List<DayLog>> = logDao.getAllLogs()

    override fun getLogStream(id: Int): Flow<DayLog?> = logDao.getLog(id)

    override suspend fun insertLog(dayLog: DayLog) = logDao.insert(dayLog)

    override suspend fun deleteLog(dayLog: DayLog) = logDao.delete(dayLog)

    override suspend fun updateLog(dayLog: DayLog) = logDao.update(dayLog)
}