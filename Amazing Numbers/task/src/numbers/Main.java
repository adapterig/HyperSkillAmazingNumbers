package numbers;

import java.util.*;

import static numbers.NumbersProperties.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome to Amazing Numbers!");
        Scanner scanner = new Scanner(System.in);
        showInstructions();
        while (true) {
            System.out.println("\nEnter a request: ");
            long number = -1;
            OptionalLong secondNumber = OptionalLong.empty();
            String[] input = scanner.nextLine().split(" ");
            String[] properties;
            if (input.length > 2) {
                properties = new String[input.length - 2];
            } else {
                properties = null;
            }
            if (Objects.equals(input[0], "")) {
                showInstructions();
                continue;
            }
            try {
                number = Long.parseLong(input[0]);
                if (number < 0) {
                    throw new IllegalArgumentException("This number is not natural!");
                } else if (number == 0) {
                    System.out.println("Goodbye!");
                    System.exit(0);
                }
            } catch (RuntimeException e) {
                System.out.println("The first parameter should be a natural number or zero.");
                continue;
            }
            try {
                if (input.length > 1) {
                    secondNumber = OptionalLong.of(Long.parseLong(input[1]));
                }
                if (secondNumber.isPresent() && secondNumber.getAsLong() <= 0) {
                    throw new IllegalArgumentException("The second parameter should be a natural number.");
                }
            } catch (RuntimeException e) {
                System.out.println("The second parameter should be a natural number.");
                continue;
            }
            if (properties != null) {
                List<String> wrongProperties = new ArrayList<>();
                for (int i = 0; i < properties.length; i++) {
                    properties[i] = input[i + 2].toUpperCase();
                    boolean isCorrectProperty = false;
                    for (String s : PROPERTIES) {
                        if (s.equals(properties[i]) || ("-" + s).equals(properties[i])) {
                            isCorrectProperty = true;
                            break;
                        }
                    }
                    if (!isCorrectProperty) {
                        wrongProperties.add(properties[i]);
                    }
                }
                if (wrongProperties.size() > 1) {
                    System.out.println("The properties " + wrongProperties + " are wrong.");
                    System.out.println("Available properties: " + Arrays.toString(PROPERTIES));
                    continue;
                }
                if (wrongProperties.size() == 1) {
                    System.out.println("The property " + wrongProperties + " is wrong.\n" +
                            "Available properties: " + Arrays.toString(PROPERTIES));
                    continue;
                }

                if (checkIsMutuallyExclusiveProperties(Arrays.stream(properties).toList())) {
                    continue;
                }
            }

            showInfo(number, secondNumber, properties);
        }
    }

    private static void showInfo(long number, boolean isInOneLine) {
        boolean isEven = checkIsEven(number);
        boolean isBuzz = checkIsBuzz(number);
        boolean isDuck = checkIsDuck(number);
        boolean isPalindromic = checkIsPalindromic(number);
        boolean isGapful = checkIsGapful(number);
        boolean isSpy = checkIsSpy(number);
        boolean isSquare = checkIsSquare(number);
        boolean isSunny = checkIsSunny(number);
        boolean isJumping = checkIsJumping(number);
        boolean isHappy = checkIsHappy(number);

        if (isInOneLine) {
            System.out.printf("""
                    %d is\s""", number);
            if (isBuzz) {
                System.out.print("buzz, ");
            }

            if (isPalindromic) {
                System.out.print("palindromic, ");
            }
            if (isDuck) {
                System.out.print("duck, ");
            }
            if (isGapful) {
                System.out.print("gapful, ");
            }
            if (isSpy) {
                System.out.print("spy, ");
            }
            if (isSquare) {
                System.out.print("square, ");
            }
            if (isSunny) {
                System.out.print("sunny, ");
            }
            if (isJumping) {
                System.out.print("jumping, ");
            }
            if (isHappy) {
                System.out.print("happy, ");
            } else {
                System.out.print("sad, ");
            }
            if (isEven) {
                System.out.println("even");
            } else {
                System.out.println("odd");
            }
        } else {
            System.out.printf("""
                    Properties of %d
                            even: %b
                             odd: %b
                            buzz: %b
                            duck: %b
                     palindromic: %b
                          gapful: %b
                             spy: %b
                          square: %b
                           sunny: %b
                           jumping: %b
                           happy: %b
                           sad: %b
                    """, number, isEven, !isEven, isBuzz, isDuck, isPalindromic, isGapful, isSpy, isSquare, isSunny,
                    isJumping, isHappy, !isHappy);
        }
    }

    private static void showInfo(long number, OptionalLong secondNumber) {
        //System.out.println("showInfo(long number, OptionalLong secondNumber)");
        if (secondNumber.isEmpty()) {
            showInfo(number, false);
            return;
        }

        for (long i = number; i < number + secondNumber.getAsLong(); i++) {
            showInfo(i, true);
        }
    }

    private static void showInstructions() {
        System.out.println("Supported requests:\n" +
                "- enter a natural number to know its properties;\n" +
                "- enter two natural numbers to obtain the properties of the list:\n" +
                "  * the first parameter represents a starting number;\n" +
                "  * the second parameter shows how many consecutive numbers are to be printed;\n" +
                "- two natural numbers and properties to search for;\n" +
                "- a property preceded by minus must not be present in numbers;\n" +
                "- separate the parameters with one space;\n" +
                "- enter 0 to exit.");

    }

    private static void showInfo(long number, OptionalLong secondNumber, String[] properties) {

        if (properties == null) {
            showInfo(number, secondNumber);
        } else {
            long counter = 0;
            long i = number;
            while (counter < secondNumber.getAsLong()) {
                boolean isNumberToShow = true;
                for (String s : properties) {
                    if (!checkProperty(i, s)) {
                        isNumberToShow = false;
                        break;
                    }
                }
                if (isNumberToShow) {
                    showInfo(i, true);
                    counter++;
                }
                i++;
            }
        }
    }
}
