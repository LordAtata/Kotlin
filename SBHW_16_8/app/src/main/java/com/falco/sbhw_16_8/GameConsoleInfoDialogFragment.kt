package com.falco.sbhw_16_8

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import com.falco.sbhw_16_8.databinding.DialogAddGameConsoleBinding

class GameConsoleInfoDialogFragment : DialogFragment() {

    private var _binding: DialogAddGameConsoleBinding? = null
    private val binding get() = _binding!!

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = DialogAddGameConsoleBinding.inflate(LayoutInflater.from(context))
        return AlertDialog.Builder(requireContext())
            .setTitle(R.string.game_console)
            .setView(binding.root)
            .setPositiveButton(
                "Добавить",
                DialogInterface.OnClickListener { _, _ -> createGameConsoleInfo() }
            )
            .setNegativeButton("Отмена", DialogInterface.OnClickListener { _, _ -> dismiss() })
            .create()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.addScreenSizeGCET.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.addResolutionHDGCET.isVisible = s.toString().isEmpty()
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
        binding.addResolutionHDGCET.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.addScreenSizeGCET.isVisible = s.toString().isEmpty()
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }

    private fun createGameConsoleInfo() {
        var isScreenSize = false
        var isResolutionHD = false
        if (binding.addScreenSizeGCET.isVisible && !binding.addResolutionHDGCET.isVisible) {
            isScreenSize = true
        } else if (!binding.addScreenSizeGCET.isVisible && binding.addResolutionHDGCET.isVisible) {
            isResolutionHD = true
        }
        val gameConsole = Electronics.GameConsole(
            name = binding.addNameGCET.text.toString(),
            imageLink = if (binding.addImageLinkGCET.text.isNotEmpty()) binding.addImageLinkGCET.text.toString() else getString(
                R.string.popuga_image_link
            ),
            screenSize = if (isScreenSize) binding.addScreenSizeGCET.text.toString() else null,
            resolution = if (isResolutionHD) binding.addResolutionHDGCET.text.toString() else null,
            countryOfOrigin = binding.addCountryGCET.text.toString(),
            price = if (binding.addPriceGCET.text.isNotEmpty()) binding.addPriceGCET.text.toString()
                .toInt() else 0,
            processor = binding.addProcessorGCET.text.toString(),
            memory = if (binding.addMemoryGCET.text.isNotEmpty()) binding.addMemoryGCET.text.toString()
                .toInt() else 0,
            rayTracing = binding.rayTracingGCCB.isChecked,
            gProcessor = binding.addGProcessorGCET.text.toString(),
            ssd = binding.ssdGCCB.isChecked
        )
        val bundle = Bundle()
        bundle.putParcelable(ListFragment.KEY_BUNDLE_ELECTRONIC, gameConsole)
        setFragmentResult(ListFragment.KEY_REQUEST_ELECTRONIC, bundle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
