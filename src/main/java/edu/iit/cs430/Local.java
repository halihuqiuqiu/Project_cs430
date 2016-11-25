package edu.iit.cs430;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by YongYang on 11/22/16.
 */
public class Local {

    public List<String> execute(List<Point> pointList) {


        List<String> lines = new ArrayList<>();
        if(pointList.size()==0){            // input has 0 point
            return lines;
        }


        int pointNumber = pointList.size();

        int[] yPosition = new int[pointNumber];

        double[] v = new double[pointNumber - 1];        //all possible position for line
        double[] h = new double[pointNumber - 1];
        for (int i = 0; i < v.length; i++) {             //Start with an arbitrary feasible solution.
            v[i] = pointList.get(i).getX() + 0.5;       //All lines are vertical
            h[i] = 0.1;                                   // 0.1 stand for not exist line
            yPosition[i] = pointList.get(i).getY();
        }

        //make yPositon sorted
        Arrays.sort(yPosition);            // all y position are sorted


        boolean change = true;

        outerloop:
        while (change == true) {        //if no change already, while loop will stop
            change = false;

            for (int i = 0; i < v.length - 1; i++) {             // choose first line in v
                if (v[i] != 0.1) {
                    for (int j = i + 1; j < v.length; j++) {      // choose second line also in v after first line
                        if (v[j] != 0.1) {

                            for (int w = 0; w < v.length; w++) {   //choose the third line in v
                                if (v[w] == 0.1) {
                                    v[i] = 0.1;
                                    v[j] = 0.1;
                                    v[w] = pointList.get(w).getX() + 0.5;
                                    if (cheackCorrect(v, h, pointList) == false) {
                                        v[w] = 0.1;    //  put this status back first(if delete add at same place)
                                        v[i] = pointList.get(i).getX() + 0.5;   //change fail, put lines back
                                        v[j] = pointList.get(j).getX() + 0.5;

                                    } else {
                                        change = true;    //line was reduced, while loop will contibue
                                        continue outerloop;
                                    }

                                }
                            }



                            for (int w = 0; w < h.length; w++) {
                                if (h[w] == 0.1) {
                                    v[i] = 0.1;
                                    v[j] = 0.1;
                                    h[w] = yPosition[w] + 0.5;
                                    if (cheackCorrect(v, h, pointList) == false) {
                                        h[w] = 0.1;
                                        v[i] = pointList.get(i).getX() + 0.5;
                                        v[j] = pointList.get(j).getX() + 0.5;

                                    } else {
                                        change = true;
                                        continue outerloop;
                                    }
                                }

                            }


                        }
                    }


                }


            }

            for (int i = 0; i < v.length; i++) {             // choose first line in v
                if (v[i] != 0.1) {
                    for (int j = 0; j < h.length; j++) {        // choose second line in h
                        if (h[j] != 0.1) {

                            for (int w = 0; w < v.length; w++) {   //choose the third line in v
                                if (v[w] == 0.1) {
                                    v[i] = 0.1;
                                    h[j] = 0.1;
                                    v[w] = pointList.get(w).getX() + 0.5;
                                    if (cheackCorrect(v, h, pointList) == false) {
                                        v[w] = 0.1;
                                        v[i] = pointList.get(i).getX() + 0.5;   //change fail, put lines back
                                        h[j] = yPosition[j] + 0.5;

                                    } else {
                                        change = true;               //line was reduced, while loop will contibue
                                        continue outerloop;
                                    }

                                }
                            }
                            for (int w = 0; w < h.length; w++) {
                                if (h[w] == 0.1) {

                                    v[i] = 0.1;
                                    h[j] = 0.1;
                                    h[w] = yPosition[w] + 0.5;
                                    if (cheackCorrect(v, h, pointList) == false) {
                                        h[w] = 0.1;
                                        v[i] = pointList.get(i).getX() + 0.5;
                                        h[j] = yPosition[j] + 0.5;

                                    } else {
                                        change = true;
                                        continue outerloop;
                                    }
                                }

                            }
                        }
                    }

                }

            }


            for (int i = 0; i < h.length - 1; i++) {
                if (h[i] != 0.1) {
                    for (int j = i + 1; j < h.length; j++) {         //choose two line both in h
                        if (h[j] != 0.1) {

                            for (int w = 0; w < v.length; w++) {   //choose the third line in v
                                if (v[w] == 0.1) {
                                    h[i] = 0.1;
                                    h[j] = 0.1;

                                    v[w] = pointList.get(w).getX() + 0.5;
                                    if (cheackCorrect(v, h, pointList) == false) {
                                        v[w] = 0.1;
                                        h[i] = yPosition[i] + 0.5;   //change fail, put lines back
                                        h[j] = yPosition[j] + 0.5;

                                    } else {
                                        change = true;               //line was reduced, while loop will contibue
                                        continue outerloop;
                                    }

                                }
                            }
                            for (int w = 0; w < h.length; w++) {
                                if (h[w] == 0.1) {
                                    h[i] = 0.1;
                                    h[j] = 0.1;
                                    h[w] = yPosition[w] + 0.5;
                                    if (cheackCorrect(v, h, pointList) == false) {
                                        h[w] = 0.1;
                                        h[i] = yPosition[i] + 0.5;
                                        h[j] = yPosition[j] + 0.5;

                                    } else {
                                        change = true;
                                        continue outerloop;
                                    }
                                }

                            }
                        }
                    }


                }


            }

            //only remove one line, it still work


            for (int i =0;i<v.length;i++){
                if (v[i]!=0.1){
                    v[i]=0.1;
                    if (cheackCorrect(v, h, pointList) == false) {
                        v[i] = pointList.get(i).getX() + 0.5;
                    } else {
                        change = true;
                        continue outerloop;
                    }
                }

            }


            for (int i =0;i<h.length;i++){
                if (h[i]!=0.1){
                    h[i]=0.1;
                    if (cheackCorrect(v, h, pointList) == false) {
                        h[i] = yPosition[i] + 0.5;
                    } else {
                        change = true;
                        continue outerloop;
                    }
                }

            }




        }




        //return result

        for (double i : v) {
            if (i != 0.1) {
                lines.add("v " + String.valueOf(i));
            }
        }

        for (double j : h) {
            if (j != 0.1) {
                lines.add("h " + String.valueOf(j));
            }
        }
        lines.add(0, String.valueOf(lines.size()));     // add total number in the first line


        return lines;


    }


