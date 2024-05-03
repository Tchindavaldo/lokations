package com.example.lokations

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class activity_update_produit : AppCompatActivity() {

    lateinit var updatedUsers : ArrayList<HashMap<String, Any>>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.decorView.systemUiVisibility= View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        window.statusBarColor = Color.TRANSPARENT
        supportActionBar?.hide()
        setContentView(R.layout.activity_update_produit)




        overridePendingTransition(R.anim.fade_in,R.anim.fade_out)


        val db = Firebase.firestore

        val modifier: CardView = findViewById(R.id.modifier)
        val supprimer: CardView = findViewById(R.id.supprimer)

        val itemView : EditText = findViewById(R.id.email)
        val prixView: EditText = findViewById(R.id.password)
        val id: EditText = findViewById(R.id.id)

        val docRef = db.collection("users").document("user")
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null && document.exists()) {

                    Toast.makeText(this,"recuperation de donné" , Toast.LENGTH_SHORT).show()
                    val users = document.get("listOfUsers") as List<HashMap<String, Any>>


                     updatedUsers = ArrayList(users)

                        val itemRecup = updatedUsers[0]["item"] as String
                        val prixRecup = updatedUsers[0]["prix"] as String

                    itemView.text=Editable.Factory.getInstance().newEditable(itemRecup)
                    prixView.text=Editable.Factory.getInstance().newEditable(prixRecup)



                } else {
                    Toast.makeText(this,"pas de document USER ", Toast.LENGTH_SHORT).show()

                }
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this,"erreur lors du get" , Toast.LENGTH_SHORT).show()


            }
        modifier.setOnClickListener {
            //    val bit2 =  Intent(this@activity_ajout_produit, HomeActivity::class.java)
            //   startActivity(bit2)
            val newItem  = itemView.text.toString()
            val newPrice  = prixView.text.toString()
            val id  = id.text.toString().toInt()

            val newData = hashMapOf<String, Any>(
                "item" to newItem,
                "prix" to newPrice,
            )

            updatedUsers[id]=newData

            docRef.set(hashMapOf("listOfUsers" to updatedUsers))
                .addOnSuccessListener { Toast.makeText(this,"mise ajour avec success" , Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener { e -> Toast.makeText(this,"erreur $e", Toast.LENGTH_SHORT).show()
                }
        }
        supprimer.setOnClickListener {
            //    val bit2 =  Intent(this@activity_ajout_produit, HomeActivity::class.java)
            //   startActivity(bit2)
            val id  = id.text.toString().toInt()
           updatedUsers.removeAt(id)

            docRef.set(hashMapOf("listOfUsers" to updatedUsers))
                .addOnSuccessListener { Toast.makeText(this,"mise ajour avec success" , Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener { e -> Toast.makeText(this,"erreur $e", Toast.LENGTH_SHORT).show()
                }
        }

    }
}