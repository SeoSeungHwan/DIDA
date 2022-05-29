package com.dida.android.di

import androidx.databinding.ktx.BuildConfig
import com.dida.android.data.interceptor.BearerInterceptor
import com.dida.android.data.interceptor.XAccessTokenInterceptor
import com.dida.android.util.GlobalConstant.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    } else {
        OkHttpClient.Builder()
            .readTimeout(5000, TimeUnit.MILLISECONDS)
            .connectTimeout(5000, TimeUnit.MILLISECONDS)
            // 로그캣에 okhttp.OkHttpClient로 검색하면 http 통신 내용을 보여줍니다.
            .addInterceptor(BearerInterceptor())
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addNetworkInterceptor(XAccessTokenInterceptor()) // JWT 자동 헤더 전송
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

//    @Provides
//    @Singleton
//    fun provideKakaoLoginApi(retrofit: Retrofit): KakaoLoginAPI =
//        retrofit.create(KakaoLoginAPI::class.java)
//
//    @Provides
//    @Singleton
//    fun provideNaverLoginApi(retrofit: Retrofit): NaverLoginAPI =
//        retrofit.create(NaverLoginAPI::class.java)
//
//    @Provides
//    @Singleton
//    fun provideRegisterApi(retrofit: Retrofit): RegisterAPI =
//        retrofit.create(RegisterAPI::class.java)

}