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
    static int n, i;

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Выберите уравнение для уточнения\n" +
                    "1) x^3 + 0,1*x^2 + 0,4x - 1,2 = 0\n" +
                    "2) ctg(x) - x/4 = 0");
            i = Integer.parseInt(reader.readLine());
            System.out.print("Введите левую границу отрезка - a: ");
            a = Double.parseDouble(reader.readLine());
            System.out.print("Введите правую границу отрезка - b: ");
            b = Double.parseDouble(reader.readLine());
            System.out.print("Введите требуемую погрешность - e: ");
            e = Double.parseDouble(reader.readLine());
            n = 0;
            // В переменной с будем хранить текущее значение функции в точке. Первоначально присвоим ей начало отрезка
            c = a;
            do {
                n++;
                g = c;
                // Расчитаем по формуле значение функции в определенной точке. После чего проверим, меньше ли нуля произведение значений фукции в точках а и с.
                // если меньше - присвоим концу отрезка значение в текущей точке, иначе - началу отрезка. Тем самым - будем каждый раз уменшать отрезок
                switch (i) {
                    case 1:
                        c = (a * fun(b) - b * fun(a)) / (fun(b) - fun(a));
                        if (fun(a) * fun(c) < 0) b = c;
                        else a = c;
                        break;
                    case 2:
                        c = (a * fun2(b) - b * fun2(a)) / (fun2(b) - fun2(a));
                        if (fun2(a) * fun2(c) < 0) b = c;
                        else a = c;
                        break;
                }
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
        return (Math.pow(x, 3) + (0.1 * Math.pow(x, 2)) + (0.4 * x)) - 1.2;
    }

    private static double fun2(double x) {
        return 1 / Math.tan(x) - x / 4;
    }
}
