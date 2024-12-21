import java.util.HashSet;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();  // Đọc số lượng phần tử n
        int[] arr = new int[n];  // Mảng chứa các số nguyên
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();  // Đọc dãy số
        }

        HashSet<Integer> seen = new HashSet<>();  // Tập hợp để lưu các phần tử đã xuất hiện

        for (int i = 0; i < n; i++) {
            if (seen.contains(arr[i])) {
                System.out.println(1);  // In 1 nếu phần tử đã xuất hiện trước đó
            } else {
                System.out.println(0);  // In 0 nếu phần tử chưa xuất hiện
                seen.add(arr[i]);  // Thêm phần tử vào tập hợp
            }
        }

        sc.close();
    }
}