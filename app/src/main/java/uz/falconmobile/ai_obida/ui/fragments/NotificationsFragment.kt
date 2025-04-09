package uz.falconmobile.ai_obida.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import uz.falconmobile.ai_obida.adapter.MyAdapter
import uz.falconmobile.ai_obida.adapter.MyAdapterRemove
import uz.falconmobile.ai_obida.databinding.FragmentNotificationsBinding
import uz.falconmobile.ai_obida.models.locate_model
import uz.falconmobile.ai_obida.service.DatabaseHelper
import uz.falconmobile.ai_obida.service.SharedPreferenceHelper
import uz.falconmobile.ai_obida.ui.activity.ShowActivity

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root
         val dbHelper = DatabaseHelper(requireActivity())

        var list=dbHelper.getAllLocateModels()
        val recyclerView: RecyclerView = binding.grid
        recyclerView.setLayoutManager(GridLayoutManager(requireActivity(), 2)) // 2 ustun


        val adapter = MyAdapterRemove(requireActivity(), list.toMutableList(), object : MyAdapterRemove.OnItemClickListener {
            override fun onItemClick(item: locate_model) {
                SharedPreferenceHelper.saveData(requireActivity(), item.id)
                startActivity(Intent(requireActivity(), ShowActivity::class.java))
            }
        })
        recyclerView.setAdapter(adapter)


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}