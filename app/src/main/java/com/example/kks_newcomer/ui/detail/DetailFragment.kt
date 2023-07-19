package com.example.kks_newcomer.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.example.kks_newcomer.data.Attraction
import com.example.kks_newcomer.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val data = arguments?.getParcelable<Attraction>("attraction")

        if (data?.images != null) {
            binding.recyclerImages.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = ImageGalleryAdapter(data.images.map { it.src })
                PagerSnapHelper().attachToRecyclerView(this)
            }
        } else {
            binding.recyclerImages.isVisible = false
        }
        binding.textName.text = data?.name
        binding.textAddress.text = data?.address
        binding.textTel.text = data?.tel
        binding.textWebsite.text = data?.officialSite
        binding.textIntroduction.text = data?.introduction
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}