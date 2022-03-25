import re

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
        now = datetime.now()
        delta = (now - self.timer).total_seconds()

        if delta < YELL:
            await message.channel.send('WE RIDE!!!')
        elif delta < EMPHASIZE:
            await message.channel.send('We Ride!')
        else:
            await message.channel.send('We Ride!')

        self.timer = now