package Matrix_work;

public class CompNum {

    private double re; // действительная часть

    private double im; // мнимая часть

    public void setRe(double re) // сеттер для действительной части (re - действительная часть)
    {
        this.re = re;
    }

    public void setIm(double im) // сеттер для мнимой части (im - мнимая часть)
    {
        this.im = im;
    }

    public double getRe() // геттер действительной части
    {
        return re; // с возвращаемым значением re - действительная часть
    }

    public double getIm() // геттер мнимой части
    {
        return im; // с возвращаемым значением im - мнимая часть
    }

    public CompNum() // дефолтный коструктор
    {
        this(0,0);
    }

    public CompNum(double re) // конструктор только для действительной части (re - действиельная часть)
    {
        this(re, 0);
    }

    public CompNum(double re, double im)// конструктор для действителной и мнимой части
    {                                   // (re - действительная часть, im - мнимая часть)
        this.re = re;
        this.im = im;
    }

    public CompNum(CompNum number) // конструктор копирования (number - комплексное число)
    {
        this.re = number.getRe();
        this.im = number.getIm();
    }

    public void print_alg() // выводит число в алгебраической форме
    {
        if (this.im != 0)
        {
            System.out.printf(this.getIm() > 0 ? "%f+%f*i" : "%f%f*i", this.getRe(), this.getIm());
        }
        else
        {
            System.out.print(this.getRe());
        }
    }

    public void print_trig() // выводит число в тригонометрической форме
    {
        if (this.abs().getRe() != 0)
        {
            System.out.printf(this.getIm() >= 0 ? "%f*(%f+%f*i)" : "%f*(%f%f*i)", this.abs().getRe(), this.getRe()/this.abs().getRe(), this.getIm()/this.abs().getRe());
        }
        else
        {
            System.out.print("0");
        }
    }

    public CompNum add(CompNum number) //сложение (number - второе комплексное число)
    {
        return new CompNum(this.getRe() + number.getRe(), this.getIm() + number.getIm());
    } // возвращает результат сложения

    public CompNum sub(CompNum number) //вычитание (number - второе коплексное число)
    {
        return new CompNum(this.getRe() - number.getRe(), this.getIm() - number.getIm());
    }// возвращает результат вычитания

    public CompNum mul(CompNum number) //умножение (number - второе коплексное число)
    {
        return new CompNum(this.getRe() * number.getRe() - this.getIm() * number.getIm(), this.getRe() * number.getIm() + this.getIm() * number.getRe());
    } // возвращает результат умножения

    public CompNum div(CompNum number) //деление (number - второе коплексное число)
    {
        return new CompNum((this.getRe() * number.getRe() + this.getIm() * number.getIm())/(number.getRe() * number.getRe() + number.getIm() * number.getIm()),
                (this.getIm() * number.getRe() - this.getRe() * number.getIm())/(number.getRe() * number.getRe() + number.getIm() * number.getIm()));
    } // возвращает результат деления

    public CompNum abs() // модуль комплексного числа
    {
        return new CompNum(Math.sqrt(Math.pow(this.getRe(), 2) + Math.pow(this.getIm(), 2)));
    } // возвращает вычисленный модуль

}
