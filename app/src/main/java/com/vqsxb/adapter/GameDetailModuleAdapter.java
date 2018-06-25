package com.vqsxb.adapter;

import android.content.Context;
import android.util.Log;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.callback.QuickAdapterCallback;
import com.vqsxb.R;
import com.vqsxb.bean.GameDetailsInfo;
import com.vqsxb.utils.OtherUtil;
import com.vqsxb.utils.ViewUtil;
import com.vqsxb.viewholder.BaseModuleHolder;
import com.vqsxb.viewholder.gamedetails.APPDetailModuleHolder1;
import com.vqsxb.viewholder.gamedetails.APPDetailModuleHolder10;
import com.vqsxb.viewholder.gamedetails.APPDetailModuleHolder11;
import com.vqsxb.viewholder.gamedetails.APPDetailModuleHolder12;
import com.vqsxb.viewholder.gamedetails.APPDetailModuleHolder13;
import com.vqsxb.viewholder.gamedetails.APPDetailModuleHolder2;
import com.vqsxb.viewholder.gamedetails.APPDetailModuleHolder3;
import com.vqsxb.viewholder.gamedetails.APPDetailModuleHolder4;
import com.vqsxb.viewholder.gamedetails.APPDetailModuleHolder5;
import com.vqsxb.viewholder.gamedetails.APPDetailModuleHolder6;
import com.vqsxb.viewholder.gamedetails.APPDetailModuleHolder7;
import com.vqsxb.viewholder.gamedetails.APPDetailModuleHolder8;
import com.vqsxb.viewholder.gamedetails.APPDetailModuleHolder9;
import com.vqsxb.viewholder.gamedetails.ViewTypeAPPDetail;

import java.util.List;

/**
 * Created by Administrator on 2017/12/1 0001.
 */

public class GameDetailModuleAdapter extends BaseQuickAdapter<GameDetailsInfo, BaseModuleHolder> {

    private Context mContext;
    private List<GameDetailsInfo> mDatas;
    private BaseModuleHolder holder;

    public GameDetailModuleAdapter(Context context, List<GameDetailsInfo> list) {
        super(list);
        this.mContext = context;
        this.mDatas = list;
        setCallback();
    }

    public void setHolder(BaseModuleHolder holder){
        this.holder = holder;
    }
    public BaseModuleHolder getHolder(){
        return holder;
    }

    @Override
    public int getItemViewType(int position) {
        String type;
        GameDetailsInfo gameDetailsInfo = mDatas.get(position);
        String model_type = gameDetailsInfo.getInfo().getType();
        if (OtherUtil.isNotEmpty(model_type)) {
            type = model_type;
        }else {
            type = gameDetailsInfo.getInfo().getModel_type();
        }
        Log.e("++++>type", type);
        return Integer.valueOf(type);
    }

