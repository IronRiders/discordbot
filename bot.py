from dotenv import load_dotenv

import os, discordbot

load_dotenv()

discordbot.start(os.getenv("BOT_TOKEN"))
