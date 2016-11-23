package edu.iit.cs430;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by YongYang on 11/22/16.
 */
public class Greedy {

    public List<String> execute(List<Point> pointList) {

        int pointNumber = pointList.size();
        double[] v = new double[pointNumber - 1];        //all possible position for line
        double[] h = new double[pointNumber - 1];
        for (int i = 0; i < v.length; i++) {             //Start with an arbitrary feasible solution.
            v[i] = pointList.get(i).getX() + 0.5;       //All lines are vertical
            h[i]=0.1;
        }


        boolean change=true;
        while(change==true){        //if no change already, while loop will stop
            change=false;
            for (int i = 0; i < v.length; i++) {             // choose first line in v
                if (v[i] != 0.1) {
                    for (int j = i; j < v.length; j++) {      // choose second line also in v after first line
                        if (v[j] != 0.1) {
                            v[i] = 0.1;
                            v[j] = 0.1;
                            for (int w = 0; w < v.length; w++) {   //choose the third line in v
                                if (v[w] == 0.1) {
                                    v[w] = pointList.get(w).getX() + 0.5;
                                    if(cheackCorrect(v,h,pointList)==false){
                                        v[i]=pointList.get(i).getX()+0.5;   //change fail, put lines back
                                        v[j]=pointList.get(j).getX()+0.5;
                                        v[w]=0.1;
                                    }else {
                                        change=true;    //line was reduced, while loop will contibue
                                    }

                                }
                            }
                            for (int w=0; w<h.length;w++){
                                if (h[w]==0.1){
                                    h[w]=pointList.get(w).getY()+0.5;
                                    if (cheackCorrect(v,h,pointList)==false){
                                        v[i]=pointList.get(i).getX()+0.5;
                                        v[j]=pointList.get(j).getX()+0.5;
                                        h[w]=0.1;
                                    }else {
                                        change=true;
                                    }
                                }

                            }
                        }
                    }

                    for (int j = 0; j < h.length; j++) {        // choose second line in h
                        if (h[j] != 0.1) {
                            v[i] = 0.1;
                            h[j] = 0.1;
                            for (int w = 0; w < v.length; w++) {   //choose the third line in v
                                if (v[w] == 0.1) {
                                    v[w] = pointList.get(w).getX() + 0.5;
                                    if(cheackCorrect(v,h,pointList)==false){
                                        v[i]=pointList.get(i).getX()+0.5;   //change fail, put lines back
                                        h[j]=pointList.get(j).getY()+0.5;
                                        v[w]=0.1;
                                    }else {
                                        change=true;               //line was reduced, while loop will contibue
                                    }

                                }
                            }
                            for (int w=0; w<h.length;w++){
                                if (h[w]==0.1){
                                    h[w]=pointList.get(w).getY()+0.5;
                                    if (cheackCorrect(v,h,pointList)==false){
                                        v[i]=pointList.get(i).getX()+0.5;
                                        h[j]=pointList.get(j).getY()+0.5;
                                        h[w]=0.1;
                                    }else {
                                        change=true;
                                    }
                                }

                            }
                        }
                    }

                }


            }


            for (int i =0; i<h.length;i++){
                if (h[i]!=0.1){
                    for (int j=i; j<h.length;j++){         //choose two line both in h
                        if (h[j] != 0.1) {
                            h[i] = 0.1;
                            h[j] = 0.1;
                            for (int w = 0; w < v.length; w++) {   //choose the third line in v
                                if (v[w] == 0.1) {
                                    v[w] = pointList.get(w).getX() + 0.5;
                                    if(cheackCorrect(v,h,pointList)==false){
                                        h[i]=pointList.get(i).getY()+0.5;   //change fail, put lines back
                                        h[j]=pointList.get(j).getY()+0.5;
                                        v[w]=0.1;
                                    }else {
                                        change=true;               //line was reduced, while loop will contibue
                                    }

                                }
                            }
                            for (int w=0; w<h.length;w++){
                                if (h[w]==0.1){
                                    h[w]=pointList.get(w).getY()+0.5;
                                    if (cheackCorrect(v,h,pointList)==false){
                                        h[i]=pointList.get(i).getY()+0.5;
                                        h[j]=pointList.get(j).getY()+0.5;
                                        h[w]=0.1;
                                    }else {
                                        change=true;
                                    }
                                }

                            }
                        }
                    }
                }
            }
        }








        //return result
        List<String> lines = new ArrayList<>();
        lines.add("");                 //keep first line for total number
        for(double i:v){
            if (i!=0.1){
                lines.add("v " + String.valueOf(i));
            }
        }

        for (double j:h){
            if (j!=0.1){
                lines.add("h " + String.valueOf(j));
            }
        }
        lines.add(0,String.valueOf(lines.size()-1));     // add total number in the first line


        return lines;
    }


    public boolean cheackCorrect(double[]v, double[]h,List<Point> pointlist){
        List<Double> vLine = new ArrayList<Double>();   // the array that has v line
        List<Double> hLine = new ArrayList<Double>();   // the array that has l line

        for (double i: v){
            if (i!=0.1){
                vLine.add(i);
            }
        }
        for (double i: h){
            if (i!=0.1){
                hLine.add(i);
            }
        }

        int [][] metrix = new int[vLine.size()+1][hLine.size()+1];     //matrix to seperate point
        for (int i =0;i<metrix.length;i++){
            for (int j =0; j<metrix[i].length;j++){
                metrix[i][j]=0;
            }
        }

        int iMetrix=0;
        int jMetrix=0;
        for (Point point: pointlist){
            int x=point.getX();
            int y=point.getY();

            for (int i=0; i<vLine.size();i++){
                if (x<vLine.get(i)){
                    iMetrix=i+1;        // if all line is small than x, imetrix will keep 0
                }
            }

            for (int j=0; j<hLine.size();j++){
                if (y<hLine.get(j)){
                    jMetrix=j+1;        // if all line is small than y, jmetrix will keep 0
                }
            }

            metrix[iMetrix][jMetrix]+=1;


        }

        for (int i =0;i<metrix.length;i++){
            for (int j =0; j<metrix[i].length;j++){
                if(metrix[i][j]>1){
                    return false;
                }
            }
        }


        return true;
    }
}
