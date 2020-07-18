package com.example.zikr

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Build
import android.os.Looper
import android.provider.Settings
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.ContextCompat.startActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.gms.location.*
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.json.JSONException
import java.util.*

class InitializeClass(context: Context) {
    var sharedPref: SharedPref
    private var notificationManager: NotificationManagerCompat
    private lateinit var pendingIntent: PendingIntent
    private var fusedLocationClient: FusedLocationProviderClient
    private var alarmManager: AlarmManager
    private lateinit var locationRequest: LocationRequest

    var PERMISSION_ID = 42
    var Sunset = ""
    var Sunrise = ""
    var latitude = ""
    var longitude = ""
    var lat = ""
    var long = ""
    var sunsetHour = 0
    var sunsetMinute = 0
    var sanriseHour = 0
    var sanriseMinute = 0
    var morningHourTrimmed = 0
    var morningMinuteTrimmed = 0
    var nightHourTrimmed = 0
    var nightMinuteTrimmed = 0

    init {

        sharedPref = SharedPref(context)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
        sunsetHour = sharedPref.getHourSunset()!!.toInt()
        sunsetMinute = sharedPref.getMinuteSunset()!!.toInt()
        sanriseHour = sharedPref.getHourSunrise()!!.toInt()
        sanriseMinute = sharedPref.getMinuteSunrise()!!.toInt()
        notificationManager = NotificationManagerCompat.from(context)
        alarmManager = getSystemService(context, AlarmManager::class.java)!!


    }

