package com.jeandavid.donwloadrevista


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var imagenini: ImageView = findViewById(R.id.image_inicio)

        Picasso.get()
            .load("https://www.uteq.edu.ec/images/page/4/l_uteq.png")
            .into(imagenini);

        var boton: Button = findViewById(R.id.btnIngresar)

        boton.setOnClickListener {
            val intent = Intent(this, Revistas_Activity::class.java)
            startActivity(intent)
        }


    }

}
