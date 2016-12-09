package Lab3;

/**
 * Created by Kostya Nirchenko.
 *
 * @since 09.12.2016
 */
public class Lab3 {
    static float[] _x;
    static float[] _prevX;
    static float[] _d;
    static float _maxD = 1f;

    static float e = 0.0001f;

    static float maxD(float[] array) {
        float maxD = 0;
        for (int i = 0; i < array.length; i++) {
            maxD = Math.max(Math.abs(array[i]), maxD);
        }
        return maxD;
    }

    static void iterate(float[][] array) {
        float s = 0;
        int iterate = 0;
        int skip = 0;

        while (_maxD > e) {
            for (int i = 0; i < array.length; i++) {
                s = array[i][array.length - 1];
                for (int j = 0; j < array.length - 1; j++) {
                    if (skip == j) {
                        continue;
                    } else {
                        s -= array[i][j] * _x[j];
                    }
                }
                skip++;

                s /= array[i][i];

                _d[i] = _x[i] - s;
                _prevX[i] = s;
                System.out.print(_x[i] + " ");
                }
                System.out.print(" ");
                for (int i = 0; i < _x.length; i++) {
                    _x[i] = _prevX[i];
                    System.out.print(_d[i]);
            }
            _maxD = maxD(_d);
            skip = 0;
            iterate++;
            System.out.println();
        }
    }

    public static void main(String[] args) {

        float[][] array = {
                {0.23f, -0.14f, 0.06f, -0.12f, 1.21f},
                {0.12f, 0.32f, -0.18f, -0.72f, 0},
                {0.08f, -0.12f, 0.23f, 0.32f, -0.58f},
                {0.25f, 0.22f, 0.14f, 1.56f, 0}
        };

        _x = new float[array.length];
        _prevX = (float[]) _x.clone();
        _d = (float[]) _x.clone();

        iterate(array);
        System.out.println("____________________________________________________________________________________________");
        for (int i = 0; i < _x.length; i++) {
            System.out.print(_x[i] + " ");
        }
    }
}
