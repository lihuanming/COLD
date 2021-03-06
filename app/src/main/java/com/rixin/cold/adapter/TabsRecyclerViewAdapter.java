package com.rixin.cold.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.rixin.cold.R;
import com.rixin.cold.domain.ColdInfo;
import com.rixin.cold.utils.SPUtils;
import com.rixin.cold.utils.UIUtils;

import java.util.ArrayList;

/**
 * 标签页的Adapter
 * Created by 飘渺云轩 on 2017/2/10.
 */

public class TabsRecyclerViewAdapter extends RecyclerView.Adapter<TabsRecyclerViewAdapter.TabsViewHolder> {

    private ArrayList<ColdInfo> data;
    private int resource;

    public TabsRecyclerViewAdapter(ArrayList<ColdInfo> data, int resource) {
        this.data = data;
        this.resource = resource;
    }

    public void setDataChangeListener(ArrayList<ColdInfo> data){
        this.data = data;
        this.notifyDataSetChanged();
    }

    @Override
    public TabsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (parent.getTag() == null) {
            View view = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
            return new TabsViewHolder(view);
        } else {
            return new TabsViewHolder((View) parent.getTag());
        }
    }

    @Override
    public void onBindViewHolder(TabsViewHolder holder, int position) {
        Glide.with(UIUtils.getContext()).load(data.get(position).picUrl)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.ic_thumb_bg)  // 默认图片
                .into(holder.pic);
        holder.title.setText(data.get(position).title + "?");
        String readIds = SPUtils.getString(UIUtils.getContext(), "readIds", "");
        if (readIds.contains(data.get(position).id + "")) {
            holder.title.setTextColor(Color.rgb(189, 189, 189));
        }else{
            holder.title.setTextColor(Color.BLACK);
        }
        holder.readCount.setText("阅读(" + data.get(position).readCount + ")");
        holder.starCount.setText("赞(" + data.get(position).starCount + ")");
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class TabsViewHolder extends RecyclerView.ViewHolder {
        private ImageView pic;
        private TextView title;
        private TextView readCount;
        private TextView starCount;

        public TabsViewHolder(View itemView) {
            super(itemView);
            pic = (ImageView) itemView.findViewById(R.id.iv_tabs_list_pic);
            title = (TextView) itemView.findViewById(R.id.tv_tabs_list_title);
            readCount = (TextView) itemView.findViewById(R.id.tv_tabs_list_reader);
            starCount = (TextView) itemView.findViewById(R.id.tv_tabs_list_star);
        }
    }
}
