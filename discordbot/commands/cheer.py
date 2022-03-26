import logging
import re
from datetime import datetime

import nextcord
from nextcord.ext import commands

logger = logging.getLogger(__name__)


class Cheer(commands.Cog):
    YELL = 2
    EMPHASIZE = 5
    CHEER_COMMAND_RESPONSES = [
        {
            "command": re.compile("^\*?\*?who rides\?\*?\*?$", re.IGNORECASE),
            "response": "We Ride!",
        },
        {
            "command": re.compile("^\*?\*?who are we\?\*?\*?$", re.IGNORECASE),
            "response": "Iron Riders!",
        },
    ]

    def __init__(self, bot):
        self.bot = bot
        self.timer = datetime.now()

    def timer_update(self) -> float:
        now = datetime.now()
        delta = (now - self.timer).total_seconds()
        self.timer = now
        return delta

    @commands.Cog.listener(name="on_message")
    async def on_message(self, message: nextcord.Message):
        for cheer_command_response in self.CHEER_COMMAND_RESPONSES:
            if cheer_command_response["command"].match(message.content):
                response: str = cheer_command_response["response"]
            else:
                continue
            delta = self.timer_update()
            author = message.author
            author = author if author.nick is None else author.nick  # If has nickname
            logger.info('Responding to "%s" from "%s"', message.content, author)
            logger.debug("delta: %s", delta)

            if delta < self.YELL:
                message_text = response.upper() + "!!"
            elif delta < self.EMPHASIZE:
                message_text = response + "!"
            else:
                message_text = response
            await message.channel.send(message_text)
            break  # Only process 1 command
