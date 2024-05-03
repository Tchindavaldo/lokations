package com.example.lokations

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.lang.Thread.sleep

class splash_activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val td = Thread {
            try {
                sleep(5000)
            } catch (ex: InterruptedException) {
                ex.printStackTrace()
            } finally {
                val intent = Intent(this@splash_activity, login::class.java)
                startActivity(intent)
                finish()
            }
        }
        td.start()
    }
}

/*
  private val DELAY_1: Long = 5000 // Délai en millisecondes pour le premier thread
    private val DELAY_2: Long = 6000 // Délai en millisecondes pour le deuxième thread

        val handler = Handler()

        // Premier thread avec un délai de 5000 ms avant d'afficher l'activité de démarrage
        val thread1 = Thread {
            try {
                Thread.sleep(DELAY_1)
            } catch (ex: InterruptedException) {
                ex.printStackTrace()
            } finally {
                handler.post {
                    // Afficher l'activité de démarrage
                    // Par exemple, vous pouvez créer une fonction pour afficher l'activité de démarrage
                    showStartupActivity()
                }
            }
        }
        thread1.start()

        // Deuxième thread avec un délai de 6000 ms avant de démarrer l'activité de connexion
        val thread2 = Thread {
            try {
                Thread.sleep(DELAY_2)
            } catch (ex: InterruptedException) {
                ex.printStackTrace()
            } finally {
                handler.post {
                    // Démarrer l'activité de connexion
                    val intent = Intent(this@SplashActivity, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
        thread2.start()
    }
 */