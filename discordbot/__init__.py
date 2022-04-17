from . import commands
from . import rules

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
intents.messages = True

bot = cmd.Bot(intents=intents, command_prefix="!")
commands.register(bot)
rules.register(bot)

def start(token):
    logger.info("Starting...")
    bot.run(token)
