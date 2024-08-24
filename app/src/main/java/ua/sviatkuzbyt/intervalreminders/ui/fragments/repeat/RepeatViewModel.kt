package ua.sviatkuzbyt.intervalreminders.ui.fragments.repeat

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ua.sviatkuzbyt.intervalreminders.data.elements.RepeatData
import ua.sviatkuzbyt.intervalreminders.data.repositories.RepeatRepository

class RepeatViewModel(application: Application) : AndroidViewModel(application) {
    private lateinit var _repeatCards: MutableList<RepeatData>
    val repeatCards = MutableLiveData<MutableList<RepeatData>>()
    private val repository = RepeatRepository(application)

    init {
        Log.v("vmfe", "init repeat")
        load()
    }

    private fun load() = viewModelScope.launch(Dispatchers.IO){
        _repeatCards = repository.load()
        repeatCards.postValue(_repeatCards)
    }

    fun remove(id: Long) = viewModelScope.launch(Dispatchers.IO){
        repository.remove(id)
    }
}