import java.util.ArrayList;
import java.util.Arrays;

public class Research {

    //формировние первой подстроки
    public static char[] substringOneSearch(String s) {
        String subs = null;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                subs = s.substring(0, i);
            }
        }
        char[] sub1Array = new char[subs.length()];
        for (int j = 0; j < subs.length(); j++) {
            sub1Array[j] = subs.charAt(j);
        }
        return sub1Array;
    }

    //формирование второй подстроки
    public static char[] substringTwoSearch(String s) {
        String subs = null;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                subs = s.substring(i + 1, s.length());
            }
        }
        char[] sub2Array = new char[subs.length()];
        for (int j = 0; j < subs.length(); j++) {
            sub2Array[j] = subs.charAt(j);
        }
        return sub2Array;
    }


    //метод проверки возможности превращения первой подстроки во вторую
    public static boolean possOfTransformation(char[] subs1, char[] subs2) {
        boolean result = false;
        //если есть повторяющиеся элементы
        if (poieial(subs1)) {
            result = arrWithRepeats(subs1, subs2); //проверяем возможности преобразовать все вхождения одной буквы в другую букву за один шаг.
        } else {
            result = true;
        }
        return result;
    }

    //метод проверки наличия в подстроке одинаковых элементов
    public static boolean poieial(char[] subs) {
        boolean b = false;
        for (int i = 0; i < subs.length - 1; i++) {
            for (int j = i + 1; j < subs.length; j++) {
                if (subs[i] == subs[j]) {
                    b = true;
                }
            }
        }
        return b;
    }

    //метод, проверяющий возможность преобразовать все вхождения одной буквы в другую букву за один шаг.
    public static boolean arrWithRepeats(char[] subs1, char[] subs2) {
        boolean result = true;
        //сначала преобразуем наши массивы
        int[] one = aaore(subs1);
        int[] two = aaore(subs2);
        //затем сравним поэлементно
        for (int i = 0; i < one.length; i++) {
            if (!(one[i] == two[i])) {
                result = false;
            }
        }
        return result;
    }

    //метод формирования массива повторяющихся элементов(элементы в данном случае заменяем на цифры)
    public static int[] aaore(char[] subs) {
        char b = '.';
        int v = 0;
        //создаем массив с нулями
        int[] arrayK1 = new int[subs.length];
        for (int t = 0; t < arrayK1.length; t++) {
            arrayK1[t] = 0;
        }
        //пробегаем по массиву подстроки
        for (int i = 0; i < subs.length - 1; i++) {
            for (int j = i + 1; j < subs.length; j++) {
                //если находим повторы и они не являются b (изначально они точно не являются b)
                if (subs[i] == subs[j] && !(subs[i] == b)) {
                    b = subs[i];
                    v++;
                    //начинаем искать эл-ты, которые равны b и присваиваем этим элементам УНИКАЛЬНУЮ цифру v
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
}

