package musichub.business;

import java.lang.Exception;
/**
* catches an Album's emptyness.
*
* @version 1.0
*
* @author MAKON Manyim Ma.
*/
public class NoAlbumFoundException extends Exception {
/**
	* constructor of the NoAlbumFoundException class.
	* @param Message message from AlbumException
	* @author MAKON Manyim Ma
	*/
	public NoAlbumFoundException (String msg) {
		super(msg);
	}
}