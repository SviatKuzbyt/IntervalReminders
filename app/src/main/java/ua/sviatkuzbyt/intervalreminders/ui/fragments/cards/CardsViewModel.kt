package ua.sviatkuzbyt.intervalreminders.ui.fragments.cards

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ua.sviatkuzbyt.intervalreminders.R
import ua.sviatkuzbyt.intervalreminders.data.elements.CardData
import ua.sviatkuzbyt.intervalreminders.data.repositories.CardsRepository
import ua.sviatkuzbyt.intervalreminders.ui.elements.SingleLiveEvent

class CardsViewModel(application: Application) : AndroidViewModel(application) {
    private lateinit var _cards: MutableList<CardData>
    private val repository = CardsRepository(application)

    val cards = MutableLiveData<MutableList<CardData>>()
    val messageCards = SingleLiveEvent<Int>()
    val messageAdd = SingleLiveEvent<Int>()

    fun loadCards() {
        if (!::_cards.isInitialized)
            load()
    }

    private fun load() = viewModelScope.launch(Dispatchers.IO){
        try {
            _cards = repository.load()
            cards.postValue(_cards)
        } catch (_: Exception){
            messageCards.postValue(R.string.cant_load)
        }
    }

    fun remove(id: Long) = viewModelScope.launch(Dispatchers.IO){
        try {
            repository.remove(id)
        } catch (_: Exception){
            messageCards.postValue(R.string.cant_remove)
        }
    }

    fun add(name: String) = viewModelScope.launch(Dispatchers.IO){
        try {
            val id = repository.add(name)
            if (::_cards.isInitialized){
                _cards.add(0, CardData(id, name))
                cards.postValue(_cards)
            }
            messageAdd.postValue(R.string.saved)
        } catch (_: Exception){
            messageAdd.postValue(R.string.save_error)
        }
    }
}