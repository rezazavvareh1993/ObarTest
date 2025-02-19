package com.example.address.di

import com.example.address.data.AddressRepositoryImpl
import com.example.address.domain.AddressRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AddressRepositoryModule {
    @Singleton
    @Binds
    fun bindAlbumRepository(impl: AddressRepositoryImpl): AddressRepository
}
