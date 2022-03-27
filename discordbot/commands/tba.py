import logging
import os

import nextcord
import tbapy
from dotenv import load_dotenv
from nextcord.ext import commands

load_dotenv()
logger = logging.getLogger(__name__)

class TBA(commands.Cog):
    def __init__(self, bot):
        self.bot = bot
        self.tba = tbapy.TBA(os.getenv("TBA_TOKEN"))

    @commands.command(name="tba")
    async def tba(self, ctx, *args):
        if await self.is_team_number(args[0]) and len(args) == 1:
            await self.get_team_info(ctx, args[0])
        # else:
        #     logger.debug(args)

    async def is_team_number(self, input: str):
        return input[0].isnumeric()

    async def get_team_info(self, ctx, team):
        if team.isnumeric():
            team = f"frc{team}"

        info = self.tba.team(team)

        embed = nextcord.Embed()
        embed.title = info.nickname
        embed.url = info.website
        embed.add_field(name="Team Number", value=info.team_number)
        embed.add_field(
            name="Location", value=f"{info.city}, {info.state_prov}, {info.country}"
        )
        embed.add_field(name="Rookie Year", value=info.rookie_year)

        await ctx.send(embed=embed)
