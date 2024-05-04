import java.util.*;

public class Main {
    public static void main(String[] args) {
        double currentOutstandingAmount = 1029230;
        double annualInterestRate = 8.5;
        double loanTermYears = 2;
        double monthlyPayment = 11204;
        double extraMonthlyPayment = 20000;
        double percentageIncrease = 10; // 10% increase in extra monthly payment
        double totalPaid = 0;
        double totalInterest = 0;
        double extraPaymentPerYear = 0;

        for (int month = 1; month <= 12 * loanTermYears; month++) {
            double monthlyInterest = calculateMonthlyInterest(currentOutstandingAmount, annualInterestRate);
            totalInterest += monthlyInterest;

            currentOutstandingAmount += monthlyInterest;
            currentOutstandingAmount -= (monthlyPayment + extraMonthlyPayment);
            totalPaid += (monthlyPayment + extraMonthlyPayment);

            System.out.printf("For month %d: Remaining balance is %.2f and interest is %.2f and monthly payment is %.2f%n", month, currentOutstandingAmount, monthlyInterest, (monthlyPayment + extraMonthlyPayment));

            // Calculate the new extra monthly payment after percentage increase
            if (month % 6 == 0) {
                extraMonthlyPayment = increaseExtraPayment(extraMonthlyPayment, percentageIncrease);
                currentOutstandingAmount -= extraPaymentPerYear;
                totalPaid += extraPaymentPerYear;
                System.out.printf("Total amount paid is %.2f and total interest increased is %.2f %n", totalPaid, totalInterest);
            }
        }

        System.out.printf("Net remaining amount after %.2f years: %.2f%n", loanTermYears, currentOutstandingAmount);
        System.out.printf("Total paid in %.2f years: %.2f%n", loanTermYears, totalPaid);
        System.out.printf("Total interest paid in %.2f years: %.2f%n", loanTermYears, totalInterest);
        System.out.printf("Total outstanding + interest paid in %.2f years: %.2f%n", loanTermYears, currentOutstandingAmount + totalInterest);
    }

    public static double calculateMonthlyInterest(double principal, double annualInterestRate) {
        // Convert annual interest rate to monthly interest rate
        double monthlyInterestRate = annualInterestRate / 12.0 / 100.0; // Assuming interest rate is in percentage

        // Calculate interest for one month
        return principal * monthlyInterestRate;
    }

    public static double increaseExtraPayment(double extraPayment, double percentageIncrease) {
        // Calculate the increase in extra payment
        double increaseAmount = extraPayment * (percentageIncrease / 100);

        // Add the increase amount to the existing extra payment
        extraPayment += increaseAmount;

        return extraPayment;
    }
}
