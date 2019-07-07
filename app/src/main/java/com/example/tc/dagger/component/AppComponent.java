package com.example.tc.dagger.component;

import android.app.Application;

import com.example.tc.App;
import com.example.tc.dagger.module.ActivityModule;
import com.example.tc.dagger.module.ApiModule;
import com.example.tc.dagger.module.ViewModelModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;


@Singleton
@Component(modules = {
        ApiModule.class,
        ViewModelModule.class,
        ActivityModule.class,
        AndroidInjectionModule.class})
public interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

    void inject(App app);

}
