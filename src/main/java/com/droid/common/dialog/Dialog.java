package com.droid.common.dialog;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.droid.common.dialog.adapter.DialogAdapter;
import com.droid.common.dialog.decorator.SpaceDecorator;
import com.droid.common.dialog.model.Message;
import com.squareup.picasso.Picasso;
import java.io.File;
import java.util.List;

/**
 * Created by sohan.gupta on 6/7/18.
 */

public class Dialog extends DialogFragment {
    private ImageView mPrimaryImgView;
    private ImageView mSecondaryImgView;
    private TextView mHeaderTxtView;
    private TextView mDialogBtn;
    private RecyclerView mRecyclerView;
    private LinearLayout mTopArcLay;
    private String mHeader;
    private int mTopBackgroudColor=-1;
    private int mImageType=-1;
    private List<Message> messages;
    private Spannable mSpannable;
    private int mHeaderColor=-1;
    private int messageColor=-1;
    private Uri mImageUri;
    private File mImageFile;
    private int mImageResource=-1;
    private String mImagePath;
    private int mButtonColor=-1;
    private String mButtonText;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        View view =inflater.inflate(R.layout.fragment_dialog,container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initViews(view);
        initListeners();
        initColor();
        populateData();
        initAdapters();
    }

    private void initViews(View view){
        mTopArcLay=(LinearLayout)view.findViewById(R.id.ll_lay_arc);
        mPrimaryImgView=(ImageView)view.findViewById(R.id.img_view_primary);
        mSecondaryImgView=(ImageView)view.findViewById(R.id.img_view_secondary);
        mHeaderTxtView=(TextView)view.findViewById(R.id.txt_view_header);
        mDialogBtn=(TextView)view.findViewById(R.id.btn_got_it);
        mRecyclerView=(RecyclerView)view.findViewById(R.id.recycler_view_bullet);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager manager=new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        SpaceDecorator decorator=new SpaceDecorator(20);
        mRecyclerView.addItemDecoration(decorator);
    }

    private void initColor(){
        if(messageColor==-1){
            messageColor=getResources().getColor(R.color.light_black);
        }

        if(mButtonColor==-1){
            mButtonColor=getResources().getColor(R.color.light_green);
        }

        if(mHeaderColor==-1){
            mHeaderColor=getResources().getColor(R.color.light_black);
        }
    }

    private void initAdapters(){
        DialogAdapter adapter=new DialogAdapter(getActivity(),messages,messageColor);
        mRecyclerView.setAdapter(adapter);
    }

    private void initListeners(){
        mDialogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId()==R.id.btn_got_it){
                    if(Dialog.this.isAdded()){
                        Dialog.this.dismiss();
                    }
                }
            }
        });
    }

    private void setHeaderColorProperty(int color){
        mHeaderTxtView.setTextColor(color);
    }

    private void setTopBackground(int color){
        mTopArcLay.setBackgroundColor(color);
    }

    private void setImage(){
        if(mImageResource!=-1){
            Picasso.with(getContext()).load(mImageResource).into(mPrimaryImgView);
        }else if(mImagePath!=null){
            Picasso.with(getContext()).load(mImagePath).into(mPrimaryImgView);
        }else if(mImageUri!=null){
            Picasso.with(getContext()).load(mImageUri).into(mPrimaryImgView);
        }else if(mImageFile!=null){
            Picasso.with(getContext()).load(mImageFile).into(mPrimaryImgView);
        }
    }

    private void populateData(){
        if(mTopBackgroudColor!=-1){
            setTopBackground(mTopBackgroudColor);
        }

        if(mHeaderColor!=-1) {
            setHeaderColorProperty(mHeaderColor);
        }

        if(mButtonColor!=-1){
            mDialogBtn.setTextColor(mButtonColor);
        }

        if(mButtonText!=null){
            mDialogBtn.setText(mButtonText);
        }

       setImage();

        mHeaderTxtView.setText(mHeader);
       /* if(mHeader!=null) {


            Shader textShader = new LinearGradient(0, 0, mHeaderTxtView.getWidth(), 0,
                    new int[]{Color.parseColor("#43264e"), Color.parseColor("#ea2331")},
                    null, Shader.TileMode.MIRROR);
            mHeaderTxtView.getPaint().setShader(textShader);
        }*/
    }

    public class Builder{
        public Builder setHeader(String txt){
            Dialog.this.setHeader(txt);
            return Builder.this;
        }

        public Builder setHeaderColor(int color){
            Dialog.this.setHeaderColor(color);
            return Builder.this;
        }

        public Builder setTopBackgroundColor(int color){
            Dialog.this.setTopBackgroundColor(color);
            return Builder.this;
        }

        public Builder setImageResource(int resId){
            Dialog.this.setImageResource(resId);
            return Builder.this;
        }

        public Builder setImagePath(String path){
            Dialog.this.setImagePath(path);
            return Builder.this;
        }

        public Builder setImageUri(Uri uri){
            Dialog.this.setImageUri(uri);
            return Builder.this;
        }

        public Builder setImageFile(File file){
            Dialog.this.setImageFile(file);
            return Builder.this;
        }

        public Builder setMessages(List<Message> list){
            Dialog.this.setMessages(list);
            return Builder.this;
        }

        public Builder setMessageColor(int color){
            Dialog.this.setMessageColor(color);
            return Builder.this;
        }

        public Builder setButtonColor(int color){
            Dialog.this.setButtonColor(color);
            return Builder.this;
        }

        public Builder setButtonText(String text){
            Dialog.this.setButtonText(text);
            return Builder.this;
        }

        public Dialog build() {
            //mSpannable=Utils.getSpannableString(mHeader,mColoredHeader,mColor);
            return Dialog.this;
        }
    }

    private void setHeader(String txt){
        mHeader=txt;
    }

    private void setTopBackgroundColor(int color){
        mTopBackgroudColor=color;
    }

    private void setMessages(List<Message> messages){
        this.messages=messages;
    }

    private void setHeaderColor(int color) {
        mHeaderColor=color;
    }

    private void setMessageColor(int color){
        messageColor=color;
    }

    private void setImageUri(Uri uri){
        mImageUri=uri;
    }

    private void setImagePath(String path){
        mImagePath=path;
    }

    private void setImageResource(int resource){
        mImageResource=resource;
    }

    private void setImageFile(File file){
        mImageFile=file;
    }

    private void setButtonColor(int color){
        mButtonColor=color;
    }

    private void setButtonText(String text){
        mButtonText=text;
    }
}
