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

public class ModuleHolder2 extends BaseModuleHolder {

    private RelativeLayout module2_item_rl;
    private ImageView module2_item_icon,module2_item_pic,module2_item_big_icon,module2_item_none_image;
    private TextView module2_item_title,module2_item_info,module2_item_content;
    private Context context;
    public ModuleHolder2(final Context context, View itemView) {
        super(itemView);
        this.context = context;
        module2_item_icon = ViewUtil.find(itemView, R.id.module2_item_icon);
        module2_item_pic = ViewUtil.find(itemView,R.id.module2_item_pic);
        module2_item_title = ViewUtil.find(itemView,R.id.module2_item_title);
        module2_item_info = ViewUtil.find(itemView,R.id.module2_item_info);
        module2_item_content = ViewUtil.find(itemView,R.id.module2_item_content);
        module2_item_rl = ViewUtil.find(itemView,R.id.module2_item_rl);
        module2_item_big_icon = ViewUtil.find(itemView,R.id.module2_item_big_icon);
        module2_item_none_image = ViewUtil.find(itemView,R.id.module2_item_none_image);
    }
    public void update(RecommendInfo info){
        List<RecommendInfo.DataBean> data = info.getData();
        for (final RecommendInfo.DataBean appinfo : data){
            Glide.with(context).load(appinfo.getIcon()).asBitmap().into(module2_item_icon);
            module2_item_title.setText(appinfo.getTitle());
            module2_item_content.setText(appinfo.getBriefContent());
            module2_item_info.setText("来自广告" + appinfo.getCommentTotal()+"条评论");
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // ActivityUtil.gotoUnknownActivity(context, appinfo.getAppID(),appinfo.getRelation_type());
                }
            });
            module2_item_rl.setBackgroundColor(ColorUtil.getColor(context,ColorUtil.getRandomColor()));
            if (OtherUtil.isEmpty(appinfo.getRec_pic())){
                module2_item_pic.setVisibility(View.INVISIBLE);
                module2_item_big_icon.setVisibility(View.VISIBLE);
                module2_item_none_image.setVisibility(View.VISIBLE);
                Glide.with(context).load(appinfo.getIcon()).asBitmap().into(module2_item_big_icon);
            }else{
                module2_item_pic.setVisibility(View.VISIBLE);
                module2_item_big_icon.setVisibility(View.INVISIBLE);
                module2_item_none_image.setVisibility(View.INVISIBLE);
                Glide.with(context).load(appinfo.getRec_pic()).asBitmap().into(module2_item_pic);
            }
        }

    }

    @Override
    public ImageView getImageView() {
        return module2_item_icon;
    }
}
