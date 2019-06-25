class Controller {

    void controller() {


        System.out.println(Text.ENTER_COMMAND);
        Commands commands = new Commands();
        String menuCommand = commands.menuCommand();

        while (!menuCommand.equals("exit")) {
            switch (menuCommand) {
                case "init array":
                    commands.initArray(commands.numbersEnteredHelper());
                    break;
                case "print":
                    commands.print(commands.map, commands.paramsXSM());
                    break;
                case "anyMore":
                    commands.anyMore(commands.list);
                    break;
                case "clear":
                    commands.clear(commands.map, commands.paramsXSM());
                    break;
                case "merge":
                    commands.merge(commands.map, commands.list);
                    break;
                case "help":
                    commands.help();
                    break;
                default:
                    System.out.println(Text.ERROR);
                    break;
            }
            menuCommand = commands.menuCommand();
        }
        System.out.println(Text.EXIT);
    }
}
