package Adapter


import Class.Class_Articulo
import android.app.*
import android.content.Context
import android.content.IntentFilter
import android.net.Uri
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.jeandavid.donwloadrevista.BroadcastReceiver
import com.jeandavid.donwloadrevista.R
import com.squareup.picasso.Picasso

class Adapter_Articulos constructor(activity_: Activity, context_: Context, downmanager: DownloadManager,
                                    var list: ArrayList<Class_Articulo>) :RecyclerView.Adapter<Adapter_Articulos.ViewHolder>() {

    val context: Context = context_
    val activity: Activity=activity_
    val downger: DownloadManager=downmanager
    var downloadid: Long = 0


    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): Adapter_Articulos.ViewHolder {
        val view =
            LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.cardview_articulos, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: Adapter_Articulos.ViewHolder, i: Int) {

        viewHolder.artid.text = list[i].nombre
        viewHolder.arttitulo.text=list[i].linkPD
        viewHolder.artfechpubli.text=list[i].publicacion
        viewHolder.arturl.text=list[i].url

        Picasso.get().load(R.drawable.downpdf).into(viewHolder.imageView2);

        viewHolder.imageView2.setOnClickListener{view: View ->
            DownPdf(viewHolder.arttitulo.text.toString())
        }
    }

    override fun getItemCount(): Int {
        return list.count()
    }

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
    {
        var artid: TextView
        var arttitulo: TextView
        var artfechpubli: TextView

        var arturl: TextView
        var imageView2:ImageView
        init {
            artid=itemView.findViewById(R.id.articulo_id)
            arttitulo=itemView.findViewById(R.id.articulo_titulo)
            artfechpubli=itemView.findViewById(R.id.articulo_fecha_publicacion)
            arturl=itemView.findViewById(R.id.articulo_link)
            imageView2=itemView.findViewById(R.id.imageView2)

        }
    }

    fun DownPdf(enlace: String) {

        val request =
            DownloadManager.Request(Uri.parse(enlace))
                .setDescription("Download PDF")
                .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE or DownloadManager.Request.NETWORK_WIFI)
                .setTitle("Download Pdf")
                .setAllowedOverMetered(true)
                .setDestinationInExternalFilesDir(context.applicationContext, Environment.DIRECTORY_DOWNLOADS,"downloadfile.pdf")

        try {
            downloadid = downger.enqueue(request)
            context.registerReceiver(BroadcastReceiver(downloadid), IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE))


        } catch (e: Exception) {
            Toast.makeText(context.applicationContext, "Error: " + e.message, Toast.LENGTH_LONG).show()
        }
    }

}
