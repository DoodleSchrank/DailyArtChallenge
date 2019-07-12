package Backend;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ThemeGenerator {

    public static String[] generate(int number, int themeID) throws IOException
    {
        String[] subjects = new String[number];
        List<String> allSubjects = new ArrayList<>();
        int randNumb;
        JSONParser parser;
        JSONObject readJSON;
        JSONArray jArray = null;
        try
        {
            BufferedReader fileReader = new BufferedReader(new FileReader(System.getProperty("user.home") + "/AppData/Local/DailyArtChallenge/words/" + themeID + ".json"));
            parser = new JSONParser();
            readJSON = (JSONObject) parser.parse(fileReader);
            jArray = (JSONArray) readJSON.get("wörter");
        }
        catch (ParseException e)
        {
            Updater.forceUpdate();
        }
        allSubjects.addAll(jArray);

        for(int i = 0; i < number; i++)
        {
            randNumb = (int)(Math.random() * allSubjects.size());
            subjects[i] = allSubjects.get(randNumb);

        }
        return subjects;
    }
}
