package com.chad.library.adapter.base.callback;


import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseViewHolder;

public  interface QuickAdapterCallback<K extends BaseViewHolder> {
    BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType);
}
