package com.daniel.proiect.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.daniel.proiect.network.AmiiboData

class DetailViewModel(amiibo: AmiiboData, app: Application)
    : AndroidViewModel(app)
{
    private val _selectedAmiibo = MutableLiveData<AmiiboData>()
    val selectedAmiibo: LiveData<AmiiboData>
        get() = _selectedAmiibo

    init
    {
        _selectedAmiibo.value = amiibo
    }
}
