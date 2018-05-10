package com.salesforce.tests.dependency.command.strategy.impl;

import com.salesforce.tests.dependency.command.strategy.SfCommandStrategy;
import com.salesforce.tests.dependency.exception.SfRuntimeException;
import com.salesforce.tests.dependency.model.SfProgram;

import java.util.Set;

/**
 * Concrete implementation of REMOVE command
 *
 * @author Bhargav
 * @since 05/09/2018
 */
public class SfRemoveCommandStrategy extends SfAbstractCommandStrategy implements SfCommandStrategy {

    @Override
    public String execute(String[] arguments) throws SfRuntimeException {
        // do data validation
        // for INSTALL command, it needs to have 2 argument
        if (arguments.length != 2) {
            throw new SfRuntimeException("Incorrect number of arguments provided");
        }

        // REMOVE current program
        SfProgram newProgram = new SfProgram(arguments[1]);
        Set<SfProgram> installed = getDependencyBo().removeProgram(newProgram);

        StringBuffer response = new StringBuffer();
        for (SfProgram program : installed) {
            response.append("\t").append(program.getProgramName()).append("\n");
        }

        return response.toString();
    }
}
