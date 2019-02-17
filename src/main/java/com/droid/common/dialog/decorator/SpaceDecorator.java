package com.droid.common.dialog.decorator;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 *
 * Created by sohan on 28/9/17.
 */
public class SpaceDecorator extends RecyclerView.ItemDecoration  {
    private final int mSpace;

    public SpaceDecorator(int space) {
        this.mSpace = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view,
                               RecyclerView parent, RecyclerView.State state) {

        outRect.right = mSpace;
        outRect.bottom = mSpace;
        outRect.top=mSpace;
        outRect.left = mSpace;

        int position=parent.getChildAdapterPosition(view);


        if(position==0){
            outRect.top=mSpace;
            outRect.bottom=mSpace/2;
        }else {
            outRect.top=mSpace/2;
            outRect.bottom=mSpace/2;
        }
    }
}

