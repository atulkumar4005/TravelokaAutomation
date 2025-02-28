package Utils;
import java.util.Scanner;


public class capchaHandler {
    public static void waitForCaptcha() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Solve the CAPTCHA manually, then press Enter to continue...");
        scanner.nextLine();  
        System.out.println("Continuing execution after CAPTCHA resolution...");
    }
}
