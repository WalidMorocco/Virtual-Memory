import java.util.Scanner;

public class PageFrame {
	private int numPageFrames;

    public PageFrame() {
        try (Scanner scanner = new Scanner(System.in)) {
			System.out.print("Enter the number of page frames (2-7): ");
			while (true) {
			    try {
			        int num = Integer.parseInt(scanner.nextLine());
			        if (num >= 2 && num <= 7) {
			            numPageFrames = num;
			            break;
			        } else {
			            System.out.print("Please enter a number between 2 and 7: ");
			        }
			    } catch (NumberFormatException e) {
			        System.out.print("Invalid input, please enter a number between 2 and 7: ");
			    }
			}
		}
    }

    public int getNumPageFrames() {
        return numPageFrames;
    }
}
