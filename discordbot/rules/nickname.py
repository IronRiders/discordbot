import logging
import re
from datetime import datetime

import nextcord
from nextcord.ext import commands

logger = logging.getLogger(__name__)

class Nickname(commands.Cog):

    def __init__(self, bot):
        self.bot = bot
        self.self_destruct = 86400
        self.nickname_embed = nextcord.Embed(
            title = "How to set a Nickname",
            url = "https://support.discord.com/hc/en-us/articles/219070107-Server-Nicknames#h_01EJTEHFA19Q5BK1GQY2XJ2ZJS"
        )

    @commands.Cog.listener(name="on_member_update")
    async def on_member_update(self, before: nextcord.Member, after: nextcord.Member): 
        if before.nick is not None and after.nick is None:
            logger.info(f"{after.display_name} unset nickname, sending message")

            # TODO: For some reason, the nickname embed doesn't send.
            msg = await after.send(
                content = f"Hi {after.display_name}! I noticed you unset your nickname. The Iron Riders discord requires you to set a nickname. Can you please fix that? Thanks! {self.nickname_embed.url}",
                delete_after = self.self_destruct,
                embeds = [self.nickname_embed]
            )

    @commands.Cog.listener(name="on_member_join")
    async def on_member_join(self, member: nextcord.Member): 
        if member.nick is None:
            logger.info(f"{member.display_name} joined, sending nickname message")
            await member.send(
                content = f"Hi {member.display_name}! Welcome to the Iron Riders discord! Before we get started, please set a nickname for yourself on the server. I recommend using your actual name, or what everyone at school refers to you as.\n\nIf your Discord username is you real name, please also set your nickname to the same. I'm not a very smart bot, and cannot tell when if your user name is your name or not. I guess you won't have to worry about that robot uprising then. Or will you? https://support.discord.com/hc/en-us/articles/219070107-Server-Nicknames#h_01EJTEHFA19Q5BK1GQY2XJ2ZJS",
                delete_after = self.self_destruct,
                embeds = [self.nickname_embed]
            )