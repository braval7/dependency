package com.salesforce.tests.dependency.bo;

import com.salesforce.tests.dependency.exception.SfRuntimeException;
import com.salesforce.tests.dependency.model.SfProgram;

import java.util.Set;

/**
 * Business object containing core logic for dependencies
 *
 * @author Bhargav
 */
public interface SfDependencyBo {

    /**
     * Method that adds dependencies to program
     *
     * @param program
     * @param dependencies
     */
    void dependsOn(SfProgram program, Set<SfProgram> dependencies) throws SfRuntimeException;

    /**
     * Lists currently installed programs
     *
     * @return
     */
    Set<SfProgram> list();

    /**
     * Install program and all it's dependencies
     *
     * @param program
     * @return
     */
    Set<SfProgram> installProgram(SfProgram program) throws SfRuntimeException;

    /**
     * Remove program and it's dependencies, if possible
     *
     * @param newProgram
     * @return
     */
    Set<SfProgram> removeProgram(SfProgram newProgram) throws SfRuntimeException;
}