    suspend fun getTimeSunrise(context: Context) {
       delay(15000)

        val alarmCalendar2 = Calendar.getInstance()
        alarmCalendar2.set(Calendar.HOUR_OF_DAY, sanriseHour)
        alarmCalendar2.set(Calendar.MINUTE, sanriseMinute)
        alarmCalendar2.set(Calendar.SECOND, 0)
        alarmCalendar2.set(Calendar.MILLISECOND, 0)

        Toast.makeText(
            context,
            "Alarm sharedpref (SUNRISE)  u $sanriseHour sati i $sanriseMinute minuta",
            Toast.LENGTH_SHORT
        ).show()

        alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val intent1 = Intent(context, NotificationReceiver::class.java)

        val pendingIntentSunrise =
            PendingIntent.getBroadcast(context, 0, intent1, PendingIntent.FLAG_UPDATE_CURRENT)

        if (alarmCalendar2.before(Calendar.getInstance())) {
            alarmCalendar2.add(Calendar.DATE, 1)
        }
        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            alarmCalendar2.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntentSunrise
        )


    }
    suspend fun getTimeSunrise1(context: Context) {
        delay(8000)

        val alarmCalendar2 = Calendar.getInstance()
        alarmCalendar2.set(Calendar.HOUR_OF_DAY, morningHourTrimmed)
        alarmCalendar2.set(Calendar.MINUTE, morningMinuteTrimmed)
        alarmCalendar2.set(Calendar.SECOND, 0)
        alarmCalendar2.set(Calendar.MILLISECOND, 0)

        Toast.makeText(
            context,
            "Direktam alarm(SUNRISE)  u $morningHourTrimmed sati i $morningMinuteTrimmed minuta",
            Toast.LENGTH_SHORT
        ).show()

        alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val intent1 = Intent(context, NotificationReceiver::class.java)

        val pendingIntentSunrise =
            PendingIntent.getBroadcast(context, 0, intent1, PendingIntent.FLAG_UPDATE_CURRENT)

        if (alarmCalendar2.before(Calendar.getInstance())) {
            alarmCalendar2.add(Calendar.DATE, 1)
        }
        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            alarmCalendar2.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntentSunrise
        )


    }
    suspend fun getTimeSunset(context: Context) {
        delay(15000)


        val alarmCalendar1 = Calendar.getInstance()
        val calendarNow = Calendar.getInstance()

        //If alarm time has passed, set for tomorrow
        alarmCalendar1.set(Calendar.HOUR_OF_DAY, sunsetHour)
        alarmCalendar1.set(Calendar.MINUTE, sunsetMinute)
        alarmCalendar1.set(Calendar.SECOND, 0)
        alarmCalendar1.set(Calendar.MILLISECOND, 0)
        Toast.makeText(
            context,
            "Alarm iz sharedpref (SANSET) $sunsetHour sati i $sunsetMinute minuta",
            Toast.LENGTH_SHORT
        ).show()

        alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val intent2 = Intent(context, NotificationReceiver::class.java)
        val pendingIntentSunset =
            PendingIntent.getBroadcast(context, 1, intent2, PendingIntent.FLAG_UPDATE_CURRENT)


        if (alarmCalendar1.after(calendarNow)) {
            alarmManager.setRepeating(
                AlarmManager.RTC_WAKEUP,
                alarmCalendar1.timeInMillis,
                AlarmManager.INTERVAL_DAY,
                pendingIntentSunset
            )

        } else {
            alarmCalendar1.add(Calendar.DAY_OF_MONTH, 1)
            alarmManager.setRepeating(
                AlarmManager.RTC_WAKEUP,
                alarmCalendar1.timeInMillis,
                AlarmManager.INTERVAL_DAY,
                pendingIntentSunset
            )
        }
    }
    suspend fun getTimeSunset1(context: Context) {
        delay(7000)


        val alarmCalendar1 = Calendar.getInstance()
        val calendarNow = Calendar.getInstance()

        //If alarm time has passed, set for tomorrow
        alarmCalendar1.set(Calendar.HOUR_OF_DAY, nightHourTrimmed)
        alarmCalendar1.set(Calendar.MINUTE, nightMinuteTrimmed)
        alarmCalendar1.set(Calendar.SECOND, 0)
        alarmCalendar1.set(Calendar.MILLISECOND, 0)
        Toast.makeText(
            context,
            "Direktno alarm(SANSET) $nightHourTrimmed sati i $nightMinuteTrimmed minuta",
            Toast.LENGTH_SHORT
        ).show()

        alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val intent2 = Intent(context, NotificationReceiver::class.java)
        val pendingIntentSunset =
            PendingIntent.getBroadcast(context, 1, intent2, PendingIntent.FLAG_UPDATE_CURRENT)


        if (alarmCalendar1.after(calendarNow)) {
            alarmManager.setRepeating(
                AlarmManager.RTC_WAKEUP,
                alarmCalendar1.timeInMillis,
                AlarmManager.INTERVAL_DAY,
                pendingIntentSunset
            )

        } else {
            alarmCalendar1.add(Calendar.DAY_OF_MONTH, 1)
            alarmManager.setRepeating(
                AlarmManager.RTC_WAKEUP,
                alarmCalendar1.timeInMillis,
                AlarmManager.INTERVAL_DAY,
                pendingIntentSunset
            )
        }
    }
    @SuppressLint("MissingPermission")


    suspend fun location (activity: Activity){
        delay(2000)
         fusedLocationClient.lastLocation.addOnCompleteListener(activity) { task ->

             val location: Location? = task.result
             if (location == null) {

                 requestNewLocationData(activity)
             } else {
                 latitude = location.latitude.toString()
                 longitude = location.longitude.toString()

                 sharedPref.putLong(longitude)
                 sharedPref.putLat(latitude)
                 Toast.makeText(
                     activity,
                     "Apdejtovane koordinate: $latitude i $longitude",
                     Toast.LENGTH_SHORT
                 ).show()
             }
         }


     }
    @SuppressLint("MissingPermission")
    fun getLastLocation(activity: Activity) {

        lat = sharedPref.getLat().toString()
        long = sharedPref.getLong().toString()

        if (lat == "" && long == "") {


            if (checkPermissions(activity)) {

                if (isLocationEnabled(activity)) {

                    fusedLocationClient.lastLocation.addOnCompleteListener(activity) { task ->

                        val location: Location? = task.result
                        if (location == null) {

                            requestNewLocationData(activity)
                        } else {
                            latitude = location.latitude.toString()
                            longitude = location.longitude.toString()

                                CoroutineScope(Dispatchers.Main).launch{
                                    getApiUpdate(activity)
                                    getTimeSunrise1(activity)
                                    getTimeSunset1(activity)
                                }

                            sharedPref.putLong(longitude)
                            sharedPref.putLat(latitude)
                            Toast.makeText(
                                activity,
                                "Koordinate prvo pokretanje su: $latitude i $longitude",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                } else {
                    showAlert(activity)
                }

            } else {
                requestPermissions(activity)
            }

        }


    }

  suspend fun getApi(context: Context) {
       delay(10000)
        val url = "http://api.aladhan.com/v1/timings?latitude=$lat&longitude=$long&method=4"

        val mQ = Volley.newRequestQueue(context)
        val jsonArrayRequest =
            JsonObjectRequest(
                Request.Method.GET, url, null,

                Response.Listener { response ->
                    try {

                        Sunrise =
                            response.getJSONObject("data").getJSONObject("timings").get("Sunrise")
                                .toString()

                        morningHourTrimmed =
                            Sunrise.split(":")[0].replaceFirst("^0+(?!$)", "").toInt() - 1
                        sharedPref.putHourSunrise(morningHourTrimmed)

                        morningMinuteTrimmed =
                            Sunrise.split(":")[1].replaceFirst("^0+(?!$)", "").toInt()
                        sharedPref.putMinuteSunrise(morningMinuteTrimmed)

                        Sunset =
                            response.getJSONObject("data").getJSONObject("timings").get("Sunset")
                                .toString()

                        nightHourTrimmed = Sunset.split(":")[0].toInt() - 1
                        sharedPref.putHourSunset(nightHourTrimmed)

                        nightMinuteTrimmed = Sunset.split(":")[1].toInt()
                        sharedPref.putMinuteSunset(nightMinuteTrimmed)

                        Toast.makeText(
                            context,
                            "GetApi (lat i long) sanriseHour: $sanriseHour i sanriseMinute: $sanriseMinute sunsetHour $sunsetHour sunsetminute $sunsetMinute" ,
                            Toast.LENGTH_SHORT
                        ).show()

                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                },
                Response.ErrorListener { }
            )
        //Add API Request
        mQ.run {
            //Add API Request
            add(jsonArrayRequest)
        }
    }

    suspend fun getApiUpdate(context: Context) {
        delay(3000)

        val url = "http://api.aladhan.com/v1/timings?latitude=$latitude&longitude=$longitude&method=4"

        //Log.i("lat",lat)
        val mQ = Volley.newRequestQueue(context)
        val jsonArrayRequest =
            JsonObjectRequest(
                Request.Method.GET, url, null,

                Response.Listener { response ->
                    try {

                        //SUNRISE TIME
                        Sunrise =
                            response.getJSONObject("data").getJSONObject("timings").get("Sunrise")
                                .toString()

                        morningHourTrimmed =
                            Sunrise.split(":")[0].replaceFirst("^0+(?!$)", "").toInt() - 1
                        sharedPref.putHourSunrise(morningHourTrimmed)



                        morningMinuteTrimmed =
                            Sunrise.split(":")[1].replaceFirst("^0+(?!$)", "").toInt()
                        sharedPref.putMinuteSunrise(morningMinuteTrimmed)


                        //SUNSET TIME
                        Sunset =
                            response.getJSONObject("data").getJSONObject("timings").get("Sunset")
                                .toString()


                        nightHourTrimmed = Sunset.split(":")[0].toInt() - 1
                        sharedPref.putHourSunset(nightHourTrimmed)

                        nightMinuteTrimmed = Sunset.split(":")[1].toInt()
                        sharedPref.putMinuteSunset(nightMinuteTrimmed)
                        Toast.makeText(
                            context,
                            "getApiUpdate (latitude i longitude) morningHour $morningHourTrimmed i morningMinute $morningMinuteTrimmed i nightHour $nightHourTrimmed i nightMinute $nightMinuteTrimmed ",
                            Toast.LENGTH_SHORT
                        ).show()

                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                },
                Response.ErrorListener { }
            )
        //Add API Request
        mQ.run {
            //Add API Request
            add(jsonArrayRequest)
        }
    }
    private fun showAlert(context: Context) {
        val dialog = AlertDialog.Builder(context)
        dialog.setTitle("Uključite lokaciju (GPS)")
            .setMessage(
                "Usluge loakcije su onemogućene! Omogućite usluge loakcije na ovom uređaju. "
            )
            .setPositiveButton("Podešavanja") { _, _ ->
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(context, intent, null)
            }
            .setNegativeButton("Zatvori") { _, _ ->
            }
        dialog.show()
    }

    @SuppressLint("MissingPermission")
    fun requestNewLocationData(context: Context) {
        locationRequest = LocationRequest().apply{
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            interval = 0
            fastestInterval = 0
            numUpdates = 2

        }


        fusedLocationClient.requestLocationUpdates(
            locationRequest,mLocationCallback, Looper.myLooper()
        )
    }

    private val mLocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            val lastLocation: Location = locationResult.lastLocation
            latitude = lastLocation.latitude.toString()
            longitude = lastLocation.longitude.toString()


        }
    }


    private fun isLocationEnabled(context: Context): Boolean {
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    private fun checkPermissions(context: Context): Boolean {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            return true
        }
        return false
    }

    private fun requestPermissions(activity: Activity) {
        ActivityCompat.requestPermissions(
            activity,
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            ),
            PERMISSION_ID
        )
    }



    fun cancelAlarm(activity: Activity) {
        val intent = Intent(activity, NotificationReceiver::class.java)
        pendingIntent = PendingIntent.getBroadcast(activity, 0, intent, 0)
        val intent1 = Intent(activity, NotificationReceiver::class.java)
        pendingIntent = PendingIntent.getBroadcast(activity, 1, intent1, 0)
        alarmManager.cancel(pendingIntent)
        Snackbar.make(
            activity.findViewById(android.R.id.content),
            "Notifikacije isključene!",
            Snackbar.LENGTH_LONG
        ).show()
    }

    private fun stopLocationUpdates() {
        fusedLocationClient.removeLocationUpdates(mLocationCallback)
    }


}