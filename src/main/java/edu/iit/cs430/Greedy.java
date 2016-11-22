package edu.iit.cs430;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by YongYang on 11/22/16.
 */
public class Greedy {
    public List<String> execute(List<Point> pointList){
        List<String> lines = new ArrayList<>();

        lines.add(String.valueOf(pointList.size()));     // add total number in the first line
        lines.add("v 12");
        lines.add("h 51");
        return lines;
    }
}
