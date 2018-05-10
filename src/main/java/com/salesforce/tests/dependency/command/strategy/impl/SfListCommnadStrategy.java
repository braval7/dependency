package com.salesforce.tests.dependency.command.strategy.impl;

import com.salesforce.tests.dependency.command.strategy.SfCommandStrategy;
import com.salesforce.tests.dependency.exception.SfRuntimeException;
import com.salesforce.tests.dependency.model.SfProgram;

import java.util.Set;

/**
 * Concrete implementation of List command
 *
 * @author Bhargav
 * @since 05/09/2018
 */
public class SfListCommnadStrategy extends SfAbstractCommandStrategy implements SfCommandStrategy {

    @Override
    public String execute(String[] arguments) throws SfRuntimeException {

        // do data validation
        // for LIST command, it needs to have 1 argument
        if (arguments.length != 2) {
            throw new SfRuntimeException("Incorrect number of arguments provided");
        }

        // get currently installed programs
        Set<SfProgram> installed = getDependencyBo().list();

        StringBuffer response = new StringBuffer();
        for (SfProgram program : installed) {
            response.append("\t").append(program.getProgramName()).append("\n");
        }

        return response.toString();
    }
}
