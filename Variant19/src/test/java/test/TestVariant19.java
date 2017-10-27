package test;

import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

import lab1.Variant19;
import static org.testng.Assert.assertEquals;
import lab1.Variant19.Begin19;

public class TestVariant19 {
    ///////////////////////////////////////////////////////////////////////////////////////////////////
    @DataProvider
    public Object[][] beginProvider(){
        return new Object[][]{{1,0,6,6,22,30},{2,3,7,8,20,25},{-4,1,-1,5,14,12}};
    }																					//Test for the inner class task.

    @Test(dataProvider = "beginProvider")
    public void begintest(int x1,int y1, int x2, int y2, int perimeter, int square) {
        assertEquals(new Variant19().begin19(x1,y1,x2,y2), new Begin19(perimeter, square));
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////
    @DataProvider
    public Object[][] integerProvider(){
        return new Object[][]{ {300, 5}, {179,2}, {519,8} };            // Test to the task which asks to count minutes.
    }

    @Test(dataProvider = "integerProvider")
    public void testInteger(int p1, int p2){

        assertEquals(new Variant19().integer19(p1), p2);
    }

    @Test(expectedExceptions = AssertionError.class)
    public void negativeNumberInputTest() {

        new Variant19().integer19(-1);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////
    @DataProvider
    public Object[][] booleanProvider(){
        return new Object[][]{ {19,5,5,false}, {1,-1,10,true}, {29,25,30,false}, {1,-1,1, true} };
    }

    @Test(dataProvider = "booleanProvider")
    public void testBoolean(int p1, int p2, int p3, boolean p4){		 //Test for finding mutually opposite numbers

        assertEquals(new Variant19().boolean19(p1,p2,p3), p4);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////
    @DataProvider
    public Object[][] ifProvider(){
        return new Object[][]{ {3,3,4,3,3}, {10,5,5,5,1}, {1,7,1,1,2}, {8,8,8,11,4} };     // Test for finding the place of different number.
    }

    @Test(dataProvider = "ifProvider")
    public void testIf19(int p1, int p2, int p3, int p4, int p5){

        assertEquals(new Variant19().if19(p1, p2, p3, p4), p5);
    }

    @DataProvider
    public Object[][] incorrectInputProvider() {
        return new Object[][] { { 1,1,2,2 }, { 2,2,2,2} };
    }


    @Test(expectedExceptions = AssertionError.class, dataProvider = "incorrectInputProvider")
    public void incorrectInputTest(int p1, int p2, int p3, int p4) {

        new Variant19().if19(p1, p2, p3, p4);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    @DataProvider
    public Object[][] caseProvider(){
        return new Object[][]{ {2012, "yellow rabbit"}, {2021, "white rat"} };     // Test for the calendar task.
    }

    @Test(dataProvider = "caseProvider")
    public void testCase19(int p1, String p2){

        assertEquals(new Variant19().case19(p1), p2);
    }

    @Test(expectedExceptions = AssertionError.class)
    public void negativeYearInputTest() {

        new Variant19().case19(-100);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
    @DataProvider
    public Object[][] forLoopProvider() {
        return new Object[][] { { 5, 120.0}, {3, 6.0}, {7, 5040.0} };		// Test for the factorial task.
    }

    @Test(dataProvider = "forLoopProvider")
    public void testFor19(int p1, double p2) {
        assertEquals(new Variant19().for19(p1), p2);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    @DataProvider
    public Object[][] whileLoopProvider() {
        return new Object[][] { { 5048, 8405}, {1000, 1}, {1234567, 7654321} };		//Test for finding reverse number.
    }

    @Test(dataProvider = "whileLoopProvider")
    public void testWhile19(int p1, int p2) {
        assertEquals(new Variant19().while19(p1), p2);
    }
    //////////////////////////////////////////////////////////////////////////////////////////////
    @DataProvider
    public Object[][] arrayProvider() {
        return new Object[][] { { new int[] { 4,3,4,9,7,0,12,6,2,3},10,7}, {new int[] {3,4,3,5,9,12,0,3},8,9}};
    }

    @Test(dataProvider = "arrayProvider")
    public void testArray19(int[] p1, int p2, int p3) {			//Test for finding the number, which is not a local minimum or maximum.

        assertEquals(new Variant19().array19(p1,p2), p3);
    }
    //////////////////////////////////////////////////////////////////////////////////////////////
    @DataProvider
    public Object[][] matrixProvider() {

        int[][] input1 = {{1,2,3},
                {4,5,6},
                {7,8,9}};

        int[][] result1 = {{2,3},
                {5,6},									//Test for deleting first positive column.
                {8,9}};

        int[][] input2 = {{1,2,-3,11},
                {-4,5,6,-12},
                {7,-8,9,13}};

        int[][] result2 = {{1,2,-3,11},
                {-4,5,6,-12},
                {7,-8,9,13}};

        int[][] input3 = {{1,2,-3,11},
                {-4,5,6,12},
                {7,8,9,13}};

        int[][] result3 = {{1,-3,11},
                {-4,6,12},
                {7,9,13}};

        return new Object[][] { {input1, 3, 3, result1}, {input2, 3, 4, result2}, {input3, 3, 4, result3 } };

    }

    @Test(dataProvider = "matrixProvider")
    public void testMatrix19(int[][] input, int n, int m, int[][] result) {

        result.equals(new Variant19().matrix19(input,n,m));
    }
//////////////////////////////////////////////////////////////////////////////////////////////

}