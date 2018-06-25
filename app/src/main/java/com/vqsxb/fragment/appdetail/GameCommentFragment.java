package com.vqsxb.fragment.appdetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vqsxb.R;

/**
 * Created by Administrator on 2017/11/13 0013.
 */

public class GameCommentFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recomment, null);
        return view;
    }


    public static GameCommentFragment newInstance(String from) {
        GameCommentFragment fragment = new GameCommentFragment();
        Bundle bundle = new Bundle();
        bundle.putString("Appid", from);
        fragment.setArguments(bundle);
        return fragment;
    }
}
