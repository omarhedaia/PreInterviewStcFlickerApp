package com.hedaia.preinterviewstcflickerapp.di

import android.content.Context
import com.hedaia.preinterviewstcflickerapp.helpers.Helper
import com.hedaia.preinterviewstcflickerapp.model.APIInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object StcPostsModule {

    @Provides
    @Singleton
    fun provideRetrofitClient(@ApplicationContext context:Context):Retrofit
    {
        val cacheSize = (5 * 1024 * 1024).toLong() //5mb cache
        val myCache = Cache(context.cacheDir, cacheSize)

        val okHttpClient = OkHttpClient.Builder()
            .cache(myCache)
            .addInterceptor { chain ->
                var request = chain.request()
                request = if (Helper.isNetworkAvailable(context))
                    request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build()
                else
                    request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7).build()
                chain.proceed(request)
            }
            .build()
        return Retrofit.Builder()
            .baseUrl("https://www.flickr.com/services/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideAPIInterface(apiClient:Retrofit): APIInterface {
        return apiClient.create(APIInterface::class.java)
    }


}