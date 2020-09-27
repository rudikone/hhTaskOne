import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws Exception {
        String s = read(); //метод чтения
        char[] subs1 = searchFirstSubstring(s); //определение первой подстроки
        char[] subs2 = searchSecondSubstring(s); //определение второй подстроки
        russianOnly(subs1);//проверка условия, что подстрока состоит из букв а-я(нижний регистр)
        russianOnly(subs2);//проверка условия, что подстрока состоит из букв а-я(нижний регистр)
        boolean b = false;
        if (equalsLength(subs1, subs2)) {
            b = possOfTransformation(subs1, subs2);//проверка возможности трансформации первой строки во вторую
        }
        executionResult(b);//вывод 0 или 1 в заисимости от результата
    }

    //далее я расположил методы в порядке их выполнения(согласно логике человека)

    //метод чтения из консоли
    public static String read() {
        //System.out.println("Input:"); - отключил, чтобы пройти тест на сайте
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

    //метод формировния первой подстроки и преобразования ее в массив
    public static char[] searchFirstSubstring(String s) throws Exception {
        String subs = null;
        if (s.matches("(.*) (.*)")) {
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == ' ') {
                    subs = s.substring(0, i);
                }
            }
        } else {
            throw new Exception("Некорректно введена строка!");
        }
        if (subs == null) throw new Exception("Некорректно введена подстрока");
        char[] sub1Array = new char[subs.length()];
        for (int j = 0; j < subs.length(); j++) {
            sub1Array[j] = subs.charAt(j);
        }
        return sub1Array;
    }

    //метод формировния второй подстроки и преобразования ее в массив
    public static char[] searchSecondSubstring(String s) throws Exception {
        String subs = null;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                subs = s.substring(i + 1);
            }
        }
        if (subs == null) throw new Exception("Некорректно введена подстрока");
        char[] sub2Array = new char[subs.length()];
        for (int j = 0; j < subs.length(); j++) {
            sub2Array[j] = subs.charAt(j);
        }
        return sub2Array;
    }

    //проверка условия, что буквы - русские
    public static void russianOnly(char[] subs) throws Exception {
        for (char sub : subs) {
            if (!(sub == 1105)) {
                if (sub < 1072 || sub > 1103) {

                    throw new Exception("Используйте только буквы русского алфавита в нижнем регистре!");
                }
            }
        }
    }

    //метод проверяющий, что строки одной длины
    public static boolean equalsLength(char[] subs1, char[] subs2) {
        boolean result = true;
        if (!(subs1.length == subs2.length)) {
            result = false;
        }
        return result;
    }

    //метод проверки возможности превращения первой подстроки во вторую
    public static boolean possOfTransformation(char[] subs1, char[] subs2) {
        boolean result = false;
        //если подстроки неидентичны
        if (!iidentity(subs1, subs2)) {
            //если есть повторяющиеся элементы
            if (poieial(subs1)) {
                result = arrWithRepeats(subs1, subs2); //проверяем возможности преобразовать все вхождения одной буквы в другую букву за один шаг.
            } else {
                result = true;
            }
        }
        else if(iidentity(subs1,subs2)){
            result=true;
        }
        return result;
    }

    //далее идут вспомогательные методы, которые нужны для выполнения метода possOfTransformation

    //метод проверки наличия в подстроке одинаковых элементов
    public static boolean poieial(char[] subs) {
        boolean b = false;
        for (int i = 0; i < subs.length - 1; i++) {
            for (int j = i + 1; j < subs.length; j++) {
                if (subs[i] == subs[j]) {
                    b = true;
                    break;
                }
            }
        }
        return b;
    }

    //метод, проверяющий возможность преобразовать все вхождения одной буквы в другую букву за один шаг.
    public static boolean arrWithRepeats(char[] subs1, char[] subs2) {
        boolean result = true;
        int b;
        int y;
        //сначала преобразуем наши массивы
        int[] one = aaore(subs1);
        //System.out.println(Arrays.toString(one));
        int[] two = aaore(subs2);
        //System.out.println(Arrays.toString(two));
        //затем сравним поэлементно
        for (int i = 0; i < one.length; i++) {
            //если элемент масссива не нулевой(это значит у него есть как минимум одна пара)
            if (!(one[i] == 0)) {
                b = one[i];
                y = two[i];
                for (int j = 0; j < one.length; j++) {
                    //в одном и том же месте двух массивов должны лежать идентичные символы(у каждого массива свой)
                    if ((one[j] == b && !(two[j] == y)) || (one[j] == b && two[j] == 0)) {
                        result = false;
                        break;
                    }
                }
            }
        }
        return result;
    }

    //далее идет вспомогательный метод, который нужен для выполнения метода arrWithRepeats

    //метод формирования массива повторяющихся элементов(элементы в данном случае заменяем на цифры)
    public static int[] aaore(char[] subs) {
        char b = 216;
        int v = 0;
        //создаем массив с нулями
        int[] arrayK1 = new int[subs.length];
        //пробегаем по массиву подстроки
        for (int i = 0; i < subs.length - 1; i++) {
            for (int j = i + 1; j < subs.length; j++) {
                //если находим повторы и они не являются b (изначально они точно не являются b)
                if (subs[i] == subs[j] && !(subs[i] == b)) {
                    b = subs[i];
                    v++;
                    //начинаем искать эл-ты, которые равны b и присваиваем этим элементам v
                    for (int k = 0; k < subs.length; k++) {
                        if (subs[k] == b) {
                            arrayK1[k] = v; //формируем массив уникальных цифр вместо повторяющихся элементов
                        }
                    }
                }
            }
        }
        return arrayK1;
    }

    //метод вывода
    public static void executionResult(boolean possOfTransformation) {
        //System.out.println("Otput:"); - отключил, чтобы пройти тест на сайте
        if (possOfTransformation) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }

    /*
    //метод, проверяющий наличие ВСЕХ букв русского аофавита в строке
    public static boolean isFullAlphabet(char[] subs) {
        Set<Integer> alphabet = new HashSet<>(33);
        boolean result = false;
        int cnt = 0;
        for (char c : subs) {
            int n = c - 'а';
            if (n >= 0 && n < 32 || n == 33) {
                if (alphabet.add(n)) {
                    cnt += 1;
                    if (cnt == 33) {
                        result = true;
                        break;
                    }
                }
            }
        }
        return result;
    }

     */

    //метод, проверяющий идентичность подстрок
    public static boolean iidentity(char[] subs1, char[] subs2) {
        boolean result = true;

        for (int i = 0; i < subs1.length; i++) {
            if (subs1[i] != subs2[i]) {
                result = false;
                break;
            }
        }
        return result;
    }
}
