# hikvision-xor-decrypter
hikvision configuration files use two layers of encryption, 

it first xor encrypts the configuration file then it proceed to encrypt it with aes 128-bit ecb encryption(electronic codebook).

-----------------------------------notes--------------------------------

I might as well add this for reference far into the future: this was written in java version 8u191
if for whatever reason you are running into problems, use that version instead.



---------------------getting hikvision configuration file------------------

If you are trying to get into a hikvision camera with an old firmware, 
use this link to get the camera's configuration file: http://camera.ip/System/configurationFile?auth=YWRtaW46MTEK
just make sure to substitute camera.ip with the ip address of the camera.




-------------steps for breaking the aes encryption on the configuration file---------------

once you have the configuration file, if you are on windows download the ubuntu subsystem and install openssl.
once you have openssl, navigate to the location where the config file is and run the following command:

openssl enc -d -in configurationFile -out decryptedoutput -aes-128-ecb -K 279977f62f6cfd2d91cd75b889ce0c9a -nosalt -md md5

now you should have a file called "decryptedoutput" this file has the aes encryption broken but hasn't been xor decoded which is where the script I wrote in java comes into play.




-----------------------XOR decode script compilation and using-----------------------------------

Im going to make the assumption that the jdk is installed and path variable is setup correctly

1) clone this repository (or download the zip whatever is easier)

2.) navigate to XORDecode.java in a command prompt and run:
      javac XORDecode.java

3.) Make a new folder and put the resulting .class file in there as well as the decryptedoutput file

4.) open command prompt in that folder you just made with the two files inside it and run:
    java XORDecode
    
5.) you now should get a plaintextOutput file. Congradulations!

Supposedly this is a sqlite3 database. Problem is You can't open it in a normal text editor like notepad

If you have a database viewer you are more than welcome to use it.
For simplicity's sake, I used a hex editor to view its text. I personally use Frhed which works fine.
