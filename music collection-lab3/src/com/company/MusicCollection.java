package com.company;
import java.util.ArrayList;

/**
 * A class to hold details of audio files.
 *
 * @author Sarvin Nami
 */
public class MusicCollection
{
    // An ArrayList for storing the file names of music files.
    private ArrayList<String> files;
    // A player for the music files.
    private MusicPlayer player;
    // An ArrayList for storing the  names of music singers.
    private ArrayList<String> singers;
    // An ArrayList for storing the years of music files.
    private ArrayList<Integer> years;

    /**
     * Create a MusicCollection
     */
    public MusicCollection()
    {
        files = new ArrayList<String>();
        singers = new ArrayList<String>();
        years = new ArrayList<Integer>();
        player = new MusicPlayer();
    }

    /**
     * Add a file to the collection.
     * @param filename The file to be added.
     */
    public void addFile(String filename, String singer, int year)
    {
        files.add(filename);
        singers.add(singer);
        years.add(year);
        System.out.println("Music with name " + filename + " added.");
    }

    /**
     * Return the number of files in the collection.
     * @return The number of files in the collection.
     */
    public int getNumberOfFiles()
    {
        return files.size();
    }

    /**
     * List a file from the collection.
     * @param index The index of the file to be listed.
     */
    public void listFile(int index)
    {
        if (validIndex(index) && index >= 0)
        {
            System.out.println("the music with number " + index + " is " + " File : " + files.get(index) + " | Singer : " + singers.get(index) + " | Year : " + years.get(index));
        }
        else
            System.out.println("This  music is not in the list !!");
    }

    /**
     * Show a list of all the files in the collection.
     */
    public void listAllFiles()
    {
        for (int i = 0; i < files.size(); i++)
        {
            System.out.println(i + 1 + ") File : " + files.get(i) + " | Singer : " + singers.get(i) + " | Year : " + years.get(i));
        }
    }

    /**
     * Remove a file from the collection.
     * @param index The index of the file to be removed.
     */
    public void removeFile(int index)
    {
        System.out.println();
        if (validIndex(index))
        {
            files.remove(index);
            singers.remove(index);
            years.remove(index);
            System.out.println("Music number " + (index + 1) + " removed.");
        }
        else
            System.out.println("This  music is not in the list !!");
    }

    /**
     * Start playing a file in the collection.
     * Use stopPlaying() to stop it playing.
     * @param index The index of the file to be played.
     */
    public void startPlaying(int index)
    {
        if (validIndex(index))
        {
            player.startPlaying(files.get(index));
        }
        else
            System.out.println("This  music is not in the list !!");
    }

    /**
     * Stop the player.
     */
    public void stopPlaying()
    {
        player.stop();
    }

    /**
     * Search the Music.
     * @param name
     */
    public void searchMusic(String name)
    {
        for (int i = 0; i < files.size(); i++)
        {
            if (files.contains(name))
            {
                System.out.println("Found.");
                System.out.println("File : " + files.get(i) + " | Singer : " + singers.get(i) + " | Year : " + years.get(i));
            }
            else
                System.out.println("Not Found!!!");

        }
    }


    /**
     * Determine whether the given index is valid for the collection.
     * Print an error message if it is not.
     * @param index The index to be checked.
     * @return true if the index is valid, false otherwise.
     */
    private boolean validIndex(int index)
    {
        // The return value.
        // Set according to whether the index is valid or not.
        if (index >= 0 && index < files.size()){
            return true;
        }
        else {
            return false;
        }
    }
}