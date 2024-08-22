package ua.sviatkuzbyt.intervalreminders.ui.main

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ua.sviatkuzbyt.intervalreminders.ui.fragments.cards.CardsFragment
import ua.sviatkuzbyt.intervalreminders.ui.fragments.repeat.RepeatFragment

class MainViewModel: ViewModel() {
    private val repeatFragment by lazy { RepeatFragment() }
    private val cardsFragment by lazy { CardsFragment() }
    private var currentNum = 1

    val fragment = MutableLiveData<Fragment>()

     fun setFragment(number: Int){
         if (number != currentNum){
             fragment.value = when(number){
                 1 -> repeatFragment
                 else -> cardsFragment
             }
             currentNum = number
         }
     }
}