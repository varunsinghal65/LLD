package LibMgmtSystem;

import LibMgmtSystem.commands.CommandExecutor;
import LibMgmtSystem.commands.CommandExecutorFactory;
import LibMgmtSystem.manager.UseCaseManager;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;


//Problem : https://workat.tech/machine-coding/practice/design-library-management-system-jgjrv8q8b136
public class Client {

    private final static String COMMAND_SEPARATOR = " ";
    private final static UseCaseManager ucManager = new UseCaseManager();


    public static void main(String[] args) {
        while(true) {
            Scanner scanner = new Scanner(new InputStreamReader(System.in));
            String input = scanner.nextLine();
            String[] inputArgs = input.split(COMMAND_SEPARATOR);
            Arrays.stream(inputArgs).forEach(command->command = command.toLowerCase());

            //parse command and execute
            String command = inputArgs[0];
            CommandExecutor commandExecutor = CommandExecutorFactory.getInstance(command, ucManager, inputArgs);
            commandExecutor.execute();
        }
    }

}
