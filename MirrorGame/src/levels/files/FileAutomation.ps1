#This constructs a lot of files automatically and places them on your desktop in a folder named "output"
$inside = "" #set to whatever you want inside the files
cd ~\Desktop
mkdir "output"
$top = "" #set to top bound
for($iterator = 0; $iterator -lt top; $iterator  = $iterator + 1){"$inside" > "f$iterator.txt"}