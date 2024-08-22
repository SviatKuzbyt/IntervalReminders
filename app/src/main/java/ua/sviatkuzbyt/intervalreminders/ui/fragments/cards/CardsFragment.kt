package ua.sviatkuzbyt.intervalreminders.ui.fragments.cards

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import ua.sviatkuzbyt.intervalreminders.R

class CardsFragment : Fragment() {

    companion object {
        fun newInstance() = CardsFragment()
    }

    private val viewModel: CardsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_cards, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val test = view.findViewById<TextView>(R.id.test)
        viewModel.test.observe(viewLifecycleOwner){
            test.text = it.toString()
        }

    }
}