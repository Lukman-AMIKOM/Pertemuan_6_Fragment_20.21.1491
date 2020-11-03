package com.dicoding.pertemuan_6_fragment_20211491;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class CategoryFragment extends Fragment implements View.OnClickListener {
    
    public CategoryFragment() {
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
        return inflater.inflate(R.layout.fragment_category, container, false);
    }
    
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    
        Button btnSubCategory = view.findViewById(R.id.btn_sub_category);
        btnSubCategory.setOnClickListener(this);
    }
    
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_sub_category) {
            DetailCategoryFragment mDetailCategoryFragment = new DetailCategoryFragment();
            
            Bundle mBundle = new Bundle();
            mBundle.putString(DetailCategoryFragment.EXTRA_NAME, "Ini dikirim dengan Bundle.");
            String description = "Ini dikirim dengan Getter/Setter.";
            
            mDetailCategoryFragment.setArguments(mBundle);
            mDetailCategoryFragment.setDescription(description);
            
            /*
            Method addToBackStack akan menambahkan fragment ke backstack.
            Behaviour dari back button akan cek fragment dari backstack,
            jika ada fragment di dalam backstack maka fragment yang akan di-close/remove,
            jika sudak tidak ada fragment di dalam backstack maka activity yang akan
            di-close/finish.
             */
            FragmentManager mFragmentManager = getFragmentManager();
            if (mFragmentManager != null) {
                mFragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_container, mDetailCategoryFragment, DetailCategoryFragment.class.getSimpleName())
                        .addToBackStack(null)
                        .commit();
            }
        }
    }
}