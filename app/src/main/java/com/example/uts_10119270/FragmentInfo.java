package com.example.uts_10119270;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

//10119270
//Albanna Rahmadani Sulistyo
//IF-7

public class FragmentInfo extends Fragment {
    FragmentPagerAdapter fragmentPagerAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_info, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ViewPager viewPager = (ViewPager) getActivity().findViewById(R.id.viewPager);
        fragmentPagerAdapter = new CustomPagerAdapter(getParentFragmentManager());
        viewPager.setAdapter(fragmentPagerAdapter);
    }
}
