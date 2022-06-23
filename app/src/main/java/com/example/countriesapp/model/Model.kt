package com.example.countriesapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Country(
    @ColumnInfo(name = "name")
    val name : String?,

    @ColumnInfo(name = "capital")
    val capital : String?,

    @ColumnInfo(name = "region")
    val region : String?,

    @ColumnInfo(name = "currency")
    val currency : String?,

    @ColumnInfo(name = "flag")
    val flag : String?,

    @ColumnInfo(name = "language")
    val language : String?
){
    //opsiyonel
    @PrimaryKey(autoGenerate = true)
    var uuid : Int = 0
}