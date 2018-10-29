# Who Did It

[![](https://jaywcjlove.github.io/sb/lang/chinese.svg)](https://github.com/JoyLau/who-did-it/blob/master/README-CN.md)
[![Build Status](https://travis-ci.org/JoyLau/who-did-it.svg?branch=master)](https://travis-ci.org/JoyLau/who-did-it)
[![Downloaded](https://img.shields.io/github/downloads/JoyLau/who-did-it/total.svg)]()
[![Version](https://img.shields.io/github/release/JoyLau/who-did-it.svg?style=flat&label=version)](https://github.com/JoyLau/who-did-it/releases)
[![License](https://img.shields.io/github/license/JoyLau/who-did-it.svg)](https://github.com/JoyLau/who-did-it/blob/master/LICENSE)
[![](https://img.shields.io/github/last-commit/JoyLau/who-did-it.svg)]()
[![](https://img.shields.io/github/languages/code-size/JoyLau/who-did-it.svg)]()
[![](https://img.shields.io/github/repo-size/JoyLau/who-did-it.svg)]()

**Explanation**

[English](https://github.com/JoyLau/who-did-it/blob/master/README.md) | [中文](https://github.com/JoyLau/who-did-it/blob/master/README-CN.md) 

`who did it` is a plugin that displays version control system information on software based on the IntelliJ platform (JetBrains IDEs), showing the last commit and submission time of the file,which will be displayed in the file tree of the project panel.

Just like this:

<img src="http://image.joylau.cn/blog/pluginwho-did-it.png" width = "600" alt="who did it plugin" />

### Supports JetBrains IDEs
- Android Studio
- AppCode
- CLion
- IntelliJ IDEA
- PhpStorm
- PyCharm
- RubyMine
- WebStorm
- DataGrip

### Installation
There are 2 ways to install the who did it plugin

1. Open Preferences | Plugins, click Browse Repositories, enter the keyword to find `who did it`, install it.
2. Direct [Click here](https://github.com/JoyLau/who-did-it/releases) to download the latest version, then open Preferences | Plugins, click Install plugin for disk, select the downloaded jar package to install

### Enable/Disable
There are three ways to enable/disable this feature:

1. Click Tools on the menu bar, select the Who Did It tab, select / deselect enable VCS Info
2. Open the Settings panel Preferences | Other Settings | Who Did It, check enable VCS Info on the panel
3. Select a folder or file on the project panel, right click and select Hide VCS Info / Show VCS Info