    private void setCallback() {
        setCallback(new QuickAdapterCallback() {
            @Override
            public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                switch (ViewTypeAPPDetail.valueOf(viewType)) {
                    case MODULE1:
                        APPDetailModuleHolder1 moduleHolder1 = new APPDetailModuleHolder1(mContext, ViewUtil.getView(mContext, parent, R.layout.item_appdetail_module1));
                        setHolder(moduleHolder1);
                        return moduleHolder1;
                    case MODULE2:
                        APPDetailModuleHolder2 moduleHolder2 = new APPDetailModuleHolder2(mContext, ViewUtil.getView(mContext, parent, R.layout.item_appdetail_module2));
                        setHolder(moduleHolder2);
                        return moduleHolder2;
                    case MODULE3:
                        APPDetailModuleHolder3 moduleHolder3 = new APPDetailModuleHolder3(mContext, ViewUtil.getView(mContext, parent, R.layout.item_appdetail_module3));
                        setHolder(moduleHolder3);
                        return moduleHolder3;
                    case MODULE4:
                        APPDetailModuleHolder4 moduleHolder4 = new APPDetailModuleHolder4(mContext, ViewUtil.getView(mContext, parent, R.layout.item_appdetail_module4));
                        setHolder(moduleHolder4);
                        return moduleHolder4;
                    case MODULE5:
                        APPDetailModuleHolder5 moduleHolder5 = new APPDetailModuleHolder5(mContext, ViewUtil.getView(mContext, parent, R.layout.item_appdetail_module5));
                        setHolder(moduleHolder5);
                        return moduleHolder5;
                    case MODULE6:
                        APPDetailModuleHolder6 moduleHolder6 = new APPDetailModuleHolder6(mContext, ViewUtil.getView(mContext, parent, R.layout.item_appdetail_module1));
                        setHolder(moduleHolder6);
                        return moduleHolder6;
                    case MODULE7:
                        APPDetailModuleHolder7 moduleHolder7 = new APPDetailModuleHolder7(mContext, ViewUtil.getView(mContext, parent, R.layout.item_appdetail_module1));
                        setHolder(moduleHolder7);
                        return moduleHolder7;
                    case MODULE8:
                        APPDetailModuleHolder8 moduleHolder8 = new APPDetailModuleHolder8(mContext, ViewUtil.getView(mContext, parent, R.layout.item_appdetail_module1));
                        setHolder(moduleHolder8);
                        return moduleHolder8;
                    case MODULE9:
                        APPDetailModuleHolder9 moduleHolder9 = new APPDetailModuleHolder9(mContext, ViewUtil.getView(mContext, parent, R.layout.item_appdetail_module1));
                        setHolder(moduleHolder9);
                        return moduleHolder9;
                    case MODULE10:
                        APPDetailModuleHolder10 moduleHolder10 = new APPDetailModuleHolder10(mContext, ViewUtil.getView(mContext, parent, R.layout.item_appdetail_module1));
                        setHolder(moduleHolder10);
                        return moduleHolder10;
                    case MODULE11:
                        APPDetailModuleHolder11 moduleHolder11 = new APPDetailModuleHolder11(mContext, ViewUtil.getView(mContext, parent, R.layout.item_appdetail_module1));
                        setHolder(moduleHolder11);
                        return moduleHolder11;
                    case MODULE12:
                        APPDetailModuleHolder12 moduleHolder12 = new APPDetailModuleHolder12(mContext, ViewUtil.getView(mContext, parent, R.layout.item_appdetail_module1));
                        setHolder(moduleHolder12);
                        return moduleHolder12;
                    case MODULE13:
                        APPDetailModuleHolder13 moduleHolder13 = new APPDetailModuleHolder13(mContext, ViewUtil.getView(mContext, parent, R.layout.item_appdetail_module1));
                        setHolder(moduleHolder13);
                        return moduleHolder13;
                    default:
                        return null;
                }
            }
        });
    }


    @Override
    protected void convert(BaseModuleHolder helper, GameDetailsInfo item) {
        holder.setIsRecyclable(false);
        if (holder instanceof APPDetailModuleHolder1){
            ((APPDetailModuleHolder1) holder).update(item);
        }else if (holder instanceof APPDetailModuleHolder2){
            ((APPDetailModuleHolder2) holder).update(item);
        }else if (holder instanceof APPDetailModuleHolder3){
            ((APPDetailModuleHolder3) holder).update(item);
        }else if (holder instanceof APPDetailModuleHolder4){
            ((APPDetailModuleHolder4) holder).update(item);
        }else if (holder instanceof APPDetailModuleHolder5){
            ((APPDetailModuleHolder5) holder).update(item);
        }else if (holder instanceof APPDetailModuleHolder6){
            ((APPDetailModuleHolder6) holder).update(item);
        }else if (holder instanceof APPDetailModuleHolder7){
            ((APPDetailModuleHolder7) holder).update(item);
        }else if (holder instanceof APPDetailModuleHolder8){
            ((APPDetailModuleHolder8) holder).update(item);
        }else if (holder instanceof APPDetailModuleHolder9) {
            ((APPDetailModuleHolder9) holder).update(item);
        }else if (holder instanceof APPDetailModuleHolder10){
            ((APPDetailModuleHolder10) holder).update(item);
        }else if (holder instanceof APPDetailModuleHolder11){
            ((APPDetailModuleHolder11) holder).update(item);
        }else if (holder instanceof APPDetailModuleHolder12){
            ((APPDetailModuleHolder12) holder).update(item);
        }else if (holder instanceof APPDetailModuleHolder13){
            ((APPDetailModuleHolder13) holder).update(item);
        }
    }
}
