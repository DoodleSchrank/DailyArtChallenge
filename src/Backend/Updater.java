package Backend;

import java.io.*;
import java.lang.*;
import java.net.URL;
import java.util.Scanner;

public class Updater {
    private static String _path = System.getProperty("user.home") + "/AppData/Local";
    private static String _link = "https://www.doodleschrank.de/web/";


    public static void checkFirstRun() throws IOException
    {
        File folder = new File(_path + "/DailyArtChallenge");
        File version = new File(_path + "/DailyArtChallenge/version.json");
        if(!folder.exists()) {
            folder.mkdirs();
            File words =  new File(folder + "/words/");
            System.out.println("Updating ...");
            words.mkdirs();
        }
        if(!version.exists()) {
            update();
        }
    }
    public static void checkForUpdate() throws IOException
    {
        File file = new File(_path + "/DailyArtChallenge/version.json");

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String currentVersion = reader.readLine();
        String newestVersion = new Scanner( new URL(_link + "/version.json").openStream() ).useDelimiter("\\A").next();
        if(!currentVersion.equals(newestVersion))
            update();
    }
    static void forceUpdate() throws IOException
    {
        update();
    }
    private static void update() throws IOException
    {
        download("version.json");
        download("words/0.json");
        download("words/1.json");
        download("words/2.json");
        download("words/3.json");
        download("words/4.json");
    }

    private static void download(String file) throws IOException
    {
        URL url = new URL(_link + file);
        System.out.println("Starting Download on file: " + file);
        BufferedInputStream inputStream = new BufferedInputStream(url.openStream());
        System.out.println(_path + "/DailyArtChallenge/" + file);
        FileOutputStream fileOutputStream = new FileOutputStream(_path + "/DailyArtChallenge/" + file);
        byte[] buffer = new byte[1024];
        int count;
        while((count = inputStream.read(buffer,0,1024)) != -1)
        {
            fileOutputStream.write(buffer, 0, count);
        }
        fileOutputStream.close();
        inputStream.close();
    }
}
