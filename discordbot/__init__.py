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

bot = cmd.Bot(intents=intents, command_prefix="!")
commands.register(bot)

def start(token):
    logger.info("Starting...")
    bot.run(token)


@bot.event
async def on_member_update(before, after):
    # Users must have "Members" or "Alumni", exclusively
    okay = False
    for role in after.roles:
        okay = role.name == "Members" or role.name == "Alumni"
        if okay:
            break

    if not okay:
        # await after.send('Hi ' + after)
        print ('Hi ' + after.name + '! Just wanted to let you know that you can either have the "Members" role, or the "Alumni" role, but not both.')
        # await after.send('👀')

@bot.event
async def on_user_update(before, after):
    print('user')
    print(before)
    print(after)

