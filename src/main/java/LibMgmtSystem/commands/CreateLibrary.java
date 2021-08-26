package LibMgmtSystem.commands;


import LibMgmtSystem.manager.UseCaseManager;

public class CreateLibrary extends CommandExecutor {
    public CreateLibrary(UseCaseManager ucManager, String[] inputArgs) {
        super(ucManager, inputArgs);
    }

    @Override
    public void execute() {
        int noRacks = Integer.parseInt(inputArgs[1]);
        ucManager.createLibrary(noRacks);
        System.out.println("Created library with " + noRacks + "number of racks");
    }
}
