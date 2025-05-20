package com.example.londondestination

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.londondestination.databinding.DetailFragmentBinding


class FragmentGuweh : Fragment() {

    private var _binding: DetailFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageResId = arguments?.getInt("imageResId") ?: R.drawable.ic_launcher_background
        val nama = arguments?.getString("nama") ?: "Nama tidak tersedia"
        val deskripsi = arguments?.getString("deskripsi") ?: "Deskripsi tidak tersedia"

        binding.detailImage.setImageResource(imageResId)
        binding.detailTitle.text = nama
        binding.detailDescription.text = deskripsi
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    companion object {
        fun newInstance(imageResId: Int, nama: String, deskripsi: String): FragmentGuweh {
            val fragment = FragmentGuweh()
            val args = Bundle()
            args.putInt("imageResId", imageResId)
            args.putString("nama", nama)
            args.putString("deskripsi", deskripsi)
            fragment.arguments = args
            return fragment
        }
    }
}
