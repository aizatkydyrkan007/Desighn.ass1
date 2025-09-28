import java.util.Arrays;
import java.util.Random;

public class QuickSort {
    // основная функция сортировки
    public static void quickSort(int[] arr) {
        if (arr == null || arr.length <= 1) return;
        quickSortHelper(arr, 0, arr.length - 1);
    }
    // рекурсивная часть
    private static void quickSortHelper(int[] arr, int left, int right) {
        if (left >= right) return;
        Random random = new Random();
        int pivotIndex = random.nextInt(right - left + 1) + left;
        int pivot = arr[pivotIndex];
        int i = left, j = right;
        while (i <= j) {
            while (arr[i] < pivot) i++;
            while (arr[j] > pivot) j--;
            if (i <= j) {
                swap(arr, i, j);
                i++;
                j--;
            }
        }
        if (left < j) quickSortHelper(arr, left, j);
        if (i < right) quickSortHelper(arr, i, right);
    }
    // метод обмена элементов
    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
    // проверка
    public static void main(String[] args) {
        int[] data = {1, 5, 9, 10, 2, 6};
        System.out.println("До: " + Arrays.toString(data));
        quickSort(data);
        System.out.println("После: " + Arrays.toString(data));
    }
}