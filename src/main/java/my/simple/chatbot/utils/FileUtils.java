package my.simple.chatbot.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileUtils {
    public static ArrayList<String> readAnswersFile(File file) {
        ArrayList<String> lines = new ArrayList<String>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while((line = br.readLine())!=null) {
                lines.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;
    }

    public static Map<String, String> getPatterns(List<String> lines) {
        Map<String, String> patterns = new HashMap<>();

        for(String line : lines) {
            String[] ln = line.split("-------");
            patterns.put(ln[0], ln[1]);
        }
        return patterns;
    }
}
