package com.salesforce.tests.dependency.command;

import com.salesforce.tests.dependency.bo.SfDependencyBo;
import com.salesforce.tests.dependency.bo.impl.SfDependencyBoImpl;
import com.salesforce.tests.dependency.command.strategy.SfCommandStrategy;
import com.salesforce.tests.dependency.command.strategy.impl.SfDependCommandStrategy;
import com.salesforce.tests.dependency.command.strategy.impl.SfInstallCommandStrategy;
import com.salesforce.tests.dependency.command.strategy.impl.SfListCommnadStrategy;
import com.salesforce.tests.dependency.command.strategy.impl.SfRemoveCommandStrategy;
import com.salesforce.tests.dependency.exception.SfRuntimeException;

import java.util.HashMap;
import java.util.Map;

import static com.salesforce.tests.dependency.command.SfCommands.DEPEND;

/**
 * Factory to create command objects based on the first argument
 *
 * @author Bhargav
 */
public class SfCommandFactory {

    private Map<SfCommands, SfCommandStrategy> commands;


    public void addCommand(SfCommands command, SfCommandStrategy strategy) {
        commands.put(command, strategy);
    }

    public SfCommandFactory() {
        commands = new HashMap<SfCommands, SfCommandStrategy>();
    }

    public static SfCommandFactory intialize() {
        // initialize the data
        SfCommandFactory factory = new SfCommandFactory();
        SfDependencyBo sfDependencyBo = new SfDependencyBoImpl();

        // add dependency strategy
        SfCommandStrategy dependCommandStrategy = new SfDependCommandStrategy();
        dependCommandStrategy.setDependencyBo(sfDependencyBo);
        factory.addCommand(SfCommands.DEPEND, dependCommandStrategy);

        // add install strategy
        SfCommandStrategy installCommandStrategy = new SfInstallCommandStrategy();
        installCommandStrategy.setDependencyBo(sfDependencyBo);
        factory.addCommand(SfCommands.INSTALL, installCommandStrategy);

        // add list strategy
        SfCommandStrategy listCommandStrategy = new SfListCommnadStrategy();
        listCommandStrategy.setDependencyBo(sfDependencyBo);
        factory.addCommand(SfCommands.LIST, listCommandStrategy);

        // add remove strategy
        SfCommandStrategy removeCommandStrategy = new SfRemoveCommandStrategy();
        removeCommandStrategy.setDependencyBo(sfDependencyBo);
        factory.addCommand(SfCommands.REMOVE, removeCommandStrategy);

        return factory;
    }

    public String execute(String[] args) throws SfRuntimeException {

        // first argument will always be command
        SfCommands command = SfCommands.valueOf(args[0]);

        // lookup in the map to see what action needs to be performed
        // and call strategy.execute for that
        if (command != null) {
            return commands.get(command).execute(args);
        }

        return "Unsupported command found";
    }
}
