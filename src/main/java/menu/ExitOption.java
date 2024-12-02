package menu;

public class ExitOption implements MenuCommand {
    @Override
    public void execute() {
        System.out.println("Exiting program...");
        System.exit(0);
    }
}
