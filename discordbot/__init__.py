import logging, re, os, sys
import nextcord
import tbapy

logger = logging.getLogger(__name__)
logger.setLevel(logging.DEBUG)
formatter = logging.Formatter("%(asctime)s [%(levelname)s] %(message)s")
console = logging.StreamHandler(sys.stdout)
console.setFormatter(formatter)
logger.addHandler(console)


from . import listeners

who_rides = listeners.WhoRides()
who_are_we = listeners.WhoAreWe()

intents = nextcord.Intents.default()
intents.members = True

bot = nextcord.Client(intents=intents)

from . import listeners

listeners.attach(bot)

def start(token):
    logger.info('Starting...')
    bot.run(token)

@bot.event
async def on_message(message):
    global who_rides_timer

    if message.author == bot.user:
        return

    if who_rides.condition(message):
        await who_rides.action(message)
    elif who_are_we.condition(message):
        await who_are_we.action(message)

