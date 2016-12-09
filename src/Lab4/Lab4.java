package Lab4;

/**
 * Created by Kostya Nirchenko.
 *
 * @since 09.12.2016
 */
public class Lab4 {
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
        return Math.abs(maxD);
    }

    static void iteration(float[][] array) {
        float s = 0;
        int iterate = 0;
        int skip = 0;

        while (_maxD > e) {
            for (int i = 0; i < array.length; i++) {
                s = array[i][array.length - 1];
                for (int j = 0; j < array.length - 1; j++) {
                    if (skip == j)
                        continue;
                    else
                        s -= array[i][j] * _x[j];
                }
                skip++;

                s /= array[i][i];
                _x[i] = s;
                _d[i] = _x[i] - _prevX[i];

                System.out.print(_x[i] + " ");
            }
            System.out.print(" ");
            for (int i = 0; i < _x.length; i++) {
                _prevX[i] = _x[i];
                System.out.print(_d[i] + " ");
            }

            _maxD = maxD(_d);

            skip = 0;
            iterate++;
            System.out.println();
        }
    }

    public static void main(String[] args) {
        float [][] array = {
                {7.8f, 5.3f, 4.8f, 1.8f},
                {3.3f, 1.1f, 1.8f, 2.3f},
                {4.5f, 3.3f, 2.8f, 3.4f}
        };

        _x = new float[array.length];
        _prevX = _x.clone();
        _d = _x.clone();

        iteration(array);

        System.out.println("_____________________________________________________________________");

        for (int i = 0; i < array.length; i++) {
            System.out.print(_x[i] + " ");
        }
    }
}

