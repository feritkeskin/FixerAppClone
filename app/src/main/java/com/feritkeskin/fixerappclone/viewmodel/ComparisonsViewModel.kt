package com.feritkeskin.fixerappclone.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.feritkeskin.fixerappclone.model.FixerModel
import com.feritkeskin.fixerappclone.service.Service
import com.feritkeskin.fixerappclone.util.Contains.API_KEY
import kotlinx.coroutines.launch

class ComparisonsViewModel : ViewModel() {

    private val _homeModel = MutableLiveData<FixerModel>()
    val homeModel: LiveData<FixerModel> = _homeModel

    fun getFixerData(
        startTime: String,
        endTime: String,
    ) {
        viewModelScope.launch {
            val hashMap = HashMap<String, String>()
            hashMap["start_date"] = startTime
            hashMap["base"] = "TRY"
            hashMap["end_date"] = endTime
            hashMap["apikey"] = API_KEY
            println("android vahit abi hashMap: $hashMap")
            val comparisonsRepository = Service().getUsers().getFixer(hashMap)
            _homeModel.value = comparisonsRepository
        }
    }
}