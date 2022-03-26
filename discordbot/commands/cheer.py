from datetime import datetime
from nextcord.ext import commands

import logging
import nextcord
import re

YELL = 2
EMPHASIZE = 5

class Cheer(commands.Cog):
    def __init__(self, bot):
        self.bot = bot
        self.logger = logging.getLogger(__name__)
        self.timer = datetime.now()

        self.who_rides = re.compile("^\*?\*?who rides\?\*?\*?$", re.IGNORECASE)
        self.who_are_we = re.compile("^\*?\*?who are we\?\*?\*?$", re.IGNORECASE)

    @commands.Cog.listener(name='on_message')
    async def on_message(self, message):
        if self.who_rides.match(message.content) or self.who_are_we.match(message.content):
            now = datetime.now()
            delta = (now - self.timer).total_seconds()
            self.logger.info('Responding to "%s" from "%s"', message.content, message.author.nick)
            self.logger.debug('delta: %s', delta)

            if self.who_rides.match(message.content):
                if delta < YELL:
                    await message.channel.send('WE RIDE!!!')
                elif delta < EMPHASIZE:
                    await message.channel.send('We Ride!!')
                else:
                    await message.channel.send('We Ride!')
            elif self.who_are_we.match(message.content):
                if delta < YELL:
                    await message.channel.send('IRON RIDERS!!!')
                elif delta < EMPHASIZE:
                    await message.channel.send('Iron Riders!!')
                else:
                    await message.channel.send('Iron Riders!')

            self.timer = now


