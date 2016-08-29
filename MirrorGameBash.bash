#Author - Adriano Hernandez
#Version - 0.1
#this sets up directory folders for level files and perhaps settings or sounds <these can be sound files or sound file names; try to avoid easy asset stealing though>

echo creating directories within your default documents folder
cd ~/Documents #this is standard and should be in every mac, on linux I don't really know
mkdir MirrorGame
cd MirrorGame
mkdir LevelFiles
mkdir Settings

#make readme
echo Mirror Game level files and settings stored in txt documents here > ~/Documents/MirrorGame/README.txt
