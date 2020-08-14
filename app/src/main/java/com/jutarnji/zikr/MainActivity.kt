package com.jutarnji.zikr

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Switch
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.BuildConfig
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var sharedPref: SharedPref
    private lateinit var a: InitializeClass
    var PERMISSION_ID = 42

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        a = InitializeClass(this)
        sharedPref = SharedPref(this)

        /********************  DRAWER LAYOUT ********************/
        setSupportActionBar(tollbar_nav)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        supportActionBar?.title = null
        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, tollbar_nav, 0, 0
        )
        toggle.drawerArrowDrawable.color = resources.getColor(R.color.hadisi)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()


        nav_view.setNavigationItemSelectedListener(this)
        val menu = nav_view.menu
        val menuItem = menu.findItem(R.id.myswitch)

       val switch = menuItem.actionView as Switch

       switch.isChecked = sharedPref.loadSwitchState()
        if (switch.isChecked) {
            a.getTimeSunrise(this@MainActivity)
            a.getTimeSunset(this@MainActivity)
            CoroutineScope(Dispatchers.Main).launch {
                a.location(this@MainActivity)
                a.getApi(this@MainActivity)
               a.getApiUpdate(this@MainActivity)
            }
        }

        switch.setOnCheckedChangeListener { _, isChecked ->

            if (isChecked) {
                a.getLastLocation(this@MainActivity)
                a.getTimeSunrise(this@MainActivity)
                a.getTimeSunset(this@MainActivity)

                CoroutineScope(Dispatchers.Main).launch {
                    a.location(this@MainActivity)
                    a.getApi(this@MainActivity)
                    a.getApiUpdate(this@MainActivity)
                }
                sharedPref.saveSwitchState(true)
            } else {
                sharedPref.saveSwitchState(false)
                a.cancelAlarm(this)

            }

        }

    /*********************************************************/

        btn_jutarnji.setOnClickListener() {
            startActivity(Intent(this, Jutarnji::class.java))
            toastJutarnji()
        }

        btn_vecernji.setOnClickListener() {
            startActivity(Intent(this, Vecernji::class.java))
           toastVecernji()
        }

        btn_share.setOnClickListener {
            try {
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.type = "text/plain"
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Jutarnji i večernji zikr")
                var shareMessage = "\nPreporučite aplikaciju drugima i zaradite dobro djelo\n\n"
                shareMessage =
                    shareMessage + "https://play.google.com/store/apps/details?id=com.jutarnji.zikr" + BuildConfig.APPLICATION_ID + "\n\n"
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
                startActivity(Intent.createChooser(shareIntent, "Please share application:"))
            } catch (e: Exception) {
            }

        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.name_sun -> {
                startActivity(Intent(this, Jutarnji::class.java))
            }
            R.id.nam_moon -> {
                startActivity(Intent(this, Vecernji::class.java))

            }
            R.id.about-> {
                startActivity(Intent(this, About::class.java))

            }
            R.id.name_share -> {
                try {
                    val shareIntent = Intent(Intent.ACTION_SEND)
                    shareIntent.type = "text/plain"
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Jutarnji i večernji zikr")
                    var shareMessage = "\nPreporučite aplikaciju drugima i zaradite dobro djelo\n\n"
                    shareMessage =
                        shareMessage + "https://play.google.com/store/apps/details?id=com.jutarnji.zikr" + BuildConfig.APPLICATION_ID + "\n\n"
                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
                    startActivity(Intent.createChooser(shareIntent, "Please share application:"))
                } catch (e: Exception) {
                    //e.toString();
                }

            }
            R.id.myswitch -> {
                Toast.makeText(this, "Isključite/uključite notifikacije za jutarnji i večernji zikr.", Toast.LENGTH_SHORT).show()
            }
        }
        item.isChecked = true
        return true
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode == PERMISSION_ID) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {

                CoroutineScope(Dispatchers.Main).launch {
                    a.getLastLocation(this@MainActivity)
                    a.location(this@MainActivity)
                    a.getApiUpdate(this@MainActivity)
                    a.getTimeSunrise1(this@MainActivity)
                    a.getTimeSunset1(this@MainActivity)
                }
            }else {
                sharedPref.saveSwitchState(false)
            }

            }


        }
    fun toastJutarnji(){
        val toast = Toast.makeText(this, "Jutarnji zikr", Toast.LENGTH_SHORT)
        val toastContentView = toast.view as LinearLayout
        val imageView = ImageView(this)
        imageView.setImageResource(R.drawable.ic_sun)
        toastContentView.addView(imageView,0)
        toast.show()
    }
    fun toastVecernji(){
        val toast = Toast.makeText(this, "Večernji zikr", Toast.LENGTH_SHORT)
        val toastContentView = toast.view as LinearLayout
        val imageView = ImageView(this)
        imageView.setImageResource(R.drawable.ic_mun)
        toastContentView.addView(imageView,0)
        toast.show()
    }

//    private val job = Job()
//
//    // The coroutine runs using the Main (UI) dispatcher
//    private val coroutineScope = CoroutineScope(job + Dispatchers.Main)
//    fun Coroutine(){
//
//        coroutineScope.launch  {
//            a.location(this@MainActivity)
//            a.getApi(this@MainActivity)
//            a.getApiUpdate(this@MainActivity)
//        }


    }












