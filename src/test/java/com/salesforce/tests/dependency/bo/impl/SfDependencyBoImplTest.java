package com.salesforce.tests.dependency.bo.impl;

import com.salesforce.tests.dependency.exception.SfRuntimeException;
import com.salesforce.tests.dependency.model.SfProgram;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Test for the SfDependencyBoImpl
 *
 * @author Bhargav
 */
public class SfDependencyBoImplTest {

    private SfDependencyBoImpl boImpl = new SfDependencyBoImpl();

    private SfProgram programA, programB, programC;

    @Before
    public void setupData() {
        programA = new SfProgram("Program A");
        programB = new SfProgram("Program B");
        programC = new SfProgram("Program C");
    }


    /**
     * Unit test that tests 3 scenario of nested dependencies
     * DEPENDS
     * // A depends on B
     * // A depends on C
     *
     * INSTALL A
     * // should install B and C
     *
     * LIST
     * // should return currently installed programs
     *
     */
    @Test
    public void dependOn_installs_all_nested_programs() {
        // create data
        Set<SfProgram> dependencies = new HashSet<SfProgram>();
        dependencies.add(programB);
        dependencies.add(programC);

        // call method on BO
        try {
            boImpl.dependsOn(programA, dependencies);
            boImpl.installProgram(programA);

        } catch (SfRuntimeException e) {
            fail("Exception occurred, this should not happen");
            e.printStackTrace();
        }

        // assert and validate the result
        assertEquals("Wrong number of programs were installed", 3, boImpl.list());
    }
}
