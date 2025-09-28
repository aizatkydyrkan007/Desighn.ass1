import java.util.Arrays;

public class DeterministicSelect {
    // находим k-й наименьший элемент (k начинается с 1)
    public static int select(int[] arr, int k) {
        if (k < 1 || k > arr.length) throw new IllegalArgumentException("Неверное k");
        return selectHelper(arr, 0, arr.length - 1, k - 1);
    }
    // рекурсивная функция
    private static int selectHelper(int[] arr, int left, int right, int k) {
        if (left == right) return arr[left];
        int pivotIndex = choosePivot(arr, left, right);
        pivotIndex = partition(arr, left, right, pivotIndex);
        if (k == pivotIndex) {
            return arr[k];
        }
        else if (k < pivotIndex) {
            return selectHelper(arr, left, pivotIndex - 1, k);
        }
        else {
            return selectHelper(arr, pivotIndex + 1, right, k);
        }
    }
    // выбираем медиану медиан как опорный элемент
    private static int choosePivot(int[] arr, int left, int right) {
        if (right - left < 5) {
            Arrays.sort(arr, left, right + 1);
            return (left + right) / 2;
        }
        // разбиваем на подмассивы по 5 элементов, сортируем и выбираем медиану
        int subIndex = left;
        for (int i = left; i <= right; i += 5) {
            int subEnd = Math.min(i + 4, right);
            Arrays.sort(arr, i, subEnd + 1);
            int median = (i + subEnd) / 2;
            swap(arr, subIndex++, median);
        }
        return choosePivot(arr, left, subIndex - 1);
    }
    // стандартный partition (разделение массива)
    private static int partition(int[] arr, int left, int right, int pivotIndex) {
        int pivot = arr[pivotIndex];
        swap(arr, pivotIndex, right);
        int store = left;
        for (int i = left; i < right; i++) {
            if (arr[i] < pivot) {
                swap(arr, store, i);
                store++;
            }
        }
        swap(arr, store, right);
        return store;
    }
    // метод обмена элементов
    private static void swap(int[] arr, int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }
    // проверка
    public static void main(String[] args) {
        int[] arr = {15, 34, 23, 8, 3, 21, 54};
        int k = 3;
        int result = select(arr, k);
        System.out.println(k + "-й наименьший элемент: " + result);
    }
}