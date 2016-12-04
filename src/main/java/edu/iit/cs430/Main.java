package edu.iit.cs430;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class Main
{
    public static void main( String[] args )
    {

        Map<String, List<Point>> fileMap = FileIO.read();

        System.out.println("Input: total "+fileMap.size() +" files. Processing...");


        Local local = new Local();

        for (String fileNumber: fileMap.keySet()){

            List<String> lines = local.execute(fileMap.get(fileNumber));  //result each line

            FileIO.write("local_solution",fileNumber,lines);

        }

        System.out.println( "Output: in output_local folder" );
        System.out.println( "Finished" );



    }
}
