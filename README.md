
# MaoTech plugin for Towny

## Description

MaoTech is a plugin that adds the ability to research different types of tools in minecraft for each town from the Towny plugin!

## Installation

To install MaoTech you need to:
1. Download the .jar file from GitHub or SpigotMC
2. Place the .jar file in the plugins folder
3. Reboot the server

## Configuration

### Configuration File

How the configuration file works:
1. When uploaded to the server it loads all cities into the configuration file, when adding/removing a city the configuration file is also updated
2. After researching a city a new line is added to the city, for example if iron tools were researched it adds: iron_tools: true
3. You can add and change different researches
Example configuration file:
`Amogus:
  stone_tools: true
  iron_tools: true
  diamond_tools: true
  netherite_tools: true`


## Usage

The mayor of each city can enter the `/technologies` command to research a new technology and a menu will open! Researching a new technology costs a certain amount of resources
But there is also the option to research via the /mttech stone/iron/golden/diamond/netherite_tools command

## Developer

Developer: MaoArbuz
