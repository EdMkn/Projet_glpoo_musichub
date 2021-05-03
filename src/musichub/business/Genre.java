package musichub.business;
/** 
* allows you to know the genre of the song or album.
* @version 2.0 
* 
* @author Sankara MVE.
*/
public enum Genre {
	JAZZ ("jazz"), CLASSIC ("classic"), HIPHOP ("hiphop"), ROCK ("rock"), POP("pop"), RAP("rap");
	private String genre;
	/**
    * constructor of the Genre class.
    *
    * @param Nom name of the genre
	* @author Sankara MVE.
	*/
	private Genre (String genre) {
		this.genre = genre;
	}
	/**
	* allows to know the name
	*
	* @return returns the name
	* @author Sankara MVE.
	*/
	public String getGenre() {
		return genre;
	}
}