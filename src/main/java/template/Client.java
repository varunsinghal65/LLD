package template;

import template.commands.CommandExecutor;
import template.commands.CommandExecutorFactory;
import template.manager.UseCaseManager;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class Client {

    private final static String COMMAND_SEPARATOR = " ";

    public static void main(String[] args) {
        while(true) {
            Scanner scanner = new Scanner(new InputStreamReader(System.in));
            String input = scanner.nextLine();
            String[] inputArgs = input.split(COMMAND_SEPARATOR);
            Arrays.stream(inputArgs).forEach(command->command = command.toLowerCase());

            //parse command and execute
            String command = inputArgs[0];
            UseCaseManager ucManager = new UseCaseManager();
            CommandExecutor commandExecutor = CommandExecutorFactory.getInstance("commandName", ucManager, inputArgs);
            commandExecutor.execute();
        }
    }

}
