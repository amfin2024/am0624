package org.company;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ToolRentalApp {
    private static Map<String, Tool> tools = new HashMap<>();

    static {
        tools.put("CHNS", Tool.builder().code("CHNS").type(ToolType.CHAINSAW).brand("Stihl").build());
        tools.put("LADW", Tool.builder().code("LADW").type(ToolType.LADDER).brand("Werner").build());
        tools.put("JAKD", Tool.builder().code("JAKD").type(ToolType.JACKHAMMER).brand("DeWalt").build());
        tools.put("JAKR", Tool.builder().code("JAKR").type(ToolType.JACKHAMMER).brand("Ridgid").build());
    }

    public static RentalAgreement generateRentalAgreement(String toolCode, int rentalDays, double discount, String checkoutDateString) throws ToolRentalException {
        Tool tool = tools.get(toolCode);

        if (tool == null) {
            throw new ToolRentalException("Invalid tool code.");
        }

        if (rentalDays < 1) {
            throw new ToolRentalException("Rental day count must be 1 or greater.");
        }

        if (discount < 0 || discount > 100) {
            throw new ToolRentalException("Discount percent must be between 0 and 100.");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");
        LocalDate checkoutDate;
        try {
            checkoutDate = LocalDate.parse(checkoutDateString, formatter);
        } catch (Exception e) {
            throw new ToolRentalException("Invalid date format. Please use MM/dd/yy.");
        }

        RentalAgreement agreement = new RentalAgreement(tool, checkoutDate, rentalDays, discount);
        return agreement;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter tool code: ");
            String toolCode = scanner.nextLine().toUpperCase();

            System.out.print("Enter rental day count: ");
            int rentalDays = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter discount percent (0-100): ");
            double discount = Double.parseDouble(scanner.nextLine());

            System.out.print("Enter checkout date (MM/dd/yy): ");
            String checkoutDateString = scanner.nextLine();

            RentalAgreement agreement = generateRentalAgreement(toolCode, rentalDays, discount, checkoutDateString);
            System.out.println("\n Below is the Rental Agreement: ");
            agreement.printAgreement();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
