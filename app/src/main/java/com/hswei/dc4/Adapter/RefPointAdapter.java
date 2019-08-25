package com.hswei.dc4.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hswei.dc4.R;

import java.util.List;

public class RefPointAdapter extends RecyclerView.Adapter<RefPointAdapter.VH> {

    private List<String> mData;
    public RefPointAdapter(List<String> data) {
        mData = data;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rec_item_layout,
                viewGroup,false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH vh, int i) {
        vh.title.setText(mData.get(i));
    }



    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class VH extends RecyclerView.ViewHolder{
        public TextView title;
        public VH(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.item_tv);
        }
    }

    public void updateData(List<String> data){
        mData = data;
        notifyItemRangeChanged(1,mData.size());
    }

    public void addData(String data){
        mData.add(data);
        notifyItemInserted(mData.size());
        notifyItemRangeChanged(mData.size()-1,mData.size());
    }
}
