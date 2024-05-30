README (ZHS)

这个Mod起初是打算为呆呆私人订制的，但是考虑到呆呆未来可能还会有想要编辑的地方，于是采用了支持自定义编辑的本地化文件，这样也就可以供所有人使用了。现已提供多国语言支持。

下面简述如何编辑本地化文件：
从游戏内的Mod配置面板里可以点击按钮打开配置文件目录。所有本地化文件的备份都在此目录下的backup/***文件夹内，其中***是语言代码，简体中文为zhs。文件夹内每个json文件对应一类内容，比如[cards-卡牌]，[relics-遗物]等。
将需要编辑的json文件复制到backup文件夹外，放到与README.txt同目录下，然后用文本编辑器打开文件编辑内容即可。请仅使用与游戏设置相同语言的json文件进行编辑，以避免未知问题发生。请勿修改文件名，并按照标准json格式编辑。保存修改后需要重启游戏生效。
使用关键字可以为文本提供一些格式上的效果，常见的包括：
" NL " ： 换行
" #r文本 #g文本 #b文本 #y文本 #p文本 " ： 红色绿色蓝色黄色紫色文本
" [R] [G] [B] [W] [E] " ： 红色绿色蓝色紫色任意色能量
" !D! !B! !M! " ： 伤害数字，格挡数字，杂项数字
" @文本@ ~文本~ " ： 颤抖文本，波动文本
" 虚无 消耗 " ： 保留关键字
部分关键字可以混合使用，此处不再一一列举。请务必注意关键字前后必要的半角空格，这是游戏识别关键字的基本依据。另外有的地方并非所有关键字都适用，比如卡牌名称不能颤抖换行等等，建议参考同一文件内其他条目的格式自行判断。

Mod兼容性相关注意事项：
如果你对本地化文件中个别条目的格式进行了大刀阔斧的编辑，将可能导致此Mod与其他调用或修改本地化文件的Mod不兼容。一个常见的冲突如下：
[Loadout Mod]会使用正则表达式拆分并复用部分卡牌的描述（已知涉及的卡牌包括：仪式匕首，贪婪之手，名利双收，狂宴，包扎，死亡收割，勤学精进），从而实现其改卡机修改卡牌描述的功能。因此对这些卡牌描述的格式修改（如去掉换行、去掉标点符号等）可能会导致[Loadout Mod]在载入时报错。
此问题目前没有解决方案，想要消除冲突只能撤销对这些个别条目的编辑。

最后祝玩得开心！



README (ENG)

How to use this mod to customize your game localization files:
In the mod config panel within the game, click button to open the config directory. All backups of the localization files are placed within the backup/*** folder, where *** is your preferred language code. Each json file inside the folder corresponds to a type of game content (cards, relics, etc.).
Copy the json file you wish to edit and paste it outside the backup folder (i.e. to the directory where README.txt is located in), then edit it with a text editor. Please only use json files in the same language as your game settings to prevent unknown issues. Please do not modify the file names, and stick to the standard json format while you edit. Saved changes will take effect after the game relaunches.
Some keywords can be used to achieve text formatting. Common keywords include:
" NL " ： Next Line
" #rTEXT #gTEXT #bTEXT #yTEXT #pTEXT " ： Red Green Blue Yellow Purple Text
" [R] [G] [B] [W] [E] " ： Red Green Blue Purple Any Energy
" !D! !B! !M! " ： Damage Number, Block Number, Misc Number
" @TEXT@ ~TEXT~ " ： Shaky Text, Wavy Text
" Ethereal Exhaust " ： Reserved Keywords
Please pay attention to the details (spaces, capital letters, etc.) while you edit.

Notes on mod compatibility:
Certain mods (e.g. Loadout Mod) use regular expressions to split and reuse text from localization files. Modifications to the text of certain cards or other objects may raise error while launching the game together with these mods. Unfortunately there is no known solution to this issue at the moment, except undoing these specific modifications.

Have fun!
