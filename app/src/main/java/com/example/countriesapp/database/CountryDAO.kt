package com.example.countriesapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.countriesapp.model.Country

@Dao
interface CountryDAO {

    //vararg -> multiple country objects
    //List<Long> -> primary keys

    @Insert
    suspend fun insertAll(vararg countries: Country) : List<Long>

    @Query("SELECT * FROM country")
    suspend fun getAllCountries(): List<Country>

    @Query("SELECT * FROM country WHERE uuid = :countryId")
    suspend fun getCountryFromId(countryId : Int) : Country

    @Query("DELETE FROM country")
    suspend fun deleteAllCountries()

}