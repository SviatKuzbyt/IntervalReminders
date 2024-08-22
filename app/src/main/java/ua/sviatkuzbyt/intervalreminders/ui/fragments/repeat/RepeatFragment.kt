package ua.sviatkuzbyt.intervalreminders.ui.fragments.repeat

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ua.sviatkuzbyt.intervalreminders.R

class RepeatFragment : Fragment() {

    companion object {
        fun newInstance() = RepeatFragment()
    }

    private val viewModel: RepeatViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_repeat, container, false)
    }
}