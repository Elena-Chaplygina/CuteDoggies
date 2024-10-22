import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {


        Scanner scanner = new Scanner(System.in);
        System.out.print("Пожалуйста, введите породу собаки: ");
        String inputText = scanner.nextLine();


        ImageUploader.downloadFileToYaDisk(inputText);

    }
}
