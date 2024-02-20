package Matrix_work;

public class compMatr {

    private CompNum[][] matrix; // создание двумерной матрицы

    public compMatr(int Row, int Col) //создание двумерной матрицы (Row - кол-во строк, Col - кол-во столбцов)
    {
        this.matrix = new CompNum[Row][Col];
        for (int i = 0; i < Row; i++)
        {
            for (int j = 0; j < Col; j++)
            {
                this.matrix[i][j] = new CompNum();
            }
        }
    }

    public void setItem(int i, int j, CompNum number) // внесение комп. числа в матрицу (i - индекс строки,
    {                                                 // j - индекс столбца, number - комп. число)
        this.matrix[i][j] = number;
    }

    public void setItem(int i, int j, double number) // внесение double числа в матрицу (i - индекс строки,
    {                                                // j - индекс столбца, number - число типа double)
        this.setItem(i, j, new CompNum(number));
    }
    /**
     * Получения элемента из матрицы
     * @param i - индекс по строке
     * @param j - индекс по столбцу
     * @return - получаемое число
     */
    public CompNum getItem(int i, int j) // получение элемента матрицы (i - индекс строки, j - индекс столбца,
    {                                    //  number - число типа double)
        return this.matrix[i][j];
    }

    public compMatr(int size) // конструктор квадратных матриц (size - размер матрицы)
    {
        this(size, size);
    }

    public compMatr(compMatr matr) // конструктор копирования матрицы (matr - матрица, которую нужно копировать)
    {
        this.matrix = new CompNum[matr.matrix.length][matr.matrix[0].length];
        for (int i = 0; i < matr.matrix.length; i++)
        {
            for (int j = 0; j < matr.matrix[0].length; j++)
            {
                this.matrix[i][j] = new CompNum(matr.getItem(i, j).getRe(), matr.getItem(i, j).getIm());
            }
        }
    }

    public void print_matrix() //Печать матрицы
    {
        for (int i = 0; i < this.matrix.length; i++)
        {
            for (int j = 0; j < this.matrix[0].length; j++)
            {
                this.getItem(i, j).print_alg();
                System.out.print(" ");
            }
            System.out.print("\n");
        }
    }

    public compMatr add(compMatr rMatr) // сложение матриц (rMatr - вторая матрица)
    {
        if (this.matrix.length != rMatr.matrix.length || this.matrix[0].length != rMatr.matrix[0].length)
        {
            throw new ArithmeticException("Сложение невозможно, матрицы разной размерности!");
        }
        compMatr Result = new compMatr(this.matrix.length, this.matrix[0].length);
        for (int i = 0; i < this.matrix.length; i++)
        {
            for (int j = 0; j < this.matrix[0].length; j++)
            {
                Result.setItem(i, j, new CompNum(this.getItem(i, j).getRe() + rMatr.getItem(i, j).getRe(), this.getItem(i, j).getIm() + rMatr.getItem(i, j).getIm()));
            }
        }
        return Result; // возвращаем результирующую матрицу
    }

    public compMatr sub(compMatr rMatr) // вычитание матрицы (rMatr - вычитаемая матрица)
    {
        if (this.matrix.length != rMatr.matrix.length || this.matrix[0].length != rMatr.matrix[0].length)
        {
            throw new ArithmeticException("Вычитание невозможно, матрицы разной размерности!");
        }
        compMatr Result = new compMatr(this.matrix.length, this.matrix[0].length);
        for (int i = 0; i < this.matrix.length; i++)
        {
            for (int j = 0; j < this.matrix[0].length; j++)
            {
                Result.setItem(i, j, new CompNum(this.getItem(i, j).getRe() - rMatr.getItem(i, j).getRe(), this.getItem(i, j).getIm() - rMatr.getItem(i, j).getIm()));
            }
        }
        return Result; // возвращаем результат вычитания
    }

    public compMatr multiplication(compMatr rMatr)  // умножение матриц (rMatrix - матрица, на которую будем умножать)
    {
        if (this.matrix[0].length != rMatr.matrix.length)
        {
            throw new ArithmeticException("Умножение невозможно, так как размерности не совпадают!");
        }
        compMatr Result = new compMatr(this.matrix.length, this.matrix[0].length);
        for (int i = 0; i < this.matrix.length; i++)
        {
            for (int j = 0; j < rMatr.matrix[0].length; j++)
            {
                for (int g = 0; g < this.matrix[0].length; g++)
                {
                    Result.setItem(i, j, Result.getItem(i, j).add(this.getItem(i, g).mul(rMatr.getItem(g, j))));
                }
            }
        }
        return Result; // возвращаем результат умножения
    }

    public compMatr T() //транспонирование матрицы
    {
        compMatr Result = new compMatr(this.matrix[0].length, this.matrix.length);
        for (int i = 0; i < this.matrix[0].length; i++)
        {
            for (int j = 0; j < this.matrix.length; j++)
            {
                Result.setItem(i, j, this.getItem(j, i));
            }
        }
        return Result; // возвращаем транспонированную матрицу
    }

    public CompNum determination() // Вычисление определителя
    {
        if (this.matrix.length != this.matrix[0].length)
        {
            throw new ArithmeticException("Определитель нельзя вычислить, матрица не квадратная!");
        }

        if (this.matrix.length == 1)
        {
            return this.getItem(0, 0);
        }
        else
        {
            CompNum Result = new CompNum();
            for (int i = 0; i < this.matrix.length; i++)
            {
                compMatr tmp_minor = new compMatr(this.matrix.length - 1);
                int ind_1 = 0, ind_2 = 0;
                for (int j = 1; j < this.matrix.length; j++)
                {
                    for (int g = 0; g < this.matrix[0].length; g++) {
                        if (g != i)
                        {
                            tmp_minor.setItem(ind_1, ind_2++, this.getItem(j, g));
                        }
                    }
                    ind_2 = 0;
                    ind_1++;
                }
                Result = Result.add(tmp_minor.determination().mul(new CompNum(Math.pow(-1, i)).mul(this.getItem(0, i))));
            }
            return Result; //возращаем значение определителя
        }
    }
}
