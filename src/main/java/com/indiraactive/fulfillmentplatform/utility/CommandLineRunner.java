package com.indiraactive.fulfillmentplatform.utility;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.springframework.stereotype.Component;

/**
 * Runs a command on the host system's shell. This class needs to be enhanced.
 */
@Component
public class CommandLineRunner {
    /**
     * Enums used to define how the shell finished running a given command
     */
    public enum CompletionErrorType {
        SUCCESS,
        FAILURE
    }

    /**
     * Runs a given command in the shell and captures whether or not it fails.
     * @param commandLineText Command to run in system's shell
     * @return CompletionErrorType of success or failure
     */
    public CompletionErrorType executeCommand(String commandLineText) {
        Process process;
        try {
            process = Runtime.getRuntime().exec(commandLineText);
            if (process != null) {
                int errorCode = process.waitFor();

                String line;
                BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
                while ((line = input.readLine()) != null) {
                    System.out.println("***FROM CONSOLE: " + line);
                }
                input.close();

                if (errorCode == 0) {
                    return CompletionErrorType.SUCCESS;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return CompletionErrorType.FAILURE;
    }
}
