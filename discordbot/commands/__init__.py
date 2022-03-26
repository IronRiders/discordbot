import logging

logger = logging.getLogger(__name__)

from .cheer import *
from .meta import *
from .tba import *

commands = [Cheer, Meta, TBA]


def register(bot):
    for command in commands:
        cog = command(bot)
        logger.debug('Registering cog "%s"', cog.qualified_name)
        for c in cog.get_commands():
            logger.debug('Added command "%s" from cog "%s"', c.name, cog.qualified_name)

        bot.add_cog(cog)
