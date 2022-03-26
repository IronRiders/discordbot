from nextcord.ext import commands

import logging


class Meta(commands.Cog):
    def __init__(self, bot):
        self.bot = bot
        self.logger = logging.getLogger(__name__)

    @commands.Cog.listener(name='on_ready')
    async def on_ready(self):
        self.logger.info('Logged in as %s', self.bot.user)


