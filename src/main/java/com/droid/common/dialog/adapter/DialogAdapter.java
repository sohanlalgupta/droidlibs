package com.droid.common.dialog.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.droid.common.dialog.R;
import com.droid.common.dialog.model.Message;
import com.squareup.picasso.Picasso;
import java.util.List;

/**
 * Created by sohan.gupta on 5/7/18.
 */

public class DialogAdapter extends RecyclerView.Adapter<DialogAdapter.DialogViewHolder> {
    private Context mContext;
    private List<Message> mList;
    private int messageColor;

    public DialogAdapter(Context context, List<Message> list,int color){
        mContext=context;
        mList=list;
        messageColor=color;
    }

    @Override
    public DialogViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.bullet_point,null);
        DialogViewHolder viewHolder= new DialogViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(DialogViewHolder holder, int position) {
        holder.getTextView().setTextColor(messageColor);
        holder.getTextView().setText(mList.get(position).getText());
        Picasso.with(mContext).load(mList.get(position).getImageUrl()).into(holder.getImageView());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

   class DialogViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        private ImageView imageView;

        public DialogViewHolder(View itemView) {
            super(itemView);
            textView=(TextView)itemView.findViewById(R.id.txt_bullet);
            imageView=(ImageView)itemView.findViewById(R.id.ic_bullet);
        }

        public ImageView getImageView(){
            return imageView;
        }

        public TextView getTextView(){
            return textView;
        }
    }
}
