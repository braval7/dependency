package com.salesforce.tests.dependency.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Individual component which will be installed or removed
 * from the system
 *
 * @author Bhargav
 * @since 05/09/2018
 */
public class SfProgram {

    // name of the program i.e TELNET
    private String programName;

    // list of Programs required
    private Set<SfProgram> dependencies;

    public SfProgram(String programName) {
        this.programName = programName;
        dependencies = new HashSet<SfProgram>();
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public Set<SfProgram> getDependencies() {
        return dependencies;
    }

    public void setDependencies(Set<SfProgram> dependencies) {
        this.dependencies = dependencies;
    }

    public boolean addDependency(SfProgram program) {
        return dependencies.add(program);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SfProgram sfProgram = (SfProgram) o;

        if (programName != null ? !programName.equals(sfProgram.programName) : sfProgram.programName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return programName != null ? programName.hashCode() : 0;
    }
}
