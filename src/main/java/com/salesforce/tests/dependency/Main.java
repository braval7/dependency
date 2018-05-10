package com.salesforce.tests.dependency;

import com.salesforce.tests.dependency.command.SfCommandFactory;
import com.salesforce.tests.dependency.command.strategy.SfCommandStrategy;
import com.salesforce.tests.dependency.exception.SfRuntimeException;

import java.util.Scanner;

/**
 * The entry point for the Test program
 */
public class Main {

    public static void main(String[] args) throws SfRuntimeException {

        SfCommandFactory factory = SfCommandFactory.intialize();
        //read input from stdin
        Scanner scan = new Scanner(System.in);

        while (true) {
            String line = scan.nextLine();

            //no action for empty input
            if (line == null || line.length() == 0) {
                continue;
            }

            //the END command to stop the program
            if ("END".equals(line)) {
                System.out.println("END");
                break;
            }

            // print the input line
            System.out.println(line);
            // split the input with SPACE
            // and call factory to perform action based on the given input
            factory.execute(line.split("\\s+"));
        }
    }
}