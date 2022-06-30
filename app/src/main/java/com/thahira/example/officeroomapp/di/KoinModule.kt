package com.thahira.example.officeroomapp.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.thahira.example.officeroomapp.adapter.PeopleRecyclerViewAdapter
import com.thahira.example.officeroomapp.rest.OfficeApi
import com.thahira.example.officeroomapp.rest.OfficeRepository
import com.thahira.example.officeroomapp.rest.OfficeRepositoryImpl
import com.thahira.example.officeroomapp.view.PeopleFragment
import com.thahira.example.officeroomapp.view.RoomFragment
import com.thahira.example.officeroomapp.viewmodel.OfficeViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module{
    fun provideRepos(officeApi: OfficeApi) : OfficeRepository = OfficeRepositoryImpl(officeApi)

    fun provideMoshi()= Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

    fun provideLoggingInterceptor() = HttpLoggingInterceptor().apply{
        level = HttpLoggingInterceptor.Level.BODY
    }

    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor)=
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30,TimeUnit.SECONDS)
            .readTimeout(30,TimeUnit.SECONDS)
            .writeTimeout(30,TimeUnit.SECONDS)
            .build()

    fun provideNetworkApi(okHttpClient: OkHttpClient,moshi: Moshi) =
        Retrofit.Builder()
            .baseUrl(OfficeApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient)
            .build()
            .create(OfficeApi::class.java)

    single { provideRepos(get())}
    single { provideMoshi()}
    single { provideLoggingInterceptor()}
    single { provideOkHttpClient(get())}
    single { provideNetworkApi(get(),get())}
}

val appModule = module{
    single {PeopleRecyclerViewAdapter(get())}
    single { PeopleFragment() }
    single { RoomFragment() }
}

val viewModelModule = module{
    viewModel{
        OfficeViewModel(get())
    }
}