package com.example.james.mvp.utils;

/**
 * Created by James on 3/13/2017.
 */

public class Note {

    private final Long unixTimeMade;
    private final String noteText;
    private final String title;
    private final long id;

    public Note(long id, String noteText)
    {
        this.noteText = noteText;
        this.title = "Untitled";
        this.unixTimeMade = TimeTools.getUnixTime();
        this.id = id;
    }

    public Note(long id, String noteText, String title)
    {
        this.noteText = noteText;
        this.title = title;
        this.unixTimeMade = TimeTools.getUnixTime();
        this.id = id;
    }

    public Note(long id, String noteText, String title, Long unixTimeMade)
    {
        this.noteText = noteText;
        this.title = title;
        this.unixTimeMade = unixTimeMade;
        this.id = id;
    }

    public Note editNote(String noteText, String title)
    {
        return new Note(id,noteText,title,unixTimeMade);
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

    public long getId()
    {
        return id;
    }

}
