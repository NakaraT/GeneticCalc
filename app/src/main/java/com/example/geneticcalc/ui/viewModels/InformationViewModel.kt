package com.example.geneticcalc.ui.viewModels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class InformationViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is information Fragment"
    }
    val text: LiveData<String> = _text
}