package uz.falconmobile.ai_obida.old;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import uz.falconmobile.ai_obida.R;

public class Procpectus extends AppCompatActivity {

    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_procpectus);
        iv = findViewById(R.id.imageView3);
        String chosen = (String) getIntent().getStringExtra("name");
        if (chosen.equals("dolorex")) {
            iv.setImageResource(R.drawable.dolorex);
        }
        if (chosen.equals("aferin")) {
            iv.setImageResource(R.drawable.aferin);
        }
        if (chosen.equals("parol")) {
            iv.setImageResource(R.drawable.parol);
        }
        if (chosen.equals("nurofen")) {
            iv.setImageResource(R.drawable.nurofen);
        }
        if (chosen.equals("majezik")) {
            iv.setImageResource(R.drawable.majezik);
        }
        Toast.makeText(this, chosen, Toast.LENGTH_SHORT).show();

    }

}
