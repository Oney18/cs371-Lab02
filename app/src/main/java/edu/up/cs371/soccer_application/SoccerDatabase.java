package edu.up.cs371.soccer_application;

import android.util.Log;

import edu.up.cs371.soccer_application.soccerPlayer.SoccerPlayer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.concurrent.Exchanger;

/**
 * Soccer player database -- presently, all dummied up
 * 
 * @author *** put your name here ***
 * @version *** put date of completion here ***
 *
 */
public class SoccerDatabase implements SoccerDB {
Hashtable<String, SoccerPlayer> database = new Hashtable<String, SoccerPlayer>();
    /**
     * add a player
     *
     * @see SoccerDB#addPlayer(String, String, int, String)
     */
    @Override
	public boolean addPlayer(String firstName, String lastName,
			int uniformNumber, String teamName) {
        String key = firstName + "  ##  "+ lastName;
        if(database.containsKey(key)) {
            return false;
        }
        else
        {
            database.put(key, new SoccerPlayer(firstName ,lastName ,uniformNumber, teamName));
            return true;
        }

	}

    /**
     * remove a player
     *
     * @see SoccerDB#removePlayer(String, String)
     */
    @Override
    public boolean removePlayer(String firstName, String lastName) {
        String key = firstName + "  ##  " + lastName;
        if(database.containsKey(key))
        {
            database.remove(key);
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * look up a player
     *
     * @see SoccerDB#getPlayer(String, String)
     */
    @Override
	public SoccerPlayer getPlayer(String firstName, String lastName) {
        String key = firstName + "  ##  "+ lastName;
        if(database.containsKey(key))
        {
            return database.get(key);
        }
        else
        {
            return null;
        }
    }

    /**
     * increment a player's goals
     *
     * @see SoccerDB#bumpGoals(String, String)
     */
    @Override
    public boolean bumpGoals(String firstName, String lastName) {
        String key = firstName + "  ##  "+ lastName;

        if(database.containsKey(key))
        {
            database.get(key).bumpGoals();
            return true;
        }
        else{
            return false;
        }
}

    /**
     * increment a player's assists
     *
     * @see SoccerDB#bumpAssists(String, String)
     */
    @Override
    public boolean bumpAssists(String firstName, String lastName) {
        String key = firstName + "  ##  "+ lastName;

        if(database.containsKey(key))
        {
            database.get(key).bumpAssists();
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * increment a player's shots
     *
     * @see SoccerDB#bumpShots(String, String)
     */
    @Override
    public boolean bumpShots(String firstName, String lastName) {
        String key = firstName + "  ##  "+ lastName;

        if(database.containsKey(key))
        {
            database.get(key).bumpShots();
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * increment a player's saves
     *
     * @see SoccerDB#bumpSaves(String, String)
     */
    @Override
    public boolean bumpSaves(String firstName, String lastName) {
        String key = firstName + "  ##  "+ lastName;

        if(database.containsKey(key))
        {
            database.get(key).bumpSaves();
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * increment a player's fouls
     *
     * @see SoccerDB#bumpFouls(String, String)
     */
    @Override
    public boolean bumpFouls(String firstName, String lastName) {
        String key = firstName + "  ##  "+ lastName;

        if(database.containsKey(key))
        {
            database.get(key).bumpFouls();
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * increment a player's yellow cards
     *
     * @see SoccerDB#bumpYellowCards(String, String)
     */
    @Override
    public boolean bumpYellowCards(String firstName, String lastName) {
        String key = firstName + "  ##  "+ lastName;

        if(database.containsKey(key))
        {
            database.get(key).bumpYellowCards();
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * increment a player's red cards
     *
     * @see SoccerDB#bumpRedCards(String, String)
     */
    @Override
    public boolean bumpRedCards(String firstName, String lastName) {
        String key = firstName + "  ##  "+ lastName;

        if(database.containsKey(key))
        {
            database.get(key).bumpRedCards();
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * tells the number of players on a given team
     *
     * @see SoccerDB#numPlayers(String)
     */
    @Override
    // report number of players on a given team (or all players, if null)
	public int numPlayers(String teamName) {
        Collection<SoccerPlayer> playersCol = database.values();
        SoccerPlayer[] playersArr = new SoccerPlayer[database.size()];
        playersCol.toArray(playersArr);

        int counter = 0;
        if(teamName == null)
        {
            return database.size();
        }
        else
        {
            for(int i = 0; i < playersArr.length; i++)
            {
                if(playersArr[i].getTeamName().equals(teamName))
                {
                    counter++;
                }
            }
            return counter;
        }
	}

    /**
     * gives the nth player on a the given team
     *
     * @see SoccerDB#playerNum(int, String)
     */
	// get the nTH player
	@Override
    public SoccerPlayer playerNum(int idx, String teamName) {
        Collection<SoccerPlayer> playersCol = database.values();
        SoccerPlayer[] playersArr = new SoccerPlayer[database.size()];
        playersCol.toArray(playersArr);

        if(teamName == null)
        {
            try {
                return playersArr[idx];
            }
            catch(Exception e){
                return null;
            }
        }
        else
        {
            int counter = 0;
            for(int i = 0; i < playersArr.length; i++)
            {

                if(idx == counter)
                {
                    return playersArr[i];
                }
                if(playersArr[i].getTeamName().equals(teamName))
                {
                    counter++;
                }
            }
        }
        return null;
    }

    /**
     * reads database data from a file
     *
     * @see SoccerDB#readData(java.io.File)
     */
	// read data from file
    @Override
	public boolean readData(File file) {
            try {
                Scanner in = new Scanner(file);

                while(in.hasNextLine()) {
                    String firstName = in.nextLine();
                    String lastName = in.nextLine();
                    String teamName = in.nextLine();
                    int uniform = Integer.valueOf(in.nextLine());
                    int goals = Integer.valueOf(in.nextLine());
                    int assists = Integer.valueOf(in.nextLine());
                    int shots = Integer.valueOf(in.nextLine());
                    int saves = Integer.valueOf(in.nextLine());
                    int fouls = Integer.valueOf(in.nextLine());
                    int yelCards = Integer.valueOf(in.nextLine());
                    int redCards = Integer.valueOf(in.nextLine());

                    String key = firstName + "  ##  " + lastName;
                    SoccerPlayer temp = new SoccerPlayer(firstName,lastName,uniform,teamName);
                    for(int i = 0; i < goals; i++)
                    {
                        temp.bumpGoals();
                    }

                    for(int i = 0; i < assists; i++)
                    {
                        temp.bumpAssists();
                    }

                    for(int i = 0; i < shots; i++)
                    {
                        temp.bumpShots();
                    }

                    for(int i = 0; i < saves; i++)
                    {
                        temp.bumpSaves();
                    }

                    for(int i = 0; i < fouls; i++)
                    {
                        temp.bumpFouls();
                    }

                    for(int i = 0; i < yelCards; i++)
                    {
                        temp.bumpYellowCards();
                    }

                    for(int i = 0; i < redCards; i++)
                    {
                        temp.bumpRedCards();
                    }


                    if(database.containsKey(key)) {
                        database.remove(key);
                    }
                    database.put(key, temp);
                }

                return true;
            }
            catch(Exception e)
            {
                return false;
            }

	}

    /**
     * write database data to a file
     *
     * @see SoccerDB#writeData(java.io.File)
     */
	// write data to file
    @Override
	public boolean writeData(File file) {
        Collection<SoccerPlayer> playersCol = database.values();
        SoccerPlayer[] playersArr = new SoccerPlayer[database.size()];
        playersCol.toArray(playersArr);


        try{
            PrintWriter pw = new PrintWriter(file);
            for(int i = 0; i < playersArr.length; i++)
            {
                pw.println(logString(playersArr[i].getFirstName()));
                pw.println(logString(playersArr[i].getLastName()));
                pw.println(logString(playersArr[i].getTeamName()));
                pw.println(logString(""+playersArr[i].getUniform()));
                pw.println(logString(""+playersArr[i].getGoals()));
                pw.println(logString(""+playersArr[i].getAssists()));
                pw.println(logString(""+playersArr[i].getShots()));
                pw.println(logString(""+playersArr[i].getSaves()));
                pw.println(logString(""+playersArr[i].getFouls()));
                pw.println(logString("" + playersArr[i].getYellowCards()));
                pw.println(logString("" + playersArr[i].getRedCards()));
            }
            pw.close();
            return true;
        }
        catch(Exception e){
            return false;
        }


	}

    /**
     * helper method that logcat-logs a string, and then returns the string.
     * @param s the string to log
     * @return the string s, unchanged
     */
    private String logString(String s) {
        Log.i("write string", s);
        return s;
    }

    /**
     * returns the list of team names in the database
     *
     * @see edu.up.cs371.soccer_application.SoccerDB#getTeams()
     */
	// return list of teams
    @Override
	public HashSet<String> getTeams()
    {
        Collection<SoccerPlayer> playersCol = database.values();
        SoccerPlayer[] playersArr = new SoccerPlayer[database.size()];
        playersCol.toArray(playersArr);
        HashSet<String> teamList = new HashSet<String>();
        for(int i = 0; i < playersArr.length; i++)
        {
            String tempName = playersArr[i].getTeamName();
            if(!teamList.contains(tempName)) {
                teamList.add(tempName);
            }
        }
        return teamList;

	}

}
