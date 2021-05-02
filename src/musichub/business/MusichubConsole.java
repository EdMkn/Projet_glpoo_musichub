package musichub.business;

import java.util.*;
import java.beans.XMLEncoder;
import java.io.FileOutputStream;
import java.io.BufferedOutputStream;

public class MusichubConsole {
    private MusicHub theHub;
    private String albumTitle;

    public MusichubConsole(){
        theHub = new MusicHub ();
        albumTitle = null;
    }

    public void printAvailableCommands(){
		System.out.println("t: display the album titles, ordered by date");
		System.out.println("g: display songs of an album, ordered by genre");
		System.out.println("d: display songs of an album");
		System.out.println("u: display audiobooks ordered by author");
		System.out.println("c: add a new song");
		System.out.println("a: add a new album");
		System.out.println("+: add a song to an album");
		System.out.println("l: add a new audiobook");
		System.out.println("p: create a new playlist from existing songs and audio books");
		System.out.println("-: delete an existing playlist");
		System.out.println("s: save elements, albums, playlists");
		System.out.println("q: quit program");
    }

    public void displayTAlbumByDate(){
        System.out.println(theHub.getAlbumsTitlesSortedByDate());
    }
    public void displaySgAlbumByGenre(){
        //songs of an album, sorted by genre
        System.out.println("Songs of an album sorted by genre will be displayed; enter the album name, available albums are:");
        System.out.println(theHub.getAlbumsTitlesSortedByDate());

        Scanner scan = new Scanner(System.in);
        albumTitle = scan.nextLine();
        try {
            System.out.println(theHub.getAlbumSongsSortedByGenre(albumTitle));
        } catch (NoAlbumFoundException ex) {
            System.out.println("No album found with the requested title " + ex.getMessage());
        }      
    }
    public void displaySgAlbum(){
        //songs of an album
        System.out.println("Songs of an album will be displayed; enter the album name, available albums are:");
        System.out.println(theHub.getAlbumsTitlesSortedByDate());

        Scanner scan = new Scanner(System.in);
        albumTitle = scan.nextLine();
        try {
            System.out.println(theHub.getAlbumSongs(albumTitle));
        } catch (NoAlbumFoundException ex) {
            System.out.println("No album found with the requested title " + ex.getMessage());
        }
    }
    public void displayAudiobookByAuthor(){
        //audiobooks ordered by author
		System.out.println(theHub.getAudiobooksTitlesSortedByAuthor());
    }


