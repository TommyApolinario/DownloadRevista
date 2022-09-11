package com.jeandavid.donwloadrevista


import Adapter.Adapter_Revistas
import Class.Class_Revistas
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.animation.AnimationUtils
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray

class Revistas_Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_revistas)
        revistasVolley()
    }


    fun revistasVolley() {
        val queue = Volley.newRequestQueue(this)
        val url: String = "https://revistas.uteq.edu.ec/ws/journals.php"
        val stringReq = StringRequest(
            Request.Method.GET, url,
            { response ->
                var arrayList = ArrayList<Class_Revistas>()

                var strResp = response.toString()
                var str: JSONArray = JSONArray(strResp)
                arrayList = Class_Revistas.JsonObjectsBuild(str)

                revistasView(arrayList)
            },
            { Log.d("downloadrevista", "that didn't work") })
        queue.add(stringReq)
    }


    private fun revistasView(userList: ArrayList<Class_Revistas>)
    {
        val intent= Intent(this, Volumenes_Activity::class.java)
        val recyclerView : RecyclerView =findViewById(R.id.recycler_revista)
        val adapter=Adapter_Revistas(this,intent,userList)

        recyclerView.layoutManager= LinearLayoutManager(this)
        recyclerView.adapter=adapter
        val res = R.anim.layout_animation_down_to_up
        val anim = AnimationUtils.loadLayoutAnimation(
            applicationContext,
            res
        )
        recyclerView.layoutAnimation = anim
    }


}