package com.example.lokations
import android.annotation.SuppressLint
import android.app.DownloadManager

import android.view.Window
import android.view.WindowManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.OpenableColumns
import android.util.Log
import android.view.View
import android.webkit.MimeTypeMap
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.cardview.widget.CardView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.renderscript.Allocation
import androidx.renderscript.Element
import androidx.renderscript.RenderScript
import androidx.renderscript.ScriptIntrinsicBlur
import com.example.lokations.data.model.network.Post
import com.example.lokations.data.model.network.UploadZik
import com.example.lokations.data.model.network.Uploadtexte
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import jp.wasabeef.blurry.Blurry
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.*
import java.net.URL

class login : AppCompatActivity() {
    val uploadtxt = Uploadtexte()
    val uploadZik = UploadZik()
    val post = Post()
    lateinit var auth : FirebaseAuth

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

             FirebaseApp.initializeApp(this)
        window.decorView.systemUiVisibility= View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        window.statusBarColor = Color.TRANSPARENT
        supportActionBar?.hide()

        setContentView(R.layout.activity_login)
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out)



        auth= Firebase.auth
       // val imageview: ImageView = findViewById(R.id.background_login_image)
        val connexion: CardView = findViewById(R.id.connexion)
        val link_to_inscription: TextView = findViewById(R.id.link_to_inscription)


       // val bitmap = BitmapFactory.decodeResource(resources, R.drawable.login_img1)
       // val blurredBitmap = blurBitmap(bitmap,10f)
       // imageview.setImageBitmap(blurredBitmap)

        /*Blurry.with(this)
            .radius(25)
            .sampling(2)
            .color(Color.argb(66, 255, 255, 0))
            .capture(imageview)
            .into(imageview);
        */

        //   val update : Update = Update()
        //  update.updateUser(this@login)
       // post.postUser(this@login)
          connexion.setOnClickListener {

              val bit =  Intent(this@login, HomeActivity::class.java)
              val emailView :EditText  = findViewById(R.id.email)
              val passwordView:EditText  = findViewById(R.id.password)

              val email  = emailView.text.toString()
              val password  = passwordView.text.toString()


              auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->if (task.isSuccessful){
             //     val user = auth.currentUser

                  Toast.makeText(this,email ,Toast.LENGTH_SHORT).show()
                  startActivity(bit)

                  finish()
              } else{
                  Toast.makeText(this,"error" ,Toast.LENGTH_SHORT).show()
               } }.addOnFailureListener { exception -> Toast.makeText(applicationContext, exception.localizedMessage,Toast.LENGTH_LONG).show() }
          }

        link_to_inscription.setOnClickListener {
            val bit2 =  Intent(this@login, activity_register::class.java)
            startActivity(bit2)

        }
                 //


      //  connexion.setOnClickListener {
       //     val intent = Intent(Intent.ACTION_GET_CONTENT)


           // filePickerResult.launch("*/*")


       // }





    }

    fun blurBitmap(bitmap: Bitmap, radius : Float): Bitmap {
        val rs = RenderScript.create(this)

        val blurScript = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs))

        val input = Allocation.createFromBitmap(rs, bitmap)
        val output = Allocation.createTyped(rs, input.type)

        blurScript.setRadius(radius)

        blurScript.setInput(input)
        blurScript.forEach(output)

        output.copyTo(bitmap)

        return bitmap
    }


    @SuppressLint("Range")
    val filePickerResult = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        if (uri != null) {
            val inputStream = contentResolver.openInputStream(uri)
            val bytes = inputStream?.readBytes()


            // Récupérer le chemin du fichier sélectionné
            val cursor = contentResolver.query(uri, null, null, null, null)
            if (cursor != null && cursor.moveToFirst()) {
                val fileName = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME))
                val fileExtension = MimeTypeMap.getSingleton().getExtensionFromMimeType(contentResolver.getType(uri))
                // Utilisez displayName et extension comme paramètres pour uploadMusic

                if (fileExtension != null) {
                    uploadZik.uploadMusic(this@login, bytes,fileName,fileExtension)
                }
            }
            cursor?.close()

        }
    }


}