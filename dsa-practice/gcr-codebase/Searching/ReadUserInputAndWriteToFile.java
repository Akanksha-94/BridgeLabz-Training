/**
 * InputStreamReader Problem 2: Read User Input and Write to File Using InputStreamReader
 * 
 * Problem:
 * Write a program that uses InputStreamReader to read user input from the console 
 * and write the input to a file. Each input should be written as a new line in the file.
 * 
 * Approach:
 * 1. Create an InputStreamReader to read from System.in (the console).
 * 2. Wrap the InputStreamReader in a BufferedReader for efficient reading.
 * 3. Create a FileWriter to write to the file.
 * 4. Read user input using readLine() and write the input to the file.
 * 5. Repeat the process until the user enters "exit" to stop inputting.
 * 6. Close the file after the input is finished.
 */

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.IOException;

public class ReadUserInputAndWriteToFile {
    
    /**
     * Reads user input from console and writes to file
     * Continues until user enters "exit"
     * @param filePath the path to the output file
     */
    public static void readInputAndWriteToFile(String filePath) {
        BufferedReader consoleReader = null;
        FileWriter fileWriter = null;
        int lineCount = 0;
        
        try {
            // Create InputStreamReader for console input
            InputStreamReader inputStreamReader = new InputStreamReader(System.in);
            consoleReader = new BufferedReader(inputStreamReader);
            
            // Create FileWriter for file output
            fileWriter = new FileWriter(filePath, true); // append mode
            
            System.out.println("=== Read User Input and Write to File ===");
            System.out.println("Enter your input (type 'exit' to stop):");
            System.out.println();
            
            String userInput;
            while (true) {
                System.out.print("Input " + (lineCount + 1) + ": ");
                userInput = consoleReader.readLine();
                
                if (userInput == null || userInput.equalsIgnoreCase("exit")) {
                    System.out.println("\nExiting input mode...");
                    break;
                }
                
                // Write to file
                fileWriter.write(userInput + "\n");
                fileWriter.flush(); // Ensure data is written
                lineCount++;
                System.out.println("✓ Written to file");
            }
            
            System.out.println();
            System.out.println("Total lines written: " + lineCount);
            System.out.println("File saved at: " + filePath);
            
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            try {
                if (consoleReader != null) {
                    consoleReader.close();
                }
                if (fileWriter != null) {
                    fileWriter.close();
                }
            } catch (IOException e) {
                System.out.println("Error closing resources: " + e.getMessage());
            }
        }
    }
    
    /**
     * Reads user input with validation and writes to file
     * @param filePath the path to the output file
     * @param maxLines maximum number of lines to accept
     */
    public static void readInputWithLimit(String filePath, int maxLines) {
        BufferedReader consoleReader = null;
        FileWriter fileWriter = null;
        int lineCount = 0;
        
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(System.in);
            consoleReader = new BufferedReader(inputStreamReader);
            fileWriter = new FileWriter(filePath);
            
            System.out.println("=== Read User Input (Limited) ===");
            System.out.println("Enter your input (maximum " + maxLines + " lines, type 'exit' to stop):");
            System.out.println();
            
            String userInput;
            while (lineCount < maxLines) {
                System.out.print("Input " + (lineCount + 1) + ": ");
                userInput = consoleReader.readLine();
                
                if (userInput == null || userInput.equalsIgnoreCase("exit")) {
                    System.out.println("\nExiting input mode...");
                    break;
                }
                
                if (userInput.trim().isEmpty()) {
                    System.out.println("Empty input skipped.");
                    continue;
                }
                
                fileWriter.write(userInput + "\n");
                fileWriter.flush();
                lineCount++;
                System.out.println("✓ Written to file");
            }
            
            System.out.println();
            System.out.println("Total lines written: " + lineCount);
            System.out.println("File saved at: " + filePath);
            
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            try {
                if (consoleReader != null) {
                    consoleReader.close();
                }
                if (fileWriter != null) {
                    fileWriter.close();
                }
            } catch (IOException e) {
                System.out.println("Error closing resources: " + e.getMessage());
            }
        }
    }
    
    /**
     * Displays the contents of the output file
     * @param filePath the path to the file to display
     */
    public static void displayFileContents(String filePath) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new java.io.FileReader(filePath));
            String line;
            int lineNumber = 1;
            
            System.out.println("\n" + "=".repeat(50));
            System.out.println("File Contents: " + filePath);
            System.out.println("=".repeat(50));
            
            while ((line = reader.readLine()) != null) {
                System.out.println(lineNumber + ": " + line);
                lineNumber++;
            }
            
            System.out.println("=".repeat(50));
            
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                System.out.println("Error closing file: " + e.getMessage());
            }
        }
    }
    
    /**
     * Main method to test user input and file writing functionality
     */
    public static void main(String[] args) {
        String outputFilePath = "user_input.txt";
        
        System.out.println("This program will guide you through reading user input");
        System.out.println("and writing it to a file using InputStreamReader.\n");
        
        // Uncomment the desired option:
        
        // Option 1: Read unlimited input until user types "exit"
        readInputAndWriteToFile(outputFilePath);
        
        // Option 2: Read limited input (maximum 5 lines)
        // readInputWithLimit(outputFilePath, 5);
        
        // Display file contents
        displayFileContents(outputFilePath);
    }
}
