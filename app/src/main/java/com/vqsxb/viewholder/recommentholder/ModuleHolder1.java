package com.vqsxb.viewholder.recommentholder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.vqsxb.R;
import com.vqsxb.bean.RecommendInfo;
import com.vqsxb.utils.ColorUtil;
import com.vqsxb.utils.OtherUtil;
import com.vqsxb.utils.ViewUtil;
import com.vqsxb.viewholder.BaseModuleHolder;

import java.util.List;

/**
 * Created by Administrator on 2017/11/28 0028.
 */

public class ModuleHolder1 extends BaseModuleHolder {

    private Context mContext;
    private RelativeLayout module1_item_rl;
    private ImageView module1_item_icon, module1_item_pic, module1_item_big_icon, module1_item_none_image;
    private TextView module1_item_title, module1_item_info, module1_item_score, module1_item_content;

    public ModuleHolder1(final Context context, View itemView) {
        super(itemView);
        mContext = context;
        module1_item_icon = ViewUtil.find(itemView, R.id.module1_item_icon);
        module1_item_title = ViewUtil.find(itemView, R.id.module1_item_title);
        module1_item_info = ViewUtil.find(itemView, R.id.module1_item_info);
        module1_item_score = ViewUtil.find(itemView, R.id.module1_item_score);
        module1_item_pic = ViewUtil.find(itemView, R.id.module1_item_pic);
        module1_item_big_icon = ViewUtil.find(itemView, R.id.module1_item_big_icon);
        module1_item_none_image = ViewUtil.find(itemView, R.id.module1_item_none_image);
        module1_item_content = ViewUtil.find(itemView, R.id.module1_item_content);
        module1_item_rl = ViewUtil.find(itemView,R.id.module1_item_rl);
    }

    @Override
    public ImageView getImageView() {
        return module1_item_icon;
    }

    public void update(final RecommendInfo info){
        List<RecommendInfo.DataBean> data = info.getData();
        for (RecommendInfo.DataBean appinfo : data){
            Glide.with(mContext).load(appinfo.getIcon()).asBitmap().into(module1_item_icon);
            module1_item_title.setText(appinfo.getTitle());
            module1_item_info.setText(appinfo.getTime()+"\t\t"+appinfo.getViews()+"人在玩\t\t" + appinfo.getCommentTotal()+"条评论");
            module1_item_score.setText(appinfo.getScore());
            module1_item_content.setText(appinfo.getBriefContent());
            module1_item_rl.setBackgroundColor(ColorUtil.getColor(mContext,ColorUtil.getRandomColor()));
            if (OtherUtil.isEmpty(appinfo.getRec_pic())){
                module1_item_pic.setVisibility(View.INVISIBLE);
                module1_item_big_icon.setVisibility(View.VISIBLE);
                module1_item_none_image.setVisibility(View.VISIBLE);
                Glide.with(mContext).load(appinfo.getIcon()).asBitmap().into(module1_item_big_icon);
            }else{
                module1_item_pic.setVisibility(View.VISIBLE);
                module1_item_big_icon.setVisibility(View.INVISIBLE);
                module1_item_none_image.setVisibility(View.INVISIBLE);
                Glide.with(mContext).load(appinfo.getRec_pic()).asBitmap().into(module1_item_pic);
            }
        }
    }
}
