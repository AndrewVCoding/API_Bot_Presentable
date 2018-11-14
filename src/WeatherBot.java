import org.jibble.pircbot.PircBot;

import java.io.IOException;

/**
 * The bot class itself
 * 
 * @author andrew
 *
 */
public class WeatherBot extends PircBot
{
	private String		NAME;
	private BotCommands	COMMANDS;
	private APIclass	DATA;
	private String		CHANNEL;

    /**
     * Initialize the weather bot
     *
     * @param name The name of the weatherbot will also be the command trigger
     */
	WeatherBot(String name)
	{
		// Set the name of the chatbot to Weatherbot
		NAME = name;
		this.setName(NAME);

		// Instantiate the commands object
		COMMANDS = new BotCommands(NAME);
		DATA = new APIclass();
	}

    /**
     * Receives messages from the IRC channel and if they start with WEATHERBOT, then it parses it
     * 	 as a command and responds accordingly by sending a message
     *
     * @param channel The channel to send the message to
     * @param sender The user sending the command
     * @param login login
     * @param hostname hostname
     * @param message Message being received
     */
	public void onMessage(String channel, String sender, String login, String hostname, String message)
	{
		Command command;

		// If message is a command directed at weatherbot, parse the information in the command to
		// determine the query
		if (COMMANDS.isCommand(message))
		{
			command = COMMANDS.parseCommand(sender, message.replaceAll("(?i)" + COMMANDS.getTRIGGER(), ""));

			// UNKNOWN COMMAND
			if (command.equalsIgnoreCase("unknown"))
			{
				sendMessage(CHANNEL, sender + ": Sorry, but I didn't recognise that command.");
			}

			else if (command.equalsIgnoreCase("help"))
                help(channel);

			else
			{
				// Retrieve data from the api
				try
				{
					// WEATHER
					if (command.equalsIgnoreCase("weather"))
						giveWeather(channel, command.user, command.zipcode, command.units);

					// TEMPERATURE
					if (command.equalsIgnoreCase("temperature"))
						giveTemperature(channel, sender, command.zipcode, command.units);
					// ISS
					if (command.equalsIgnoreCase("iss"))
						giveISS(channel, sender);
				}
				catch (IOException e)
				{
					sendMessage(CHANNEL, sender + ": Sorry, but the weather is currently unavailable.");
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Display the commands the bot can respond to
	 * 
	 * @param channel the channel to send messages to
	 */
    private void help(String channel)
	{
		sendMessage(channel, "Commands: <I/M> specifies Imperial or Metric units");
		sendMessage(channel, "          " + NAME.toUpperCase() + " <I/M> <zipcode>             : Gives a general description of the weather");
		sendMessage(channel, "          " + NAME.toUpperCase() + " temperature <I/M> <zipcode> : Gives the temerature");
		sendMessage(channel,
				"          " + NAME.toUpperCase() + " ISS                         : Gives the current position of the International Space Station");
	}

	/**
	 * Displays the weather
	 * 
	 * @param channel The channel to send messages to
	 * @param sender The name of the user who sent the command
     * @param zipcode The zipcode location
     * @param units The units to use
	 * @throws IOException
	 */
    private void giveWeather(String channel, String sender, String zipcode, String units) throws IOException
	{
		DATA.fetchWeather(zipcode);
		String description = DATA.getWeather(units);
		sendMessage(channel, sender + ": " + description);
	}

	/**
	 * Displays the temperature
	 *
	 * @param channel The channel to send messages to
	 * @param sender The name of the user who sent the command
     * @param zipcode The zipcode location
     * @param units The units to use
	 * @throws IOException
	 */
    private void giveTemperature(String channel, String sender, String zipcode, String units) throws IOException
	{
		DATA.fetchWeather(zipcode);
		sendMessage(channel, sender + ": The current temperature is " + DATA.getTemp(units));
	}

    /**
     * Send a message to the channel with the coordinates of the ISS
     * @param channel The channel to send the message to
     * @param sender The User requesting the information
     * @throws IOException
     */
	private void giveISS(String channel, String sender) throws IOException
	{
		DATA.fetchISS();
		sendMessage(channel, sender + ": The current position of the ISS is " + DATA.getPos());
	}

	/**
	 * Message to send when joining the channel to let users know about the bot
	 * 
	 * @param channel The name of the channel to send messages to
	 */
    void welcome(String channel)
	{
		CHANNEL = channel;
		sendMessage(channel, "Hi, I'm " + NAME + "! To see how to give me commands, type \"" + NAME + " help\"");
	}

}
