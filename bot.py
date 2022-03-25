import re, os
import nextcord

from datetime import datetime
from dotenv import load_dotenv

load_dotenv()

bot = nextcord.Client()
who_rides_timer = datetime.now()

import listeners

who_rides = listeners.WhoRides()
who_are_we = listeners.WhoAreWe()

@bot.event
async def on_message(message):
    global who_rides_timer

    if message.author == bot.user:
        return

    if who_rides.condition(message):
        await who_rides.action(message)
    elif who_are_we.condition(message):
        await who_are_we.action(message)


bot.run(os.getenv('BOT_TOKEN'))