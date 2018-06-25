package com.vqsxb.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.callback.QuickAdapterCallback;
import com.vqsxb.R;
import com.vqsxb.bean.RecommendInfo;
import com.vqsxb.utils.ViewUtil;
import com.vqsxb.viewholder.BaseModuleHolder;
import com.vqsxb.viewholder.recommentholder.ModuleHolder1;
import com.vqsxb.viewholder.recommentholder.ModuleHolder10;
import com.vqsxb.viewholder.recommentholder.ModuleHolder11;
import com.vqsxb.viewholder.recommentholder.ModuleHolder2;
import com.vqsxb.viewholder.recommentholder.ModuleHolder3;
import com.vqsxb.viewholder.recommentholder.ModuleHolder4;
import com.vqsxb.viewholder.recommentholder.ModuleHolder5;
import com.vqsxb.viewholder.recommentholder.ModuleHolder6;
import com.vqsxb.viewholder.recommentholder.ModuleHolder7;
import com.vqsxb.viewholder.recommentholder.ModuleHolder8;

import java.util.List;

/**
 * Created by Administrator on 2017/11/28 0028.
 */

public class BaseModuleAdapter extends BaseQuickAdapter<RecommendInfo, BaseModuleHolder> {

    private Context mContext;
    private List<RecommendInfo> mDatas;
    private BaseModuleHolder holder;

    public BaseModuleAdapter(Context context, List<RecommendInfo> list) {
        super(list);
        this.mContext = context;
        this.mDatas = list;
        setCallback();
    }

    private void setCallback() {
        setCallback(new QuickAdapterCallback() {
            @Override
            public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                switch (ViewType.valueOf(viewType)){
                    case MODULE1:
                    case MODULE9:
                        ModuleHolder1 moduleHolder1 = new ModuleHolder1(mContext, ViewUtil.getView(mContext, parent, R.layout.item_module1_layout));
                        setHolder(moduleHolder1);
                        return moduleHolder1;
                    case MODULE2:
                        ModuleHolder2 moduleHolder2 = new ModuleHolder2(mContext,ViewUtil.getView(mContext, parent, R.layout.item_module2_layout));
                        setHolder(moduleHolder2);
                        return moduleHolder2;
                    case MODULE3:
                        ModuleHolder3 moduleHolder3 = new ModuleHolder3(mContext,ViewUtil.getView(mContext, parent, R.layout.item_module3_layout));
                        setHolder(moduleHolder3);
                        return moduleHolder3;
                    case MODULE4:
                        ModuleHolder4 moduleHolder4 = new ModuleHolder4(mContext,ViewUtil.getView(mContext, parent, R.layout.layout_module4_item));
                        setHolder(moduleHolder4);
                        return moduleHolder4;
                    case MODULE5:
                        ModuleHolder5 moduleHolder5 = new ModuleHolder5(mContext,ViewUtil.getView(mContext, parent, R.layout.layout_module5_item));
                        setHolder(moduleHolder5);
                        return moduleHolder5;
                    case MODULE6:
                        ModuleHolder6 moduleHolder6 = new ModuleHolder6(mContext,ViewUtil.getView(mContext, parent, R.layout.layout_module6_item));
                        setHolder(moduleHolder6);
                        return moduleHolder6;
                    case MODULE7:
                        ModuleHolder7 moduleHolder7 = new ModuleHolder7(mContext,ViewUtil.getView(mContext, parent, R.layout.layout_module7_item));
                        setHolder(moduleHolder7);
                        return moduleHolder7;
                    case MODULE8:
                        ModuleHolder8 moduleHolder8 = new ModuleHolder8(mContext,ViewUtil.getView(mContext, parent, R.layout.layout_module8_item));
                        setHolder(moduleHolder8);
                        return moduleHolder8;
                    case MODULE10:
                        ModuleHolder10 moduleHolder10 = new ModuleHolder10(mContext,ViewUtil.getView(mContext, parent, R.layout.layout_module10_item));
                        setHolder(moduleHolder10);
                        return moduleHolder10;
                    case MODULE11:
                        ModuleHolder11 moduleHolder11 = new ModuleHolder11(mContext,ViewUtil.getView(mContext, parent, R.layout.layout_module11_item));
                        setHolder(moduleHolder11);
                        return moduleHolder11;
                    default:
                        return null;
                }
            }
        });
    }

    public void setHolder(BaseModuleHolder holder){
        this.holder = holder;
    }
    public BaseModuleHolder getHolder(){
        return holder;
    }

    @Override
    protected void convert(BaseModuleHolder helper, RecommendInfo item) {
        holder.setIsRecyclable(false);
        if (holder instanceof ModuleHolder1){
            ((ModuleHolder1) holder).update(item);
        }else if (holder instanceof ModuleHolder2){
            ((ModuleHolder2) holder).update(item);
        }else if (holder instanceof ModuleHolder3){
            ((ModuleHolder3) holder).update(item);
        }else if (holder instanceof ModuleHolder4){
            ((ModuleHolder4) holder).update(item);
        }else if (holder instanceof ModuleHolder5){
            ((ModuleHolder5) holder).update(item);
        }else if (holder instanceof ModuleHolder6){
            ((ModuleHolder6) holder).update(item);
        }else if (holder instanceof ModuleHolder7){
            ((ModuleHolder7) holder).update(item);
        }else if (holder instanceof ModuleHolder8){
            ((ModuleHolder8) holder).update(item);
        }else if (holder instanceof ModuleHolder10){
            ((ModuleHolder10) holder).update(item);
        }else if (holder instanceof ModuleHolder11){
            ((ModuleHolder11) holder).update(item);
        }
    }

    @Override
    public int getItemViewType(int position) {

        if (mDatas.size()>position){
            RecommendInfo recommendInfo = mDatas.get(position);
            String model_type = recommendInfo.getInfo().getModel_type();
            return Integer.valueOf(model_type);
        }
        return super.getItemViewType(position);
    }
}
