package musichub.business;

import java.lang.Exception;
/**
* catches an Album's emptyness.
*
* @version 1.0
*
* @author Najmi Mehdi.
*/
public class NoPlayListFoundException extends Exception {
/**
	* constructor of the NoPlayListFoundException class.
	* @param Message message from AlbumException
	* @author Najmi Mehdi
	*/
	public NoPlayListFoundException (String msg) {
		super(msg);
	}
}