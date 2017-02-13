package com.okason.prontodiary;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Valentine on 2/13/2017.
 */

public class JournalViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.text_view_journal_title)
    TextView title;

    @BindView(R.id.text_view_journal_date)
    TextView journalDate;

    @BindView(R.id.image_view_delete)
    ImageView delete;

    @BindView(R.id.image_view) ImageView journalIcon;

    public JournalViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
