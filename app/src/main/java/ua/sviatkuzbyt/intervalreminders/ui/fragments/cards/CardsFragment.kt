package ua.sviatkuzbyt.intervalreminders.ui.fragments.cards

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import ua.sviatkuzbyt.intervalreminders.databinding.FragmentCardsBinding
import ua.sviatkuzbyt.intervalreminders.ui.elements.RecyclerAction
import ua.sviatkuzbyt.intervalreminders.ui.elements.recyclers.CardAdapter

class CardsFragment : Fragment(), RecyclerAction {
    private lateinit var viewModel: CardsViewModel
    private lateinit var adapter: CardAdapter

    //set view
    private var _binding: FragmentCardsBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCardsBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity())[CardsViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loadCards()

        //init list
        binding.recyclerCards.layoutManager = LinearLayoutManager(context)
        viewModel.cards.observe(viewLifecycleOwner){
            if (::adapter.isInitialized){
                adapter.add()
            } else{
                adapter = CardAdapter( it, this, requireActivity())
                binding.recyclerCards.adapter = adapter
            }
        }

        //show error messages
        viewModel.messageCards.observe(viewLifecycleOwner){
            Toast.makeText(context, getString(it), Toast.LENGTH_SHORT).show()
        }
    }

    override fun removeAction(id: Long) {
        viewModel.remove(id)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}