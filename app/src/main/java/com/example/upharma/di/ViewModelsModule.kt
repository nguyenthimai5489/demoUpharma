package com.example.upharma.di


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.upharma.employee.viewmodel.Invoice.InvoiceProductLstViewModel
import com.example.upharma.employee.viewmodel.user.LoginViewModel

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelsModule {

    // We'd like to take this implementation of the ViewModel class and make it available in an injectable map with MainViewModel::class as a key to that map.

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    //user
    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class) // We use a restriction on multibound map defined with @ViewModelKey annotation, and if don't need any, we should use @ClassKey annotation provided by Dagger.
    abstract fun bindLoginViewModel(loginViewModel: LoginViewModel): ViewModel

    //invocie
    @Binds
    @IntoMap
    @ViewModelKey(InvoiceProductLstViewModel::class)
    abstract fun bindInvoiceProductLstViewModel(invoiceProductLstViewModel: InvoiceProductLstViewModel): ViewModel



}