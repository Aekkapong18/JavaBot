/**
* File: google.java
* Author: zifnab [at] zifnab06 [dot] net
* License: cc-by-nc
* usage: google.search("Search Terms");
* returns: URL in String format, or "No Results Found!"
* requires: org.json package. 
*/
import org.json.JSONObject;
import org.json.JSONArray;
import java.net.*;
import java.io.*;

public class google{
	public static String[] search(String terms){
		String key = "ABQIAAAA6KnsrpTIyjEMJ1EqHjKG_xRBaorPdDj7IJd2xMtxtE9DwSoKhRQgazFCenlI-wnGV1574jW06163iw";
		String ip = "173.255.208.207";
		System.out.println("Google-ing: " + terms);
		terms = terms.replace(' ', '+');
		try {
			//Opens URL
			URL googlesearch = new URL("http://ajax.googleapis.com/ajax/services/search/web?v=1.0&" + "q=" + terms + "&key=" + key + "&userip=" + ip);
			URLConnection connection = googlesearch.openConnection();
			connection.addRequestProperty("Referer", "zifnab06.net");
			//Read in JSON crap from URL.
			String line;
			StringBuilder results = new StringBuilder();
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			while((line = reader.readLine()) != null) {
				results.append(line + "\n");
			}
			//JSON Crap. Google needs to use XML....
			JSONObject json = new JSONObject(results.toString());
			JSONArray ja = json.getJSONObject("responseData").getJSONArray("results");
			JSONObject j = ja.getJSONObject(0);
			//Returns Title: URL
			String[] ret = {j.toString(), j.getString("titleNoFormatting") + ": " + j.getString("url")};
			return ret;
			} catch (MalformedURLException e) {
				//Shouldn't EVER hit this.
				System.out.println(e);
				String[] ret = {"error", e.toString()};
				return(ret);
			} catch (IOException e) {
				//Shouldn't EVER hit this.
				System.out.println(e);
				String[] ret = {"error", e.toString()};
				return(ret);

			} catch (Exception e) {
				System.out.println(e);
				String[] ret =  {"error","No results found!"};
				return ret;
		}
	}
}

