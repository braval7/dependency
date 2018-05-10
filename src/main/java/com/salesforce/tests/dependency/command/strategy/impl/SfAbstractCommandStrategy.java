package com.salesforce.tests.dependency.command.strategy.impl;

import com.salesforce.tests.dependency.bo.SfDependencyBo;

/**
 * Abstract command strategy
 *
 * @author Bhargav
 *
 * @since 05/09/2018
 */
public class SfAbstractCommandStrategy {

    private SfDependencyBo dependencyBo;

    public void setDependencyBo(SfDependencyBo dependencyBo) {
        this.dependencyBo = dependencyBo;
    }

    public SfDependencyBo getDependencyBo() {
        return dependencyBo;
    }
}
