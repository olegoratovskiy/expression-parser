package expression;

import expression.exceptions.EvaluatingException;
import expression.exceptions.ExpressionParser;
import expression.exceptions.ParsingException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            System.out.print("Enter value for variable x: ");
            int x = in.nextInt();
            System.out.print("Enter value for variable y: ");
            int y = in.nextInt();
            System.out.print("Enter value for variable z: ");
            int z = in.nextInt();
            System.out.print("Enter expression: ");
            in.nextLine();
            String expression = in.nextLine();
            ExpressionParser parser = new ExpressionParser();
            int evaluationResult = parser.parse(expression).evaluate(x, y, z);
            String toMiniStringResult = parser.parse(expression).toMiniString();
            System.out.println("Evaluation result: " + evaluationResult);
            System.out.println("ToMiniString result: " + toMiniStringResult);
        } catch (InputMismatchException e) {
            System.out.println("Incorrect input");
        } catch (EvaluatingException e) {
            System.out.println("Problem during evaluation: " + e.getMessage());
        } catch (ParsingException e) {
            System.out.println("Problem during parsing: " + e.getMessage());
        }
    }
}