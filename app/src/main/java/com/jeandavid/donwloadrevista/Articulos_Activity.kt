package com.jeandavid.donwloadrevista

import Adapter.Adapter_Articulos
import Class.Class_Articulo
import android.app.DownloadManager
import android.content.Context
import android.graphics.Color
import android.os.Build
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

class Articulos_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_articulos)

        val bundle=intent.extras
        articulosVolley(bundle?.getString("info").toString())
    }


    fun articulosVolley(parm: String) {
        val queue = Volley.newRequestQueue(this)
        val url: String = "https://revistas.uteq.edu.ec/ws/pubs.php?i_id="+parm
        val stringReq = StringRequest(
            Request.Method.GET, url,
            { response ->
                var arrayList = ArrayList<Class_Articulo>()
                var strResp = response.toString()
                var str: JSONArray = JSONArray(strResp)

                arrayList = Class_Articulo.JsonObjectsBuild(str)
                articulosView(arrayList)

            },
            { Log.d("downloadrevista", "that didn't work") })
        queue.add(stringReq)
    }

    private fun articulosView(list: ArrayList<Class_Articulo>)
    {
        val recyclerView_ : RecyclerView =findViewById(R.id.recycler_articulos)
        val downmanager = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            val adapter=Adapter_Articulos(this,this,downmanager,list)

            recyclerView_.layoutManager= LinearLayoutManager(this)
            recyclerView_.adapter=adapter

            val res = R.anim.layout_animation_down_to_up
            val anim = AnimationUtils.loadLayoutAnimation(
                applicationContext,
                res
            )
            recyclerView_.layoutAnimation = anim
        }


    }
}