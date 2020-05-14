package com.daniel.proiect.master

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.daniel.proiect.database.getDatabase
import kotlinx.coroutines.Job
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import com.daniel.proiect.network.*

enum class RequestStatus
{
    LOADING,
    ERROR,
    DONE
}

class MasterViewModel(application: Application) : ViewModel()
{
    private val job = Job()
    private val uiScope = CoroutineScope(job + Dispatchers.Main)

    private val _status = MutableLiveData<RequestStatus>()
    val status: LiveData<RequestStatus>
        get() = _status

    private val repository = AmiiboRepository(getDatabase(application))
    val amiiboList: LiveData<List<AmiiboData>>
        get() = repository.amiibos

    private val _navigateToAmiiboDetails = MutableLiveData<AmiiboData>()
    val navigateToAmiiboDetails: LiveData<AmiiboData>
        get() = _navigateToAmiiboDetails

    init
    {
        getAmiiboList()
    }

    override fun onCleared()
    {
        super.onCleared()
        job.cancel()
    }

    fun displayAmiiboDetails(amiibo: AmiiboData)
    {
        _navigateToAmiiboDetails.value = amiibo
    }

    fun displayAmiiboDetailsComplete()
    {
        _navigateToAmiiboDetails.value = null
    }

    private fun getAmiiboList()
    {
        uiScope.launch {
            try
            {
                _status.value = RequestStatus.LOADING
                repository.refreshRepository()
                _status.value = RequestStatus.DONE
            }
            catch (e: Exception)
            {
                _status.value = RequestStatus.DONE
            }
        }
    }
}
