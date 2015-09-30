package com.prjvt.intellifitn.interfaces;

import android.view.View;

/**
 * Created by Vitor on 20/09/2015.
 */
public class RecyclerViewClick {
    public interface RecyclerViewClickListener {
        public void recyclerViewListClicked(View v, int position);
    }

    public interface RecyclerViewClickDetailListener {
        public void recyclerViewListDetailClicked(View v, int positionMaster, int positionDetail);
    }
}