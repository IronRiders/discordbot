import logging

logger = logging.getLogger(__name__)

class Listener:
    def __init__(self):
        logger.debug('Listener created for "%s"', self.regex.pattern)

    def condition(self, message):
        return False

    async def action(self, message):
        return False

from .who_rides import *
from .who_are_we import *

listeners = [
    WhoRides(),
    WhoAreWe()
]

def attach(bot):
    global listeners
    logger.debug('Attaching %s listeners', len(listeners))

    @bot.event
    async def on_message(message):
        if message.author == bot.user:
            return

        for listener in listeners:
            if listener.condition(message):
                await listener.action(message)        
