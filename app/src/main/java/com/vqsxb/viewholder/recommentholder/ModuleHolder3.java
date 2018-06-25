package com.vqsxb.viewholder.recommentholder;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.vqsxb.R;
import com.vqsxb.bean.RecommendInfo;
import com.vqsxb.utils.ViewUtil;
import com.vqsxb.viewholder.BaseModuleHolder;
import com.vqsxb.widget.RecycItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/11/28 0028.
 */

public class ModuleHolder3 extends BaseModuleHolder {
    private TextView module3_item_more;
    private RecyclerView module3_item_recyclerview;
    private List<RecommendInfo.DataBean> commentItems = new ArrayList<>();
    private Context context;
    private Module3Adapter adapter;
    public ModuleHolder3(final Context context, View itemView) {
        super(itemView);
        this.context = context;
        module3_item_more = ViewUtil.find(itemView, R.id.module3_item_more);
        module3_item_recyclerview = ViewUtil.find(itemView,R.id.module3_item_recyclerview);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        module3_item_recyclerview.setLayoutManager(manager);
        adapter = new Module3Adapter(commentItems);
        module3_item_recyclerview.addItemDecoration(new RecycItemDecoration(context).setRight(R.dimen.x20));
        module3_item_recyclerview.setAdapter(adapter);
    }
    public void update(final RecommendInfo info){
        commentItems.clear();
        List<RecommendInfo.DataBean> data = info.getData();

        Log.e("++++>data", data.size()+"");

        for (RecommendInfo.DataBean appinfo : data){
            commentItems.add(appinfo);
        }

        Log.e("+++++>commentItems", commentItems.size()+"");

        adapter.setNewData(commentItems);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //ActivityUtil.gotoCommentDetailActivity(context,commentItems.get(position).getCard_id());
            }
        });
        module3_item_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ActivityUtil.gotoListCommentMoreActivity(context,info.getName(),info.getModel_type(),info.getLoad_type(),info.getLoad_value());
            }
        });
    }

    @Override
    public ImageView getImageView() {
        return null;
    }

    class Module3Adapter extends BaseQuickAdapter<RecommendInfo.DataBean, ModuleHolder3ItemHolder> {

        public Module3Adapter(@Nullable List<RecommendInfo.DataBean> data) {
            super(R.layout.layout_module3_item_item,data);
        }

        @Override
        protected void convert(ModuleHolder3ItemHolder helper, RecommendInfo.DataBean item) {
            helper.updata(context,item);
        }
    }
}
