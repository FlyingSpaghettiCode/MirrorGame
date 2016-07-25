::this is an improved init script
::this can be run from anywhere

::DIRECTORY SETUP
mkdir C:\Spaghetti
mkdir C:\Spaghetti\level-files
mkdir C:\Spaghetti\sound-files
mkdir C:\Spaghetti\global-settings
mkdir C:\Spaghetti\game-files

::PRINT
@echo on
@echo finished setting up basic directory structure for game files

::TEXT FOR TEST LEVEL
@echo #### This is a test level file #### > C:\Spaghetti\level-files\test-level.txt
::    Subsequent echoes rewrite the first line instead of adding new lines.
::    It should be able to write line by line, or preferably I will just move files from our download.
::@echo #### Author = Adriano Hernandez #### > C:\Spaghetti\level-files\test-level.txt
::@echo #### Version = 0.1 #### > C:\Spaghetti\level-files\test-level.txt
::@echo #### 23 7 2016 #### > C:\Spaghetti\level-files\test-level.txt

::PAUSE
pause
