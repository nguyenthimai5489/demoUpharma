package com.example.upharma.di

import android.content.Context
import androidx.room.Room
import com.example.upharma.App
import com.example.upharma.BuildConfig
import com.example.upharma.common.AuthenticationInterceptor
import com.example.upharma.common.LiveDataCallAdapterFactory
import com.example.upharma.dao.UserDAO
import com.example.upharma.data.AppDatabase
import com.example.upharma.utilities.API_SERVICE_BASE_URL
import com.example.upharma.utilities.DATABASE_NAME
import com.example.upharma.utilities.DATE_FORMAT_PATTERN
import com.example.upharma.webservice.WebService
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * AppModule will provide app-wide dependencies for a part of the application.
 * It should initialize objects used across our application, such as Room database, Retrofit, Shared Preference, etc.
 */
@Module(includes = [ViewModelsModule::class])
class AppModule() {

    @Singleton // Annotation informs Dagger compiler that the instance should be created only once in the entire lifecycle of the application.
    @Provides // Annotation informs Dagger compiler that this method is the constructor for the Context return type.
    fun provideContext(app: App): Context = app // Using provide as a prefix is a common convention but not a requirement.

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        // We need to prepare a custom OkHttp client because need to use our custom call interceptor.
        // to be able to authenticate our requests.
        val builder = OkHttpClient.Builder()
        // We add the interceptor to OkHttpClient.
        // It will add authentication headers to every call we make.
        builder.interceptors().add(AuthenticationInterceptor())

        // Configure this client not to retry when a connectivity problem is encountered.
        builder.retryOnConnectionFailure(false)

        // Log requests and responses.
        // Add logging as the last interceptor, because this will also log the information which
        // you added or manipulated with previous interceptors to your request.
        builder.interceptors().add(HttpLoggingInterceptor().apply {
            // For production environment to enhance apps performance we will be skipping any
            // logging operation. We will show logs just for debug builds.
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        })
        return builder.build()
    }

    @Provides
    @Singleton
    fun provideApiService(httpClient: OkHttpClient): WebService {
        return Retrofit.Builder() // Create retrofit builder.
            .baseUrl(API_SERVICE_BASE_URL) // Base url for the api has to end with a slash.
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setDateFormat(DATE_FORMAT_PATTERN).create())) // Use GSON converter for JSON to POJO object mapping.
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .client(httpClient) // Here we set the custom OkHttp client we just created.
            .build().create(WebService::class.java) // We create an API using the interface we defined.
    }

    @Provides
    @Singleton
    fun provideDb(app: App): AppDatabase {
        return Room.databaseBuilder(app, AppDatabase::class.java, DATABASE_NAME)
            // At current moment we don't want to provide migrations and specifically want database to be cleared when upgrade the version.
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideUserDAO(db: AppDatabase): UserDAO {
        return db.userDao()
    }

}