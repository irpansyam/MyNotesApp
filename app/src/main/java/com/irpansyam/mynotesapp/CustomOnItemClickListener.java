package com.irpansyam.mynotesapp;

import android.view.View;
import android.view.View.OnClickListener;

/**
 * Created by SONY on 12/4/2017.
 */

public class CustomOnItemClickListener implements OnClickListener {
    private int position;
    private OnItemClickCallback onItemClickCallback;
    public CustomOnItemClickListener(int position, OnItemClickCallback onItemClickCallback) {
        this.position = position;
        this.onItemClickCallback = onItemClickCallback;
    }
    @Override
    public void onClick(View view) {
        onItemClickCallback.onItemClicked(view, position);
    }
    public interface OnItemClickCallback {
        void onItemClicked(View view, int position);
    }
}
