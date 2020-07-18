package com.example.zikr

import android.app.Dialog
import android.content.Context
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Vibrator
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_jutarnji.*
import kotlinx.android.synthetic.main.activity_jutarnji.btn_audio1

import kotlinx.android.synthetic.main.activity_jutarnji.btn_audio11
import kotlinx.android.synthetic.main.activity_jutarnji.btn_audio12
import kotlinx.android.synthetic.main.activity_jutarnji.btn_audio13
import kotlinx.android.synthetic.main.activity_jutarnji.btn_audio14
import kotlinx.android.synthetic.main.activity_jutarnji.btn_audio15
import kotlinx.android.synthetic.main.activity_jutarnji.btn_audio16
import kotlinx.android.synthetic.main.activity_jutarnji.btn_audio17
import kotlinx.android.synthetic.main.activity_jutarnji.btn_audio19
import kotlinx.android.synthetic.main.activity_jutarnji.btn_audio19_1
import kotlinx.android.synthetic.main.activity_jutarnji.btn_audio2
import kotlinx.android.synthetic.main.activity_jutarnji.btn_audio3
import kotlinx.android.synthetic.main.activity_jutarnji.btn_audio4
import kotlinx.android.synthetic.main.activity_jutarnji.btn_audio5
import kotlinx.android.synthetic.main.activity_jutarnji.btn_audio6
import kotlinx.android.synthetic.main.activity_jutarnji.btn_audio7
import kotlinx.android.synthetic.main.activity_jutarnji.btn_audio8
import kotlinx.android.synthetic.main.activity_jutarnji.btn_audio9
import kotlinx.android.synthetic.main.activity_jutarnji.btn_brojac1
import kotlinx.android.synthetic.main.activity_jutarnji.btn_brojac11
import kotlinx.android.synthetic.main.activity_jutarnji.btn_brojac12
import kotlinx.android.synthetic.main.activity_jutarnji.btn_brojac13
import kotlinx.android.synthetic.main.activity_jutarnji.btn_brojac14
import kotlinx.android.synthetic.main.activity_jutarnji.btn_brojac15
import kotlinx.android.synthetic.main.activity_jutarnji.btn_brojac16
import kotlinx.android.synthetic.main.activity_jutarnji.btn_brojac17
import kotlinx.android.synthetic.main.activity_jutarnji.btn_brojac19
import kotlinx.android.synthetic.main.activity_jutarnji.btn_brojac19_1
import kotlinx.android.synthetic.main.activity_jutarnji.btn_brojac2
import kotlinx.android.synthetic.main.activity_jutarnji.btn_brojac3
import kotlinx.android.synthetic.main.activity_jutarnji.btn_brojac4
import kotlinx.android.synthetic.main.activity_jutarnji.btn_brojac5
import kotlinx.android.synthetic.main.activity_jutarnji.btn_brojac6
import kotlinx.android.synthetic.main.activity_jutarnji.btn_brojac7
import kotlinx.android.synthetic.main.activity_jutarnji.btn_brojac8
import kotlinx.android.synthetic.main.activity_jutarnji.btn_brojac9
import kotlinx.android.synthetic.main.activity_jutarnji.layout1
import kotlinx.android.synthetic.main.activity_jutarnji.layout11
import kotlinx.android.synthetic.main.activity_jutarnji.layout12
import kotlinx.android.synthetic.main.activity_jutarnji.layout13
import kotlinx.android.synthetic.main.activity_jutarnji.layout14
import kotlinx.android.synthetic.main.activity_jutarnji.layout15
import kotlinx.android.synthetic.main.activity_jutarnji.layout16
import kotlinx.android.synthetic.main.activity_jutarnji.layout17
import kotlinx.android.synthetic.main.activity_jutarnji.layout19
import kotlinx.android.synthetic.main.activity_jutarnji.layout2
import kotlinx.android.synthetic.main.activity_jutarnji.layout3
import kotlinx.android.synthetic.main.activity_jutarnji.layout4
import kotlinx.android.synthetic.main.activity_jutarnji.layout5
import kotlinx.android.synthetic.main.activity_jutarnji.layout6
import kotlinx.android.synthetic.main.activity_jutarnji.layout7
import kotlinx.android.synthetic.main.activity_jutarnji.layout8
import kotlinx.android.synthetic.main.activity_jutarnji.layout9

import kotlinx.android.synthetic.main.activity_vecernji.*
import kotlinx.android.synthetic.main.dialog_layout.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.json.JSONException
import java.util.*

