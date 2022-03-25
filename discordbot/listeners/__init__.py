class Listener:
    def condition(self, message):
        return False

    async def action(self, message):
        return False

from .who_rides import *
from .who_are_we import *