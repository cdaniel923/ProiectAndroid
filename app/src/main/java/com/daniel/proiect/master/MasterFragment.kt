package com.daniel.proiect.master

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.daniel.proiect.databinding.FragmentMasterBinding

class MasterFragment : Fragment()
{
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View?
    {
        val viewModelFactory = MasterViewModelFactory(requireNotNull(activity).application)
        val viewModel = ViewModelProviders.of(this, viewModelFactory).get(MasterViewModel::class.java)
        viewModel.navigateToAmiiboDetails.observe(viewLifecycleOwner, Observer { amiibo ->
            if (amiibo != null)
            {
                val action = MasterFragmentDirections.actionShowDetail(amiibo)
                this.findNavController().navigate(action)
                viewModel.displayAmiiboDetailsComplete()
            }
        })

        val adapter = AmiiboGridAdapter(AmiiboGridAdapter.OnClickListener { amiibo ->
            viewModel.displayAmiiboDetails(amiibo)
        })

        val binding = FragmentMasterBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.photosGrid.adapter = adapter
        return binding.root
    }
}
