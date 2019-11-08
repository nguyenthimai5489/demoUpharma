package com.example.upharma.data


import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.upharma.Converters
import com.example.upharma.dao.UserDAO
import com.example.upharma.model.entity.NPPGuestInfo
import com.example.upharma.utilities.VERSION_APP

@Database(entities = arrayOf(NPPGuestInfo::class), version = VERSION_APP, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase(){
    abstract fun userDao(): UserDAO
}