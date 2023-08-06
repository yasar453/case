package com.homework.homeworkcase.view

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.homework.homeworkcase.R
import com.homework.homeworkcase.databinding.FragmentModeminfoBinding
import com.homework.homeworkcase.model.Flag
import com.homework.homeworkcase.utill.Flaginf
import com.homework.homeworkcase.viewmodel.ModeminfoViewModel


class ModeminfoFragment : Fragment() {

    private lateinit var viewModel: ModeminfoViewModel
    private lateinit var binding: FragmentModeminfoBinding
    private var modemid=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_modeminfo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding=FragmentModeminfoBinding.bind(view)
        arguments.let {
        modemid=ModeminfoFragmentArgs.fromBundle(it!!).modemid
        }
        viewModel=ViewModelProvider(this).get(ModeminfoViewModel::class.java)
        viewModel.getDataWithid(modemid)
        deviceinfoDataObserve()
        viewModel.isEditable()
        binding.savenewtitleBtn.setOnClickListener {
            val newTitleText=binding.titleEditextInfofragment.text.toString()
            viewModel.saveSpeacialTitle(newTitleText)
            val action=ModeminfoFragmentDirections.actionModeminfoFragmentToDeviceListFragment(0)
            Navigation.findNavController(view).navigate(action)
            viewModel.upDateShared(Flag(Flaginf.Editable.flagName,true))

        }
        viewModel.upDateShared(Flag(Flaginf.Editable.flagName,false))

    }



    fun deviceinfoDataObserve(){
        viewModel.modeDeviceData.observe(this, Observer{
            binding.titleInfofragment.text=it.DeviceTitleName
            binding.firmwareInfofragment.text=" Firmware : "+it.Firmware
            binding.snInfofragment.text="SN: "+it.Server_Event
            binding.modelInfofragment.text="Model: "+it.Platform
            binding.macaddresInfofragment.text="MacAdress :"+it.MacAddress
            binding.titleEditextInfofragment.setText(it.DeviceTitleName)
            binding.modemPicInfofragment.apply {
                val id=context.resources.getIdentifier(it.DevicePictures,"drawable",context.packageName)
                Glide.with(context)
                    .load(id)
                    .into(binding.modemPicInfofragment)
            }
        })

        viewModel.editable.observe(this, Observer {
            if (it) {
                 val editText=binding.titleEditextInfofragment
                editText.visibility= View.VISIBLE
                binding.titleInfofragment.visibility=View.GONE
                binding.savenewtitleBtn.visibility=View.VISIBLE
                editText.requestFocus()
                val inputMethodManager = context!!.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
            }
        })


    }




}