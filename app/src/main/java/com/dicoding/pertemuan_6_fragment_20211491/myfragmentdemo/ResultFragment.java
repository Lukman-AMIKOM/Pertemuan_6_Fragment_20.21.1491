package com.dicoding.pertemuan_6_fragment_20211491.myfragmentdemo;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.dicoding.pertemuan_6_fragment_20211491.R;

import java.util.Objects;

public class ResultFragment extends Fragment {
    
    public static final String EXTRA_RESULT = "result";
    public static final String RESULT_WINNER = "winner";
    public static final String RESULT_DRAW = "draw";
    
    private String result;
    private String winner;
    
    private TextView tvResult;
    private Button btnPlayAgain;
    
    private OnPlayAgainClickListener onPlayAgainClickListener;
    
    public ResultFragment() {
        // Required empty public constructor
    }
    
    public static ResultFragment newInstance(String result, String ... winner) {
        ResultFragment fragment = new ResultFragment();
        Bundle args = new Bundle();
        
        if (!result.equals(RESULT_WINNER) && !result.equals(RESULT_DRAW)) {
            throw new IllegalArgumentException("Result is not valid!");
        }
        
        args.putString(EXTRA_RESULT, result);
        if (winner.length > 0) {
            if (!winner[0].equals(FillFragment.TYPE_BLUE) && !winner[0].equals(FillFragment.TYPE_RED)) {
                throw new IllegalArgumentException("Result is not valid!");
            }
            
            args.putString(RESULT_WINNER, winner[0]);
        }
        
        fragment.setArguments(args);
        return fragment;
    }
    
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        
        MyFragmentDemoActivity myFragmentDemoActivity = (MyFragmentDemoActivity) getActivity();
        if (myFragmentDemoActivity != null) {
            onPlayAgainClickListener = myFragmentDemoActivity.onPlayAgainClickListener;
        }
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            result = getArguments().getString(EXTRA_RESULT);
            winner = getArguments().getString(RESULT_WINNER);
        }
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_result, container, false);
    }
    
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        tvResult = view.findViewById(R.id.tv_result);
        btnPlayAgain = view.findViewById(R.id.btn_play_again);
        
        if (result.equals(RESULT_DRAW)) {
            tvResult.setText(R.string.result_draw);
        } else {
            int bgId = 0;
            
            switch (winner) {
                case FillFragment.TYPE_BLUE:
                    tvResult.setText(R.string.result_blue_wins);
                    bgId = R.drawable.ripple_square_p1;
                    break;
                case FillFragment.TYPE_RED:
                    tvResult.setText(R.string.result_red_wins);
                    bgId = R.drawable.ripple_square_p2;
                    break;
            }
            
            tvResult.setBackground(ResourcesCompat.getDrawable(getResources(), bgId, Objects.requireNonNull(getContext()).getTheme()));
        }
        
        btnPlayAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPlayAgainClickListener.onPlayAgainClicked();
            }
        });
    }
    
    public interface OnPlayAgainClickListener {
        void onPlayAgainClicked();
    }
}