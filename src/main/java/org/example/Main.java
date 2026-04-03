package org.example;


import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        AIController controller = new AIController();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Mental Health AI started. Type 'exit' to quit.\n");

        while (true) {
            System.out.print("You: ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Goodbye.");
                break;
            }

            String response = controller.handleUserInput(input);

            System.out.println("AI: " + response + "\n");
        }

        scanner.close();
    }
}