package ua.sviatkuzbyt.intervalreminders.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.commit
import ua.sviatkuzbyt.intervalreminders.R
import ua.sviatkuzbyt.intervalreminders.databinding.ActivityMainBinding
import ua.sviatkuzbyt.intervalreminders.ui.fragments.AddFragment
import ua.sviatkuzbyt.intervalreminders.ui.fragments.cards.CardsFragment
import ua.sviatkuzbyt.intervalreminders.ui.fragments.repeat.RepeatFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var isRepeatFragment = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //set first fragment and buttons
        if (savedInstanceState == null){
            supportFragmentManager.commit {
                add(R.id.mainFragmentContainer, RepeatFragment(), "repeat")
            }
        } else{
            if (!savedInstanceState.getBoolean("isRepeat"))
                updateButtonStyles(false)
        }

        //change fragments
        binding.cardsButton.setOnClickListener{
            supportFragmentManager.commit {
                updateButtonStyles(false)
                val repeat = supportFragmentManager.findFragmentByTag("repeat")
                val cards = supportFragmentManager.findFragmentByTag("cards")

                if (cards == null) add(R.id.mainFragmentContainer, CardsFragment(), "cards")
                else show(cards)

                repeat?.let { hide(it) }
            }
        }

        binding.repeatButton.setOnClickListener{
            supportFragmentManager.commit {
                updateButtonStyles(true)
                val repeat = supportFragmentManager.findFragmentByTag("repeat")
                val cards = supportFragmentManager.findFragmentByTag("cards")
                cards?.let { hide(it) }
                repeat?.let { show(it) }
            }
        }

        //add fragment
        binding.addButton.setOnClickListener {
            val addFragment = AddFragment()
            addFragment.show(supportFragmentManager, addFragment.tag)
        }
    }

    private fun updateButtonStyles(isRepeat: Boolean) {
        isRepeatFragment = isRepeat
        val repeatDrawableId = if (isRepeatFragment) R.drawable.remind_blue_ic else R.drawable.remind_ic
        val cardsDrawableId = if (isRepeatFragment) R.drawable.cards_ic else R.drawable.cards_blue_ic
        val repeatTextColor = if (isRepeatFragment) R.color.blue else R.color.gray
        val cardsTextColor = if (isRepeatFragment) R.color.gray else R.color.blue

        binding.apply {
            val drawableRepeat = ContextCompat.getDrawable(this@MainActivity, repeatDrawableId)
            repeatButton.setCompoundDrawablesWithIntrinsicBounds(null, drawableRepeat, null, null)
            repeatButton.setTextColor(ContextCompat.getColor(this@MainActivity, repeatTextColor))

            val drawableCards = ContextCompat.getDrawable(this@MainActivity, cardsDrawableId)
            cardsButton.setCompoundDrawablesWithIntrinsicBounds(null, drawableCards, null, null)
            cardsButton.setTextColor(ContextCompat.getColor(this@MainActivity, cardsTextColor))
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean("isRepeat", isRepeatFragment)
    }
}