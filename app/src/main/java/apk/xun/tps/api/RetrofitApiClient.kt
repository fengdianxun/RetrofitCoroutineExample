package apk.xun.tps.api

import android.content.Context

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory

import java.net.CookieHandler
import java.net.CookieManager
import java.net.CookiePolicy
import java.util.ArrayList
import java.util.concurrent.TimeUnit

import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by fengdianxun on 2017/11/28.
 */

class RetrofitApiClient(private val root: String) {

    fun <API> create(context: Context, service: Class<API>): API {
        return setupRestClient(context).create(service)
    }

    private fun setupRestClient(context: Context): Retrofit {


        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val builder = OkHttpClient.Builder()
                .addNetworkInterceptor(interceptor)
                .readTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
        val gson = GsonBuilder().setLenient().create()
        return Retrofit.Builder()
                .baseUrl(root)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .client(builder.build())
                .build()
    }


}
