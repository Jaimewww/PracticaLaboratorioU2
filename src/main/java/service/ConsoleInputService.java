package service;

import java.util.Scanner;

public class ConsoleInputService implements InputService {
    private final Scanner scanner;

    public ConsoleInputService(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public boolean getBoolean(String prompt) {
        System.out.println(prompt + " (y/n):");
        String input = scanner.nextLine();
        return input.equalsIgnoreCase("y");
    }

    @Override
    public int getInt(String prompt) {
        while (true) {
            try {
                System.out.println(prompt);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    @Override
    public String getString(String prompt) {
        while (true) {
            try{
                System.out.println(prompt);
                return scanner.nextLine();
            }catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + " Please try again.");
            }
        }
    }

    @Override
    public double getDouble(String prompt) {
        while (true) {
            try {
                System.out.println(prompt);
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }
}
