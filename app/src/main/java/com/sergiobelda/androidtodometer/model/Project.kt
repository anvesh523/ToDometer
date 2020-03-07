package com.sergiobelda.androidtodometer.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "project_table")
data class Project(
    val name: String,
    val description: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}