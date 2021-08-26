package LibMgmtSystem.commands;


import LibMgmtSystem.manager.UseCaseManager;

public abstract class CommandExecutor {

    //this will be called by the sub-class to execute commands.
    protected final UseCaseManager ucManager;
    protected final String[] inputArgs;

    public CommandExecutor(UseCaseManager ucManager, String[] inputArgs) {
        this.ucManager = ucManager;
        this.inputArgs = inputArgs;
    }

    public abstract void execute();

}
