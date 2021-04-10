package com.falco.sbhw_10_8

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.falco.sbhw_10_8.databinding.FragmentDetailBinding

class DetailFragment : Fragment(R.layout.fragment_detail) {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.nameTextView.text = requireArguments().getString(KEY_NAME)
        binding.descriptionTextView.text = requireArguments().getString(KEY_DESCRIPTION)
    }

    companion object {
        private const val KEY_NAME = "key_name"
        private const val KEY_DESCRIPTION = "key_description"
        fun newInstance(name: String, description: String): DetailFragment {
            return DetailFragment().withArguments {
                putString(KEY_NAME, name)
                putString(KEY_DESCRIPTION, description)
            }
        }
    }
}