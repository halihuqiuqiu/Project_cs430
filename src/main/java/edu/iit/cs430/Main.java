package edu.iit.cs430;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.jar.Attributes;

/**
 * Hello world!
 *
 */
public class Main
{
    public static void main( String[] args )
    {

        Map<Integer, List<Point>> fileMap = FileIO.read();

        System.out.println(fileMap.get(0).get(0).getX()+ " " + fileMap.get(0).get(0).getY());
        System.out.println(fileMap.size());
        System.out.println(fileMap.get(0).size());

        Greedy greedy = new Greedy();
        int fileNum = fileMap.size();

        for (int i=1; i<=fileNum;i++){
            List<String> lines = greedy.execute(fileMap.get(i-1));  //result each line
            if(i<10){
                FileIO.write("local_solution0",i,lines);
            }else {
                FileIO.write("local_solution",i,lines);
            }
        }

        System.out.println( "Output is in folder" );



    }
}
