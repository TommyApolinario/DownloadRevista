package Class


import com.jeandavid.donwloadrevista.R
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class Class_Articulo(a: JSONObject) {
    var id: String
    var nombre: String
    var publicacion: String
    var img: Int
    var url:String
    var urlhtml:String
    var linkPD:String
    var seccion: String
    var doi:String

    companion object {
        @Throws(JSONException::class)
        fun JsonObjectsBuild(jsonArray: JSONArray): ArrayList<Class_Articulo> {
            val publicacion: ArrayList<Class_Articulo> = ArrayList<Class_Articulo>()
            var i = 0
            while (i < jsonArray.length()) {
                publicacion.add(Class_Articulo(jsonArray.getJSONObject(i)))
                i++
            }
            return publicacion
        }
    }

    init {
        nombre = a.getString("title").toString()
        publicacion = a.getString("date_published").toString()
        id = a.getString("publication_id").toString()
        img= R.drawable.ic_download
        var asd=a.getJSONArray("galeys")
        url=asd.getJSONObject(0).getString("UrlViewGalley")
        urlhtml=asd.getJSONObject(0).getString("UrlViewGalley")
        seccion=a.getString("section").toString()
        linkPD=""
        doi=a.getString("doi").toString()

        //URL para descargar PDF
        var rev1="csye"
        var rev2="cyt"
        var rev3="ingenio"
        var submission_id=a.getString("submission_id").toString()
        var galley_id=asd.getJSONObject(0).getString("galley_id")
        var file_id=asd.getJSONObject(0).getString("file_id")

        var preUrl="https://revistas.uteq.edu.ec/index.php/"

        if(url.contains(rev1)){
            preUrl=preUrl+ rev1+"/article/download/$submission_id/$galley_id/$file_id"+".pdf"
        }
        else if(url.contains(rev2)){
            preUrl=preUrl+rev2+"/article/download/$submission_id/$galley_id/$file_id"+".pdf"
        }
        else if(url.contains(rev3)){
            preUrl=preUrl+rev3+"/article/download/$submission_id/$galley_id/$file_id"+".pdf"
        }
        else{preUrl="fail"}
        linkPD=preUrl

    }

}