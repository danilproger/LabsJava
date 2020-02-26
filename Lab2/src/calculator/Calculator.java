package calculator;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Calculator {
    public void calculate(String fileName) {
        Scanner scanner = fileName.equals("") ? new Scanner(System.in) : new Scanner(fileName);

        while(scanner.hasNextLine()){
            String commandName;
            List<String> args;
            List<String> words = Arrays.asList(scanner.nextLine().split("\\s+"));

            commandName = words.get(0);
            args = words.subList(1, words.size()-1);
        }

    }
}
