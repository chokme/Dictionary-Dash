package com.local.mez.dictionarydash.utils;

import java.io.*;
import java.util.*;

public class CsvFileReader {

    public static Set<String> parse(final File csvFile) {

        BufferedReader br = null;
        String line;
        String delimiter = ",";
        List<String> csvAsArray = new ArrayList();

        try {
            System.out.println("Loading words!!");
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                System.out.println("Reading line: " + line);
                String[] results = line.split(delimiter);
                List<String> resultLowerCase = new ArrayList();
                for(String s : results ) {
                    resultLowerCase.add(s.toLowerCase());
                }
                csvAsArray.addAll(resultLowerCase);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("Done");
        Set<String> uniqueWords = new HashSet();
        uniqueWords.addAll(csvAsArray);

        return uniqueWords;
    }


}
