package com.example.geneticcalc.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "relatives_profiles_table")
class RelativesEntity {
    @PrimaryKey(autoGenerate = true)
    var id = 0
    var relativesType = ""
    var eyeColor = ""
    var hairColor = ""
    var dateofBirth = ""
    var bloodType = ""
}