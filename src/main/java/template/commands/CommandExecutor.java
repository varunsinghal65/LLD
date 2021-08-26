package template.commands;

import template.manager.UseCaseManager;

public abstract class CommandExecutor {

    //this will be called by the sub-class to execute commands.
    private final UseCaseManager ucManager;
    private final String[] inputArgs;

    public CommandExecutor(UseCaseManager ucManager, String[] inputArgs) {
        this.ucManager = ucManager;
        this.inputArgs = inputArgs;
    }

    public abstract void execute();

}
