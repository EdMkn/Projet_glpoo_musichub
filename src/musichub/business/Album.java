package musichub.business;

import java.util.UUID;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.w3c.dom.*;
import java.text.*;

/**
 * the Application class launch the Album class
 *
 * @version 1.0
 *
 * @see the Application class
 * @author Najmi Mehdi.
 */

public class Album {
	private String title;
	private String artist;
	private int lengthInSeconds;
	private UUID uuid;
	private Date date;
	private ArrayList<UUID> songsUIDs;

	/**
	 * constructor of the Album class.
	 *
	 * @param title           title of the Album
	 * @param artist          Artist of the Album
	 * @param lengthInSeconds Length of the Album
	 * @param id  			  Id of the album
	 * @param date            Date of the Album
	 * @param songsUIDs		  List of IDs of songs of the album
	 * @author Najmi Mehdi.
	 */
	public Album(String title, String artist, int lengthInSeconds, String id, String date, ArrayList<UUID> songsUIDs) {
		this.title = title;
		this.artist = artist;
		this.lengthInSeconds = lengthInSeconds;
		this.uuid = UUID.fromString(id);
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			this.date = sdf.parse(date);
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		this.songsUIDs = songsUIDs;
	}

	
	/**
	 * constructor of the Album class.
	 *
	  * @param title           title of the Album
	 * @param artist          Artist of the Album
	 * @param lengthInSeconds Length of the Album
	 * @param date            Date of the Album
	 * @author Najmi Mehdi.
	 */
	public Album(String title, String artist, int lengthInSeconds, String date) {
		this.title = title;
		this.artist = artist;
		this.lengthInSeconds = lengthInSeconds;
		this.uuid = UUID.randomUUID();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			this.date = sdf.parse(date);
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		this.songsUIDs = new ArrayList<UUID>();
	}
	
	/**
	 * constructor of the Album class.
	 *
	  * @param xmlElement           XML element from a XML file
	 * @author Manyim Ma Makon
	 */
	public Album(Element xmlElement) throws Exception {
		try {
			this.title = xmlElement.getElementsByTagName("title").item(0).getTextContent();
			this.lengthInSeconds = Integer
					.parseInt(xmlElement.getElementsByTagName("lengthInSeconds").item(0).getTextContent());
			String uuid = null;
			try {
				uuid = xmlElement.getElementsByTagName("UUID").item(0).getTextContent();
			} catch (Exception ex) {
				System.out.println("Empty album UUID, will create a new one");
			}
			if ((uuid == null) || (uuid.isEmpty()))
				this.uuid = UUID.randomUUID();
			else
				this.uuid = UUID.fromString(uuid);

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			this.date = sdf.parse(xmlElement.getElementsByTagName("date").item(0).getTextContent());
			// parse list of songs:
			Node songsElement = xmlElement.getElementsByTagName("songs").item(0);
			NodeList songUUIDNodes = songsElement.getChildNodes();
			if (songUUIDNodes == null)
				return;

			this.songsUIDs = new ArrayList<UUID>();

			for (int i = 0; i < songUUIDNodes.getLength(); i++) {
				if (songUUIDNodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
					Element songElement = (Element) songUUIDNodes.item(i);
					if (songElement.getNodeName().equals("UUID")) {
						try {
							this.addSong(UUID.fromString(songElement.getTextContent()));
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
				}
			}
		} catch (Exception ex) {
			throw ex;
		}
	}

	/**
	 * Method adding a song to an album
	 * @param song song to add
	 */
	public void addSong(UUID song) {
		songsUIDs.add(song);
	}
	
	/**
	 *  returns a list of UUIDs of songs of albums
	 * @return songsUIDs 
	 */
	public List<UUID> getSongs() {
		return songsUIDs;
	}

	/**
	 * return a random list of songs UUIDs
	 * @return shuffledSongs
	 */
	public ArrayList<UUID> getSongsRandomly() {
		ArrayList<UUID> shuffledSongs = songsUIDs;
		Collections.shuffle(shuffledSongs);
		return shuffledSongs;
	}

	/**
	 * Method to get the title of the album
	 * @return title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 *  returns the date of creation of album
	 * @return
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Exports an album to an XML file
	 * @param document
	 * @param parentElement
	 */
	public void createXMLElement(Document document, Element parentElement) {
		Element albumElement = document.createElement("album");
		parentElement.appendChild(albumElement);

		Element nameElement = document.createElement("title");
		nameElement.appendChild(document.createTextNode(title));
		albumElement.appendChild(nameElement);

		Element artistElement = document.createElement("artist");
		artistElement.appendChild(document.createTextNode(artist));
		albumElement.appendChild(artistElement);

		Element lengthElement = document.createElement("lengthInSeconds");
		lengthElement.appendChild(document.createTextNode(Integer.valueOf(lengthInSeconds).toString()));
		albumElement.appendChild(lengthElement);

		Element UUIDElement = document.createElement("UUID");
		UUIDElement.appendChild(document.createTextNode(uuid.toString()));
		albumElement.appendChild(UUIDElement);

		Element dateElement = document.createElement("date");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		dateElement.appendChild(document.createTextNode(sdf.format(date)));
		albumElement.appendChild(dateElement);

		Element songsElement = document.createElement("songs");
		for (Iterator<UUID> songUUIDIter = this.songsUIDs.listIterator(); songUUIDIter.hasNext();) {
			UUID currentUUID = songUUIDIter.next();

			Element songUUIDElement = document.createElement("UUID");
			songUUIDElement.appendChild(document.createTextNode(currentUUID.toString()));
			songsElement.appendChild(songUUIDElement);
		}
		albumElement.appendChild(songsElement);

	}
}