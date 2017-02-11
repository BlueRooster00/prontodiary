package com.okason.prontodiary.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by vokafor on 1/18/2017.
 */

public class SampleData {

    public static List<String> getSampleTags() {
        List<String> tagNames = new ArrayList<>();

        tagNames.add("Family");
        tagNames.add("Word");
        tagNames.add("Productivity");
        tagNames.add("Personal");
        tagNames.add("Finance");
        tagNames.add("Fitness");
        tagNames.add("Blog Posts");
        tagNames.add("Social Media");


        return tagNames;

    }


        public static List<JournalEntry> getSampleNotes() {

        List<JournalEntry> journalEnrties = new ArrayList<>();
        //create the dummy journal
        JournalEntry journalEntry1 = new JournalEntry();
        journalEntry1.setTitle("DisneyLand Trip");
        journalEntry1.setContent("We went to Disneyland today and the kids had lots of fun!");
        Calendar calendar1 = GregorianCalendar.getInstance();
        journalEntry1.setDateModified(calendar1.getTimeInMillis());
        journalEnrties.add(journalEntry1);


        //create the dummy note
        JournalEntry journalEntry2 = new JournalEntry();
        journalEntry2.setTitle("Gym Work Out");
        journalEntry2.setContent("I went to the Gym today and I got a lot of exercises");

        //change the date to random time
        Calendar calendar2 = GregorianCalendar.getInstance();
        calendar2.add(Calendar.DAY_OF_WEEK, -1);
        calendar2.add(Calendar.MILLISECOND, 10005623);
        journalEntry2.setDateModified(calendar2.getTimeInMillis());
        journalEnrties.add(journalEntry2);


        //create the dummy note
        JournalEntry journalEntry3 = new JournalEntry();
        journalEntry3.setTitle("Blog Post Idea");
        journalEntry3.setContent("I will like to write a blog post about how to make money online");


        //change the date to random time
        Calendar calendar3 = GregorianCalendar.getInstance();
        calendar3.add(Calendar.DAY_OF_WEEK, -2);
        calendar3.add(Calendar.MILLISECOND, 8962422);
             journalEntry3.setDateModified(calendar3.getTimeInMillis());
        journalEnrties.add(journalEntry3);


        //create the dummy note
        JournalEntry journalEntry4 = new JournalEntry();
        journalEntry4.setTitle("Cupcake Recipe");
        journalEntry4.setContent("Today I found a recipe to make cup cake from www.google.");

        //pad the date with random number of days and minute
        //so all the journalEnrties do not have the same time stamp
        Calendar calendar4 = GregorianCalendar.getInstance();
        calendar4.add(Calendar.DAY_OF_WEEK, -4);
        calendar4.add(Calendar.MILLISECOND, 49762311);
        journalEntry4.setDateModified(calendar4.getTimeInMillis());
        journalEnrties.add(journalEntry4);


        //create the dummy note
        JournalEntry journalEntry5 = new JournalEntry();
        journalEntry5.setTitle("Notes from Networking Event");
        journalEntry5.setContent("Today I attended a developer's networking event and it was great");

        //pad the date with two days
        //pad the date with random number of days and minute
        //so all the journalEnrties do not have the same time stamp
        Calendar calendar5 = GregorianCalendar.getInstance();
        calendar4.add(Calendar.MONTH, -2);
        calendar5.add(Calendar.MILLISECOND, 2351689);
        journalEntry5.setDateModified(calendar5.getTimeInMillis());
        journalEnrties.add(journalEntry5);

        return journalEnrties;
    }
}
