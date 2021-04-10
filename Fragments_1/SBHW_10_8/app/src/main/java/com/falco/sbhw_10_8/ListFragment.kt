package com.falco.sbhw_10_8

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.falco.sbhw_10_8.databinding.FragmentListBinding

class ListFragment : Fragment(R.layout.fragment_list) {
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val listItems = listOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5")
        val adapter = ArrayAdapter(requireContext(), R.layout.list_item, R.id.textItem, listItems)
        binding.itemListView.adapter = adapter
        binding.itemListView.setOnItemClickListener { parent, view, position, id ->
            fragmentManager?.beginTransaction()
                ?.replace(
                    R.id.mainFragmentContainer, DetailFragment.newInstance(
                        listItems[position],
                        "parent: $parent,\n view: $view,\n position: $position,\n id: $id"
                    )
                )
                ?.addToBackStack(id.toString())
                ?.commit()
        }
    }
}