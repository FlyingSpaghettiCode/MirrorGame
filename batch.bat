::most stuff will be in a jar, but level files and settings will be seperate
::this is for ease of use with respect to modders and level creators
::it also creates a folder for settings in case we also want to keep those in files, but
::that won't necessarily be the case

::Author - Adriano Hernandez

@echo creating directories within your default documents folder

::create folders
cd C:\Users\%username%\Documents
mkdir MirrorGame
cd MirrorGame
mkdir LevelFiles
mkdir Settings

::create a readme
@echo Mirror Game level files and settings stored in txt documents here > C:\Users\%username%\Documents\MirrorGame\README.txt

::move the level files from the installation location to the call directory
::assuming that the batch is near the level files in a constant way on every system
cd %~dp0
::precondition is that they are there
cd LevelFiles
:: move stuff

pause