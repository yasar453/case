package com.homework.homeworkcase.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.homework.homeworkcase.R
import com.homework.homeworkcase.databinding.FragmentGuideBinding
import com.homework.homeworkcase.databinding.FragmentModemListBinding


class GuideFragment : Fragment() {
    private lateinit var binding: FragmentGuideBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_guide, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding=FragmentGuideBinding.bind(view)
        binding.buttonBackGuide.setOnClickListener {
            val action=GuideFragmentDirections.actionGuideFragmentToDeviceListFragment()
            Navigation.findNavController(it).navigate(action)
        }
        binding.root.setOnLongClickListener() {
            val action=GuideFragmentDirections.actionGuideFragmentToDeviceListFragment()
            Navigation.findNavController(it).navigate(action)
            false
        }

    }
}