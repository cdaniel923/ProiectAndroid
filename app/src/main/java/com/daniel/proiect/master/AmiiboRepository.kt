package com.daniel.proiect.master

import androidx.lifecycle.Transformations
import com.daniel.proiect.database.AmiiboDatabase
import com.daniel.proiect.database.asNetworkModel
import com.daniel.proiect.network.AmiiboApi
import com.daniel.proiect.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AmiiboRepository(private val database: AmiiboDatabase)
{
    val amiibos = Transformations.map(database.amiiboDao.getAmiiboList()) {
        it.asNetworkModel()
    }

    suspend fun refreshRepository()
    {
        withContext(Dispatchers.IO) {
            val amiiboList = AmiiboApi.retrofitService.getAmiiboList().await()
            database.amiiboDao.insertAll(amiiboList.amiibo.asDatabaseModel())
        }
    }
}
