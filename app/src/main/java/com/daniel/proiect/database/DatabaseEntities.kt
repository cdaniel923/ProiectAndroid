package com.daniel.proiect.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.daniel.proiect.network.AmiiboData

@Entity(tableName = "amiibo_database")
data class AmiiboDatabaseData(
    @PrimaryKey
    val id: String,
    val head: String,
    val tail: String,
    val name: String,
    val character: String,
    val gameSeries: String,
    val amiiboSeries: String,
    val imageUrl: String
)

fun List<AmiiboDatabaseData>.asNetworkModel(): List<AmiiboData>
{
    return map {
        AmiiboData(
            head = it.head,
            tail = it.tail,
            name = it.name,
            character = it.character,
            gameSeries = it.gameSeries,
            amiiboSeries = it.amiiboSeries,
            imageUrl = it.imageUrl,
            type = "")
    }
}
