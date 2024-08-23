package ua.sviatkuzbyt.intervalreminders.ui.fragments

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ua.sviatkuzbyt.intervalreminders.R
import ua.sviatkuzbyt.intervalreminders.data.db.CardEntity
import ua.sviatkuzbyt.intervalreminders.data.db.DataBaseObject
import ua.sviatkuzbyt.intervalreminders.data.db.RepeatEntity
import ua.sviatkuzbyt.intervalreminders.databinding.FragmentAddBinding
import java.time.LocalDate

class AddFragment: BottomSheetDialogFragment() {
    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddBinding.inflate(inflater, container, false)
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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun save(){
        lifecycleScope.launch(Dispatchers.IO){
            try {
                addCard(binding.addText.text.toString())
                withContext(Dispatchers.Main) {
                    Toast.makeText(requireActivity(), R.string.saved, Toast.LENGTH_SHORT).show()
                }
            } catch (_: Exception){
                withContext(Dispatchers.Main) {
                    Toast.makeText(requireActivity(), R.string.save_error, Toast.LENGTH_SHORT).show()
                }
            } finally {
                dismiss()
            }
        }
    }

    private fun addCard(name: String){
        val dao = DataBaseObject.getDao(requireActivity())
        val currencyDay = LocalDate.now()

        val cardId = dao.addCard(CardEntity(0, name))
        val dates = listOf(
            currencyDay.plusDays(1).toEpochDay(),
            currencyDay.plusDays(7).toEpochDay(),
            currencyDay.plusMonths(1).toEpochDay(),
            currencyDay.plusMonths(3).toEpochDay(),
            currencyDay.plusMonths(6).toEpochDay(),
            currencyDay.plusYears(1).toEpochDay()
        )

        dates.forEach {
            dao.addRepeat(RepeatEntity(0, it, cardId))
        }
    }
}