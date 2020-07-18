@file:Suppress("UNREACHABLE_CODE")

package com.example.zikr

import android.content.Context
import android.content.SharedPreferences


class SharedPref(context: Context) {
    private val settingsPref: SharedPreferences = context.getSharedPreferences("SETTINGS", Context.MODE_PRIVATE)


    fun saveDialog1(state: Boolean){
        val editor: SharedPreferences.Editor = settingsPref.edit()
        editor.putBoolean("dialogShown1", state)
        editor.apply()
    }


//    fun putString(key: String, value: String){
//        val editor: SharedPreferences.Editor = settingsPref.edit()
//        editor.putString(key, value).apply()
//        Log.i("PUTSTRING",value)
//    }
//    fun getString(key: String): String?{
//        return settingsPref.getString(key, "")
//        Log.i("GETSTRING", key)
//    }

    fun putHourSunrise(nightHourTrimmed: Int){
        val editor: SharedPreferences.Editor = settingsPref.edit()
        editor.putInt("SUNRISEHOUR", nightHourTrimmed).apply()
    }
    fun getHourSunrise(): Int?{
        return settingsPref.getInt("SUNRISEHOUR", 0)
    }

    fun putMinuteSunrise(nightMinuteTrimmed: Int){
        val editor: SharedPreferences.Editor = settingsPref.edit()
        editor.putInt("SUNRISEMINUTE", nightMinuteTrimmed).apply()
    }
    fun getMinuteSunrise(): Int?{
        return settingsPref.getInt("SUNRISEMINUTE", 0)
    }
    fun putHourSunset(morningHourTrimmed: Int){
        val editor: SharedPreferences.Editor = settingsPref.edit()
        editor.putInt("SUNSETHOUR", morningHourTrimmed).apply()
    }
    fun getHourSunset(): Int?{
        return settingsPref.getInt("SUNSETHOUR", 0)
    }
    fun putMinuteSunset(morningHourTrimmed: Int){
        val editor: SharedPreferences.Editor = settingsPref.edit()
        editor.putInt("SUNSETMINUTE", morningHourTrimmed).apply()
    }
    fun getMinuteSunset(): Int?{
        return settingsPref.getInt("SUNSETMINUTE", 0)
    }

    fun putLong(longitude1: String){
        val editor: SharedPreferences.Editor = settingsPref.edit()
        editor.putString("LONGITUDE", longitude1).apply()

    }



    fun getLong(): String?{
        return settingsPref.getString("LONGITUDE", "")
    }
    fun putLat(latitude1: String){
        val editor: SharedPreferences.Editor = settingsPref.edit()
        editor.putString("LATITUDE", latitude1).apply()
    }
    fun getLat(): String?{
        return settingsPref.getString("LATITUDE", "")
    }
    fun loadDialog1(): Boolean{
        return settingsPref.getBoolean("dialogShown1", false)
    }


    fun saveArabicState1(state: Boolean) {
        val editor: SharedPreferences.Editor = settingsPref.edit()
        editor.putBoolean("ArabicState1", state)
        editor.apply()
    }

    fun loadArabicState1(): Boolean {
        return settingsPref.getBoolean("ArabicState1", false)
    }

    fun saveBosnianState1(state: Boolean) {
        val editor: SharedPreferences.Editor = settingsPref.edit()
        editor.putBoolean("BosnianState1", state)
        editor.apply()
    }

    fun loadBosnianState1(): Boolean {
        return settingsPref.getBoolean("BosnianState1", false)
    }
    fun saveTranscriptionState1(state: Boolean) {
        val editor: SharedPreferences.Editor = settingsPref.edit()
        editor.putBoolean("TranscriptionState1", state)
        editor.apply()
    }

    fun loadTranscriptionState1(): Boolean {
        return settingsPref.getBoolean("TranscriptionState1", false)
    }
    fun saveArabicState(state: Boolean) {
        val editor: SharedPreferences.Editor = settingsPref.edit()
        editor.putBoolean("ArabicState", state)
        editor.apply()
    }

    fun loadArabicState(): Boolean {
        return settingsPref.getBoolean("ArabicState", false)
    }

    fun saveBosnianState(state: Boolean) {
        val editor: SharedPreferences.Editor = settingsPref.edit()
        editor.putBoolean("BosnianState", state)
        editor.apply()
    }

    fun loadBosnianState(): Boolean {
        return settingsPref.getBoolean("BosnianState", false)
    }
    fun saveTranscriptionState(state: Boolean) {
        val editor: SharedPreferences.Editor = settingsPref.edit()
        editor.putBoolean("TranscriptionState", state)
        editor.apply()
    }

    fun loadTranscriptionState(): Boolean {
        return settingsPref.getBoolean("TranscriptionState", false)
    }
    fun saveSwitchState(state: Boolean) {
        val editor: SharedPreferences.Editor = settingsPref.edit()
        editor.putBoolean("SWITCH", state)
        editor.apply()
    }

    fun loadSwitchState(): Boolean {
        return settingsPref.getBoolean("SWITCH", true)
    }


}

