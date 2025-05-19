package com.example.londondestination

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.navigation.fragment.findNavController
import androidx.core.os.bundleOf
import com.example.londondestination.databinding.HomeFragmentBinding


class HomeFragment : Fragment() {

    private var _binding: HomeFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: MyAdapter
    private val myDataList = listOf(
        MyData("Tower Bridge", "Ikon jembatan di London", 1894, "https://en.wikipedia.org/wiki/Tower_Bridge"),
        MyData("London Eye", "Kincir raksasa di tepi Sungai Thames", 2000, "https://en.wikipedia.org/wiki/London_Eye"),
        MyData("British Museum", "Museum terkenal dengan koleksi dunia", 1753, "https://en.wikipedia.org/wiki/British_Museum"),
        MyData("British Museum", "Museum terkenal dengan koleksi dunia", 1753, "https://en.wikipedia.org/wiki/British_Museum"),
        MyData("British Museum", "Museum terkenal dengan koleksi dunia", 1753, "https://en.wikipedia.org/wiki/British_Museum")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = MyAdapter(myDataList) { selectedItem ->
            val bundle = bundleOf(
                "imageResId" to R.drawable.ic_launcher_background,
                "nama" to selectedItem.nama,
                "deskripsi" to selectedItem.description
            )
            findNavController().navigate(R.id.action_HomeFragment_to_detailFragment, bundle)
        }

        binding.rvCharacter.layoutManager = LinearLayoutManager(requireContext())
        binding.rvCharacter.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
