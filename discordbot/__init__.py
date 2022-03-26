from . import commands

from nextcord.ext import commands as cmd

import logging, sys
import nextcord

logger = logging.getLogger(__name__)
logger.setLevel(logging.DEBUG)
formatter = logging.Formatter("%(asctime)s [%(levelname)s] %(message)s")
console = logging.StreamHandler(sys.stdout)
console.setFormatter(formatter)
logger.addHandler(console)

intents = nextcord.Intents.default()
intents.members = True

bot = cmd.Bot(intents=intents)
commands.register(bot)


def start(token):
    logger.info("Starting...")
    bot.run(token)
