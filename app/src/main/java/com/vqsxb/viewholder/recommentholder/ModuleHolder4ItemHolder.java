package com.vqsxb.viewholder.recommentholder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;
import com.vqsxb.R;
import com.vqsxb.bean.RecommendInfo;
import com.vqsxb.utils.ColorUtil;
import com.vqsxb.utils.OtherUtil;
import com.vqsxb.utils.ViewUtil;

/**
 * Created by lx on 2017/10/20.lx
 */

public class ModuleHolder4ItemHolder extends BaseViewHolder {
    ImageView module4_item_icon,module4_item_big_icon,module4_item_icon_bg;
    TextView module4_item_title,module4_item_score;

    public ModuleHolder4ItemHolder(View view) {
        super(view);
        module4_item_icon = ViewUtil.find(view, R.id.module4_item_icon);
        module4_item_title = ViewUtil.find(view,R.id.module4_item_title);
        module4_item_score = ViewUtil.find(view,R.id.module4_item_score);
        module4_item_big_icon = ViewUtil.find(view,R.id.module4_item_big_icon);
        module4_item_icon_bg = ViewUtil.find(view,R.id.module4_item_icon_bg);
    }
    public void updata(final Context context, RecommendInfo.DataBean item){
        if (OtherUtil.isEmpty(item.getRec_pic())){
            module4_item_big_icon.setVisibility(View.VISIBLE);
            module4_item_icon_bg.setVisibility(View.VISIBLE);
            Glide.with(context).load(item.getIcon()).asBitmap().into(module4_item_icon);
            module4_item_icon.setBackgroundColor(ColorUtil.getColor(context,ColorUtil.getRandomColor()));
        }else{
            module4_item_big_icon.setVisibility(View.INVISIBLE);
            module4_item_icon_bg.setVisibility(View.INVISIBLE);
            Glide.with(context).load(item.getIcon()).asBitmap().into(module4_item_icon);
        }
        module4_item_title.setText(item.getTitle());
        module4_item_score.setText(item.getScore());
    }
}
