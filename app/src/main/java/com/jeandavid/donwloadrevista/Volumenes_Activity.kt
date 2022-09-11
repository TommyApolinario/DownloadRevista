package com.jeandavid.donwloadrevista


import Adapter.Adapter_Volumenes
import Class.Class_Volumenes
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

class Volumenes_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_volumenes)

        val bundle=intent.extras
        volumenesVolley(bundle?.getString("info").toString())
    }

    fun volumenesVolley(parm: String) {
        val queue = Volley.newRequestQueue(this)
        val url: String = "https://revistas.uteq.edu.ec/ws/issues.php?j_id="+parm

        val stringReq = StringRequest(
            Request.Method.GET, url,
            { response ->
                var arrayList = ArrayList<Class_Volumenes>()
                var strResp = response.toString()
                var str: JSONArray = JSONArray(strResp)
                arrayList = Class_Volumenes.JsonObjectsBuild(str)

                volumenesView(arrayList)

            },
            { Log.d("downloadrevista", "that didn't work") })
        queue.add(stringReq)
    }

    private fun volumenesView(userList: ArrayList<Class_Volumenes>)
    {
        val recyclerView_ : RecyclerView =findViewById(R.id.recycler_volumenes)
        val adapter_=Adapter_Volumenes(this, userList)

        recyclerView_.layoutManager= LinearLayoutManager(this,RecyclerView.HORIZONTAL,false)
        recyclerView_.adapter=adapter_

        val res = R.anim.layout_animation_down_to_up
        val anim = AnimationUtils.loadLayoutAnimation(
            applicationContext,
            res
        )
        recyclerView_.layoutAnimation = anim
    }
}