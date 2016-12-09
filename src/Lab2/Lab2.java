package Lab2;

/**
 * Created by Kostya Nirchenko.
 *
 * @since 08.12.2016
 */
public class Lab2 {

    static float _max;
    static float[] _x;

    /**
     * Поиск максимального элемента в столбце и определение индекса этого столбца
     * @param array - массив с данными
     * @param column - номер текущей колонки
     * @param row - номер текущей строки
     * @return int - индекс колонки с максимальным элементом
     */
    public static int maxInColumn(float[][] array, int column, int row) {
        float max = 0;
        int iMax = 0;
        for (int i = row; i < array.length; i++) {
            max = Math.max(Math.abs(array[i][column]), max);
            if (Math.max(Math.abs(array[i][column]), max) == Math.abs(array[i][column])) {
                iMax = i;
            }
        }
        _max = array[iMax][column];
        return iMax;
    }

    /**
     * Перестановка строк
     * @param array - массив данных
     * @param firstRow - первая строка
     * @param secondRow - вторая строка
     */
    public static void rowsSwap(float[][] array, int firstRow, int secondRow) {
        float tmp;
        for (int i = 0; i < array.length; i++) {
            tmp = array[firstRow][i];
            array[firstRow][i] = array[secondRow][i];
            array[secondRow][i] = tmp;
        }
    }

    /**
     * Вычисление корней системы уравнений
     * @param array - массив данных
     */
    public static void doGauss(float[][] array) {
        // Счетчики столбцов, строк, строк на перестановку
        int currRow = 1;
        int currCol = 0;
        int rowToSwap = 0;

        float mp = 0;
        while (currCol < array.length - 2) {
            rowsSwap(array, maxInColumn(array, currCol, currRow - 1), rowToSwap);
            for (int i = currRow; i < array.length; i++) {
                //Вычислим множители для каждого прохода
                mp = array[i][currCol] / _max;
                if (Math.round(mp) != 0) { // если множитель равен 0 - можем пропустить шаг
                    for (int j = currCol; j < array.length; j++) {
                        // определим новые значения в массиве данных
                        array[i][j] = array[i][j] - (array[currRow - 1][j] * mp);
                    }
                }
            }
            if (Math.round(mp) != 0) arrayDisplay(array);
            // инкрементируем счетчики
            currRow++;
            currCol++;
            rowToSwap++;
        }

        int count = _x.length - 1;
        float s = 0;
        for (int i = array.length - 1; i >= 0; i--) {
            s = array[i][array.length - 1];
            for (int j = array.length - 2; j >= 0; j--) {
                if (j != count) {
                    // подсчитаем суммы произведений всех известных и коэфициентом, разницы между
                    // ними и свободным членом
                    s -= array[i][j] * _x[j];
                }
                if (j == 0) {
                    // найдем неизвестное значение
                    _x[count] = s / array[i][count];
                    count--;
                }
            }
        }
    }

    /**
     * Вывод матрицы на пользовательский экран
     * @param array - массив данных
     */
    public static void arrayDisplay(float[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
            System.out.println();
        }
    }

    public static void main(String[] args) {
        float[][] array = {
            {4.24f, 2.73f, -1.55f, 1.87f},
            {2.34f, 1.27f, 3.15f, 2.16f},
            {3.05f, -1.05f, -0.63f, -1.25f}
        };
        _x = new float[array.length];
        arrayDisplay(array);
        doGauss(array);
        for (int i = 0; i < _x.length; i++) {
                System.out.print(_x[i] + " ");
        }
        System.out.println();
        // проверим полученные результаты
        float check = 0;
        for (int i = 0; i < array.length - 1; i++) {
            check += array[0][i] * _x[i];
        }
        System.out.println(check);
    }

}
