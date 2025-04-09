package uz.falconmobile.ai_obida.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import uz.falconmobile.ai_obida.R
import uz.falconmobile.ai_obida.models.LanguageItem

class CustomSpinnerAdapter(context: Context, private val items: List<LanguageItem>) :
    ArrayAdapter<LanguageItem>(context, 0, items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createItemView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createItemView(position, convertView, parent)
    }

    private fun createItemView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.spinner_item, parent, false)
        val imageFlag = view.findViewById<ImageView>(R.id.image_flag)

        val item = items[position]
        imageFlag.setImageResource(item.flagResId)

        return view
    }
}
