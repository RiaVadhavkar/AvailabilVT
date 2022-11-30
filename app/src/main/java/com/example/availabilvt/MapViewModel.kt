package com.example.availabilvt


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MapViewModel () : ViewModel( ){
    private var markerSelectedLiveData = MutableLiveData<String>()
    private var markerSelected = ""

    init {
        markerSelectedLiveData.value = markerSelected
    }

    fun setMarker(s: String) {
        markerSelected = s
        markerSelectedLiveData.postValue(markerSelected)
    }

    fun getMarker() : MutableLiveData<String>{
        return markerSelectedLiveData
    }
}