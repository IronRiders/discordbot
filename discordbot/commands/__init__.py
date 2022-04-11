import logging
from typing import List

from nextcord.ext import commands

from .cheer import Cheer
from .meta import Meta
from .tba import TBA

logger = logging.getLogger(__name__)

all_commands: List[commands.Cog] = [Cheer, Meta, TBA]


def register(bot: commands.Bot):
    for command in all_commands:
        cog: commands.Cog = command(bot)
        logger.debug('Registering command cog "%s"', cog.qualified_name)
        for c in cog.get_commands():
            logger.debug('Added command "%s" from cog "%s"', c.name, cog.qualified_name)

        bot.add_cog(cog)
