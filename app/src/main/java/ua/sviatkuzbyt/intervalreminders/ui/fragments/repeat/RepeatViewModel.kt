package ua.sviatkuzbyt.intervalreminders.ui.fragments.repeat

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ua.sviatkuzbyt.intervalreminders.R
import ua.sviatkuzbyt.intervalreminders.data.CardData
import ua.sviatkuzbyt.intervalreminders.data.repositories.RepeatRepository
import ua.sviatkuzbyt.intervalreminders.ui.elements.SingleLiveEvent

class RepeatViewModel(application: Application) : AndroidViewModel(application) {
    val repeatCards = MutableLiveData<MutableList<CardData>>()
    val message = SingleLiveEvent<Int>()

    private lateinit var _repeatCards: MutableList<CardData>
    private val repository = RepeatRepository(application)

    init { load() }

    fun load() = viewModelScope.launch(Dispatchers.IO){
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