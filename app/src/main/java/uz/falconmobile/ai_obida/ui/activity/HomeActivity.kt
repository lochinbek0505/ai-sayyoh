package uz.falconmobile.ai_obida.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.falconmobile.ai_obida.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    lateinit var binding: ActivityHomeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding= ActivityHomeBinding.inflate(layoutInflater)

        setContentView(binding.root)


        CoroutineScope(Dispatchers.Main).launch {

            delay(3000)
            startActivity(Intent(this@HomeActivity, MainActivity::class.java))

        }


    }
}