package com.daniel.proiect.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

private lateinit var INSTANCE: AmiiboDatabase

fun getDatabase(context: Context): AmiiboDatabase
{
    synchronized(AmiiboDatabase::class.java)
    {
        if (!::INSTANCE.isInitialized)
        {
            val builder = Room.databaseBuilder(
                context.applicationContext,
                AmiiboDatabase::class.java,
                "amiibos")
            INSTANCE = builder.build()
        }
    }
    return INSTANCE
}

@Database(entities = [AmiiboDatabaseData::class], version = 1, exportSchema = false)
abstract class AmiiboDatabase: RoomDatabase()
{
    abstract val amiiboDao: AmiiboDao
}

@Dao
interface AmiiboDao
{
    @Query("SELECT * FROM amiibo_database")
    fun getAmiiboList(): LiveData<List<AmiiboDatabaseData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(amiibos: List<AmiiboDatabaseData>)
}
