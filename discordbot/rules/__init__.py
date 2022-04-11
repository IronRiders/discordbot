import logging
from typing import List

from nextcord.ext import commands

from .nickname import Nickname

logger = logging.getLogger(__name__)

all_rules: List[commands.Cog] = [Nickname]

def register(bot: commands.Bot):
    for rule in all_rules:
        cog: commands.Cog = rule(bot)
        logger.debug('Registering rule cog "%s"', cog.qualified_name)
    
        bot.add_cog(cog)