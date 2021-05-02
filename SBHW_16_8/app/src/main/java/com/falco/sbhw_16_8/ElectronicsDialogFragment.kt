package com.falco.sbhw_16_8

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.falco.sbhw_16_8.databinding.DialogAddElectronicsBottomsheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ElectronicsDialogFragment : BottomSheetDialogFragment() {

    private var _binding: DialogAddElectronicsBottomsheetBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogAddElectronicsBottomsheetBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClickListeners()
    }

    private fun setClickListeners() {
        binding.addSmartphoneTextView.setOnClickListener {
            setFragmentResult(ListFragment.KEY_REQUEST_TYPE, bundleOf(ListFragment.KEY_BUNDLE_TYPE to ListFragment.TYPE_SP))
            Log.e("life", "result")
            dismiss()
        }
        binding.addTelevisionTextView.setOnClickListener {
            setFragmentResult(ListFragment.KEY_REQUEST_TYPE, bundleOf(ListFragment.KEY_BUNDLE_TYPE to ListFragment.TYPE_TV))
        }
        binding.addGameConsoleTextView.setOnClickListener {
            setFragmentResult(ListFragment.KEY_REQUEST_TYPE, bundleOf(ListFragment.KEY_BUNDLE_TYPE to ListFragment.TYPE_GC))
        }
    }
}
