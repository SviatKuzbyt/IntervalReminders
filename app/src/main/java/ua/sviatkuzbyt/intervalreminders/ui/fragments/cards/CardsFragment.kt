package ua.sviatkuzbyt.intervalreminders.ui.fragments.cards

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import ua.sviatkuzbyt.intervalreminders.R
import ua.sviatkuzbyt.intervalreminders.databinding.FragmentCardsBinding
import ua.sviatkuzbyt.intervalreminders.ui.elements.interfaces.RecyclerAction
import ua.sviatkuzbyt.intervalreminders.ui.elements.recyclers.CardAdapter
import ua.sviatkuzbyt.intervalreminders.ui.elements.recyclers.RemindAdapter

class CardsFragment : Fragment(), RecyclerAction {
    private val viewModel: CardsViewModel by viewModels()
    private var _binding: FragmentCardsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCardsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //init list
        binding.recyclerCards.layoutManager = LinearLayoutManager(context)
        viewModel.cards.observe(viewLifecycleOwner){
            binding.recyclerCards.adapter = CardAdapter(
                it, this, requireActivity()
            )
        }

        //show error messages
        viewModel.message.observe(viewLifecycleOwner){
            Toast.makeText(context, getString(it), Toast.LENGTH_SHORT).show()
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