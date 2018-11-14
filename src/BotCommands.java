import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Handles the logic behind fulfilling commands received by the chatbot
 *
 * @author Duntorah
 */
class BotCommands {
    private String TRIGGER;
    private static final Pattern p = Pattern.compile("^((?i)temperature|weather|iss) ((?i)[im]) ([0-9]{5})(.*)");
    private static final Pattern request_type = Pattern.compile("((?i)temperature|weather|iss|help)");
    private static final Pattern units = Pattern.compile("( [im] )");
    private static final Pattern zipcode = Pattern.compile("([0-9]{5})");


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
    Command parseCommand(String user, String message) {
        String com = "unkown";
        String unit = "i";
        String zip = "";
        Matcher m_com_type = request_type.matcher(message);
        Matcher m_units = units.matcher(message);
        Matcher m_zipcode = zipcode.matcher(message);

        if (m_com_type.find())
            com = m_com_type.group(0).replaceAll(" ", "");
        if (m_units.find())
            unit = m_units.group(0).replaceAll(" ", "");
        if (m_zipcode.find())
            zip = m_zipcode.group(0).replaceAll(" ", "");

        return new Command(user, message, com, zip, unit);
    }

    /**
     * Returns true if the given String is the name of the bot
     *
     * @param message The message being tested
     * @return True if the message is a command
     */
    boolean isCommand(String message) {
        return message.matches("(?i).*" + TRIGGER + ".*");
    }

    String getTRIGGER(){
        return TRIGGER;
    }
}
