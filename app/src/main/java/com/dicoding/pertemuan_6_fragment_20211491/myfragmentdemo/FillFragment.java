package com.dicoding.pertemuan_6_fragment_20211491.myfragmentdemo;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.dicoding.pertemuan_6_fragment_20211491.R;

public class FillFragment extends Fragment {
    
    public static final String TYPE = "type";
    public static final String TYPE_BLUE = "blue";
    public static final String TYPE_RED = "red";
    
    private String type;
    
    private ImageView imgFill;
    
    private OnViewCreatedListener onViewCreatedListener;
    
    public FillFragment() {
        // Required empty public constructor
    }
    
    public String getType() {
        return type;
    }
    
    public static FillFragment newInstance(String type) {
        FillFragment fragment = new FillFragment();
        Bundle args = new Bundle();
        args.putString(TYPE, type);
        fragment.setArguments(args);
        return fragment;
    }
    
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        
        MyFragmentDemoActivity myFragmentDemoActivity = (MyFragmentDemoActivity) getActivity();
        if (myFragmentDemoActivity != null) {
            onViewCreatedListener = myFragmentDemoActivity.onViewCreatedListener;
        }
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            type = getArguments().getString(TYPE);
        }
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fill, container, false);
    }
    
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    
        imgFill = view.findViewById(R.id.img_fill);
        
        if (type.equals(TYPE_BLUE)) {
            imgFill.setImageDrawable(ContextCompat.getDrawable(view.getContext(), R.drawable.ripple_square_p1));
        } else {
            imgFill.setImageDrawable(ContextCompat.getDrawable(view.getContext(), R.drawable.ripple_square_p2));
        }
    }
    
    @Override
    public void onResume() {
        super.onResume();
        onViewCreatedListener.onViewCreated();
    }
    
    public interface OnViewCreatedListener {
        void onViewCreated();
    }
}