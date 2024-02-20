package Matrix_work;

import java.util.Scanner;

public class Main {
    private static Scanner in = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("Введите количество строк и столбцов матрицы");
        int row = in.nextInt(), col = in.nextInt();

        compMatr Matrix = new compMatr(row, col);

        for (int i = 0; i < row; i++)
        {
            for (int j = 0; j < col; j++)
            {
                Matrix.setItem(i, j, new CompNum(i, j));
            }
        }

        //тестирование

        System.out.println("Печать матрицы с комплексными числами");
        Matrix.print_matrix();
        System.out.println();

        System.out.println("Представление комплексного числа в тригонометрической форме");
        CompNum Num = new CompNum(10, 8);
        Num.print_trig();
        System.out.println();
        System.out.println();

        System.out.println("Сложение двух матриц");
        compMatr tmp = Matrix.add(Matrix);
        tmp.print_matrix();
        System.out.println();

        System.out.println("Операция транспонирования");
        tmp = Matrix.T();
        tmp.print_matrix();
        System.out.println();

        /*
        System.out.println("Умножение двух матриц");
        tmp = Matrix.multiplication(Matrix);
        tmp.print_matrix();
        System.out.println();

        System.out.println("Вычисление детерминанта");
        CompNum a = Matrix.determination();
        a.print_alg();
        */
    }
}