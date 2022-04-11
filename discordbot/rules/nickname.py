import logging
import re
from datetime import datetime

import nextcord
from nextcord.ext import commands

logger = logging.getLogger(__name__)

class Nickname(commands.Cog):

    def __init__(self, bot):
        self.bot = bot

    @commands.Cog.listener(name="on_member_update")
    async def on_member_update(self, before: nextcord.Member, after: nextcord.Member): 
        if after.nick is None:
            logger.info(f"{after.display_name} has no nickname, sending message")
            await after.send(f"Hi {after.display_name}! The Iron Riders discord requires you to set a nickname. We recommend using your actual name, or what everyone at school refers to you as. Thanks! https://support.discord.com/hc/en-us/articles/219070107-Server-Nicknames#h_01EJTEHFA19Q5BK1GQY2XJ2ZJS")

    @commands.Cog.listener(name="on_member_join")
    async def on_member_join(self, member: nextcord.Member): 
        if member.nick is None:
            logger.info(f"{member.display_name} joined, sending nickname message")
            await member.send(f"Hi {after.display_name}! The Iron Riders discord requires you to set a nickname. We recommend using your actual name, or what everyone at school refers to you as. Thanks! https://support.discord.com/hc/en-us/articles/219070107-Server-Nicknames#h_01EJTEHFA19Q5BK1GQY2XJ2ZJS")