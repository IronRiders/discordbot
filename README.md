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

### How to contribute:
* Create a branch or fork the repository.
* Make your changes.
* Test your changes.
* Change the version in the `pom.xml` file.
* Create a pull request.

### Help:
Ping `@Software` for help.