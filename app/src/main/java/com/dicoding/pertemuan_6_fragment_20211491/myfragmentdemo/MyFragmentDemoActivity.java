package com.dicoding.pertemuan_6_fragment_20211491.myfragmentdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.dicoding.pertemuan_6_fragment_20211491.R;

public class MyFragmentDemoActivity extends AppCompatActivity implements View.OnClickListener {
    
    private FragmentManager fragmentManager;
    
    private FrameLayout[][] board;
    
    private boolean p1turn = true;
    private int turns = 0;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_fragment_demo);
        
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("My Fragment Demo");
            getSupportActionBar().setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.background_actionbar_fragment_demo));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        
        init();
    }
    
    private void init() {
        board = new FrameLayout[4][4];
        
        String strResId = "frame_";
        int resId;
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                resId = getResources().getIdentifier(strResId + i + "_" + j, "id", getPackageName());
                (board[i][j] = findViewById(resId)).setOnClickListener(this);
            }
        }
    }
    
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return true;
    }
    
    @Override
    public void onClick(View v) {
        String type;
        if (p1turn) {
            type = FillFragment.TYPE_BLUE;
        } else {
            type = FillFragment.TYPE_RED;
        }
        
        fragmentManager = getSupportFragmentManager();
        FillFragment fillFragment = FillFragment.newInstance(type);
        
        fragmentManager
                .beginTransaction()
                .add(v.getId(), fillFragment, String.valueOf(v.getId()))
                .commit();
        v.setClickable(false);
        
        p1turn = !p1turn;
        turns++;
    }
    
    private String check() {
        String f1, f2, f3, f4;
        // Horizontal check
        for (FrameLayout[] frameLayouts : board) {
            f1 = getFillFragment(frameLayouts[0]);
            f2 = getFillFragment(frameLayouts[1]);
            f3 = getFillFragment(frameLayouts[2]);
            f4 = getFillFragment(frameLayouts[3]);
            if (check(f1, f2, f3, f4)) {
                return f1;
            }
        }
        
        // Vertical check
        for (int i = 0; i < board.length; i++) {
            f1 = getFillFragment(board[0][i]);
            f2 = getFillFragment(board[1][i]);
            f3 = getFillFragment(board[2][i]);
            f4 = getFillFragment(board[3][i]);
            if (check(f1, f2, f3, f4)) {
                return f1;
            }
        }
        
        // Diagonal check
        f1 = getFillFragment(board[0][0]);
        f2 = getFillFragment(board[1][1]);
        f3 = getFillFragment(board[2][2]);
        f4 = getFillFragment(board[3][3]);
        if (check(f1, f2, f3, f4)) {
            return f1;
        }
        
        f1 = getFillFragment(board[0][3]);
        f2 = getFillFragment(board[1][2]);
        f3 = getFillFragment(board[2][1]);
        f4 = getFillFragment(board[3][0]);
        if (check(f1, f2, f3, f4)) {
            return f1;
        }
        
        return null;
    }
    
    private boolean check(String f1Type, String f2Type, String f3Type, String f4Type) {
        return !f1Type.equals("null") && f1Type.equals(f2Type) && f1Type.equals(f3Type) && f1Type.equals(f4Type);
    }
    
    private String getFillFragment(FrameLayout frameLayout) {
        FillFragment fillFragment = (FillFragment) fragmentManager.findFragmentById(frameLayout.getId());
        
        if (fillFragment != null) {
            return fillFragment.getType();
        } else {
            return "null";
        }
    }
    
    private void setFrameLayoutState(boolean state) {
        for (FrameLayout[] frameLayouts : board) {
            for (FrameLayout frameLayout : frameLayouts) {
                frameLayout.setClickable(state);
            }
        }
    }
    
    public final FillFragment.OnViewCreatedListener onViewCreatedListener = new FillFragment.OnViewCreatedListener() {
        @Override
        public void onViewCreated() {
            
            if (turns >= 7 && turns < 16) {
                String winner = check();
                if (winner != null) {
                    ResultFragment resultFragment = ResultFragment.newInstance(ResultFragment.RESULT_WINNER, winner);
                    fragmentManager
                            .beginTransaction()
                            .add(R.id.frame_result, resultFragment, ResultFragment.class.getSimpleName())
                            .commit();
                    
                    setFrameLayoutState(false);
                    turns = 0;
                    p1turn = true;
                }
            } else if (turns >= 16) {
                ResultFragment resultFragment = ResultFragment.newInstance(ResultFragment.RESULT_DRAW);
                fragmentManager
                        .beginTransaction()
                        .add(R.id.frame_result, resultFragment, ResultFragment.class.getSimpleName())
                        .commit();
                turns = 0;
                p1turn = true;
            }
        }
    };
    
    public final ResultFragment.OnPlayAgainClickListener onPlayAgainClickListener = new ResultFragment.OnPlayAgainClickListener() {
        @Override
        public void onPlayAgainClicked() {
            for (Fragment fragment : fragmentManager.getFragments()) {
                fragmentManager.beginTransaction().remove(fragment).commit();
            }
            setFrameLayoutState(true);
        }
    };
}