# Iron Riders discord bot (JDA)
The Discord bot in the Iron Riders' discord server.

### How to execute:
* Clone GitHub in intellij.
* Create a run configuration that runs `org.ironriders.discordbot.Bot`.
  * Paste text below into the `VM options` in that configuration.
  ```properties
  -DBOT_TOKEN=BOT_TOKEN
  -DTBA_TOKEN=TBA_TOKEN
  ```
  * Replace `BOT_TOKEN` with your bot token.
  * Replace `TBA_TOKEN` with your TBA token.
* After running, use the bot invite link from the log to invite the bot to your server.

Note: This bot must have message content intent enabled to function.

### How to package:
* Create a maven configuration that runs `package`.
* Run it, the packaged `.jar` will be at `target/discordbot-VERSION-jar-with-dependencies.jar`.

Note: Make sure to pass in your tokens to run command if you plan to run it. 

### For other FRC teams:
We have made it simple for you to repurpose the code from this bot for your own team's discord bot. Simply:
* Fork this repository.
* Customize the constants class.
* Search for comment `// Team Specific` to find any other areas that have team specific code or variables.
  * You can search for text globally by clicking the `shift` key twice quickly and pasting in your text, but you must enable the feature first in the settings.
* Package the project to a standalone `.jar` and run it wherever you like.

### How to contribute:
* Create a branch or fork the repository.
* Make your changes.
* Test your changes.
* Change the version in the `pom.xml` file.
* Create a pull request.

### Help:
* Ping `@Software` on the Iron Riders Discord server.
* Create an issue on GitHub.
* Email `software@ironriders.org`.