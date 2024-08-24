package ua.sviatkuzbyt.intervalreminders.ui.fragments.cards

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ua.sviatkuzbyt.intervalreminders.databinding.FragmentAddBinding

class AddFragment: BottomSheetDialogFragment() {
    private lateinit var viewModel: CardsViewModel
    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity())[CardsViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.addText.requestFocus()

        binding.addText.setOnEditorActionListener { _, _, _ ->
            save()
            true
        }

        binding.saveButton.setOnClickListener {
            it.isClickable = false
            save()
        }

        binding.cancelButton.setOnClickListener {
            dismiss()
        }

        viewModel.messageAdd.observe(viewLifecycleOwner){
            Toast.makeText(context, getString(it), Toast.LENGTH_SHORT).show()
            dismiss()
        }
    }

    private fun save(){
        viewModel.add(binding.addText.text.toString())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}