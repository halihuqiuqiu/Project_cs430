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

    public static Map<String, List<Point>> read(){

        Map<String, List<Point>> fileMap = new HashMap<String, List<Point>>(){};

        File[] files = new File("./input").listFiles();

        for (File file : files){

            if(!file.getName().startsWith("instance")){
                continue; // only read instance
            }

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
            String fileNumber = file.getName().substring(8,10);
            fileMap.put(fileNumber,inputPoints);

        }

        return fileMap;
    }

    public static void write (String fileName, String fileNumber, List<String> lines){
        Path path = Paths.get("./output_local/" +fileName+fileNumber);
        try {
            Files.createFile(path);
            Files.write(path, lines, Charset.forName("UTF-8"));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
