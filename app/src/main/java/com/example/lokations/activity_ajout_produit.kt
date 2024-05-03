package com.example.lokations

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class activity_ajout_produit : AppCompatActivity() {


    lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility= View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        window.statusBarColor = Color.TRANSPARENT
        supportActionBar?.hide()

        setContentView(R.layout.activity_ajout_produit)


        overridePendingTransition(R.anim.fade_in,R.anim.fade_out)


        val db = Firebase.firestore
        val ajout_produit: CardView = findViewById(R.id.ajout_produit)
        val ajout_produit_set: CardView = findViewById(R.id.ajout_produit_set)
        val emailView : EditText = findViewById(R.id.email)
        val passwordView: EditText = findViewById(R.id.password)





        ajout_produit.setOnClickListener {
         //    val bit2 =  Intent(this@activity_ajout_produit, HomeActivity::class.java)
         //   startActivity(bit2)

            val docRef = db.collection("users").document("user")
            val email  = emailView.text.toString()
            val password  = passwordView.text.toString()


// Create a new user with a first, middle, and last name
            val newUser = listOf( hashMapOf(
                "email" to email,
                "password" to password,
                "age" to 20,
                "gender" to "garçon"
            ),  hashMapOf(
                    "email" to email,
                "password" to password,
                "age" to 20,
                "gender" to "garçon"
            )  )


            docRef.set(hashMapOf("listOfUsers" to newUser))
                .addOnSuccessListener { Toast.makeText(this,"donné n°1 ajouté avec success" , Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener { e -> Toast.makeText(this,"erreur sur donné n°1 ajouté $e", Toast.LENGTH_SHORT).show()
                }
        }


        ajout_produit_set.setOnClickListener {
         //    val bit2 =  Intent(this@activity_ajout_produit, HomeActivity::class.java)
         //   startActivity(bit2)
            val email  = emailView.text.toString()
            val password  = passwordView.text.toString()

// Add a new document with a generated ID

            val docRef = db.collection("users").document("user")
            docRef.get()
                .addOnSuccessListener { document ->
                    if (document != null && document.exists()) {
                        Toast.makeText(this,"recuperation de donné" , Toast.LENGTH_SHORT).show()
                        val users = document.get("listOfUsers") as List<HashMap<String, Any>>

                        // Créer le nouvel utilisateur
                        val newUser = hashMapOf<String, Any>(
                            "item" to email,
                            "prix" to password,
                        )

                        // Ajouter le nouvel utilisateur à la liste
                        val updatedUsers = ArrayList(users)
                        updatedUsers.add(newUser)

                        // Réécrire le document avec la nouvelle liste d'utilisateurs
                        docRef.set(hashMapOf("listOfUsers" to updatedUsers))
                            .addOnSuccessListener { Toast.makeText(this,"ecrit avec success" , Toast.LENGTH_SHORT).show()
                            }
                            .addOnFailureListener { e -> Toast.makeText(this,"erreur $e", Toast.LENGTH_SHORT).show()
                            }

                    } else {
                        Toast.makeText(this,"pas de document USER ", Toast.LENGTH_SHORT)
                        val newUser = listOf( hashMapOf(
                        "item" to email,
                        "prix" to password,
                    )  )


                        docRef.set(hashMapOf("listOfUsers" to newUser))
                            .addOnSuccessListener { Toast.makeText(this,"donné n°1 ajouté avec success" , Toast.LENGTH_SHORT).show()
                            }
                            .addOnFailureListener { e -> Toast.makeText(this,"erreur sur donné n°1 ajouté $e", Toast.LENGTH_SHORT).show()
                            }
                    }
                }
                .addOnFailureListener { exception ->
                    Toast.makeText(this,"erreur lors du get" , Toast.LENGTH_SHORT).show()
                }
        }
    }
}