import java.util.*;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {

        var data = createTestData(100000);
        var startTime = System.nanoTime();
        int[] result;
//        var result = bubbleSort(data);
        var timeResult = System.nanoTime() - startTime;
//        System.out.println(TimeUnit.MILLISECONDS.convert(timeResult, TimeUnit.NANOSECONDS) + " milliseconds");

//        data = createTestData(100000);
//        startTime = System.nanoTime();
//        result = directSort(data);
//        timeResult = System.nanoTime() - startTime;
//        System.out.println(TimeUnit.MILLISECONDS.convert(timeResult, TimeUnit.NANOSECONDS) + " milliseconds");

        data = createTestData(10000000);
        startTime = System.nanoTime();
        mergeSort(data, 1, data.length);
        timeResult = System.nanoTime() - startTime;
        System.out.println(TimeUnit.MILLISECONDS.convert(timeResult, TimeUnit.NANOSECONDS) + " milliseconds");

        List<Integer> testData = Arrays.stream(createTestData(10000000)).boxed().toList();
        startTime = System.nanoTime();
        List<Integer> r = testData.stream().sorted(Comparator.reverseOrder()).toList();
        result = testData.stream().mapToInt(i->i).toArray();
        timeResult = System.nanoTime() - startTime;
        System.out.println(TimeUnit.MILLISECONDS.convert(timeResult, TimeUnit.NANOSECONDS) + " milliseconds");

        data = createTestData(10000000);
        startTime = System.nanoTime();
        quickSort(data, 0, data.length-1);
        timeResult = System.nanoTime() - startTime;
        System.out.println(TimeUnit.MILLISECONDS.convert(timeResult, TimeUnit.NANOSECONDS) + " milliseconds");

    }

    public static int[] bubbleSort(int[] arr){
        boolean sorted = false;
        int i = 0;
        do {
            sorted = true;
            for (int j = 1; j < arr.length - i; j++) {
                if (arr[j - 1] > arr[j]) {
                    int tmp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = tmp;
                    sorted = false;
                }
            }
            i += 1;
        }
        while(!sorted);

        return arr;
    }

    private static int[] createTestData(int size){
        Random random = new Random();
        return random.ints(-100000,50000).limit(size).toArray();
    }

    public static int[] directSort(int[] array){
        for (int i = 0; i < array.length-1; i++) {
            int min = i;
            for (int j = i+1; j < array.length; j++) {
                if (array[j] < array[min]){
                    min = j;
                }
            }
            if (i != min){
                int tmp = array[i];
                array[i] = array[min];
                array[min] = tmp;
            }
        }
        return array;
    }

    public static void mergeSort(int[] A, int p, int r) {
        if (p < r) {
            int q = (p + r) / 2;
            mergeSort(A, p, q);
            mergeSort(A, q + 1, r);
            merge(A, p, q, r);
        }
    }

    private static void merge(int[] A, int lo, int mid, int hi) {
        int first = 0;
        int middle = mid - lo;
        int second = middle + 1;
        int[] buf = Arrays.copyOfRange(A, lo - 1, hi);
        for (int i = lo - 1; i < hi; i++) {
            if (first > middle) {
                A[i] = buf[second++];
            } else if (second > buf.length - 1) {
                A[i] = buf[first++];
            } else if (buf[first] < buf[second]) {
                A[i] = buf[first++];
            } else {
                A[i] = buf[second++];
            }
        }
    }

    public static void quickSort(int[] array, int start, int end){
        int left = start;
        int right = end;
        int pivot = array[(start + end) / 2];
        do {
            while (array[left] < pivot){
                left++;
            }
            while (array[right] > pivot){
                right--;
            }
            if (left <= right){
                if (left < right){
                    int temp = array[left];
                    array[left] = array[right];
                    array[right] = temp;
                }
                left++;
                right--;
            }
        } while (left <= right);
        if (left < end){
            quickSort(array, left, end);
        }
        if (start < right ){
            quickSort(array, start, right);
        }
    }
}
