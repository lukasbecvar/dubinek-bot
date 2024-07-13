# Dubinek discord bot
My personal discord chatbot

# Features
- Command System
- YML config system
- Event system
- Log system (msg, system, error)
- Mysql log system
- Command cli controller
- Etc...

# Command manager
- Simple add new commands
- You can only set a specific channel as the terminal channel
- Included help command
- Execute logger
- Etc...

# Config system
- Auto create config
- All values in json file "config.yml"
- token = Discord api token
- ActivityType - Bot RPC type (Watching, Listening, Playing)
- statusText - RPC status text
- Status - Bot status (Online, DoNotDisturb, Invisible, idle)
- BotName - Bot name
- DevMode - true or false
- CommandPrefix - Main command prefix for call commands
- TerminalChannel - Terminal channel id or "all" (String value)
- TerminalChannelName - Terminal channel name
- DefaultRole - Default role add for userJoinEvent id or none (String value)
- UserJoinEventEnabled - true or false
- UserLeaveEventEnabled - true or false
- logging - true or false
- mysqlLogging - true or false
- mysqlIP - Mysql/MariaDB server ip adress
- mysqlDatabase - Mysql database name
- mysqlUser - Mysql connection username
- mysqlPassword - Mysql connection password

## Dependencies
This project requires Java 17.
* JDA
    * Version: **5.0.0-alpha.3**
    * [Website](https://github.com/DV8FromTheWorld/JDA)
* Slf4j-api
    * Version: **1.7.5**
    * [Website](http://www.slf4j.org/)
* yamlbeans
  * Version: **1.15**
  * [Website](https://github.com/EsotericSoftware/yamlbeans)
* Mysql-connector
  * Version: **8.0.29**
  * [Website](https://dev.mysql.com/downloads/connector/j)
