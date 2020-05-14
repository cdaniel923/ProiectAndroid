package com.daniel.proiect.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.daniel.proiect.databinding.FragmentDetailBinding

class DetailFragment : Fragment()
{
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View?
    {

        val amiibo = DetailFragmentArgs.fromBundle(requireArguments()).selectedAmiibo
        val viewModelFactory = DetailViewModelFactory(amiibo, requireNotNull(activity).application)
        val viewModel = ViewModelProviders.of(this, viewModelFactory).get(DetailViewModel::class.java)

        val binding = FragmentDetailBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }
}
