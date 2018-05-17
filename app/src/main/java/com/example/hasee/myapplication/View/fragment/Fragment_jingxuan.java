package com.example.hasee.myapplication.View.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hasee.myapplication.R;
import com.example.hasee.myapplication.utils.ChenJinUtil;

public class Fragment_jingxuan extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       /* View view = View.inflate(getActivity(), R.layout.fragment_jingxuan_layout, null);
        return view;*/
        View view = View.inflate(getContext(), R.layout.fragment_jingxuan_layout, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
    @Override
    public void onResume() {
        super.onResume();
        initChenJin();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

        if(! hidden) {
            initChenJin();
        }
    }



    private void initChenJin() {
        ChenJinUtil.setStatusBarColor(getActivity(), Color.TRANSPARENT);
    }
}
