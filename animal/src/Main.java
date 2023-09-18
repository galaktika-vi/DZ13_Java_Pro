import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Выберите задачу для решения:");
            System.out.println("1: Найти первый элемент, встречающийся k раз в массиве");
            System.out.println("2: Проверить, равны ли два массива");
            System.out.println("3: Сгруппировать слова с одинаковым набором символов");
            System.out.println("4: Выход");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Введите массив чисел через пробел:");
                    int[] arr = Arrays.stream(scanner.nextLine().split(" "))
                            .mapToInt(Integer::parseInt)
                            .toArray();
                    System.out.println("Введите значение k:");
                    int k = scanner.nextInt();
                    System.out.println("Первый элемент, встречающийся " + k + " раз: " + findFirstElementOccurringKTimes(arr, k));
                    break;
                case 2:
                    System.out.println("Введите первый массив чисел через пробел:");
                    int[] arr1 = Arrays.stream(scanner.nextLine().split(" "))
                            .mapToInt(Integer::parseInt)
                            .toArray();
                    System.out.println("Введите второй массив чисел через пробел:");
                    int[] arr2 = Arrays.stream(scanner.nextLine().split(" "))
                            .mapToInt(Integer::parseInt)
                            .toArray();
                    System.out.println("Массивы равны: " + areArraysEqual(arr1, arr2));
                    break;
                case 3:
                    System.out.println("Введите список слов через пробел:");
                    String[] words = scanner.nextLine().split(" ");
                    System.out.println("Группы слов с одинаковым набором символов:");
                    groupWordsWithSameCharacters(words);
                    break;
                case 4:
                    System.out.println("Выход из программы.");
                    return;
                default:
                    System.out.println("Неверный выбор. Попробуйте еще раз.");
            }
        }
    }

    public static Integer findFirstElementOccurringKTimes(int[] arr, int k) {
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : arr) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }
        for (int num : arr) {
            if (countMap.get(num) == k) {
                return num;
            }
        }
        return null;
    }

    public static boolean areArraysEqual(int[] arr1, int[] arr2) {
        if (arr1.length != arr2.length) {
            return false;
        }
        Set<Integer> set1 = Arrays.stream(arr1).boxed().collect(Collectors.toSet());
        Set<Integer> set2 = Arrays.stream(arr2).boxed().collect(Collectors.toSet());
        return set1.equals(set2);
    }

    public static void groupWordsWithSameCharacters(String[] words) {
        Map<String, List<String>> groups = new HashMap<>();
        for (String word : words) {
            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            String sortedWord = new String(chars);
            groups.putIfAbsent(sortedWord, new ArrayList<>());
            groups.get(sortedWord).add(word);
        }
        for (List<String> group : groups.values()) {
            System.out.println(String.join(", ", group));
        }
    }
}
