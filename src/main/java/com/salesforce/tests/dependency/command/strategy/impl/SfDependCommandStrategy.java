package com.salesforce.tests.dependency.command.strategy.impl;

import com.salesforce.tests.dependency.bo.SfDependencyBo;
import com.salesforce.tests.dependency.command.strategy.SfCommandStrategy;
import com.salesforce.tests.dependency.exception.SfRuntimeException;
import com.salesforce.tests.dependency.model.SfProgram;

import java.util.HashSet;
import java.util.Set;

/**
 * Concrete implementation of Depend command
 *
 * @author Bhargav
 * @since 05/09/2018
 */
public class SfDependCommandStrategy extends SfAbstractCommandStrategy implements SfCommandStrategy {

    @Override
    public String execute(String[] arguments) throws SfRuntimeException {

        // do data validation
        // for DEPEND command, it needs to have 3 arguments atleast
        if (arguments.length < 3) {
            throw new SfRuntimeException("Incorrect number of arguments provided");
        }

        // create source program
        SfProgram parent = new SfProgram(arguments[1]); // argument of 1 is source program

        // create dependencies set
        Set<SfProgram> programs = new HashSet<SfProgram>();
        for (int i = 2; i <= arguments.length;i++) {
            programs.add(new SfProgram(arguments[i]));
        }

        // call method on BO
        getDependencyBo().dependsOn(parent, programs);

        return null;
    }
}
