import logging, re

from datetime import datetime
from listeners import Listener

YELL = 2
EMPHASIZE = 5

class WhoRides(Listener):
    regex = re.compile("^\*?\*?who rides\?\*?\*?$", re.IGNORECASE)
    timer = datetime.now()

    def condition(self, message):
        return self.regex.match(message.content)

    async def action(self, message):
        logging.info('Responding to %s from %s', message.content, message.author.nick)
        now = datetime.now()
        delta = (now - self.timer).total_seconds()
        logging.debug('delta: %s', delta);

        if delta < YELL:
            await message.channel.send('WE RIDE!!!')
        elif delta < EMPHASIZE:
            await message.channel.send('We Ride!')
        else:
            await message.channel.send('We Ride!')

        self.timer = now
