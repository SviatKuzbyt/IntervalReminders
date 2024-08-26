package ua.sviatkuzbyt.intervalreminders.ui.fragments.repeat

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import ua.sviatkuzbyt.intervalreminders.R
import ua.sviatkuzbyt.intervalreminders.databinding.FragmentRepeatBinding
import ua.sviatkuzbyt.intervalreminders.ui.elements.RecyclerAction
import ua.sviatkuzbyt.intervalreminders.ui.elements.recyclers.RemindAdapter

class RepeatFragment : Fragment(), RecyclerAction {
    private val viewModel: RepeatViewModel by viewModels()

    //set views
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

        //init list
        binding.recyclerRepeat.layoutManager = LinearLayoutManager(context)
        viewModel.repeatCards.observe(viewLifecycleOwner){
            binding.recyclerRepeat.adapter = RemindAdapter(
                it, this
            )
        }

        //show error messages
        viewModel.message.observe(viewLifecycleOwner){
            Toast.makeText(context, getString(it), Toast.LENGTH_SHORT).show()
        }

        //update list
        binding.repeatRefresh.setColorSchemeResources(R.color.blue)
        binding.repeatRefresh.setOnRefreshListener {
            viewModel.load()
            binding.repeatRefresh.isRefreshing = false
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