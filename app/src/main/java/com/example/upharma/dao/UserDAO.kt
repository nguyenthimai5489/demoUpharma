package com.example.upharma.dao


import androidx.room.*
import com.example.upharma.model.entity.NPPGuestInfo

@Dao
interface UserDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(nppGuestInfo: NPPGuestInfo): Long

    @Update
    fun update(nppGuestInfo: NPPGuestInfo)

    @Delete
    fun delete(nppGuestInfo: NPPGuestInfo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(users: List<NPPGuestInfo>)

}