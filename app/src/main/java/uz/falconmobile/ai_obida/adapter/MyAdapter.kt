package uz.falconmobile.ai_obida.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.falconmobile.ai_obida.R
import uz.falconmobile.ai_obida.databinding.ItemLayoutBinding
import uz.falconmobile.ai_obida.models.locate_model

class MyAdapter(private val itemList: List<locate_model>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = itemList[position]
        holder.binding.name.text = item.name_uz
        holder.binding.image.setImageResource(item.image)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class MyViewHolder(val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root)
}
