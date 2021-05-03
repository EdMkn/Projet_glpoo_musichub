package musichub.business;
/** 
* allows you to know the language of the audio book.
* @version 1.0 
* 
* @author Sankara MVE.
*/
public enum Language {
	FRENCH ("french"), ENGLISH ("english"), ITALIAN ("italian"), SPANISH ("spanish"), GERMAN("german");
	private String language;
	
	/**
    * constructor of the Langue class.
    *
    * @param Nom name of the language
	* @author Sankara MVE.
	*/
	private Language (String language) {
		this.language = language;
	}
	/**
	* allows to know the name
	*
	* @return returns the name
	* @author Sankara MVE.
	*/
	public String getLanguage() {
		return language;
	}
}