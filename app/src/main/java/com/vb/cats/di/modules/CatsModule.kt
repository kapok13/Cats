package com.vb.cats.di.modules

import com.vb.cats.network.service.CatsApiService
import com.vb.cats.cats.model.CatsModel
import com.vb.cats.cats.model.CatsModelImpl
import com.vb.cats.cats.presentation.CatsViewModel
import com.vb.cats.cats.model.FavouriteCatsModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.scope.get
import org.koin.dsl.module

val catsModule = module {
    single<CatsModel> {
        CatsModelImpl(get(CatsApiService::class.java))
    }
    viewModel<CatsViewModel> {
        CatsViewModel(get(CatsModel::class.java), get(FavouriteCatsModel::class.java))
    }
}
