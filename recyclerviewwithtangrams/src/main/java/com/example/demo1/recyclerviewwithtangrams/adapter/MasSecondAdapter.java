package com.example.demo1.recyclerviewwithtangrams.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.demo1.recyclerviewwithtangrams.R;
import com.example.demo1.recyclerviewwithtangrams.entity.MasSecondEntity;

import java.util.List;

public class MasSecondAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private int HEADER_TYPE = 1;
    private int BONTOM_TYPE = 2;
    private Context context;
    private List<MasSecondEntity> list;

    public MasSecondAdapter(Context context, List<MasSecondEntity> list) {
        this.context = context;
        this.list = list;
    }

    public void setList(List<MasSecondEntity> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        //返回显示类型
        if (position == 0 || position == 1 || position == 2){
            return HEADER_TYPE;
        }else {
            return BONTOM_TYPE;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = null;
        if (viewType == HEADER_TYPE){
            view = LayoutInflater.from(context).inflate(R.layout.mas_second_item_layout, viewGroup, false);
            return new MasSecondViewHeaderHolder(view);
        }else if (viewType == BONTOM_TYPE){
            view = LayoutInflater.from(context).inflate(R.layout.mas_second_item_bottom_layout, viewGroup, false);
            return new MasSecondViewBottomHolder(view);
        }else {
            return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MasSecondEntity masSecondEntity = list.get(position);

        if (holder instanceof MasSecondViewHeaderHolder){
            ((MasSecondViewHeaderHolder) holder).bindData(masSecondEntity);
            ((MasSecondViewHeaderHolder) holder).linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //TODO 点击事件处理

                }
            });
        }else if (holder instanceof  MasSecondViewBottomHolder){
            ((MasSecondViewBottomHolder) holder).bindData(masSecondEntity);

        }
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }


    class MasSecondViewHeaderHolder extends RecyclerView.ViewHolder{

        private LinearLayout linearLayout;
        private ImageView imageViewIv;
        private TextView tvTitle, tvContent;

        public MasSecondViewHeaderHolder(@NonNull View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.item_rl);
            imageViewIv = itemView.findViewById(R.id.item_image_iv);
            tvTitle = itemView.findViewById(R.id.item_title_tv);
            tvContent = itemView.findViewById(R.id.item_content_tv);
        }

        public void bindData(MasSecondEntity masSecondEntity){
            imageViewIv.setImageResource(R.drawable.pic_1);
            tvTitle.setText(masSecondEntity.getTitle());
            tvContent.setText(masSecondEntity.getContent());
        }

    }

    class MasSecondViewBottomHolder extends RecyclerView.ViewHolder{
        private RelativeLayout relativeLayout;
        private ImageView imageViewIv;
        private TextView tvTitle, tvContent;

        public MasSecondViewBottomHolder(@NonNull View itemView) {
            super(itemView);
            relativeLayout = itemView.findViewById(R.id.item_bottom_rl);
            imageViewIv = itemView.findViewById(R.id.item_bottom_image_iv);
            tvTitle = itemView.findViewById(R.id.item_bottom_title_tv);
            tvContent = itemView.findViewById(R.id.item_bottom_content_tv);
        }

        public void bindData(MasSecondEntity masSecondEntity){
            imageViewIv.setImageResource(R.drawable.pic_1);
            tvTitle.setText(masSecondEntity.getTitle());
            tvContent.setText(masSecondEntity.getContent());
        }
    }
}
