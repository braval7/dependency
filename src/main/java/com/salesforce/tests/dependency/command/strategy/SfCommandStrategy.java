package com.salesforce.tests.dependency.command.strategy;

import com.salesforce.tests.dependency.bo.SfDependencyBo;
import com.salesforce.tests.dependency.exception.SfRuntimeException;

/**
 * Strategy for commands
 *
 * @author Bhargav
 * @since 05/09/2018
 */
public interface SfCommandStrategy {

    String execute(String[] arguments) throws SfRuntimeException;

    public void setDependencyBo(SfDependencyBo dependencyBo);
}
