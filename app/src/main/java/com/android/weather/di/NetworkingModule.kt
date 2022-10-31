package com.android.weather.di

import android.content.Context
import com.android.weather.BuildConfig
import com.android.weather.data.api.ApiInterface
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkingModule = module {
    single { provideOkHttpClient(get()) }

    single { (GsonConverterFactory.create() as Converter.Factory) }
    single (named("GoogleMapRouteRetrofit")) { provideMain(get()) }
    single { get<Retrofit>(named("GoogleMapRouteRetrofit")).create(ApiInterface::class.java)}
}


fun provideOkHttpClient(context:Context) = if (BuildConfig.BUILD_TYPE != "release") {
    OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.HEADERS)
                .setLevel(HttpLoggingInterceptor.Level.BODY))
        .addInterceptor(chuck(context))
        .connectTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build()
} else OkHttpClient
    .Builder()
    .addInterceptor(
        HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.HEADERS)
            .setLevel(HttpLoggingInterceptor.Level.BODY))
    .addInterceptor(chuck(context))
    .connectTimeout(10, TimeUnit.MINUTES)
    .writeTimeout(10, TimeUnit.MINUTES)
    .readTimeout(10, TimeUnit.MINUTES)
    .build()

private fun chuck(context:Context): ChuckerInterceptor{
    return ChuckerInterceptor.Builder(context).build()
}


private fun provideMain(
    okHttpClient: OkHttpClient
): Retrofit =
    Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()


