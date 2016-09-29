package Lab1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Kostya Nirchenko.
 * Уточнение корней методом хорд
 * @since 23.09.2016
 */
public class Lab1 {

    static double a, b, c, e, g;
    static int n;

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Введите левую границу отрезка - a: ");
        try {
            a = Double.parseDouble(reader.readLine());
            System.out.print("Введите правую границу отрезка - b: ");
            b = Double.parseDouble(reader.readLine());
            System.out.print("Введите требуемую погрешность - e: ");
            e = Double.parseDouble(reader.readLine());
            n = 0;
            c = a;
            do {
                n++;
                g = c;
                c = (a * fun(b) - b * fun(a)) / (fun(b) - fun(a));
                if (fun(a) * fun(c) < 0) b = c;
                else a = c;
                System.out.println("Текущее значение: " + c);
            }
            while (Math.abs(g - c) > e);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        System.out.println("Приближенное значение корня - Х = " + c);
        System.out.println("Число итераций - N = " + n);
    }

    private static double fun(double x) {
//        return (Math.pow(x, 3) + (0.1 * Math.pow(x, 2)) + (0.4 * x)) - 1.2;
        return 1 / Math.tan(x) - x / 4;
    }
}
