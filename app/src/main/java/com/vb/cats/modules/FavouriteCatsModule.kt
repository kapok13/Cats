package com.vb.cats.modules

import com.vb.cats.database.database.CatsDatabase
import com.vb.cats.favouriteCats.model.FavouriteCatsModel
import com.vb.cats.favouriteCats.model.FavouriteCatsModelImpl
import com.vb.cats.favouriteCats.presentation.FavouriteCatsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.scope.get
import org.koin.dsl.module

val favouriteCatsModule = module {
    single<FavouriteCatsModel> {
        FavouriteCatsModelImpl(get(CatsDatabase::class.java))
    }
    viewModel<FavouriteCatsViewModel> {
        FavouriteCatsViewModel(get(FavouriteCatsModel::class.java))
    }
}
