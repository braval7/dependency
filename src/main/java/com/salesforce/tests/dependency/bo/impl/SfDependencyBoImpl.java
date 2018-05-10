package com.salesforce.tests.dependency.bo.impl;

import com.salesforce.tests.dependency.bo.SfDependencyBo;
import com.salesforce.tests.dependency.exception.SfRuntimeException;
import com.salesforce.tests.dependency.model.SfProgram;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Business logic to handle the dependencies of the system
 *
 * @author Bhargav
 */
public class SfDependencyBoImpl implements SfDependencyBo {

    // maintain all incoming dependencies for quicker lookup
    private Map<SfProgram, Set<SfProgram>> incomingDep;
    private Set<SfProgram> currentlyInstalled;

    // default constructor
    public SfDependencyBoImpl() {
        incomingDep = new HashMap<SfProgram, Set<SfProgram>>();
        currentlyInstalled = new HashSet<SfProgram>();
    }

    @Override
    public void dependsOn(SfProgram program, Set<SfProgram> dependencies) throws SfRuntimeException {
        // do data validation
        if(program == null) {
            throw new SfRuntimeException("Program is null");
        }

        // if program is already installed and we are trying to add depedencies then fail
        if (currentlyInstalled.contains(program)) {
            throw new SfRuntimeException("Can't add dependency to program that's already installed");
        }

        // initialize map for this program
        initializeIncomingDependency(program);

        // loop over all items and add dependencies
        for (SfProgram oneProgram : dependencies) {
            //initialize incoming for each
            initializeIncomingDependency(oneProgram);
            // add dependency to model
            program.addDependency(oneProgram);
            // add incoming dependency
            incomingDep.get(oneProgram).add(program);
        }
    }

    @Override
    public Set<SfProgram> list() {
        return currentlyInstalled;
    }

    @Override
    public Set<SfProgram> installProgram(SfProgram program) throws SfRuntimeException {

        // do null check

        // check if program is already installed, in that case throw error
        if (currentlyInstalled.contains(program)) {
            throw new SfRuntimeException("Program is already installed");
        }

        Set<SfProgram> allDependentPrograms = new HashSet<SfProgram>();
        addAllDependencies(program, allDependentPrograms);

        return allDependentPrograms;
    }

    @Override
    public Set<SfProgram> removeProgram(SfProgram program) throws SfRuntimeException {
        // check if it's installed program
        // check if program is not installed, in that case throw error
        if (!currentlyInstalled.contains(program)) {
            throw new SfRuntimeException("Program is not installed");
        }

        // TODO logic for removal

        return null;
    }

    /**
     * Check of nested dependencies and make sure to install them one by one
     * @param program
     * @param allDependentPrograms
     * @throws SfRuntimeException
     */
    protected void addAllDependencies(SfProgram program, Set<SfProgram> allDependentPrograms) throws SfRuntimeException {
        allDependentPrograms.add(program);

        for (SfProgram dependency : program.getDependencies()) {
            if (!currentlyInstalled.contains(dependency)) {
                if (allDependentPrograms.contains(dependency)) {
                    throw new SfRuntimeException("Discovered loop in dependency when installing program.");
                }
                addAllDependencies(dependency, allDependentPrograms);
            }
        }
        currentlyInstalled.add(program);
    }

    protected void initializeIncomingDependency(SfProgram program) {
        if (!incomingDep.containsKey(program)) {
            incomingDep.put(program, new HashSet<SfProgram>());
        }
    }
}
