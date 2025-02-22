package com.example.address.di

import com.example.address.data.apiservice.AddressApiServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AddressApiServiceModule {
    @Provides
    @Singleton
    fun provideAddressApiServices(retrofit: Retrofit): AddressApiServices = retrofit.create(AddressApiServices::class.java)
}
