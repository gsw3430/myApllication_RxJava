package com.vqsxb.viewholder.recommentholder;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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

public class ModuleHolder4 extends BaseModuleHolder {

    private TextView module4_item_more ,module4_title;
    private RelativeLayout module4_rl;
    private RecyclerView module4_item_recyclerview;
    private List<RecommendInfo.DataBean> commentItems = new ArrayList<>();
    private Context context;
    private Module4Adapter adapter;
    private RecommendInfo info;
    public ModuleHolder4(final Context context, View itemView) {
        super(itemView);
        this.context = context;
        module4_item_more = ViewUtil.find(itemView,R.id.module4_item_more);
        module4_title = ViewUtil.find(itemView,R.id.module4_title);
        module4_rl = ViewUtil.find(itemView,R.id.module4_rl);
        module4_item_recyclerview = ViewUtil.find(itemView,R.id.module4_item_recyclerview);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        module4_item_recyclerview.setLayoutManager(manager);
        adapter = new Module4Adapter(commentItems);
        module4_item_recyclerview.addItemDecoration(new RecycItemDecoration(context).setLeft(R.dimen.x15).setRight(R.dimen.x5));
        module4_item_recyclerview.setAdapter(adapter);
       /* module4_rl.setOnClickListener(this);
        module4_item_more.setOnClickListener(this);*/
    }
    public void update(RecommendInfo info){
        this.info = info;
        commentItems.clear();
        List<RecommendInfo.DataBean> data = info.getData();
        for (RecommendInfo.DataBean appinfo : data){
            commentItems.add(appinfo);
        }
        adapter.setNewData(commentItems);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                // ActivityUtil.gotoDetailActivity(context,commentItems.get(position).getAppID());
            }
        });
        module4_title.setText(info.getInfo().getName());
    }

    @Override
    public ImageView getImageView() {
        return null;
    }

  /*  @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.module4_item_more:
                ActivityUtil.gotoListGameMoreActivity(context,info.getName(),info.getModel_type(),info.getLoad_type(),info.getLoad_value());
                break;
            case R.id.module4_rl:
                break;
        }

    }*/

    class Module4Adapter extends BaseQuickAdapter<RecommendInfo.DataBean,ModuleHolder4ItemHolder> {

        public Module4Adapter(@Nullable List<RecommendInfo.DataBean> data) {
            super(R.layout.layout_module4_item_item,data);
        }

        @Override
        protected void convert(ModuleHolder4ItemHolder helper, RecommendInfo.DataBean item) {
            helper.updata(context,item);
        }
    }

}
