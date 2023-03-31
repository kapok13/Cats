package com.vb.cats.cats.model

import android.util.Log
import com.jakewharton.rxrelay3.BehaviorRelay
import com.vb.cats.modules.API_KEY
import com.vb.cats.network.service.CatsApiService
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

class CatsModelImpl(private val apiService: CatsApiService) : CatsModel {
    private var currentPage = 0

    private data class Pagination(
        val page: Int
    )

    companion object {
        private const val LIMIT = 20
    }

    override fun getNextPage() {
        paginationRelay.value?.let { pagination ->
            currentPage = pagination.page + 1
            paginationRelay.accept(pagination.copy(page = currentPage))
        }
    }

    override fun resetPage() {
        currentPage = 0
        paginationRelay.accept(Pagination(currentPage))
    }

    private val paginationRelay = BehaviorRelay.createDefault(Pagination(currentPage))

    override val cats: Observable<List<CatResponse>> = paginationRelay.switchMap {
        apiService.getCats(API_KEY, LIMIT, it.page)
            .doOnError { error ->
                Log.d(this::class.java.name, error.toString())
            }
            .subscribeOn(Schedulers.io())
    }.scan { oldState, newState ->
        if (currentPage == 0) {
            newState
        } else {
            oldState + newState
        }
    }
        .replay(1).autoConnect()
}