    public boolean cheackCorrect(double[] v, double[] h, List<Point> pointlist) {
        List<Double> vLine = new ArrayList<Double>();   // the array that has v line (sorted)
        List<Double> hLine = new ArrayList<Double>();   // the array that has h line (sorted)

        for (double i : v) {
            if (i != 0.1) {
                vLine.add(i);
            }                          // v line should already sorted
        }
        for (double j : h) {
            if (j != 0.1) {
                hLine.add(j);
            }
        }

        Collections.sort(hLine);       //sort h line


        int[][] metrix = new int[vLine.size() + 1][hLine.size() + 1];     //matrix to seperate point
        for (int i = 0; i < metrix.length; i++) {
            for (int j = 0; j < metrix[i].length; j++) {
                metrix[i][j] = 0;
            }
        }


        for (Point point : pointlist) {
            int iMetrix = 0;
            int jMetrix = 0;
            int x = point.getX();
            int y = point.getY();

            for (int i = 0; i < vLine.size(); i++) {
                if (x < vLine.get(i)) {
                    iMetrix = i + 1;        // if all line is small than x, imetrix will keep 0
                    break;
                }
            }

            for (int j = 0; j < hLine.size(); j++) {
                if (y < hLine.get(j)) {
                    jMetrix = j + 1;        // if all line is small than y, jmetrix will keep 0
                    break;
                }
            }

            metrix[iMetrix][jMetrix] += 1;

        }

        for (int i = 0; i < vLine.size() + 1; i++) {
            for (int j = 0; j < hLine.size() + 1; j++) {
                if (metrix[i][j] > 1) {
                    return false;
                }
            }
        }


        return true;
    }
}
