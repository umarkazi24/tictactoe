README FILE

HOW TO COMPILE: 

Run the program and the first time, there will be a pop up GUI box that 
will prompt you to hit '1' for Server and '2' for Client. Enter in '1' the first time,
then run the program again and when prompted, enter in '2'. This way, you will have two
display windows of tic-tac-toe games, one for the server and for the client. This game can
have two players who play against each other. I was not able to figure out how to correctly
implement an AI system where you would play against the "Computer" or the "AI". 

FILES INCLUDED IN PROJECT:

--main.java - Class that starts the game by calling and executing the ServerGame and
ClientGame methods. Also, there is code for the GUI interface for user to choose
either 1 for server or 2 for client.

--Window.java - Initializes methods and variables to utilize the game window  

--GameWindow.java - Class used to for the actual game display window. It renders the display
window field sizes, receives input, and implements an input class that extends MouseAdapter
which receives input from the clicks of the mouse that will be used in the game. Using 
our paint method that passes in a Graphics 2D argument, I created fields for the tic tac toe
display using lines with an even width and height.

--Game.java - Class that handles all the game logic such as Size of display field, the player
objects, the display messages for winning, the PORT number inputs, etc. This class serves
as the superclass to many of the other classes such as ServerGame and ClientGame.

--Resources.java - We will be using images for the 'X' and 'O' that will pop up on our GUI. 
This class will handle loading resources for the game such as textures and images. In this
file, we have a class called BufferedImage LoadImage in which we pass an argument for a 
string path. From there, we return the image read from accessing the path. So we create a
new class called BufferedImage Letters and we use this to access our two PNG files which
I have included: 'x.png' and 'o.png'


--ServerGame.java - The purpose of this class is to handle the server side of the game. 
The server has a socket that is initialized and creates an object that passes in the 
port number as a parameter. This server can now communicate with a socket that connects to
it. The constructor also initializes a method that stops and waits until something
connects onto this port on the IP address and then returns the socket that connected. When
the windows are closed by the user, the server is closed as well.

--ClientGame.java - This is the Client class that will handle the Client side. This is the 
User Client that will be player#2. This class initializes a socket and passes the port number through its parameters as well as a string
paramter called "localhost" for the current computer to access it. 

--Connection.java - This class is responsible for writing and receiving data. The class 
creates and outputStream and an inputStream object. Then, the constructor takes in a socket
and gets the input and output stream of the socket. This class resposible for 
sending packets through the output stream and receiving packets through the input stream. 
This class is used by both the server and client classes to access connection between each
other.

--UpdatePacket.java - Class that is responsible for keeping track of the current state of the 
game. Must be sent everytime the field is updated.

--ClientPlayPacket.java - Class for the ClientGame class to use to send packets to the 
server and the server will respond by giving the game state. 

--GameEndPacket.java - Class used to send packets to signal end of game

--x.png - image used for the 'x' in the game

--o.png - image used for the 'o' in the game





 