package uz.falconmobile.ai_obida.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.falconmobile.ai_obida.R
import uz.falconmobile.ai_obida.databinding.ItemLayoutBinding
import uz.falconmobile.ai_obida.models.locate_model
import uz.falconmobile.ai_obida.service.DatabaseHelper

class MyAdapterRemove(
    val context: Context,
    private var itemList: MutableList<locate_model>, // MutableList for modification
    private val itemClickListener: OnItemClickListener // Item click listener
) : RecyclerView.Adapter<MyAdapterRemove.MyViewHolder>() {

    // Define the interface for item click
    interface OnItemClickListener {
        fun onItemClick(item: locate_model)
    }

    private val dbHelper = DatabaseHelper(context)

    // A cache for items that are already in the database
    private val savedItems = mutableSetOf<String>()

    init {
        // Preload existing items from the database
        for (item in itemList) {
            if (dbHelper.isLocateModelExists(item.id)) {
                savedItems.add(item.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        if (position < itemList.size) { // Ensure valid position
            val item = itemList[position]
            holder.binding.name.text = item.name_uz
            holder.binding.image.setImageResource(item.image)

            // Check if the item is saved
            val isSaved = savedItems.contains(item.id)
            holder.binding.favorite.setImageResource(
                if (isSaved) R.drawable.save else R.drawable.heart
            )

            holder.binding.favorite.setOnClickListener {
                if (isSaved) {
                    // Delete from database and remove from the list
                    dbHelper.deleteLocateModel(item.id)
                    savedItems.remove(item.id)
                    itemList.removeAt(position) // Remove the item from the list
                    if (itemList.isEmpty()) {
                        // If the list is empty, ensure no further item removal attempts are made
                        notifyDataSetChanged()
                    } else {
                        // Notify the adapter about the item removal
                        notifyItemRemoved(position)
                    }
                } else {
                    // Insert into database
                    dbHelper.insertLocateModel(item)
                    savedItems.add(item.id)
                    holder.binding.favorite.setImageResource(R.drawable.save)
                }
            }

            // Set the click listener
            holder.itemView.setOnClickListener {
                // Call the listener's onItemClick method when the item is clicked
                itemClickListener.onItemClick(item)
            }
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class MyViewHolder(val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root)
}
