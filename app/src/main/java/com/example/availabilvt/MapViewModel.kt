package com.example.availabilvt


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MapViewModel : ViewModel(){
    private var markerSelectedLiveData = MutableLiveData<String>()
    private var markerSelected = ""

    private var timeStartLiveData = MutableLiveData<String>()
    private var timeStart = ""

    private var timeEndLiveData = MutableLiveData<String>()
    private var timeEnd = ""

    private var dateLiveData = MutableLiveData<String>()
    private var date = ""

    private var partLiveData = MutableLiveData<String>()
    private var part = ""

    init {
        markerSelectedLiveData.value = markerSelected
        timeStartLiveData.value = timeStart
        timeEndLiveData.value = timeEnd
        dateLiveData.value = date
        partLiveData.value = part
    }


    fun getMarker() : MutableLiveData<String>{
        return markerSelectedLiveData
    }

    fun setMarker(s: String) {
        markerSelected = s
        markerSelectedLiveData.postValue(markerSelected)
    }

    fun getStart() : MutableLiveData<String>{
        return timeStartLiveData
    }

    fun setStart(s: String) {
        timeStart = s
        timeStartLiveData.postValue(timeStart)
    }

    fun getEnd() : MutableLiveData<String>{
        return timeEndLiveData
    }

    fun setEnd(s: String) {
        timeEnd = s
        timeEndLiveData.postValue(timeEnd)
    }

    fun getDate() : MutableLiveData<String>{
        return dateLiveData
    }

    fun setDate(s: String) {
        date = s
        dateLiveData.postValue(date)
    }

    fun getPart() : MutableLiveData<String>{
        return partLiveData
    }

    fun setPart(s: String) {
        part = s
        partLiveData.postValue(part)
    }


}