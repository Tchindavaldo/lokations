package com.example.lokations

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout

@Suppress("UNREACHABLE_CODE")
class
MikelActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mikel)


        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)

        val fragment1  = zina1Fragment()
        val fragment2  = zina2Fragment()
        val fragment3  = zina3Fragment()
        supportFragmentManager.beginTransaction().replace(R.id.nivaFameLayout, fragment1).commit()


        val fmanager = supportFragmentManager




        val transac1 = fmanager.beginTransaction()
        val transac11 = fmanager.beginTransaction()
        val transac2 = fmanager.beginTransaction()
        val transac3 = fmanager.beginTransaction()

        val transac1s = fmanager.beginTransaction()
        val transac2s = fmanager.beginTransaction()
        val transac3s = fmanager.beginTransaction()

        val transac1d = fmanager.beginTransaction()
        val transac2d = fmanager.beginTransaction()
        val transac3d = fmanager.beginTransaction()

        val transac1h = fmanager.beginTransaction()
        val transac2h = fmanager.beginTransaction()
        val transac3h = fmanager.beginTransaction()


        transac2.replace(R.id.nivaFameLayout, fragment2)
        transac3.replace(R.id.nivaFameLayout, fragment3)


        transac1d.remove( fragment1)
        transac2d.remove(fragment2)
        transac3d.remove(fragment3)

        transac1h.hide( fragment1)
        transac2h.hide(fragment2)
        transac3h.hide(fragment3)

        transac1s.show( fragment1)
        transac2s.show(fragment2)
        transac3s.show(fragment3)

       /* tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener
        {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                TODO("Not yet implemented")

           /*     when(tab?.position)
                {
                    0-> {transac.commit()}
                } */

                Toast.makeText(this@MikelActivity, "ttttttt", Toast.LENGTH_SHORT).show()
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                TODO("Not yet implemented")
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                TODO("Not yet implemented")
            }

        })*/
        //   viewPager = findViewById<ViewPager>(R.id.viewPager)

        tabLayout.addTab(tabLayout.newTab().setText("staut"))
        tabLayout.addTab(tabLayout.newTab().setText("discussion"))
        tabLayout.addTab(tabLayout.newTab().setText("journal"))

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {

               val fgg = when(tab?.position)
                {
                    0-> zina1Fragment()

                      //  Toast.makeText(this@MikelActivity, "statut", Toast.LENGTH_SHORT).show()



                    1-> zina2Fragment()




                    2-> zina1Fragment()
                   else-> null
                }
                supportFragmentManager.beginTransaction().replace(R.id.nivaFameLayout, fgg!!).commit()
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

                when(tab?.position)
                {
                    0-> { transac1s.commitAllowingStateLoss()

                        Toast.makeText(this@MikelActivity, "statut", Toast.LENGTH_SHORT).show() }


                    1-> {  val fg2 = supportFragmentManager.findFragmentByTag("zina2Fragment")
                           val fg3 = supportFragmentManager.findFragmentByTag("zina3Fragment")


                        if (fg2==null){
                            Toast.makeText(this@MikelActivity, "iscusion supprimer", Toast.LENGTH_SHORT).show()
                        }



                    }


                    2-> { val fg = supportFragmentManager.findFragmentByTag("zina2Fragment")
                          val fg3 = supportFragmentManager.findFragmentByTag("zina3Fragment")


                        if (fg!=null){
                            Toast.makeText(this@MikelActivity, "suppresion en cour", Toast.LENGTH_SHORT).show()
                            transac1d.commitAllowingStateLoss()
                            transac2d.commitAllowingStateLoss()


                        }else{
                            Toast.makeText(this@MikelActivity, "deja supprime", Toast.LENGTH_SHORT).show()
                        }

                        if (fg3 ==null){

                            Toast.makeText(this@MikelActivity, "deja visible", Toast.LENGTH_SHORT).show()
                        }


                        }
                }

            }
        })



    }
}