    public void addSong(){
        // add a new song
        System.out.println("Enter a new song: ");
        System.out.println("Song title: ");
        Scanner scan = new Scanner(System.in);
        String title = scan.nextLine();
        System.out.println("Song genre (jazz, classic, hiphop, rock, pop, rap):");
        String genre = scan.nextLine();
        System.out.println("Song artist: ");
        String artist = scan.nextLine();
        System.out.println ("Song length in seconds: ");
        int length = Integer.parseInt(scan.nextLine());
        System.out.println("Song content: "); 
        String content = scan.nextLine();
        Song s = new Song (title, artist, length, content, genre);
        theHub.addElement(s);
        System.out.println("New element list: ");
        Iterator<AudioElement> it = theHub.elements();
        while (it.hasNext()) System.out.println(it.next().getTitle());
        System.out.println("Song created!");
    }
    public void addAlbum(){
        // add a new album
        System.out.println("Enter a new album: ");
        System.out.println("Album title: ");
        Scanner scan = new Scanner(System.in);
        String aTitle = scan.nextLine();
        System.out.println("Album artist: ");
        String aArtist = scan.nextLine();
        System.out.println ("Album length in seconds: ");
        int aLength = Integer.parseInt(scan.nextLine());
        System.out.println("Album date as YYYY-DD-MM: "); 
        String aDate = scan.nextLine();
        Album a = new Album(aTitle, aArtist, aLength, aDate);
        theHub.addAlbum(a);
        System.out.println("New list of albums: ");
        Iterator<Album> ita = theHub.albums();
        while (ita.hasNext()) System.out.println(ita.next().getTitle());
        System.out.println("Album created!");
    }
    public void addSgToAlbum(){
        //add a song to an album:
        System.out.println("Add an existing song to an existing album");
        System.out.println("Type the name of the song you wish to add. Available songs: ");
        Iterator<AudioElement> itae = theHub.elements();
        while (itae.hasNext()) {
            AudioElement ae = itae.next();
            if ( ae instanceof Song) System.out.println(ae.getTitle());
        }
        Scanner scan = new Scanner(System.in);
        String songTitle = scan.nextLine();	
        
        System.out.println("Type the name of the album you wish to enrich. Available albums: ");
        Iterator<Album> ait = theHub.albums();
        while (ait.hasNext()) {
            Album al = ait.next();
            System.out.println(al.getTitle());
        }
        String titleAlbum = scan.nextLine();
        try {
            theHub.addElementToAlbum(songTitle, titleAlbum);
        } catch (NoAlbumFoundException ex){
            System.out.println (ex.getMessage());
        } catch (NoElementFoundException ex){
            System.out.println (ex.getMessage());
        }
        System.out.println("Song added to the album!");
    }
    public void addAudioBook(){
        // add a new audiobook
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter a new audiobook: ");
        System.out.println("AudioBook title: ");
        String bTitle = scan.nextLine();
        System.out.println("AudioBook category (youth, novel, theater, documentary, speech)");
        String bCategory = scan.nextLine();
        System.out.println("AudioBook artist: ");
        String bArtist = scan.nextLine();
        System.out.println ("AudioBook length in seconds: ");
        int bLength = Integer.parseInt(scan.nextLine());
        System.out.println("AudioBook content: "); 
        String bContent = scan.nextLine();
        System.out.println("AudioBook language (french, english, italian, spanish, german)");
        String bLanguage = scan.nextLine();
        AudioBook b = new AudioBook (bTitle, bArtist, bLength, bContent, bLanguage, bCategory);
        theHub.addElement(b);
        System.out.println("Audiobook created! New element list: ");
        Iterator<AudioElement> itl = theHub.elements();
        while (itl.hasNext()) System.out.println(itl.next().getTitle());
    }
    public void createPlayList(){
        //create a new playlist from existing elements
        System.out.println("Add an existing song or audiobook to a new playlist");
        Scanner scan = new Scanner(System.in);
        System.out.println("Existing playlists:");
        Iterator<PlayList> itpl = theHub.playlists();
        while (itpl.hasNext()) {
            PlayList pl = itpl.next();
            System.out.println(pl.getTitle());
        }
        System.out.println("Type the name of the playlist you wish to create:");
        String playListTitle = scan.nextLine();	
        PlayList pl = new PlayList(playListTitle);
        theHub.addPlaylist(pl);
        System.out.println("Available elements: ");
        
        Iterator<AudioElement> itael = theHub.elements();
        while (itael.hasNext()) {
            AudioElement ae = itael.next();
            System.out.println(ae.getTitle());
        }
        String choice = "";
        while (choice.charAt(0)!= 'n') 	{
            System.out.println("Type the name of the audio element you wish to add or 'n' to exit:");
            String elementTitle = scan.nextLine();	
            try {
                theHub.addElementToPlayList(elementTitle, playListTitle);
            } catch (NoPlayListFoundException ex) {
                System.out.println (ex.getMessage());
            } catch (NoElementFoundException ex) {
                System.out.println (ex.getMessage());
            }
                
            System.out.println("Type y to add a new one, n to end");
            choice = scan.nextLine();
        }
        System.out.println("Playlist created!");
    }

    public void deletePlayList(){
        //delete a playlist
        System.out.println("Delete an existing playlist. Available playlists:");
        Scanner scan = new Scanner(System.in);
        Iterator<PlayList> itp = theHub.playlists();
        while (itp.hasNext()) {
            PlayList p = itp.next();
            System.out.println(p.getTitle());
        }
        String plTitle = scan.nextLine();	
        try {
            theHub.deletePlayList(plTitle);
        }	catch (NoPlayListFoundException ex) {
            System.out.println (ex.getMessage());
        }
        System.out.println("Playlist deleted!");
    }
    public void save(){
        //save elements, albums, playlists
        theHub.saveElements();
        theHub.saveAlbums();
        theHub.savePlayLists();
        System.out.println("Elements, albums and playlists saved!");
    }
}