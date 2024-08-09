
# MaoTech plugin for Towny
![2024-08-10_01 10 10](https://github.com/user-attachments/assets/7d3def88-2e96-436b-bbe1-27811a60d563)


## Description

MaoTech is a plugin that adds the ability to research different types of tools in minecraft for each town from the Towny plugin!

## Installation

To install MaoTech you need to:
1. Download the .jar file from GitHub or SpigotMC and download the Towny plugin
2. Place the .jar file and Towny plugin in the plugins folder
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
![2024-08-10_01 10 39](https://github.com/user-attachments/assets/8bb7a022-9f1b-4308-8e20-77cfd383e3b0)


## Developer

Developer: MaoArbuz
