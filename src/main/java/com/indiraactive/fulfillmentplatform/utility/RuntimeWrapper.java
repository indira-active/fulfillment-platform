package com.indiraactive.fulfillmentplatform.utility;

import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Abstract out the runtime for modularity and testability.
 */
@Component
public class RuntimeWrapper {
    /**
     * Runs a command on the systems runtime
     * @return the process that the command was executed in.
     */
    public Process executeCommand(String commandToExec) throws IOException {
        return Runtime.getRuntime().exec(commandToExec);
    }
}
