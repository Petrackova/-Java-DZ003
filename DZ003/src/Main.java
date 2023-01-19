import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
public class Main {

    static String fio = "";
    static String birthDay;
    static long phoneNumber;
    static char gender;

    public static void main(String[] args) {
        try {
            inputUser();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void inputUser() throws IOException {
        System.out.println("Введите данные пользователя через пробел");
        System.out.println("Фамилия Имя Отчество, Дата рождения, Номер телефона, Пол");
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();
        String[] arr = userInput.split(" ");
        sc.close();
        if (arr.length < 6) {
            try {
                throw new Exception("Введено меньше информации. Ожидается 6 данных");
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.exit(1);
            }
        }
        if (arr.length > 6) {
            try {
                throw new Exception("Введено больше информации. Ожидается 6 данных");
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.exit(1);
            }
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].length() == 1) {
                checkGender(arr[i].charAt(0));
                if (arr[i].equals("f") | arr[i].equals("m")) {
                    gender = arr[i].charAt(0);
                }
            }
            if (arr[i].matches("^[0-9]*$")) {
                if ( arr[i].length()>11) {
                    try {
                        throw new Exception("Номер телефона больше необходимого, Ожидается 11 цифр");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        System.exit(1);
                    }
                }
                else if ( arr[i].length()<11) {
                    try {
                        throw new Exception("Номер телефона меньше необходимого, Ожидается 11 цифр");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        System.exit(1);
                    }
                }
                phoneNumber = Long.valueOf(arr[i]);
            }
            if (arr[i].matches("^[0-9.]*$") & arr[i].length() <11) {
                checkBirhday(arr[i]);
                birthDay = arr[i];
            }
            if (arr[i].matches("^[a-zA-Zа-яА-Я]*$") & arr[i].length() > 1) {
                fio += arr[i] + " ";
            }
            if (arr[i].matches(".*[a-zA-Zа-яА-Я].*$") && arr[i].matches(".*[0-9].*")) {
                try {
                    throw new Exception("Неверный формат ввода. Ожидается только символы или числа ");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    System.exit(2);
                }
            }
        }
        String[] familyName = fio.split(" ");
        File file = new File(String.format("C:\\Users\\dispr\\DZ003//%s.txt", familyName[0]));
        boolean writeOverwrite = true;
        try {
            if (file.createNewFile()) {
                System.out.println("File is created!");
                writeOverwrite = false;
            } else if (familyName[0] == familyName[1]) {
                System.out.println("File is created!");
                writeOverwrite = false;
            } else {
                System.out.println("File already exists.");
            }
            FileWriter writer = new FileWriter(file, writeOverwrite);
            writer.write("<" + fio + ">" + "<" + birthDay + ">" + "<" + phoneNumber + ">" + "<" + gender + ">\n");
            writer.close();
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
            System.out.println(e.getMessage());
            System.exit(4);
        }
    }

    public static void checkGender(char gender) {
        if (gender != 102 && gender != 109) {
            System.err.println(gender);
            try {
                throw new Exception("неверный параметр пола, введите f или m. введено ");
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.exit(3);
            }
        }
    }

    public static void checkBirhday(String birthDay) {
        if (!birthDay.matches("\\d{2}.\\d{2}.\\d{4}")) {
            try {
                throw new Exception("неверный формат даты рождения, необходимо ввести dd.MM.yyyy ");
            } catch (Exception e) {
                System.out.println(e.getMessage() + birthDay);
                System.exit(5);
            }
        }
    }

}