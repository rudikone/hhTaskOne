public class Rules {

    //проверка условия, что буквы - русские
    public static void russianOnly(char[] subs) throws Exception {
        boolean result = true;
        for (int i = 0; i < subs.length; i++) {
            if (subs[i] < 1072 || subs[i] > 1103) {
                result = false;
            }
        }
        if (result == false) {
            throw new Exception("Используйте только буквы русского алфавита в нижнем регистре!");
        }
    }

    //метод проверяющий, что строки одной длины
    public static void equalsLength(char[] subs1, char[] subs2) throws Exception {
        if (!(subs1.length == subs2.length)) {
            throw new Exception("Введены подстроки разной длины!");
        }
    }
}
