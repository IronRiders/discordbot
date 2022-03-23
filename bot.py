import re, os
import nextcord

from dotenv import load_dotenv

load_dotenv()

bot = nextcord.Client()

@bot.event
async def on_message(message):
    if message.author == bot.user:
        return

    if re.compile("^who rides\?$").match(message.content):
        await message.channel.send('We Ride!')

bot.run(os.getenv('BOT_TOKEN'))