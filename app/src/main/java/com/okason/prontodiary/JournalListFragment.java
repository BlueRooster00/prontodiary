package com.okason.prontodiary;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.okason.prontodiary.model.JournalEntry;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class JournalListFragment extends Fragment {

    private DatabaseReference mDatabase;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private DatabaseReference journalCloudEndPoint;
    private DatabaseReference tagCloudEndPoint;


    @BindView(R.id.journal_recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.empty_text)
    TextView mEmptyText;

    private FirebaseRecyclerAdapter<JournalEntry, JournalViewHolder> mJournalFirebaseAdapter;
    private View mRootView;


    public JournalListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();mDatabase = FirebaseDatabase.getInstance().getReference();


        journalCloudEndPoint =  mDatabase.child("/users/" + mFirebaseUser.getUid() + "journalentries");
        tagCloudEndPoint =  mDatabase.child("/users/" + mFirebaseUser.getUid() + "tags");



        // Inflate the layout for this fragment
        mRootView = inflater.inflate(R.layout.fragment_journal_list, container, false);
        ButterKnife.bind(this, mRootView);


        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mJournalFirebaseAdapter = new FirebaseRecyclerAdapter<JournalEntry, JournalViewHolder>(
                JournalEntry.class,
                R.layout.journal_custom_row,
                JournalViewHolder.class,
                journalCloudEndPoint) {

            @Override
            protected JournalEntry parseSnapshot(DataSnapshot snapshot) {
                JournalEntry note = super.parseSnapshot(snapshot);
                if (note != null){
                    note.setJournalId(snapshot.getKey());
                }
                return note;
            }

            @Override
            protected void populateViewHolder(JournalViewHolder holder, final JournalEntry journalEntry, int position) {
                holder.title.setText(journalEntry.getTitle());

                holder.journalDate.setText(getDueDate(journalEntry.getDateModified()));
                holder.delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!TextUtils.isEmpty(journalEntry.getJournalId())) {
                            journalCloudEndPoint.child(journalEntry.getJournalId()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    if (mJournalFirebaseAdapter.getItemCount() < 1){
                                        showEmptyText();
                                    }
                                }
                            });
                        }
                    }
                });
                String firstLetter = journalEntry.getTitle().substring(0, 1);
                ColorGenerator generator = ColorGenerator.MATERIAL;
                int color = generator.getRandomColor();
                TextDrawable drawable = TextDrawable.builder()
                        .buildRound(firstLetter, color);
                holder.journalIcon.setImageDrawable(drawable);
            }
        };

        mRecyclerView.setAdapter(mJournalFirebaseAdapter);
        return mRootView;
    }






    private static String getDueDate(long date){

        String displayDate = new SimpleDateFormat("MMM dd, yyyy").format(new Date(date));
        return displayDate;
    }
    public void showEmptyText() {
        mRecyclerView.setVisibility(View.GONE);
        mEmptyText.setVisibility(View.VISIBLE);
    }

    public void hideEmptyText() {
        mRecyclerView.setVisibility(View.VISIBLE);
        mEmptyText.setVisibility(View.GONE);
    }

}
