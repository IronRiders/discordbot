import logging

from nextcord.ext import commands

logger = logging.getLogger(__name__)


class Meta(commands.Cog):
    def __init__(self, bot):
        self.bot = bot

    @commands.Cog.listener(name="on_ready")
    async def on_ready(self):
        logger.info("Logged in as %s", self.bot.user)
