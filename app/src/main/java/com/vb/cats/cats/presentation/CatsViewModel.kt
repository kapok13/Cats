package com.vb.cats.cats.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vb.cats.database.entity.Cat
import com.vb.cats.cats.model.CatsModel
import com.vb.cats.cats.model.transformToCat
import com.vb.cats.favouriteCats.model.FavouriteCatsModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

class CatsViewModel(
    private val catsModel: CatsModel,
    private val favouriteCatsModel: FavouriteCatsModel
) : ViewModel() {

    fun getNextPage() {
        catsModel.getNextPage()
    }

    fun getCats(): MutableLiveData<List<Cat>> {
        val response = MutableLiveData<List<Cat>>()
        Observable.combineLatest(
            catsModel.cats,
            favouriteCatsModel.getFavouriteCats()
        ) { catsResponse, favouriteCats ->
            val cats = mutableListOf<Cat>()
            catsResponse.forEach { currentCat ->
                if (favouriteCats.find { it.id == currentCat.id } != null) {
                    cats.add(currentCat.transformToCat().copy(isFavourite = true))
                } else {
                    cats.add(currentCat.transformToCat())
                }
            }
            cats
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                response.value = it
            }
        return response
    }

    fun addCatToFavourites(cat: Cat) {
        favouriteCatsModel.insertCat(cat)
    }
}
