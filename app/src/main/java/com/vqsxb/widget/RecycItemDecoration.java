package com.vqsxb.widget;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Administrator on 2017/10/17.
 */

public class RecycItemDecoration extends RecyclerView.ItemDecoration {
    private Context context;
    private int dividerTop = 0;
    private int dividerLeft = 0;
    private int dividerRight = 0;
    private int dividerBottom = 0;

    public RecycItemDecoration  setTop(int id){
        dividerTop = (int) context.getResources().getDimension(id);
        return this;
    }
    public RecycItemDecoration  setLeft(int id){
        dividerLeft = (int) context.getResources().getDimension(id);
        return this;
    }
    public RecycItemDecoration  setRight(int id){
        dividerRight = (int) context.getResources().getDimension(id);
        return this;
    }
    public RecycItemDecoration  setBottom(int id){
        dividerBottom = (int) context.getResources().getDimension(id);
        return this;
    }
    public RecycItemDecoration(Context context) {
        this.context = context;
    }
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.bottom=dividerBottom;
        outRect.top=dividerTop;
        outRect.left=dividerLeft;
        outRect.right=dividerRight;
    }
}
