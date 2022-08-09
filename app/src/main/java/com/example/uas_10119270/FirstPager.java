package com.example.uas_10119270;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

//10119270
//Albanna Rahmadani Sulistyo
//IF-7

public class FirstPager extends Fragment {
    private String title;
    private int page;

    public static FirstPager newInstance(int page, String title) {
        FirstPager firstPager = new FirstPager();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someStr", title);
        firstPager.setArguments(args);
        return firstPager;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someStr");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pager1, container, false);
        return view;
    }
}
