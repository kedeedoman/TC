package com.example.tc.viewmodel;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;


import com.example.tc.data.Resource;
import com.example.tc.data.remote.ApiService;
import com.example.tc.data.repository.BlogRepository;

import javax.inject.Inject;


public class BlogViewModel extends ViewModel {

    private LiveData<Resource<String>> blogDataAt10xIndex;
    private LiveData<Resource<String>> blogDataAt0thIndex;
    private LiveData<Resource<String>> blogWordCount;

    private BlogRepository blogRepository;

    @Inject
    public BlogViewModel(ApiService apiService, Application application) {
        blogRepository = new BlogRepository(apiService, application);
        blogDataAt10xIndex = blogRepository.getBlogDataAt10xIndex();
        blogDataAt0thIndex = blogRepository.getBlogDataAt0thIndex();
        blogWordCount = blogRepository.getBlogWordCount();
    }

    public LiveData<Resource<String>> getBlogDataAt10xIndex() {
        return blogDataAt10xIndex;
    }

    public LiveData<Resource<String>> getBlogDataAt0thIndex() {
        return blogDataAt0thIndex;
    }

    public LiveData<Resource<String>> getBlogWordCount() {
        return blogWordCount;
    }
}
