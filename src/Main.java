import java.util.*;

public class Main {
    public static void main(String[] args) {
        double currentOutstandingAmount = 1029230;
        double annualInterestRate = 8.5;
        double years = 1.5;
        double emiAmount = 11204;
        double extraEmiAmount = 18800;
        double percentageIncrease = 30; // 10% increase in extra EMI amount
        double totalPaid = 0;
        double totalInterest = 0;
        double extraPerYear = 0;

        for (int i = 1; i <= 12 * years; i++) {
            double monthlyInterest = calculateMonthlyInterest(currentOutstandingAmount, annualInterestRate);
            totalInterest += monthlyInterest;

            currentOutstandingAmount += monthlyInterest;
            currentOutstandingAmount -= (emiAmount + extraEmiAmount);
            totalPaid += (emiAmount + extraEmiAmount);

            System.out.printf("for month %d : %f  and interest is %f and monthly EMI %f%n", i, currentOutstandingAmount, monthlyInterest, (emiAmount + extraEmiAmount));

            // Calculate the new extra EMI amount after percentage increase
            if (i % 12 == 0) {
                extraEmiAmount = increaseExtraEmi(extraEmiAmount, percentageIncrease);
                currentOutstandingAmount -= extraPerYear;
                totalPaid += extraPerYear;
                System.out.printf("total amount paid is %f and total interest increased is %f %n", totalPaid, totalInterest);
            }
            if (i == 12) {
                currentOutstandingAmount -= 200000;
            }
        }

        System.out.printf("Net remaining amount after %f years: ", years);
        System.out.println(currentOutstandingAmount);
        System.out.printf("Total paid in %f years: ", years);
        System.out.println(totalPaid);
        System.out.printf("Total interest paid in %f years: ", years);
        System.out.println(totalInterest);
        System.out.printf("Total outstanding + interest paid in %f years: ", years);
        System.out.println(currentOutstandingAmount + totalInterest);
    }

    public static double calculateMonthlyInterest(double principal, double annualInterestRate) {
        // Convert annual interest rate to monthly interest rate
        double monthlyInterestRate = annualInterestRate / 12.0 / 100.0; // Assuming interest rate is in percentage

        // Calculate interest for one month
        return principal * monthlyInterestRate;
    }

    public static double increaseExtraEmi(double extraEmiAmount, double percentageIncrease) {
        // Calculate the increase in extra EMI amount
        double increaseAmount = extraEmiAmount * (percentageIncrease / 100);

        // Add the increase amount to the existing extra EMI amount
        extraEmiAmount += increaseAmount;

        return extraEmiAmount;
    }
}
