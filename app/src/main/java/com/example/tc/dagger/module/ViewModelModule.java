package com.example.tc.dagger.module;



import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.tc.dagger.key.ViewModelKey;
import com.example.tc.viewmodel.BlogViewModel;
import com.example.tc.viewmodel.ViewModelFactory;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);

    @Binds
    @IntoMap
    @ViewModelKey(BlogViewModel.class)
    protected abstract ViewModel blogViewModel(BlogViewModel blogViewModel);
}