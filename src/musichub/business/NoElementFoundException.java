package musichub.business;

import java.lang.Exception;
/**
* catches an Album's emptyness.
*
* @version 1.0
*
* @author MAKON Manyim.
*/
public class NoElementFoundException extends Exception {
/**
	* constructor of the NoElementFoundException class.
	* @param msg message from AlbumException
	* @author MAKON Manyim Ma
	*/
	public NoElementFoundException (String msg) {
		super(msg);
	}
}