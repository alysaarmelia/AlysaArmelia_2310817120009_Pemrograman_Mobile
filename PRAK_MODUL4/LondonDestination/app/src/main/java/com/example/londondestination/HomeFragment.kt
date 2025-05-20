package com.example.londondestination

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.londondestination.databinding.HomeFragmentBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var _binding: HomeFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: MyAdapter

    private val viewModel: HomeViewModel by viewModels {
        HomeViewModelFactory()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = MyAdapter(emptyList()) { selectedItem ->
            viewModel.onItemClicked(selectedItem)
        }

        binding.rvCharacter.layoutManager = LinearLayoutManager(requireContext())
        binding.rvCharacter.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.destinationList.collectLatest { list ->
                adapter = MyAdapter(list) { selectedItem ->
                    viewModel.onItemClicked(selectedItem)
                }
                binding.rvCharacter.adapter = adapter
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.selectedItem.collectLatest { item ->
                item?.let {
                    Log.d("HomeFragment", "Navigasi ke DetailFragment untuk: ${it.nama}, Tahun: ${it.year}, Deskripsi: ${it.description}")

                    val bundle = bundleOf(
                        "imageResId" to it.image,
                        "nama" to it.nama,
                        "deskripsi" to it.description
                    )
                    findNavController().navigate(R.id.action_HomeFragment_to_detailFragment, bundle)
                    viewModel.resetSelectedItem()
                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
