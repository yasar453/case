package com.homework.homeworkcase.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.homework.homeworkcase.R
import com.homework.homeworkcase.adapter.DeviceRecylerAdapter
import com.homework.homeworkcase.databinding.FragmentModemListBinding
import com.homework.homeworkcase.model.Flag
import com.homework.homeworkcase.model.PopUp
import com.homework.homeworkcase.servis.CallRecylerViewint
import com.homework.homeworkcase.utill.Flaginf
import com.homework.homeworkcase.viewmodel.DeviceListViewModel


class DeviceListFragment : Fragment(),CallRecylerViewint {

    private lateinit var viewModel: DeviceListViewModel
    private val deviceRecylerAdapter= DeviceRecylerAdapter(arrayListOf(),this)
    private lateinit var binding:FragmentModemListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_modem_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel=ViewModelProvider(this).get(DeviceListViewModel::class.java)


        binding=FragmentModemListBinding.bind(view)
        binding.devicelistRecylerview.layoutManager=LinearLayoutManager(context)
        binding.devicelistRecylerview.adapter=deviceRecylerAdapter
        viewModel.getModemData()
        viewModel.updateShared(Flag(Flaginf.Editable.flagName,false))
        deviceLiveDataObserve()
        binding.buttonReset.setOnClickListener {
        viewModel.updateShared(Flag(Flaginf.Refresh.flagName,true))
        viewModel.updateShared(Flag(Flaginf.Editable.flagName,false))

            binding.devicelistRecylerview.removeAllViews()
            val action=DeviceListFragmentDirections.actionDeviceListFragmentToGuideFragment()
            Navigation.findNavController(it).navigate(action)
        }



    }
    fun deviceLiveDataObserve(){
        viewModel.deviceList.observe(this, Observer { devices ->
            devices.let {
                deviceRecylerAdapter.refreshRecylerView(it)
                binding.progressBar.visibility=View.GONE
            }



        })
        viewModel.isDataLoading.observe(this,Observer{
            binding.progressBar.visibility=View.GONE
            if (it) {
                binding.progressBar.visibility=View.VISIBLE

            }

        })



    }

    override fun sendShPrefercens(flag: Flag) {
        viewModel.updateShared(flag)
    }

    override fun PopupCall(popUp: PopUp) {
        viewModel.handlePopUp(popUp)
        val action=DeviceListFragmentDirections.actionDeviceListFragmentToGuideFragment()
        this?.view?.let { Navigation.findNavController(it).navigate(action) }

    }


}