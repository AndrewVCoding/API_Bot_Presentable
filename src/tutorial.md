# Chatbot/API Java Tutorial

This is a simple IRC chatbot that tells you the weather and the current coordinates of the International Space Station. Pircbot is used as the chatbot interface and gson is used to parse the json strings received from the API.

## Setup

### libraries used:

* [gson][0]
* [PircBot][1]

### Tools used:

* [JSON Lite][2] - A browser addon to format JSON Strings for easier viewing
* [IntelliJ][3] - My IDE of choice for Java

### APIs used:

* [open weather][4]
* [iss position][5]

## IRC

[Internet Relay chat (IRC)][6] is a protocol for online text communication created in 1988. A server running IRC can contain multiple chatrooms that a client may connect to and talk with other users connected to the chat room or by private message. The basic concept is somewhat similar to Discord and Slack, but without all the bells and whistles. 

In order to connect to an IRC network, you will need to either install an IRC or connect to a network through a browser client.

[Freenode][7] is the most populated network and they include an in-browser client to connect to.

If you want to download a client, [hexchat][8] is my personal choice. [Pidgin][9] is also a good chat client that supports more than just IRC.

Once you have a way to connect to an IRC network, you can either join an existing channel or create new one. If you try to join a channel that doesn't exist, Freenode will create a new channel for you.

I've embedded a client here with #SheepBotsTesting as the default channel. This is the channel that I will be connecting my own bots to for public testing.


<iframe src="https://webchat.freenode.net/#SheepBotsTeting" style="border:0; width:100%; height:500px;"></iframe>

## API

[Application Programming Interface (API)][10] is simply a set of methods for communicating between different applications. In this project, we'll just be dealing with simple API requests that return a Json string and don't require anything more than a simple API key.
If you want to find more public APIs to use, [toddmotto][11] has a large repository of them [here][12].

## JSON

[0]: https://mvnrepository.com/artifact/com.google.code.gson/gson/2.8.5
[1]: http://www.jibble.org/pircbot.php
[2]: https://github.com/lauriro/json-lite
[3]: https://www.jetbrains.com/idea/
[4]: https://openweathermap.org/
[5]: http://open-notify.org/Open-Notify-API/ISS-Location-Now/
[6]: https://en.wikipedia.org/wiki/Internet_Relay_Chat
[7]: https://freenode.net/
[8]: https://hexchat.github.io/
[9]: https://pdgin.im
[10]: https://en.wikipedia.org/wiki/Application_programming_interface
[11]: https://github.com/toddmotto
[12]: https://github.com/toddmotto/public-apis