package lab1;

public class Variant19 {

    ///////////////////////////////////////////////////////////////////

    public static class Begin19 {
        private int perimeter;
        private int square;

        public Begin19() {
            this.perimeter = 0;
            this.square = 0;
        }

        public Begin19(int perimeter, int square) {

            this.perimeter = perimeter;
            this.square = square;
        }

        public void result(int x1, int y1, int x2, int y2) {

            int length, width;

            length = Math.abs(x1 - x2);
            width = Math.abs(y1 - y2);

            this.perimeter = 2 * (length + width);
            this.square = length * width;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj == null || obj.getClass() != this.getClass()) {
                return false;
            }

            Begin19 myobj = (Begin19) obj;
            return ((this.perimeter == myobj.perimeter) && (this.square == myobj.square));

        }
    }

    ///////////////////////////////////////////////////////////////////

    public Begin19 begin19(int x1, int y1, int x2, int y2) {

        Begin19 obj = new Begin19();
        obj.result(x1, y1, x2, y2);
        return obj;
    }

    ///////////////////////////////////////////////////////////////////
    public int integer19(int n) {
        assert n > 0 : "Negative number!!!";
        int minutes;
        minutes = n / 60;
        return minutes;
    }

    ///////////////////////////////////////////////////////////////////

    public boolean boolean19(int x, int y, int z) {

        return ((x + y) == 0 || (x + z) == 0 || (y + z) == 0);
    }

    ///////////////////////////////////////////////////////////////////

    public int if19(int x1, int x2, int x3, int x4) {

        if (x1 == x2 && x1 != x3 && x1 != x4) {
            throw new AssertionError("Incorrect input!");
        } else if (x1 == x3 && x2 == x4 && x1 != x4) {
            throw new AssertionError("Incorrect input!");
        } else if (x1 == x2 && x1 == x3 && x1 == x4) {
            throw new AssertionError("Incorrect input!");
        }

        if (x1 != x2 && x2 == x3) {
            return 1;
        } else if (x2 != x3 && x3 == x4) {
            return 2;
        } else if (x3 != x4 && x4 == x1) {
            return 3;
        } else {
            return 4;
        }

    }

    ///////////////////////////////////////////////////////////////////

    public String case19(int n) {

        if (n < 0) {
            throw new AssertionError("Year can't be negative!!!");
        }

        double subcircle;
        int year;
        String result = "";
        double temp = (n - 1984) % 60;
        subcircle = Math.ceil((temp / 12));

        switch ((int) subcircle) {
            case 1:
                result += "green";
                break;
            case 2:
                result += "red";
                break;
            case 3:
                result += "yellow";
                break;
            case 4:
                result += "white";
                break;
            case 5:
                result += "black";
                break;
        }

        year = (int) (temp % 12);

        switch (year) {
            case 1:
                result += " rat";
                break;
            case 2:
                result += " cow";
                break;
            case 3:
                result += " tiger";
                break;
            case 4:
                result += " rabbit";
                break;
            case 5:
                result += " dragon";
                break;
            case 6:
                result += " snake";
                break;
            case 7:
                result += " horse";
                break;
            case 8:
                result += " sheep";
                break;
            case 9:
                result += " monkey";
                break;
            case 10:
                result += " chicken";
                break;
            case 11:
                result += " dog";
                break;
            case 12:
                result += " pig";
                break;
        }

        return result;
    }

    ///////////////////////////////////////////////////////////////////

    public double for19(int n) {

        double result = 1;
        int iterator = n;
        for (int i = 1; i < iterator; i++) {
            result *= n;
            n--;
        }
        return result;
    }

    ///////////////////////////////////////////////////////////////////

    public int while19(int n) {

        int result = 0, temp;

        while (n > 0) {
            temp = n % 10;
            n /= 10;
            result += temp;
            result *= 10;
        }
        result /= 10;

        return result;
    }

    ///////////////////////////////////////////////////////////////////

    public int array19(int[] array, int n) {

        int[] local_minimum = new int[n];
        int[] local_maximum = new int[n];
        int max;

        for (int i = 1; i < n - 1; i++) {
            if (array[i] > array[i + 1] && array[i] > array[i - 1]) {
                local_maximum[i] = array[i];
            }
            if (array[i] < array[i + 1] && array[i] < array[i - 1]) {
                local_minimum[i] = array[i];
            }
        }

        for (int i = 1; i < n - 1; i++) {
            if (array[i] == local_maximum[i] || array[i] == local_minimum[i]) {
                array[i] = -21002845;
            }

        }
        if (array[0] > array[1]) {
            array[0] = -21002845;
        }
        if (array[0] < array[1]) {
            array[0] = -21002845;
        }
        if (array[n - 1] > array[n - 2]) {
            array[n - 1] = -21002845;
        }
        if (array[n - 1] < array[n - 2]) {
            array[n - 1] = -21002845;
        }

        max = 0;
        for (int i = 0; i < n; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }

        return max;
    }

    ////////////////////////////////////////////////////////////////////

    public int[][] matrix19(int[][] twoDimensional, int n, int m) {

        int cnt;

        for (int j = 0; j < m; j++) {
            cnt = 0;

            for (int i = 0; i < n; i++)
                if (twoDimensional[i][j] > 0) {
                    cnt++;
                }

            if (cnt == n) {
                for (int k = j + 1; k < m; k++) {
                    for (int i = 0; i < n; i++) {
                        twoDimensional[i][k - 1] = twoDimensional[i][k];
                    }
                }
                m--;
                break;
            }
        }

        int[][] resmatrix = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                resmatrix[i][j] = twoDimensional[i][j];
            }
        }
        return resmatrix;
    }

    ///////////////////////////////////////////////////////////////////

    public static void main(String[] args) {

        System.out.println("Work of the first lab...");
        System.out.println("Done!!!");

    }

}