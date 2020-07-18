package com.example.zikr

import android.app.Dialog
import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Vibrator
import android.view.MenuItem
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_jutarnji.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_layout.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.*
import java.util.Calendar.DAY_OF_WEEK
import java.util.Calendar.SUNDAY


class Jutarnji : AppCompatActivity() {

    var Counter8 = 7
    var Counter9 = 3
    var Counter11 = 3
    var Counter12 = 3
    var Counter13 = 100
    var Counter14 = 1
    var Counter15 = 3
    var Counter16 = 3
    var Counter17 = 3
    var Counter18 = 10
    var Counter19 = 10


    private lateinit var j: InitializeClass
    lateinit var myDialog: Dialog
    lateinit var sharedPref: SharedPref
    lateinit var zikrovi: Zikrovi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jutarnji)

        sharedPref = SharedPref(this)
        zikrovi = Zikrovi()
        j = InitializeClass(this)


        CoroutineScope(Dispatchers.Main).launch {
            j.getApi(this@Jutarnji)
            j.location(this@Jutarnji)
            j.getApiUpdate(this@Jutarnji)



        }
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE)

        showDialogDays()
        loadStates()
        playDhikr()
        hideArTrBS()



    }



    private fun hideArTrBS() {
        BTNarapski.setOnClickListener {

            showHideArabian()

        }
        BTNbos.setOnClickListener {
            showHideBosnian()
        }
        BTNtrans.setOnClickListener {
            showHideTranskription()
        }


    }



    fun showHideArabian() {
        if (txtAr1.visibility == View.GONE) {

            txtAr1.visibility = View.VISIBLE
            txtAr2.visibility = View.VISIBLE
            txtAr3.visibility = View.VISIBLE
            txtAr4.visibility = View.VISIBLE
            txtAr5.visibility = View.VISIBLE
            txtAr6.visibility = View.VISIBLE
            txtAr7.visibility = View.VISIBLE

            txtAr9.visibility = View.VISIBLE
            txtAr10.visibility = View.VISIBLE
            txtAr11.visibility = View.VISIBLE
            txtAr12.visibility = View.VISIBLE
            txtAr13.visibility = View.VISIBLE
            txtAr14.visibility = View.VISIBLE
            txtAr15.visibility = View.VISIBLE
            txtAr13_1.visibility = View.VISIBLE
            txtAr14_1.visibility = View.VISIBLE
            txtAr16.visibility = View.VISIBLE
            txtAr16_1.visibility = View.VISIBLE
            txtAr17.visibility = View.VISIBLE
            txtAr17_1.visibility = View.VISIBLE
            txtAr18.visibility = View.VISIBLE
            txtAr19.visibility = View.VISIBLE
            txtAr19_1.visibility = View.VISIBLE

            sharedPref.saveArabicState(false)


        } else {

            txtAr1.visibility = View.GONE
            txtAr2.visibility = View.GONE
            txtAr3.visibility = View.GONE
            txtAr4.visibility = View.GONE
            txtAr5.visibility = View.GONE
            txtAr6.visibility = View.GONE
            txtAr7.visibility = View.GONE
            txtAr9.visibility = View.GONE
            txtAr10.visibility = View.GONE
            txtAr11.visibility = View.GONE
            txtAr12.visibility = View.GONE
            txtAr13.visibility = View.GONE
            txtAr14.visibility = View.GONE
            txtAr15.visibility = View.GONE
            txtAr13_1.visibility = View.GONE
            txtAr14_1.visibility = View.GONE
            txtAr16.visibility = View.GONE
            txtAr16_1.visibility = View.GONE
            txtAr17.visibility = View.GONE
            txtAr17_1.visibility = View.GONE
            txtAr18.visibility = View.GONE
            txtAr19.visibility = View.GONE
            txtAr19_1.visibility = View.GONE


            sharedPref.saveArabicState(true)

        }
    }

    fun showHideBosnian() {
        if (txtBs1.visibility == View.GONE) {
            txtBs1.visibility = View.VISIBLE
            txtBs1.visibility = View.VISIBLE
            txtBs2.visibility = View.VISIBLE
            txtBs3.visibility = View.VISIBLE
            txtBs4.visibility = View.VISIBLE
            txtBs5.visibility = View.VISIBLE
            txtBs6.visibility = View.VISIBLE
            txtBs7.visibility = View.VISIBLE

            txtBs9.visibility = View.VISIBLE
            txtBs10.visibility = View.VISIBLE
            txtBs11.visibility = View.VISIBLE
            txtBs12.visibility = View.VISIBLE
            txtBs13.visibility = View.VISIBLE
            txtBs14.visibility = View.VISIBLE
            txtBs15.visibility = View.VISIBLE
            txtBs16.visibility = View.VISIBLE
            txtBs17.visibility = View.VISIBLE
            txtBs18.visibility = View.VISIBLE
            txtBs19.visibility = View.VISIBLE
            txtBs19_1.visibility = View.VISIBLE
            txt_salavat.visibility = View.VISIBLE
            txt_salavat1.visibility = View.VISIBLE


            sharedPref.saveBosnianState(false)


        } else {
            txtBs1.visibility = View.GONE
            txtBs2.visibility = View.GONE
            txtBs3.visibility = View.GONE
            txtBs4.visibility = View.GONE
            txtBs5.visibility = View.GONE
            txtBs6.visibility = View.GONE
            txtBs7.visibility = View.GONE
            txtBs9.visibility = View.GONE
            txtBs10.visibility = View.GONE
            txtBs11.visibility = View.GONE
            txtBs12.visibility = View.GONE
            txtBs13.visibility = View.GONE
            txtBs14.visibility = View.GONE
            txtBs15.visibility = View.GONE
            txtBs16.visibility = View.GONE
            txtBs17.visibility = View.GONE
            txtBs18.visibility = View.GONE
            txtBs19.visibility = View.GONE
            txtBs19_1.visibility = View.GONE
            txt_salavat.visibility = View.GONE
            txt_salavat1.visibility = View.GONE

            sharedPref.saveBosnianState(true)

        }
    }

    fun showHideTranskription() {
        if (txtTr1.visibility == View.GONE ) {
            txtTr1.visibility = View.VISIBLE
            txtTr1.visibility = View.VISIBLE
            txtTr2.visibility = View.VISIBLE
            txtTr3.visibility = View.VISIBLE
            txtTr4.visibility = View.VISIBLE
            txtTr5.visibility = View.VISIBLE
            txtTr6.visibility = View.VISIBLE
            txtTr7.visibility = View.VISIBLE

            txtTr9.visibility = View.VISIBLE
            txtTr10.visibility = View.VISIBLE
            txtTr11.visibility = View.VISIBLE
            txtTr12.visibility = View.VISIBLE
            txtTr13.visibility = View.VISIBLE
            txtTr14.visibility = View.VISIBLE
            txtTr15.visibility = View.VISIBLE
            txtTr16.visibility = View.VISIBLE
            txtTr17.visibility = View.VISIBLE
            txtTr18.visibility = View.VISIBLE
            txtTr19.visibility = View.VISIBLE
            txtTr19_1.visibility = View.VISIBLE


            sharedPref.saveTranscriptionState(false)


        } else {
            txtTr1.visibility = View.GONE
            txtTr2.visibility = View.GONE
            txtTr3.visibility = View.GONE
            txtTr4.visibility = View.GONE
            txtTr5.visibility = View.GONE
            txtTr6.visibility = View.GONE
            txtTr7.visibility = View.GONE
            txtTr9.visibility = View.GONE
            txtTr10.visibility = View.GONE
            txtTr11.visibility = View.GONE
            txtTr12.visibility = View.GONE
            txtTr13.visibility = View.GONE
            txtTr14.visibility = View.GONE
            txtTr15.visibility = View.GONE
            txtTr16.visibility = View.GONE
            txtTr17.visibility = View.GONE
            txtTr18.visibility = View.GONE
            txtTr19.visibility = View.GONE
            txtTr19_1.visibility = View.GONE


            sharedPref.saveTranscriptionState(true)
        }
    }

    fun showDialog() {

        myDialog = Dialog(this)
        myDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        myDialog.setContentView(R.layout.dialog_layout)
        myDialog.setTitle("Svaki dan nova korist učenja zikra!")
        myDialog.txtShow2.text = zikrovi.lista.random()

        myDialog.setCancelable(true)
        myDialog.show()
    }


    fun loadStates() {
        if (sharedPref.loadArabicState()) {
            txtAr1.visibility = View.VISIBLE

            showHideArabian()
            BTNarapski.isChecked = true
        } else {
            txtAr1.visibility = View.GONE
            showHideArabian()

        }

        if (sharedPref.loadBosnianState()) {
            txtBs1.visibility = View.VISIBLE
            showHideBosnian()
            BTNbos.isChecked =true
        } else {
            txtBs1.visibility = View.GONE
            showHideBosnian()

        }

        if (sharedPref.loadTranscriptionState()) {
            txtTr1.visibility = View.VISIBLE
            showHideTranskription()

            BTNtrans.isChecked = true
        } else {
            txtTr1.visibility = View.GONE
            showHideTranskription()


        }
    }

    fun showDialogDays() {

        val isDialogShown1 = sharedPref.loadDialog1()
        val rightNow1 = Calendar.getInstance()


        val dayOfWeek = rightNow1.get(DAY_OF_WEEK)


        if (dayOfWeek == 6)
            if (!isDialogShown1)
           {
                showDialog()
                sharedPref.saveDialog1(true)

            } else {
                sharedPref.saveDialog1(false)

            }

//        val isDialogShown = sharedPref.loadDialog()
//        val rightNow = Calendar.getInstance()


//        if (rightNow[DAY_OF_WEEK] == Calendar.WEDNESDAY)
//
//            if (!isDialogShown)
//                            {
//                showDialog()
//                sharedPref.saveDialog(true)
//
//            } else {
//                sharedPref.saveDialog(false)
//
//            }

    }

    fun playDhikr() {
        val mp1 = MediaPlayer.create(this, R.raw.asbahna_ve_asbehal)
        btn_audio1.setOnClickListener {

            if (mp1.isPlaying())
                mp1.pause()
            else
                mp1.start()

        }

        val mp2 = MediaPlayer.create(this, R.raw.asbahna_ala_fitreti)
        btn_audio2.setOnClickListener {

            if (mp2.isPlaying())
                mp2.pause()
            else
                mp2.start()

        }

        val mp3 = MediaPlayer.create(this, R.raw.allahume_alimel_gajbi)
        btn_audio3.setOnClickListener {

            if (mp3.isPlaying())
                mp3.pause()
            else
                mp3.start()

        }

        val mp4 = MediaPlayer.create(this, R.raw.allahume_ini_esseluke)
        btn_audio4.setOnClickListener {

            if (mp4.isPlaying())
                mp4.pause()
            else
                mp4.start()

        }

        val mp5 = MediaPlayer.create(this, R.raw.allahume_bike_asbahna)
        btn_audio5.setOnClickListener {

            if (mp5.isPlaying())
                mp5.pause()
            else
                mp5.start()

        }

        val mp6 = MediaPlayer.create(this, R.raw.ja_hajju)
        btn_audio6.setOnClickListener {

            if (mp6.isPlaying())
                mp6.pause()
            else
                mp6.start()

        }

        val mp7 = MediaPlayer.create(this, R.raw.allahume_ente_rabi)
        btn_audio7.setOnClickListener {

            if (mp7.isPlaying())
                mp7.pause()
            else
                mp7.start()

        }

        val mp8 = MediaPlayer.create(this, R.raw.hasbijallahu)
        btn_audio8.setOnClickListener {

            if (mp8.isPlaying())
                mp8.pause()
            else
                mp8.start()

        }

        val mp9 = MediaPlayer.create(this, R.raw.bismilahilezi_lajeduru)
        btn_audio9.setOnClickListener {

            if (mp9.isPlaying())
                mp9.pause()
            else
                mp9.start()

        }



        val mp11 = MediaPlayer.create(this, R.raw.reditu_billahi)
        btn_audio11.setOnClickListener {

            if (mp11.isPlaying())
                mp11.pause()
            else
                mp11.start()

        }

        val mp12 = MediaPlayer.create(this, R.raw.adede_halkih)
        btn_audio12.setOnClickListener {

            if (mp12.isPlaying())
                mp12.pause()
            else
                mp12.start()

        }

        val mp13 = MediaPlayer.create(this, R.raw.subhanallahi_vebihamdih)
        btn_audio13.setOnClickListener {

            if (mp13.isPlaying())
                mp13.pause()
            else
                mp13.start()

        }

        val mp14 = MediaPlayer.create(this, R.raw.kursija)
        btn_audio14.setOnClickListener {

            if (mp14.isPlaying())
                mp14.pause()
            else
                mp14.start()

        }

        val mp15 = MediaPlayer.create(this, R.raw.ihlas)
        btn_audio15.setOnClickListener {

            if (mp15.isPlaying())
                mp15.pause()
            else
                mp15.start()

        }

        val mp16 = MediaPlayer.create(this, R.raw.felek)
        btn_audio16.setOnClickListener {

            if (mp16.isPlaying())
                mp16.pause()
            else
                mp16.start()

        }

        val mp17 = MediaPlayer.create(this, R.raw.nas)
        btn_audio17.setOnClickListener {

            if (mp17.isPlaying())
                mp17.pause()
            else
                mp17.start()

        }
        val mp18 = MediaPlayer.create(this, R.raw.laillahe_vahdehu)
        btn_audio18.setOnClickListener {

            if (mp18.isPlaying())
                mp18.pause()
            else
                mp18.start()

        }
        val mp19 = MediaPlayer.create(this, R.raw.alahume_salli_alla_muhammed)
        btn_audio19.setOnClickListener {

            if (mp19.isPlaying())
                mp19.pause()
            else
                mp19.start()

        }
        val mp19_1 = MediaPlayer.create(this, R.raw.salavat1)
        btn_audio19_1.setOnClickListener {

            if (mp19_1.isPlaying())
                mp19_1.pause()
            else
                mp19_1.start()

        }
    }

    fun clickLayout1(view: View) {
        var Counter = 1
        if (Counter == 0) {
            btn_brojac1.setText("$Counter")

        } else {
            Counter--
            btn_brojac1.setText("$Counter")

        }

        if (Counter == 0) {

            layout1.setBackgroundColor(getResources().getColor(R.color.layout))

            val vibratorService = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            vibratorService.vibrate(40)

        }


    }

    fun clickLayout2(view: View) {

        var Counter1 = 1

        if (Counter1 == 0) {
            btn_brojac2.setText("$Counter1")

        } else {
            Counter1--
            btn_brojac2.setText("$Counter1")
        }

        if (Counter1 == 0) {
            layout2.setBackgroundColor(getResources().getColor(R.color.layout))
            val vibratorService = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            vibratorService.vibrate(40)


        }

    }
    fun clickLayout3(view: View) {

        var Counter3 = 1

        if (Counter3 == 0) {
            btn_brojac3.setText("$Counter3")

        } else {
            Counter3--
            btn_brojac3.setText("$Counter3")
        }

        if (Counter3 == 0) {
            layout3.setBackgroundColor(getResources().getColor(R.color.layout))
            val vibratorService = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            vibratorService.vibrate(40)


        }

    }
    fun clickLayout4(view: View) {

        var Counter4 = 1

        if (Counter4 == 0) {
            btn_brojac4.setText("$Counter4")

        } else {
            Counter4--
            btn_brojac4.setText("$Counter4")
        }

        if (Counter4 == 0) {
            layout4.setBackgroundColor(getResources().getColor(R.color.layout))
            val vibratorService = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            vibratorService.vibrate(40)

        }

    }
    fun clickLayout5(view: View) {

        var Counter5 = 1

        if (Counter5 == 0) {
            btn_brojac5.setText("$Counter5")

        } else {
            Counter5--
            btn_brojac5.setText("$Counter5")
        }

        if (Counter5 == 0) {
            layout5.setBackgroundColor(getResources().getColor(R.color.layout))
            val vibratorService = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            vibratorService.vibrate(40)


        }

    }
    fun clickLayout6(view: View) {

        var Counter6 = 1

        if (Counter6 == 0) {
            btn_brojac6.setText("$Counter6")

        } else {
            Counter6--
            btn_brojac6.setText("$Counter6")
        }

        if (Counter6 == 0) {
            layout6.setBackgroundColor(getResources().getColor(R.color.layout))
            val vibratorService = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            vibratorService.vibrate(40)

        }

    }
    fun clickLayout7(view: View) {

        var Counter7 = 1

        if (Counter7 == 0) {
            btn_brojac7.setText("$Counter7")

        } else {
            Counter7--
            btn_brojac7.setText("$Counter7")
        }

        if (Counter7 == 0) {
            layout7.setBackgroundColor(getResources().getColor(R.color.layout))
            val vibratorService = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            vibratorService.vibrate(40)


        }

    }
    fun clickLayout8(view: View) {



        if (Counter8 == 0) {
            btn_brojac8.setText("$Counter8")

        } else {
            Counter8--
            btn_brojac8.setText("$Counter8")
            val vibratorService = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            vibratorService.vibrate(40)

        }

        if (Counter8 == 0) {
            layout8.setBackgroundColor(getResources().getColor(R.color.layout))
            val vibratorService = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            vibratorService.vibrate(250)


        }

    }
    fun clickLayout9(view: View) {



        if (Counter9 == 0) {
            btn_brojac9.setText("$Counter9")

        } else {
            Counter9--
            btn_brojac9.setText("$Counter9")
            val vibratorService = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            vibratorService.vibrate(40)
        }

        if (Counter9 == 0) {
            layout9.setBackgroundColor(getResources().getColor(R.color.layout))
            val vibratorService = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            vibratorService.vibrate(250)


        }

    }

    fun clickLayout11(view: View) {


        if (Counter11 == 0) {
            btn_brojac11.setText("$Counter11")

        } else {
            Counter11--
            btn_brojac11.setText("$Counter11")
            val vibratorService = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            vibratorService.vibrate(40)
        }

        if (Counter11 == 0) {
            layout11.setBackgroundColor(getResources().getColor(R.color.layout))
            val vibratorService = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            vibratorService.vibrate(250)


        }

    }
    fun clickLayout12(view: View) {



        if (Counter12 == 0) {
            btn_brojac12.setText("$Counter12")

        } else {
            Counter12--
            btn_brojac12.setText("$Counter12")
            val vibratorService = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            vibratorService.vibrate(40)
        }

        if (Counter12 == 0) {
            layout12.setBackgroundColor(getResources().getColor(R.color.layout))
            val vibratorService = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            vibratorService.vibrate(250)


        }

    }
    fun clickLayout13(view: View) {



        if (Counter13 == 0) {
            btn_brojac13.setText("$Counter13")

        } else {
            Counter13--
            btn_brojac13.setText("$Counter13")
            val vibratorService = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            vibratorService.vibrate(40)
        }

        if (Counter13 == 0) {
            layout13.setBackgroundColor(getResources().getColor(R.color.layout))
            val vibratorService = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            vibratorService.vibrate(250)


        }

    }
    fun clickLayout14(view: View) {



        if (Counter14 == 0) {
            btn_brojac14.setText("$Counter14")

        } else {
            Counter14--
            btn_brojac14.setText("$Counter14")
        }

        if (Counter14 == 0) {
            layout14.setBackgroundColor(getResources().getColor(R.color.layout))
            val vibratorService = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            vibratorService.vibrate(40)


        }

    }
    fun clickLayout15(view: View) {



        if (Counter15 == 0) {
            btn_brojac15.setText("$Counter15")

        } else {
            Counter15--
            btn_brojac15.setText("$Counter15")
            val vibratorService = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            vibratorService.vibrate(40)
        }

        if (Counter15 == 0) {
            layout15.setBackgroundColor(getResources().getColor(R.color.layout))
            val vibratorService = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            vibratorService.vibrate(250)


        }

    }
    fun clickLayout16(view: View) {



        if (Counter16 == 0) {
            btn_brojac16.setText("$Counter16")

        } else {
            Counter16--
            btn_brojac16.setText("$Counter16")
            val vibratorService = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            vibratorService.vibrate(40)
        }

        if (Counter16 == 0) {
            layout16.setBackgroundColor(getResources().getColor(R.color.layout))
            val vibratorService = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            vibratorService.vibrate(250)


        }

    }

    fun clickLayout17(view: View) {



        if (Counter17 == 0) {
            btn_brojac17.setText("$Counter17")

        } else {
            Counter17--
            btn_brojac17.setText("$Counter17")
            val vibratorService = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            vibratorService.vibrate(40)
        }

        if (Counter17 == 0) {
            layout17.setBackgroundColor(getResources().getColor(R.color.layout))
            val vibratorService = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            vibratorService.vibrate(250)


        }

    }
    fun clickLayout18(view: View) {

        if (Counter18 == 0) {
            btn_brojac18.setText("$Counter18")

        } else {
            Counter18--
            btn_brojac18.setText("$Counter18")
            val vibratorService = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            vibratorService.vibrate(40)
        }

        if (Counter18 == 0) {
            layout18.setBackgroundColor(getResources().getColor(R.color.layout))
            val vibratorService = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            vibratorService.vibrate(250)


        }

    }

    fun clickLayout19(view: View) {

        if (Counter19 == 0)
          {
            btn_brojac19.setText("$Counter19")
              btn_brojac19_1.setText("$Counter19")

        } else {
            Counter19--
            btn_brojac19.setText("$Counter19")
            btn_brojac19_1.setText("$Counter19")
            val vibratorService = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            vibratorService.vibrate(40)

        }

        if (Counter19 == 0) {
            layout19.setBackgroundColor(getResources().getColor(R.color.layout))
            val vibratorService = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            vibratorService.vibrate(250)


        }



    }

}