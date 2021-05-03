package musichub.business;
/** 
* allows you to know the categorie of the song or album.
* @version 2.0 
* 
* @author Sankara MVE.
*/
public enum Category {
	YOUTH ("youth"), NOVEL ("novel"), THEATER ("theater"), DOCUMENTARY ("documentary"), SPEECH("speech");
	private String category;
	/**
    * constructor of the Categorie class.
    *
    * @param Nom name of the category
	* @author Najmi Mehdi.
	*/
	private Category (String category) {
		this.category = category;
	}
	/**
	* allows to know the category
	*
	* @return returns the categorie
	* @author Najmi Mehdi
	*/
	public String getCategory() {
		return category;
	}
}