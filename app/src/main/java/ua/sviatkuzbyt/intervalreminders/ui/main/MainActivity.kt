package ua.sviatkuzbyt.intervalreminders.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.commit
import ua.sviatkuzbyt.intervalreminders.R
import ua.sviatkuzbyt.intervalreminders.databinding.ActivityMainBinding
import ua.sviatkuzbyt.intervalreminders.ui.fragments.AddFragment
import ua.sviatkuzbyt.intervalreminders.ui.fragments.repeat.RepeatFragment

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //change fragments
        viewModel.fragment.observe(this){
            updateButtonStyles(it is RepeatFragment)
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                replace(R.id.mainFragmentContainer, it)
            }
        }

        //bottom menu buttons
        binding.repeatButton.setOnClickListener {
            viewModel.setFragment(1)
        }
        binding.cardsButton.setOnClickListener {
            viewModel.setFragment(2)
        }
        binding.addButton.setOnClickListener {
            val addFragment = AddFragment()
            addFragment.show(supportFragmentManager, addFragment.tag)
        }
    }

    private fun updateButtonStyles(isRepeatFragment: Boolean) {
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
}