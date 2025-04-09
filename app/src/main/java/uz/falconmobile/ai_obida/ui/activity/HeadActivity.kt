package uz.falconmobile.ai_obida.ui.activity

import android.content.Intent
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import uz.falconmobile.ai_obida.R
import uz.falconmobile.ai_obida.databinding.ActivityHeadBinding

class HeadActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHeadBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHeadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.bottomAppBar

        val navController = findNavController(R.id.nav_host_fragment_activity_head)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        binding.centerIcon.setOnClickListener {
            startActivity(Intent(this@HeadActivity, MainActivity::class.java))
        }
        navView.setupWithNavController(navController)
    }
}