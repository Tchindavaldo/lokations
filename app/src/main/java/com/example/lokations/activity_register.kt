package com.example.lokations

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class activity_register : AppCompatActivity() {


    lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        window.decorView.systemUiVisibility= View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        window.statusBarColor = Color.TRANSPARENT
        supportActionBar?.hide()

        setContentView(R.layout.activity_register)
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out)


        auth= Firebase.auth
        val inscription: CardView = findViewById(R.id.inscription)
        val link_to_connexion: TextView = findViewById(R.id.link_to_connexion)

        inscription.setOnClickListener {

            val bit =  Intent(this@activity_register, HomeActivity::class.java)
            val emailView : EditText = findViewById(R.id.email)
            val passwordView: EditText = findViewById(R.id.password)

            val email  = emailView.text.toString()
            val password  = passwordView.text.toString()

                 startActivity(bit)
            finish()
          /*  auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->if (task.isSuccessful){
                //     val user = auth.currentUser

                Toast.makeText(this,email , Toast.LENGTH_SHORT).show()
                startActivity(bit)
                finish()

            } else{
                Toast.makeText(this,"error" , Toast.LENGTH_SHORT).show()
            } }.addOnFailureListener { exception -> Toast.makeText(applicationContext, exception.localizedMessage,
                Toast.LENGTH_LONG).show() } */
        }

        link_to_connexion.setOnClickListener {
            val bit2 =  Intent(this@activity_register, login::class.java)
            startActivity(bit2)

             }
    }
}


/*
db.collection("users")
        .get()
        .addOnSuccessListener { result ->
            for (document in result) {
                Log.d(TAG, "${document.id} => ${document.data}")
            }
        }
        .addOnFailureListener { exception ->
            Log.w(TAG, "Error getting documents.", exception)
        }
 */


/*

// Create a storage reference from our app
var storageRef = storage.reference

// Create a reference to "mountains.jpg"
val mountainsRef = storageRef.child("mountains.jpg")

// Create a reference to 'images/mountains.jpg'
val mountainImagesRef = storageRef.child("images/mountains.jpg")

// While the file names are the same, the references point to different files
mountainsRef.name == mountainImagesRef.name // true
mountainsRef.path == mountainImagesRef.path // false
 */

/*
// Create a reference with an initial file path and name
val pathReference = storageRef.child("images/stars.jpg")

// Create a reference to a file from a Google Cloud Storage URI
val gsReference = storage.getReferenceFromUrl("gs://bucket/images/stars.jpg")

// Create a reference from an HTTPS URL
// Note that in the URL, characters are URL escaped!
val httpsReference = storage.getReferenceFromUrl(
        "https://firebasestorage.googleapis.com/b/bucket/o/images%20stars.jpg")
 */