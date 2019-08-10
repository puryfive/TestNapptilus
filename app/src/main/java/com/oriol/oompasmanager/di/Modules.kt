package com.oriol.oompasmanager.di

import com.oriol.oompasmanager.data.datasource.GetOmpaDetailDataSource
import com.oriol.oompasmanager.data.datasource.GetOmpaListDataSource
import com.oriol.oompasmanager.data.repository.GetOmpaDetailRepositoryImpl
import com.oriol.oompasmanager.data.repository.GetOmpaListRepositoryImpl
import com.oriol.oompasmanager.datasource.remote.DetailApi
import com.oriol.oompasmanager.datasource.remote.GetOmpaDetailDataSourceImpl
import com.oriol.oompasmanager.datasource.remote.GetOmpaListDataSourceImpl
import com.oriol.oompasmanager.datasource.remote.ListApi
import com.oriol.oompasmanager.domain.repository.GetOmpaDetailRepository
import com.oriol.oompasmanager.domain.repository.GetOmpaListRepository
import com.oriol.oompasmanager.domain.usecase.GetOmpaDetailUseCase
import com.oriol.oompasmanager.domain.usecase.GetOmpaListUseCase
import com.oriol.oompasmanager.presentation.ompadetail.OmpaDetailViewModel
import com.oriol.oompasmanager.presentation.ompalist.OmpaListViewModel
import com.oriol.oompasmanager.utils.BASE_URL
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val viewModelModule: Module = module {
    viewModel { OmpaListViewModel(getOmpaListUseCase = get()) }
    viewModel { OmpaDetailViewModel(getOmpaDetailUseCase = get()) }
}

val useCaseModule: Module = module {
    factory { GetOmpaListUseCase(getOmpaListRepository = get()) }
    factory { GetOmpaDetailUseCase(getOmpaDetailRepository = get()) }
}

val repositoryModule: Module = module {
    single { GetOmpaListRepositoryImpl(remoteDataSource = get()) as GetOmpaListRepository }
    single { GetOmpaDetailRepositoryImpl(remoteDataSource = get()) as GetOmpaDetailRepository }
}

val dataSourceModule: Module = module {
    single { GetOmpaListDataSourceImpl(api = listApi) as GetOmpaListDataSource }
    single { GetOmpaDetailDataSourceImpl(api = detailApi) as GetOmpaDetailDataSource }
}

val networkModule: Module = module {
    single { listApi }
    single { detailApi }
}

private val retrofit: Retrofit = Retrofit.Builder()
    .addCallAdapterFactory(
        RxJava2CallAdapterFactory.create())
    .addConverterFactory(
        GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

private val listApi: ListApi = retrofit.create(ListApi::class.java)
private val detailApi: DetailApi = retrofit.create(DetailApi::class.java)

