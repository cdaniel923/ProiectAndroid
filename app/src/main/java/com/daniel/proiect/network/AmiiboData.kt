package com.daniel.proiect.network

import android.os.Parcelable
import com.daniel.proiect.database.AmiiboDatabaseData
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

data class AmiiboList(
    val amiibo: List<AmiiboData>
)

@Parcelize
data class AmiiboData(
    val head: String,
    val tail: String,
    val name: String,
    val character: String,
    val gameSeries: String,
    val amiiboSeries: String,
    @Json(name = "image") val imageUrl: String,
    val type: String): Parcelable
{
    val id
        get() = head + tail
}

fun List<AmiiboData>.asDatabaseModel(): List<AmiiboDatabaseData>
{
    return map {
        AmiiboDatabaseData(
            id = it.id,
            head = it.head,
            tail = it.tail,
            name = it.name,
            character = it.character,
            gameSeries = it.gameSeries,
            amiiboSeries = it.amiiboSeries,
            imageUrl = it.imageUrl
        )
    }
}
