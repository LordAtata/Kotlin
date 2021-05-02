package com.falco.sbhw_16_8

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import com.falco.sbhw_16_8.databinding.DialogAddSmartphoneBinding

class SmartphoneInfoDialogFragment : DialogFragment() {

    private var _binding: DialogAddSmartphoneBinding? = null
    private val binding get() = _binding!!

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = DialogAddSmartphoneBinding.inflate(LayoutInflater.from(context))
        return AlertDialog.Builder(requireContext())
            .setTitle(R.string.smartphone)
            .setView(binding.root)
            .setPositiveButton(
                "Добавить"
            ) { _, _ -> createSmartphoneInfo() }
            .setNegativeButton("Отмена") { _, _ -> dismiss() }
            .create()
    }

    private fun createSmartphoneInfo() {
        val smartphone = Electronics.Smartphone(
            name = binding.addNameSPET.text.toString(),
            imageLink = if (binding.addImageLinkSPET.text.isNotEmpty()) binding.addImageLinkSPET.text.toString() else getString(
                R.string.popuga_image_link
            ),
            screenSize = binding.addScreenSizeSPET.text.toString(),
            countryOfOrigin = binding.addCountrySPET.text.toString(),
            price = if (binding.addPriceSPET.text.isNotEmpty()) binding.addPriceSPET.text.toString()
                .toInt() else 0,
            processor = binding.addProcessorSPET.text.toString(),
            memory = if (binding.addMemorySPET.text.isNotEmpty()) binding.addMemorySPET.text.toString()
                .toInt() else 0,
            os = binding.addOSSPET.text.toString(),
            ram = if (binding.addRAMSPET.text.isNotEmpty()) binding.addRAMSPET.text.toString()
                .toInt() else 0,
            camera = binding.addCameraSPET.text.toString(),
            battery = if (binding.addBatterySPET.text.isNotEmpty()) binding.addBatterySPET.text.toString()
                .toInt() else 0
        )
        val bundle = Bundle()
        bundle.putParcelable(ListFragment.KEY_BUNDLE_ELECTRONIC, smartphone)
        setFragmentResult(ListFragment.KEY_REQUEST_ELECTRONIC, bundle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
