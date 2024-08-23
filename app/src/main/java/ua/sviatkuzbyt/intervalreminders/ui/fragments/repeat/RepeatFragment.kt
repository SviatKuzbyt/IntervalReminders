package ua.sviatkuzbyt.intervalreminders.ui.fragments.repeat

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import ua.sviatkuzbyt.intervalreminders.R
import ua.sviatkuzbyt.intervalreminders.databinding.FragmentAddBinding
import ua.sviatkuzbyt.intervalreminders.databinding.FragmentRepeatBinding
import ua.sviatkuzbyt.intervalreminders.ui.elements.interfaces.RecyclerAction
import ua.sviatkuzbyt.intervalreminders.ui.elements.recyclers.CardAdapter

class RepeatFragment : Fragment(), RecyclerAction {
    private val viewModel: RepeatViewModel by viewModels()

    private var _binding: FragmentRepeatBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRepeatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerRepeat.layoutManager = LinearLayoutManager(context)

        viewModel.repeatCards.observe(viewLifecycleOwner){
            binding.recyclerRepeat.adapter = CardAdapter(
                it, R.drawable.done_ic, this
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun removeAction(id: Long) {
        viewModel.remove(id)
    }
}