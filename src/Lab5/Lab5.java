package Lab5;

/**
 * Created by Kostya Nirchenko.
 *
 * @since 09.12.2016
 */
public class Lab5 {
    static float[][] _b;
    static float[][] _c;
    static float[] _x;

    public static void arrayDisplay(float[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static float getSumOnBAndCMatrix(int i, int j, float[][] array, int lim) {
        float s = 0;
        int k = 0;
        System.out.print("(");
        while (k < lim) {
            System.out.print(_b[i][k] * _c[k][j]);
            if (k != lim - 1)
                System.out.print("+");
            s += _b[i][k] * _c[k][j];
            k++;
        }
        System.out.print(")");
        return s;
    }

    public static float getBYSum(int i, float[] y, int lim) {
        float s = 0;
        int k = 0;
        while (k < lim) {
            System.out.print(_b[i][k] * y[k]);
            if (k != lim - 1)
                System.out.print("+");
            s += _b[i][k] * y[k];
            k++;
        }
        return s;
    }

    public static float getCXSum(int i, int lim) {
        float s = 0;
        int k = lim;
        while (k >= 1) {
                        System.out.print(_c[i][k] * _x[k]);
            if (k != 1)
                System.out.print("+");
            s += _c[i][k] * _x[k];
            k--;
        }
        return s;
    }

    public static void firstStep(float[][] array) {
        for (int i = 0; i < array.length; i++) {
            _b[i][0] = array[i][0];
            System.out.println(array[i][0]);
        }
        for (int j = 0; j < array.length - 1; j++) {
            _c[0][j] = array[0][j] / _b[0][0];
            System.out.println(array[0][j] / _b[0][0]);
        }
    }

    public static float[] getY(float[][] array) {
        System.out.println("Расчитаем значения у");
        _x[0] = array[0][array.length - 1] / _b[0][0];
        System.out.println("y0 = " + array[0][array.length - 1] / _b[0][0]);
        for (int i = 1; i < array.length; i++) {
            System.out.printf("y(%d) = %f - (", i, array[i][array.length - 1]);
            System.out.print(")");
            System.out.println(" / " + _b[i][i] + " = " + _x[i]);
            _x[i] = (array[i][array.length - 1] - getBYSum(i, _x, array.length - 1)) / _b[i][i];
        }
        System.out.println();
        return _x;
    }

    public static void getX(float[] y) {
        System.out.println("Расчитаем значения x");
        System.out.printf("x(%d) = y(%d) = %f", _x.length - 1, y.length - 1, y[y.length - 1]);
        for (int i = y.length - 2; i >= 0 ; i--) {
            System.out.printf("x(%d) = %f -(", i, y[i]);
            _x[i] = y[i] - getCXSum(i, y.length - 1);
            System.out.print(")");
            System.out.println(" = " + _x[i]);
        }
    }

    public static void haletski(float[][] array) {
        System.out.println("Расчитаем значения для матрицы B и C");
        firstStep(array);
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - 1; j++) {
                if (j <= i && j > 0) {
                    _b[i][j] = array[i][j] - getSumOnBAndCMatrix(
                            i, j, array, array.length - 1
                    );
                }
                if (i <= j && i > 0) {
                    _c[i][j] = (array[i][j] - getSumOnBAndCMatrix(i, j, array, array.length - 1)) / _b[i][i];
                }
            }
        }
        for (int i = 0; i < _c.length; i++) {
            _c[i][i] = 0;
        }
        getX(getY(array).clone());
    }

    public static void main(String[] args) {
        float[][] array = {
                {0.17f, -0.13f, -0.11f, -0.12f, 0.22f},
                {1.00f, -1.00f, -0.13f, 0.13f, 0.11f},
                {0.35f, 0.33f, 0.12f, 0.13f, 0.12f},
                {0.13f, 0.11f, -0.13f, -0.11f, 1.00f}
        };
        _b = new float[array.length][array.length];
        _c = new float[array.length][array.length];
        _x = new float[array.length];

        haletski(array);

        for (int i = 0; i < _x.length; i++) {
            System.out.print(_x[i] + " ");
        }
        float check = 0;
        int k = 0;
        while (k < _x.length) {
            for (int i = 0; i < _x.length; i++) {
                check += array[k][i] * _x[i];
            }
            k++;
            check = 0;
        }
    }

}
