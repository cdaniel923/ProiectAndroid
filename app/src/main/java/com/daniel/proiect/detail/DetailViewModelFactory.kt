package com.daniel.proiect.detail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.daniel.proiect.network.AmiiboData

class DetailViewModelFactory(
    private val amiibo: AmiiboData,
    private val application: Application) : ViewModelProvider.Factory
{
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T
    {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java))
            return DetailViewModel(amiibo, application) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
