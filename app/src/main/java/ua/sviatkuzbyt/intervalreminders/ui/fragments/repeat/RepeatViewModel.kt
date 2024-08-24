package ua.sviatkuzbyt.intervalreminders.ui.fragments.repeat

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ua.sviatkuzbyt.intervalreminders.R
import ua.sviatkuzbyt.intervalreminders.data.elements.CardData
import ua.sviatkuzbyt.intervalreminders.data.repositories.RepeatRepository
import ua.sviatkuzbyt.intervalreminders.ui.elements.SingleLiveEvent

class RepeatViewModel(application: Application) : AndroidViewModel(application) {
    private lateinit var _repeatCards: MutableList<CardData>
    val repeatCards = MutableLiveData<MutableList<CardData>>()
    private val repository = RepeatRepository(application)
    val message = SingleLiveEvent<Int>()

    init { load() }

    private fun load() = viewModelScope.launch(Dispatchers.IO){
        try {
            _repeatCards = repository.load()
            repeatCards.postValue(_repeatCards)
        } catch (_: Exception){
            message.postValue(R.string.cant_load)
        }

    }

    fun remove(id: Long) = viewModelScope.launch(Dispatchers.IO){
        try {
            repository.remove(id)
        } catch (_: Exception){
            message.postValue(R.string.cant_remove)
        }
    }
}