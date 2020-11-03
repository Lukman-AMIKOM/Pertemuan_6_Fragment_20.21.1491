package com.dicoding.pertemuan_6_fragment_20211491;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.dicoding.pertemuan_6_fragment_20211491.myfragmentdemo.MyFragmentDemoActivity;

public class HomeFragment extends Fragment implements View.OnClickListener {
    
    public HomeFragment() {
        // Required empty public constructor
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
    
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    
        Button btnCategory = view.findViewById(R.id.btn_category);
        Button btnFragmentDemo = view.findViewById(R.id.btn_fragment_demo);
        
        btnCategory.setOnClickListener(this);
        btnFragmentDemo.setOnClickListener(this);
    }
    
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_category) {
            CategoryFragment mCategoryFragment = new CategoryFragment();
            FragmentManager mFragmentManager = getFragmentManager();
            
            if (mFragmentManager != null) {
                mFragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_container, mCategoryFragment, CategoryFragment.class.getSimpleName())
                        .addToBackStack(null)
                        .commit();
            }
        } else if (v.getId() == R.id.btn_fragment_demo) {
            Intent fragmentDemoIntent = new Intent(getActivity(), MyFragmentDemoActivity.class);
            startActivity(fragmentDemoIntent);
        }
    }
}