import java.util.Scanner;

public class ArrayOperations {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the size of the array:");
        int size = scanner.nextInt();
        int[] array = new int[size];

        System.out.println("Enter the elements of the array:");
        for (int i = 0; i < size; i++) {
            array[i] = scanner.nextInt();
        }

        System.out.println("Enter the element to insert:");
        int element = scanner.nextInt();
        System.out.println("Enter the position to insert the element:");
        int position = scanner.nextInt();

        array = insertElement(array, element, position);

        System.out.println("Array after insertion:");
        printArray(array);

        System.out.println("Enter the position to delete an element:");
        position = scanner.nextInt();

        array = deleteElement(array, position);

        System.out.println("Array after deletion:");
        printArray(array);
    }

    public static int[] insertElement(int[] array, int element, int position) {
        int[] newArray = new int[array.length + 1];
        for (int i = 0, j = 0; i < newArray.length; i++) {
            if (i == position) {
                newArray[i] = element;
            } else {
                newArray[i] = array[j++];
            }
        }
        return newArray;
    }

    public static int[] deleteElement(int[] array, int position) {
        int[] newArray = new int[array.length - 1];
        for (int i = 0, j = 0; i < array.length; i++) {
            if (i != position) {
                newArray[j++] = array[i];
            }
        }
        return newArray;
    }

    public static void printArray(int[] array) {
        for (int elem : array) {
            System.out.print(elem + " ");
        }
        System.out.println();
    }
}
