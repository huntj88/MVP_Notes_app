package com.example.james.mvp.utils;

/**
 * Created by James on 3/13/2017.
 */

public class Note {

    private final Long unixTimeMade;
    private final String noteText;
    private final String title;

    public Note(String noteText)
    {
        this.noteText = noteText;
        this.title = "Untitled";
        this.unixTimeMade = TimeTools.getUnixTime();
    }

    public Note(String noteText, String title)
    {
        this.noteText = noteText;
        this.title = title;
        this.unixTimeMade = TimeTools.getUnixTime();
    }

    public Note(String noteText, String title, Long unixTimeMade)
    {
        this.noteText = noteText;
        this.title = title;
        this.unixTimeMade = unixTimeMade;
    }

    public Note editNote(String noteText)
    {
        return new Note(noteText,title,unixTimeMade);
    }

    public String getNoteText()
    {
        return noteText;
    }

    public String getTitle()
    {
        return title;
    }

    public Long getUnixTimeMade()
    {
        return unixTimeMade;
    }

}
