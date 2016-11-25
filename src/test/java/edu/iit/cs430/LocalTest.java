package edu.iit.cs430;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by YongYang on 11/24/16.
 */
public class LocalTest {


    private Local local = new Local();
    private List<Point> pointList = new ArrayList<Point>();
    private List<Point> pointList1 = new ArrayList<Point>();


    private Point p1 = new Point(1,3);
    private Point p2 = new Point(2,4);
    private Point p3 = new Point(3,1);
    private Point p4 = new Point(4,2);
    private Point p5 = new Point(5,5);

    private double[] v = new double[2];        //all possible position for line
    private double[] h = new double[1];

    private double[] v1 = new double[1];        //all possible position for line
    private double[] h1 = new double[1];


    @Before
    public void setup(){
        v[0]=1.5;
        v[1]=3.5;
        h[0]=3.5;

        v1[0]=0.5;
        h1[0]=0.5;

        pointList1.add(p1);


        pointList.add(p1);
        pointList.add(p2);
        pointList.add(p3);
        pointList.add(p4);
        pointList.add(p5);
    }



    @Test
    public void cheackCorrect() throws Exception {


        assertEquals(true, local.cheackCorrect(v,h,pointList));

    }
    @Test
    public void cheackCorrect1() throws Exception {


        assertEquals(true, local.cheackCorrect(v,h,pointList1));

    }
    @Test
    public void cheackCorrect3() throws Exception {


        assertEquals(false, local.cheackCorrect(v1,h1,pointList));

    }


/*
    @Test
    public void execute() throws Exception {
        assertEquals(null,local.execute(pointList));

    }

*/
}