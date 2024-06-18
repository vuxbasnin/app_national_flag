package vbn.clean.nation_flag.core.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import vbn.clean.nation_flag.core.common.Constants
import vbn.clean.nation_flag.core.common.HeaderRetrofitEnum
import vbn.clean.nation_flag.core.utils.Utility
import vbn.clean.nation_flag.data.local.dao.NationalFlagDao
import vbn.clean.nation_flag.data.local.room_database.NationalFlagRoomDatabase
import vbn.clean.nation_flag.data.network.service.DemoService
import java.util.Collections
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitServiceModule {
    private fun getHttpClient(
        context: Context,
        headerRetrofitEnum: HeaderRetrofitEnum = HeaderRetrofitEnum.NONE
    ): OkHttpClient {
        val deviceId = Utility.getDeviceId(context)
        return OkHttpClient.Builder().also { client ->
            client.retryOnConnectionFailure(true)
            client.addInterceptor {
                val newRequest = it.request().newBuilder().apply {
                    //check enum header in here to set header
                }.build()
                it.proceed(newRequest)
            }
            if (vbn.clean.nation_flag.BuildConfig.DEBUG) {
                val loggingContent = HttpLoggingInterceptor()
                loggingContent.setLevel(HttpLoggingInterceptor.Level.BODY)
                val collector = ChuckerCollector(context)
                val logging = ChuckerInterceptor.Builder(context).alwaysReadResponseBody(true)
                    .collector(collector).build()
                client.interceptors().add(logging)
                client.interceptors().add(loggingContent)
            }
            client.connectTimeout(30, TimeUnit.SECONDS)
            client.readTimeout(30, TimeUnit.SECONDS)
            client.protocols(Collections.singletonList(Protocol.HTTP_1_1))
        }.build()
    }

    @Provides
    @Singleton
    @Named(Constants.Inject.API)
    fun provideDemoRetrofit(gson: Gson, context: Context): Retrofit {
        return Retrofit.Builder()
            .baseUrl(vbn.clean.nation_flag.BuildConfig.BASE_URL_NATION_FLAG)
            .client(getHttpClient(context))
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Singleton
    fun provideAppService(@Named(Constants.Inject.API) retrofit: Retrofit): DemoService =
        retrofit.create(DemoService::class.java)

    @Provides
    @Singleton
    fun provideNationalFlagDao(nationalFlagRoomDatabase: NationalFlagRoomDatabase): NationalFlagDao {
        return nationalFlagRoomDatabase.nationalFlagDao()
    }
}