class Vecernji : AppCompatActivity() {
    lateinit var myDialog: Dialog
    lateinit var sharedPref: SharedPref
    lateinit var zikrovi: Zikrovi
    var Counter8 = 7
    var Counter9 = 3
    var Counter10 = 3
    var Counter11 = 3
    var Counter12 = 3
    var Counter13 = 100
    var Counter14 = 1
    var Counter15 = 3
    var Counter16 = 3
    var Counter17 = 3
    var Counter18 = 10
    var Counter19 = 10
    private lateinit var v: InitializeClass


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vecernji)
        v = InitializeClass(this)
        sharedPref = SharedPref(this)


        CoroutineScope(Dispatchers.Main).launch {
            v.getApi(this@Vecernji)
            v.location(this@Vecernji)
            v.getApiUpdate(this@Vecernji)

        }






        zikrovi = Zikrovi()



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

    fun hideArTrBS() {
        BTNarapski1.setOnClickListener {

            showHideArabian1()

        }
        BTNbos1.setOnClickListener {
            showHideBosnian1()
        }
        BTNtrans1.setOnClickListener {
            showHideTranskription1()
        }


    }



    fun showHideArabian1() {
        if (txtArr1.visibility == View.GONE) {

            txtArr1.visibility = View.VISIBLE
            txtArr2.visibility = View.VISIBLE
            txtArr3.visibility = View.VISIBLE
            txtArr4.visibility = View.VISIBLE
            txtArr5.visibility = View.VISIBLE
            txtArr6.visibility = View.VISIBLE
            txtArr7.visibility = View.VISIBLE
            txtArr8.visibility = View.VISIBLE
            txtArr9.visibility = View.VISIBLE
            txtArr10.visibility = View.VISIBLE
            txtArr11.visibility = View.VISIBLE
            txtArr12.visibility = View.VISIBLE
            txtArr13.visibility = View.VISIBLE
            txtArr14.visibility = View.VISIBLE
            txtArr15.visibility = View.VISIBLE
            txtArr13_1.visibility = View.VISIBLE
            txtArr14_1.visibility = View.VISIBLE
            txtArr16.visibility = View.VISIBLE
            txtArr16_1.visibility = View.VISIBLE
            txtArr17.visibility = View.VISIBLE
            txtArr17_1.visibility = View.VISIBLE

            txtArr19.visibility = View.VISIBLE
            txtArr19_1.visibility = View.VISIBLE

            sharedPref.saveArabicState1(false)


        } else {

            txtArr1.visibility = View.GONE
            txtArr2.visibility = View.GONE
            txtArr3.visibility = View.GONE
            txtArr4.visibility = View.GONE
            txtArr5.visibility = View.GONE
            txtArr6.visibility = View.GONE
            txtArr7.visibility = View.GONE
            txtArr8.visibility = View.GONE
            txtArr9.visibility = View.GONE
            txtArr10.visibility = View.GONE
            txtArr11.visibility = View.GONE
            txtArr12.visibility = View.GONE
            txtArr13.visibility = View.GONE
            txtArr14.visibility = View.GONE
            txtArr15.visibility = View.GONE
            txtArr13_1.visibility = View.GONE
            txtArr14_1.visibility = View.GONE
            txtArr16.visibility = View.GONE
            txtArr16_1.visibility = View.GONE
            txtArr17.visibility = View.GONE
            txtArr17_1.visibility = View.GONE

            txtArr19.visibility = View.GONE
            txtArr19_1.visibility = View.GONE


            sharedPref.saveArabicState1(true)

        }
    }

    fun showHideBosnian1() {
        if (txtBss1.visibility == View.GONE) {
            txtBss1.visibility = View.VISIBLE
            txtBss1.visibility = View.VISIBLE
            txtBss2.visibility = View.VISIBLE
            txtBss3.visibility = View.VISIBLE
            txtBss4.visibility = View.VISIBLE
            txtBss5.visibility = View.VISIBLE
            txtBss6.visibility = View.VISIBLE
            txtBss7.visibility = View.VISIBLE
            txtBss8.visibility = View.VISIBLE
            txtBss9.visibility = View.VISIBLE
            txtBss10.visibility = View.VISIBLE
            txtBss11.visibility = View.VISIBLE
            txtBss12.visibility = View.VISIBLE
            txtBss13.visibility = View.VISIBLE
            txtBss14.visibility = View.VISIBLE
            txtBss15.visibility = View.VISIBLE
            txtBss16.visibility = View.VISIBLE
            txtBss17.visibility = View.VISIBLE

            txtBss19.visibility = View.VISIBLE
            txtBss19_1.visibility = View.VISIBLE
            txt_salavatt.visibility = View.VISIBLE
            txt_salavatt1.visibility = View.VISIBLE


            sharedPref.saveBosnianState1(false)


        } else {
            txtBss1.visibility = View.GONE
            txtBss2.visibility = View.GONE
            txtBss3.visibility = View.GONE
            txtBss4.visibility = View.GONE
            txtBss5.visibility = View.GONE
            txtBss6.visibility = View.GONE
            txtBss7.visibility = View.GONE
            txtBss8.visibility = View.GONE
            txtBss9.visibility = View.GONE
            txtBss10.visibility = View.GONE
            txtBss11.visibility = View.GONE
            txtBss12.visibility = View.GONE
            txtBss13.visibility = View.GONE
            txtBss14.visibility = View.GONE
            txtBss15.visibility = View.GONE
            txtBss16.visibility = View.GONE
            txtBss17.visibility = View.GONE

            txtBss19.visibility = View.GONE
            txtBss19_1.visibility = View.GONE
            txt_salavatt.visibility = View.GONE
            txt_salavatt1.visibility = View.GONE

            sharedPref.saveBosnianState1(true)

        }
    }

    fun showHideTranskription1() {
        if (txtTrr1.visibility == View.GONE ) {
            txtTrr1.visibility = View.VISIBLE
            txtTrr1.visibility = View.VISIBLE
            txtTrr2.visibility = View.VISIBLE
            txtTrr3.visibility = View.VISIBLE
            txtTrr4.visibility = View.VISIBLE
            txtTrr5.visibility = View.VISIBLE
            txtTrr6.visibility = View.VISIBLE
            txtTrr7.visibility = View.VISIBLE
            txtTrr8.visibility = View.VISIBLE
            txtTrr9.visibility = View.VISIBLE
            txtTrr10.visibility = View.VISIBLE
            txtTrr11.visibility = View.VISIBLE
            txtTrr12.visibility = View.VISIBLE
            txtTrr13.visibility = View.VISIBLE
            txtTrr14.visibility = View.VISIBLE
            txtTrr15.visibility = View.VISIBLE
            txtTrr16.visibility = View.VISIBLE
            txtTrr17.visibility = View.VISIBLE

            txtTrr19.visibility = View.VISIBLE
            txtTrr19_1.visibility = View.VISIBLE


            sharedPref.saveTranscriptionState1(false)


        } else {
            txtTrr1.visibility = View.GONE
            txtTrr2.visibility = View.GONE
            txtTrr3.visibility = View.GONE
            txtTrr4.visibility = View.GONE
            txtTrr5.visibility = View.GONE
            txtTrr6.visibility = View.GONE
            txtTrr7.visibility = View.GONE
            txtTrr8.visibility = View.GONE
            txtTrr9.visibility = View.GONE
            txtTrr10.visibility = View.GONE
            txtTrr11.visibility = View.GONE
            txtTrr12.visibility = View.GONE
            txtTrr13.visibility = View.GONE
            txtTrr14.visibility = View.GONE
            txtTrr15.visibility = View.GONE
            txtTrr16.visibility = View.GONE
            txtTrr17.visibility = View.GONE

            txtTrr19.visibility = View.GONE
            txtTrr19_1.visibility = View.GONE


            sharedPref.saveTranscriptionState1(true)
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
        if (sharedPref.loadArabicState1()) {
            txtArr1.visibility = View.VISIBLE

            showHideArabian1()
            BTNarapski1.isChecked = true
        } else {
            txtArr1.visibility = View.GONE
            showHideArabian1()

        }

        if (sharedPref.loadBosnianState1()) {
            txtBss1.visibility = View.VISIBLE
            showHideBosnian1()
            BTNbos1.isChecked =true
        } else {
            txtBss1.visibility = View.GONE
            showHideBosnian1()

        }

        if (sharedPref.loadTranscriptionState1()) {
            txtTrr1.visibility = View.VISIBLE
            showHideTranskription1()

            BTNtrans1.isChecked = true
        } else {
            txtTrr1.visibility = View.GONE
            showHideTranskription1()


        }
    }

    fun showDialogDays() {

        val isDialogShown1 = sharedPref.loadDialog1()

        val rightNow1 = Calendar.getInstance()


        val dayOfWeek = rightNow1.get(Calendar.DAY_OF_WEEK)



        if (dayOfWeek == 3)
            if (!isDialogShown1)
            {
                showDialog()
                sharedPref.saveDialog1(true)

            } else {
                sharedPref.saveDialog1(false)

            }

//        val isDialogShown = sharedPref.loadDialog()
//        val rightNow = Calendar.getInstance()
//
//
//        if (rightNow[Calendar.DAY_OF_WEEK] == Calendar.WEDNESDAY)
//
//            if (!isDialogShown)
//            {
//                showDialog()
//                sharedPref.saveDialog(true)
//
//            } else {
//                sharedPref.saveDialog(false)
//
//            }

    }

    fun playDhikr() {
        val mp1 = MediaPlayer.create(this, R.raw.emsejna_veemselmulku)
        btn_audio1.setOnClickListener {

            if (mp1.isPlaying())
                mp1.pause()
            else
                mp1.start()

        }

        val mp2 = MediaPlayer.create(this, R.raw.emsejnaala_fitretl)
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

        val mp5 = MediaPlayer.create(this, R.raw.allahume_bike_emsejna)
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
        val mp10 = MediaPlayer.create(this, R.raw.euzubi_kelimatilahi)
        btn_audio10.setOnClickListener {

            if (mp10.isPlaying())
                mp10.pause()
            else
                mp10.start()

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

    fun clickLayout10(view: View) {



        if (Counter10 == 0) {
            btn_brojac10.setText("$Counter10")

        } else {
            Counter10--
            btn_brojac10.setText("$Counter10")
            val vibratorService = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            vibratorService.vibrate(40)
        }

        if (Counter10 == 0) {
            layout10.setBackgroundColor(getResources().getColor(R.color.layout))
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

