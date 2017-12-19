/**
 * 
 */
package com.applabs;

import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;

import javax.xml.bind.DatatypeConverter;

import org.springframework.http.HttpStatus;

import com.applabs.utils.Base62;

/**
 * @author prabodh.hend
 *
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		
		String[] elements = {
                "a","b","c","d","e","f","g","h","i","j","k","l","m","n","o",
                "p","q","r","s","t","u","v","w","x","y","z","1","2","3","4",
                "5","6","7","8","9","0","A","B","C","D","E","F","G","H","I",
                "J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X",
                "Y","Z"
                };
		String arr[] = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z" };
		String url = "https://www.google.com";

		MessageDigest md;
		String myHash = "";
		try {

			md = MessageDigest.getInstance("SHA-1");

			md.update(url.getBytes());
			byte[] digest = md.digest();
			myHash = DatatypeConverter.printBase64Binary(digest);

			
			
			
			String s = Base62.encode(999999999);
			
			System.out.println(myHash);
			System.out.println(myHash.length());
			System.out.println(s);

			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
