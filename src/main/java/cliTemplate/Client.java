package cliTemplate;

import cliTemplate.commands.CommandExecutor;
import cliTemplate.commands.CommandExecutorFactory;
import cliTemplate.manager.UseCaseManager;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class Client {

    private final static String COMMAND_SEPARATOR = " ";
    private static final UseCaseManager ucManager = new UseCaseManager();
    
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
