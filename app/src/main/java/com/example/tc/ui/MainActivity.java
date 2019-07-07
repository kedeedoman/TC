package com.example.tc.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.example.tc.R;
import com.example.tc.databinding.MainActivityBinding;
import com.example.tc.viewmodel.BlogViewModel;
import com.example.tc.viewmodel.ViewModelFactory;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class MainActivity extends AppCompatActivity {

    @Inject
    ViewModelFactory viewModelFactory;

    private MainActivityBinding binding;

    private BlogViewModel blogViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        blogViewModel = ViewModelProviders.of(this, viewModelFactory).get(BlogViewModel.class);
    }

    private void getData() {
        blogViewModel.getBlogDataAt10xIndex().observe(this, resource -> {
            if(resource.isLoading()) {
                showLoading(binding.textView10xChar, binding.progressBar10xChar);
            }
            else if(resource.isSuccess()) {
                updateData(binding.textView10xChar, binding.progressBar10xChar, resource.data);
            }
            else {
                updateData(binding.textView10xChar, binding.progressBar10xChar, getString(R.string.error));
            }
        });

        blogViewModel.getBlogDataAt0thIndex().observe(this, resource -> {
            if(resource.isLoading()) {
                showLoading(binding.textView0thChar, binding.progressBar0thChar);
            }
            else if(resource.isSuccess()) {
                updateData(binding.textView0thChar, binding.progressBar0thChar, resource.data);
            }
            else {
                updateData(binding.textView10xChar, binding.progressBar10xChar, getString(R.string.error));
            }
        });

        blogViewModel.getBlogWordCount().observe(this, resource -> {
            if(resource.isLoading()) {
                showLoading(binding.textViewWordCount, binding.progressBarWordCount);
            }
            else if(resource.isSuccess()) {
                updateData(binding.textViewWordCount, binding.progressBarWordCount, resource.data);
            }
            else {
                updateData(binding.textView10xChar, binding.progressBar10xChar, getString(R.string.error));
            }
        });
    }

    private void updateData(TextView textView, ProgressBar progressBar, String data) {
        progressBar.setVisibility(View.GONE);
        textView.setVisibility(View.VISIBLE);
        textView.setText(data);
    }

    private void showLoading(TextView textView, ProgressBar progressBar) {
        textView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    public void onButtonClick(View view) {
        binding.button.setVisibility(View.GONE);
        binding.textViewFirstCharTitle.setVisibility(View.VISIBLE);
        binding.textView10xCharTitle.setVisibility(View.VISIBLE);
        binding.textViewWordCountTitle.setVisibility(View.VISIBLE);
        getData();
    }
}
