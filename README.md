# Hikvision XOR Decrypter

~~**FYI this script is soon going to be replaced. I am actively working on an all-encompassing utility that does all the steps listed below**~~

**After a year of putting the following goal off, I finally got around to building said all-encompassing utility. It can be found [here](https://github.com/WormChickenWizard/hikvision-decrypter)**

Hikvision configuration files use two layers of encryption, 

It first XOR encrypts the configuration file then it proceed to encrypt it with aes 128-bit ecb encryption(electronic codebook).

## Notes

I might as well add this for reference far into the future: this was written in java version 8u191
if for whatever reason you are running into problems, use that version instead.

## Obtaining the Hikvision configuration file

If you are trying to get into a Hikvision camera with an old firmware, 
use this link to get the camera's configuration file: 
```
http://camera.ip/System/configurationFile?auth=YWRtaW46MTEK
```
Just make sure to substitute camera.ip with the ip address of the camera.

## Steps for breaking the AES encryption on the configuration file

Now that you have the configuration file, if you are on windows download the ubuntu subsystem and install openssl.
Once installed, navigate to the location where the config file is and run the following command:
```
openssl enc -d -in configurationFile -out decryptedoutput -aes-128-ecb -K 279977f62f6cfd2d91cd75b889ce0c9a -nosalt -md md5
```
You should have a new file called "decryptedoutput". This file has the AES encryption broken but, but still is XOR encoded which is where the script I wrote comes into play.

## XOR decode script compilation and using

I'm going to make the assumption that the jdk is installed and the path variable is setup correctly

1.) Clone this repository (or download the zip whatever is easier)

2.) Navigate to XORDecode.java in a command prompt and run:
```
javac XORDecode.java
```
3.) Make a new folder and put the resulting .class file in there as well as the decryptedoutput file

4.) Open command prompt in that folder you just made with the two files inside it and run:
```
java XORDecode
```
5.) You now should get a plaintextOutput file. Congratulations!

Supposedly this is a sqlite3 database. Problem is You can't open it in a normal text editor like notepad

If you have a database viewer you are more than welcome to use it.
For simplicity's sake, I used a hex editor to view its contents. I personally use Frhed which works fine.
