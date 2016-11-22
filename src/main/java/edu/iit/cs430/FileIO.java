package edu.iit.cs430;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * Created by YongYang on 11/22/16.
 */
public class FileIO {

    public static Map<Integer, List<Point>> read(){

        Map<Integer, List<Point>> fileMap = new HashMap<Integer, List<Point>>(){};

        File[] files = new File("./input").listFiles();
        Integer fileNumber = 0;
        for (File file : files){
            List<Point> inputPoints = new ArrayList<Point>();
            try {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNext()){
                    String line =scanner.nextLine();
                    String numbers[] = line.split(" ");
                    if(numbers.length!=2){
                        continue;
                    }
                    Point point = new Point(Integer.parseInt(numbers[0]),Integer.parseInt(numbers[1]));
                    inputPoints.add(point);
                }
                scanner.close();

            }catch (FileNotFoundException e){
                System.out.println("file not find");
            }
            fileMap.put(fileNumber,inputPoints);
            fileNumber++;
        }

        return fileMap;
    }

    public static void write (String fileName, int fileNumber, List<String> lines){
        Path path = Paths.get("./output_greedy/" +fileName+fileNumber+".txt");
        try {
            Files.createFile(path);
            Files.write(path, lines, Charset.forName("UTF-8"));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
