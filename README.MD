# JDA DISCORD BOT BASE

# - Features
* Command System
* Json config system
* Event system
* Log system (msgs & system log)
* Command cli controller
* Etc...

# - Command manager
* Simple add new commands
* You can only set a specific channel as the terminal channel
* Included help command
* Execute logger
* Etc...

# - Config system
* Auto create config
* All values in json file "config.json"
* 1) apiToken Discord bot api token
* 2) ActivityType - Bot RPC type (Watching, Listening, Playing)
* 3) statusText - RPC status text
* 4) Status - Bot status (Online, DoNotDisturb, Invisible, idle)
* 5) BotName - Bot name
* 6) DevMode - Developer mode config 0 = disable 1 = enable
* 7) CommandPrefix - Main command prefix for call commands
* 8) TerminalChannel - Terminal channel id or "all" (String value)
* 9) TerminalChannelName - Terminal channel name
* 10) DefaultRole - Default role add for userJoinEvent id or none (String value)
* 11) UserJoinEventEnabled - Enable welcome msg & auto role values: 1 or 0
* 12) UserLeaveEventEnabled - Enable left msg values: 0 or 1
* 13) MessageLoggerEnabled - Enable logging msgs to file values:1 or 0
* 14) SystemLoggerEnabled - Enable system log to file values: 1 or 0
