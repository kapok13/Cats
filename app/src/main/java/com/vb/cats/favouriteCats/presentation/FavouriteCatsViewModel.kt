package com.vb.cats.favouriteCats.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vb.cats.database.entity.Cat
import com.vb.cats.favouriteCats.model.FavouriteCatsModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class FavouriteCatsViewModel(private val numberInputModel: FavouriteCatsModel) : ViewModel() {
    fun getFavouriteCats(): MutableLiveData<List<Cat>> {
        val response = MutableLiveData<List<Cat>>()
        numberInputModel.getFavouriteCats()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                response.value = it
            }
        return response
    }
}
