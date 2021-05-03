package musichub.main;
import musichub.business.*;
import musichub.util.*;
import java.util.*;

import java.beans.XMLEncoder;
import java.io.FileOutputStream;
import java.io.BufferedOutputStream;
	
public class Main
{
 	public static void main (String[] args) {

		MusichubConsole console = new MusichubConsole ();
		
		System.out.println("Type h for available commands");
		
		
		Scanner scan = new Scanner(System.in);
		String choice = scan.nextLine();
		
		if (choice.length() == 0) System.exit(0);						
		
		while (choice.charAt(0)!= 'q') 	{
			switch (choice.charAt(0)) 	{
				case 'h':
					console.printAvailableCommands();
					choice = scan.nextLine();
				break;
				case 't':
					//album titles, ordered by date
					console.displayTAlbumByDate();
					console.printAvailableCommands();
					choice = scan.nextLine();
				break;
				case 'g':
					//songs of an album, sorted by genre
					console.displaySgAlbumByGenre();
					console.printAvailableCommands();
					choice = scan.nextLine();
				break;
				case 'd':
					//songs of an album
					console.displaySgAlbum();
					console.printAvailableCommands();
					choice = scan.nextLine();
				break;
				case 'u':
					//audiobooks ordered by author
					console.displayAudiobookByAuthor();
					console.printAvailableCommands();
					choice = scan.nextLine();
				break;
				case 'c':
					// add a new song
          console.addSong();
          console.printAvailableCommands();
          choice = scan.nextLine();
          break;
				case 'a':
					// add a new album
					console.addAlbum();
          console.printAvailableCommands();
          choice = scan.nextLine();
				break;
				case '+':
					//add a song to an album:
					console.addSgToAlbum();
					console.printAvailableCommands();
          choice = scan.nextLine();
					break;
				case 'l':
					// add a new audiobook
					console.addAudioBook();
          console.printAvailableCommands();
          choice = scan.nextLine();
				break;
				case 'p':
					//create a new playlist from existing elements
					console.createPlayList();
					console.printAvailableCommands();
					choice = scan.nextLine();
					break;
				case '-':
					//delete a playlist
					console.deletePlayList();
					console.printAvailableCommands();
					choice = scan.nextLine();
				break;
				case 's':
					//save elements, albums, playlists
					console.save();
					console.printAvailableCommands();
					choice = scan.nextLine();
				break;
				default:
				
				break;
			}
		}
		scan.close();
	}
}