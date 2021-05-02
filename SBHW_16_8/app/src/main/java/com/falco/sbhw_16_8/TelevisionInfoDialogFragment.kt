package com.falco.sbhw_16_8

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import com.falco.sbhw_16_8.databinding.DialogAddTelevisionBinding

class TelevisionInfoDialogFragment : DialogFragment() {

    private var _binding: DialogAddTelevisionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = DialogAddTelevisionBinding.inflate(LayoutInflater.from(context))
        return AlertDialog.Builder(requireContext())
            .setTitle(R.string.television)
            .setView(binding.root)
            .setPositiveButton(
                "Добавить"
            ) { _, _ -> createTelevisionInfo() }
            .setNegativeButton("Отмена") { _, _ -> dismiss() }
            .create()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.isSmartTVCB.setOnCheckedChangeListener { _, isChecked ->
            binding.addSmartPlatformTVET.isVisible = isChecked
        }
    }

    private fun createTelevisionInfo() {
        val television = Electronics.Television(
            name = binding.addNameTVET.text.toString(),
            imageLink = if (binding.addImageLinkTVET.text.isNotEmpty()) binding.addImageLinkTVET.text.toString() else getString(
                R.string.popuga_image_link
            ),
            screenSize = binding.addScreenSizeTVET.text.toString(),
            countryOfOrigin = binding.addCountryTVET.text.toString(),
            price = if (binding.addPriceTVET.text.isNotEmpty()) binding.addPriceTVET.text.toString()
                .toInt() else 0,
            isSmart = binding.isSmartTVCB.isChecked,
            smartPlatform = if (binding.isSmartTVCB.isChecked) binding.addSmartPlatformTVET.text.toString() else null,
            resolutionHD = binding.addResolutionHDTVET.text.toString()
        )
        val bundle = Bundle()
        bundle.putParcelable(ListFragment.KEY_BUNDLE_ELECTRONIC, television)
        setFragmentResult(ListFragment.KEY_REQUEST_ELECTRONIC, bundle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
