/**
 * Handles the logic behind fulfilling commands received by the chatbot
 *
 * @author Duntorah
 */
class BotCommands {
    private String TRIGGER;

    /**
     * @param trigger The trigger word the bot looks for in a message
     */
    BotCommands(String trigger) {
        TRIGGER = trigger;
    }

    /**
     * Determines what information to display based on the command given
     *
     * @param message the command received
     * @return Internal simplification of the different command types
     */
    String parseCommand(String message) {
        String[] commands = message.split(" ");

        if (commands[1].equalsIgnoreCase("help"))
            return "help";
        else {
            if (commands[1].matches("[IM]") && commands[2].matches("[0-9]{5}"))
                return "weather";
            if (commands[1].matches("temperature"))
                return "temperature";
            if (commands[1].matches("ISS"))
                return "ISS";
            else
                return "unknown";
        }
    }

    /**
     * Returns true if the given String is the name of the bot
     *
     * @param message The message being tested
     * @return True if the message is a command
     */
    boolean isCommand(String message) {
        return message.matches(".* " + TRIGGER + " .*");
    }
}
