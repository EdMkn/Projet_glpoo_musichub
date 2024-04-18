package musichub.main;

import musichub.business.*;
import musichub.util.*;
import java.util.*;

import java.beans.XMLEncoder;
import java.io.FileOutputStream;
import java.io.BufferedOutputStream;

public class Main {
	public static void main(String[] args) {

		MusichubConsole console = new MusichubConsole();

		System.out.println("Type h for available commands");

		Scanner scan = new Scanner(System.in).useDelimiter("\n");;
		String choice = scan.next();

		if (choice.length() == 0)
			System.exit(0);

		while (!choice.equals("q")) {
			switch (choice) {
				case "h":
					break;
				case "t":
					// album titles, ordered by date
					console.displayTAlbumByDate();
					break;
				case "g":
					// songs of an album, sorted by genre
					console.displaySgAlbumByGenre();
					break;
				case "d":
					// songs of an album
					console.displaySgAlbum();
					break;
				case "u":
					// audiobooks ordered by author
					console.displayAudiobookByAuthor();
					break;
				case "c":
					// add a new song
					console.addSong();
					break;
				case "a":
					// add a new album
					console.addAlbum();
					break;
				case "+":
					// add a song to an album:
					console.addSgToAlbum();
					break;
				case "l":
					// add a new audiobook
					console.addAudioBook();
					break;
				case "p":
					// create a new playlist from existing elements
					console.createPlayList();
					break;
				case "-":
					// delete a playlist
					console.deletePlayList();
					break;
				case "s":
					// save elements, albums, playlists
					console.save();
					
					break;
				default:

					break;
			}
			console.printAvailableCommands();
			
			while(!scan.hasNext())
			scan.next();
			choice = scan.nextLine();
		}
		scan.close();
	}
}