/*
 * Copyright 2020 Sergio Belda
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sergiobelda.androidtodometer.persistence

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.sergiobelda.androidtodometer.model.Project
import com.sergiobelda.androidtodometer.model.ProjectTask
import kotlinx.coroutines.flow.Flow

@Dao
interface ProjectDao {

    @Query("SELECT * FROM project_table WHERE projectId = :id")
    fun getProject(id: Int): Flow<Project>

    @Query("SELECT * FROM project_table ORDER BY projectId ASC")
    fun getProjects(): DataSource.Factory<Int, Project>

    @Transaction
    @Query("SELECT * FROM project_table")
    fun getTaskProjects(): Flow<List<ProjectTask>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertProject(project: Project)

    @Query("DELETE FROM project_table")
    suspend fun deleteProjects()

    @Delete
    suspend fun deleteProject(project: Project)

    @Query("DELETE FROM project_table WHERE projectId = :id")
    suspend fun deleteProject(id: Int)

    @Update
    suspend fun updateProject(project: Project)
}
