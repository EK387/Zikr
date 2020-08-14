package com.jutarnji.zikr

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
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
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
import kotlinx.coroutines.delay
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

    fun getTimeSunrise(context: Context) {


        val alarmCalendar = Calendar.getInstance()
        val calendarNow = Calendar.getInstance()
        alarmCalendar.set(Calendar.HOUR_OF_DAY, sanriseHour)
        alarmCalendar.set(Calendar.MINUTE, sanriseMinute)
        alarmCalendar.set(Calendar.SECOND, 0)
        alarmCalendar.set(Calendar.MILLISECOND, 0)
   //Toast.makeText(context, "Pokreće se getTimeSUnrise sanriseHour($sanriseHour)", Toast.LENGTH_SHORT).show()

        alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val intent = Intent(context, MorningReceiver::class.java)

        val pendingIntentSunrise =
            PendingIntent.getBroadcast(context, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        if (alarmCalendar.after(calendarNow)) {
            alarmManager.setRepeating(
                AlarmManager.RTC_WAKEUP,
                alarmCalendar.timeInMillis,
                AlarmManager.INTERVAL_DAY,
                pendingIntentSunrise
            )

        } else {
            alarmCalendar.add(Calendar.DAY_OF_MONTH, 1)
            alarmManager.setRepeating(
                AlarmManager.RTC_WAKEUP,
                alarmCalendar.timeInMillis,
                AlarmManager.INTERVAL_DAY,
                pendingIntentSunrise

            )
        }


    }

    suspend fun getTimeSunrise1(context: Context) {
        delay(20000)

        val alarmCalendar = Calendar.getInstance()
        val calendarNow2 = Calendar.getInstance()
        alarmCalendar.set(Calendar.HOUR_OF_DAY, morningHourTrimmed)
        alarmCalendar.set(Calendar.MINUTE, morningMinuteTrimmed)
        alarmCalendar.set(Calendar.SECOND, 0)
        alarmCalendar.set(Calendar.MILLISECOND, 0)
    //Toast.makeText(context, "Pokreće se getTimeSUnrise1 morningHourTrimmed ($morningHourTrimmed i morningMinuteTrimmed $morningMinuteTrimmed)", Toast.LENGTH_SHORT).show()
        alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val intent = Intent(context, MorningReceiver::class.java)

        val pendingIntentSunrise =
            PendingIntent.getBroadcast(context, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        if (alarmCalendar.after(calendarNow2)) {
            alarmManager.setRepeating(
                AlarmManager.RTC_WAKEUP,
                alarmCalendar.timeInMillis,
                AlarmManager.INTERVAL_DAY,
                pendingIntentSunrise
            )

        } else {
            alarmCalendar.add(Calendar.DAY_OF_MONTH, 1)
            alarmManager.setRepeating(
                AlarmManager.RTC_WAKEUP,
                alarmCalendar.timeInMillis,
                AlarmManager.INTERVAL_DAY,
                pendingIntentSunrise

            )
        }

    }

    fun getTimeSunset(context: Context) {

        val alarmCalendar = Calendar.getInstance()
        val calendarNow = Calendar.getInstance()

        alarmCalendar.set(Calendar.HOUR_OF_DAY, sunsetHour)
        alarmCalendar.set(Calendar.MINUTE, sunsetMinute)
        alarmCalendar.set(Calendar.SECOND, 0)
        alarmCalendar.set(Calendar.MILLISECOND, 0)
   //Toast.makeText(context, "Pokreće se getTimeSunset sunsetHour ($sunsetHour)", Toast.LENGTH_SHORT).show()
        alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val intent = Intent(context, NightReceiver::class.java)
        val pendingIntentSunset =
            PendingIntent.getBroadcast(context, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT)


        if (alarmCalendar.after(calendarNow)) {
            alarmManager.setRepeating(
                AlarmManager.RTC_WAKEUP,
                alarmCalendar.timeInMillis,
                AlarmManager.INTERVAL_DAY,
                pendingIntentSunset
            )

        } else {
            alarmCalendar.add(Calendar.DAY_OF_MONTH, 1)
            alarmManager.setRepeating(
                AlarmManager.RTC_WAKEUP,
                alarmCalendar.timeInMillis,
                AlarmManager.INTERVAL_DAY,
                pendingIntentSunset
            )
        }
    }

    suspend fun getTimeSunset1(context: Context) {
        delay(20000)


        val alarmCalendar = Calendar.getInstance()
        val calendarNow = Calendar.getInstance()
        alarmCalendar.set(Calendar.HOUR_OF_DAY, nightHourTrimmed)
        alarmCalendar.set(Calendar.MINUTE, nightMinuteTrimmed)
        alarmCalendar.set(Calendar.SECOND, 0)
        alarmCalendar.set(Calendar.MILLISECOND, 0)
    // Toast.makeText(context, "Pokreće se getTimeSunset1 nightHourTrimmed ($nightHourTrimmed i nightMinuteTrimmed $nightMinuteTrimmed  )", Toast.LENGTH_SHORT).show()
        alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val intent = Intent(context, NightReceiver::class.java)
        val pendingIntentSunset =
            PendingIntent.getBroadcast(context, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT)


        if (alarmCalendar.after(calendarNow)) {
            alarmManager.setRepeating(
                AlarmManager.RTC_WAKEUP,
                alarmCalendar.timeInMillis,
                AlarmManager.INTERVAL_DAY,
                pendingIntentSunset
            )

        } else {
            alarmCalendar.add(Calendar.DAY_OF_MONTH, 1)
            alarmManager.setRepeating(
                AlarmManager.RTC_WAKEUP,
                alarmCalendar.timeInMillis,
                AlarmManager.INTERVAL_DAY,
                pendingIntentSunset
            )
        }
    }


    @SuppressLint("MissingPermission")
    suspend fun location(activity: Activity) {

        if (checkPermissions(activity)) {
            try {
                delay(7000)
                fusedLocationClient.lastLocation.addOnCompleteListener(activity) { task ->

                    val location: Location? = task.result
                    if (location == null) {


                        requestNewLocationData(activity)
                    } else {
                        latitude = location.latitude.toString()
                        longitude = location.longitude.toString()
                        sharedPref.putLong(longitude)
                        sharedPref.putLat(latitude)

                 // Toast.makeText(activity, "Pokreće se location funkcija  7 sekundi kasni $latitude", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                Toast.makeText(activity, e.message, Toast.LENGTH_LONG).show()

            }
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(activity)
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
                            sharedPref.putLong(longitude)
                            sharedPref.putLat(latitude)
              //         Toast.makeText(activity, "Pokreće se funkcija getLastLocation", Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {

                    if (!(activity).isFinishing) {
                        try {
                            showAlert(activity)
                        } catch (e: WindowManager.BadTokenException) {
                            Log.e("WindowManagerBad ", e.toString())
                        }

                    }

                }

            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(activity)
                }
            }

        }

    }

    suspend fun getApi(context: Context) {
        delay(10000)
        val latt = sharedPref.getLat().toString()
        val longg = sharedPref.getLong().toString()
        val url = "http://api.aladhan.com/v1/timings?latitude=$latt&longitude=$longg&method=4"

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

              //  Toast.makeText(context,"Pokrece se getApi,$latt&longitude=$longg, $morningMinuteTrimmed $nightHourTrimmed", Toast.LENGTH_SHORT).show()

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
        delay(12000)

        val url =
            "http://api.aladhan.com/v1/timings?latitude=$latitude&longitude=$longitude&method=4"

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
                  //  Toast.makeText(context,"Pokrece se getApiUpdate,  ucitava trenutne koordinate \"http://api.aladhan.com/v1/timings?latitude=$latitude&longitude=$longitude&method=4\"", Toast.LENGTH_SHORT).show()

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
        locationRequest = LocationRequest().apply {
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            interval = 0
            fastestInterval = 0
            numUpdates = 2

        }


        fusedLocationClient.requestLocationUpdates(
            locationRequest, mLocationCallback, Looper.myLooper()
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
        val intent = Intent(activity, MorningReceiver::class.java)
        pendingIntent = PendingIntent.getBroadcast(activity, 1, intent, 0)
        alarmManager.cancel(pendingIntent)
        val intent1 = Intent(activity, NightReceiver::class.java)
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