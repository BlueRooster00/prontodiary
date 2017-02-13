package com.okason.prontodiary;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.okason.prontodiary.model.JournalEntry;
import com.okason.prontodiary.model.SampleData;
import com.okason.prontodiary.model.Tag;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.category;

public class DiaryListActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private DatabaseReference journalCloudEndPoint;
    private DatabaseReference tagCloudEndPoint;
    private static final String LOG_TAG = "DiaryListActivityy";

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private List<JournalEntry> mJournalEntries;
    private List<Tag> mTags;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDatabase =  FirebaseDatabase.getInstance().getReference();
        journalCloudEndPoint = mDatabase.child("journalentris");
        tagCloudEndPoint = mDatabase.child("tags");


        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();
        if (sharedPreferences.getBoolean(Constants.FIRST_RUN, true)) {
            addInitialDataToFirebase();;
            editor.putBoolean(Constants.FIRST_RUN, false).commit();
        }

        openFragment(new JournalListFragment(), "Journal Entries");
        mJournalEntries = new ArrayList<>();
        mTags = new ArrayList<>();

//        journalCloudEndPoint.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot noteSnapshot: dataSnapshot.getChildren()){
//                    JournalEntry note = noteSnapshot.getValue(JournalEntry.class);
//                    mJournalEntries.add(note);
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                Log.d(LOG_TAG, databaseError.getMessage());
//            }
//        });
//
//        tagCloudEndPoint.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot categorySnapshot: dataSnapshot.getChildren()){
//                    Tag tag = categorySnapshot.getValue(Tag.class);
//                    mTags.add(tag);
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                Log.d(LOG_TAG, databaseError.getMessage());
//            }
//        });

    }

    public void openFragment(Fragment fragment, String screenTitle){
        getSupportFragmentManager()
                .beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.container, fragment)
                .addToBackStack(screenTitle)
                .commit();
        getSupportActionBar().setTitle(screenTitle);
    }

    private void addInitialDataToFirebase() {

        List<JournalEntry> sampleJournalEntries = SampleData.getSampleJournalEntries();
        for (JournalEntry journalEntry: sampleJournalEntries){
            String key = journalCloudEndPoint.push().getKey();
            journalEntry.setJournalId(key);
            journalCloudEndPoint.child(key).setValue(journalEntry);
        }

        List<String> tagNames = SampleData.getSampleTags();
        for (String name: tagNames){
            String tagKey = tagCloudEndPoint.push().getKey();
            Tag tag = new Tag();
            tag.setTagName(name);
            tag.setTagId(tagKey);
            tagCloudEndPoint.child(tag.getTagId()).setValue(category);
        }

    }



}
