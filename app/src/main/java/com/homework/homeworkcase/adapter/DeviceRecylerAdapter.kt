package com.homework.homeworkcase.adapter



import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.homework.homeworkcase.databinding.ModemlistRecylerRowBinding
import com.homework.homeworkcase.model.DeviceModemData
import com.homework.homeworkcase.model.Flag
import com.homework.homeworkcase.model.PopUp
import com.homework.homeworkcase.servis.CallRecylerViewint
import com.homework.homeworkcase.utill.CallPopup
import com.homework.homeworkcase.utill.Flaginf
import com.homework.homeworkcase.view.DeviceListFragmentDirections



class DeviceRecylerAdapter(val deviceList:ArrayList<DeviceModemData>,val handler:CallRecylerViewint):RecyclerView.Adapter<DeviceRecylerAdapter.DeviceViewHolder>() {
    private lateinit var binding: ModemlistRecylerRowBinding
    class DeviceViewHolder(  itemVie:ModemlistRecylerRowBinding):RecyclerView.ViewHolder(itemVie.root) {

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeviceViewHolder {
        val inflater=LayoutInflater.from(parent.context)
         binding=ModemlistRecylerRowBinding.inflate(inflater,parent,false)
        return  DeviceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DeviceViewHolder, position: Int) {
        binding.modemlistBaslikRecylerrow.text=deviceList[position].DeviceTitleName
        binding.modemlistSnRecylerrow.text=deviceList[position].Server_Event
        holder.itemView.setOnClickListener {
            val action=DeviceListFragmentDirections.actionDeviceListFragmentToModeminfoFragment(deviceList[position].uid)
            Navigation.findNavController(it).navigate(action)

        }
        holder.itemView.setOnLongClickListener {
            val popupdata=PopUp(deviceList[position].DeviceTitleName,deviceList[position].uid)
            val callPopup=CallPopup(handler)
            callPopup.deletePopup(it.context,popupdata)

        true

        }

        binding.modemlistGoinfoBtnRecylerrow.setOnClickListener {
            val action=DeviceListFragmentDirections.actionDeviceListFragmentToModeminfoFragment(deviceList[position].uid)
            Navigation.findNavController(it).navigate(action)
            handler.sendShPrefercens(Flag(Flaginf.Refresh.flagName,false))
            handler.sendShPrefercens(Flag(Flaginf.Editable.flagName,true))
        }

        binding.modemlistPicRecylerrow.apply {

            val id=context.resources.getIdentifier(deviceList[position].DevicePictures,"drawable",context.packageName)
            Glide.with(context)
                .load(id)
                .into(binding.modemlistPicRecylerrow)
        }



        }

    override fun getItemCount(): Int {
       return deviceList.size
    }

    fun refreshRecylerView(newdevicelist:List<DeviceModemData>){
        deviceList.clear()
        deviceList.addAll(newdevicelist)
        notifyDataSetChanged()

    }





}


