/*
 * Programmer: Maksim Shishkov
 * Chemeketa Community College 
 * Created: 5/23/2013
 * Class: CIS234J
 * Assignment: MadLib
 * File Name: Talk.java
 * Description: Speech synthesizer
 * 
 */


import com.sun.speech.freetts.ValidationException;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.audio.JavaClipAudioPlayer;
import com.sun.speech.freetts.en.us.CMULexicon;
import de.dfki.lt.freetts.en.us.MbrolaVoice;
import de.dfki.lt.freetts.en.us.MbrolaVoiceValidator;



/**
 * The speaker method is static and therefore all you need to do is to include
 * class in the package with your other classes and call the speaker() method
 * by using the class object and pass in the string you want 
 * spoken as in this example:
 *         Talk.speaker("some string");
 *@param The class holds one variable type Voice. The variable is added to
 *       allow other classe to edit the synthesizer.
 */

public class Talk 
{
	Voice helloVoice;
	Talk()
	{
		try 
		{
			String voiceClassName =
				"com.sun.speech.freetts.en.us.CMUDiphoneVoice";
			Class voiceClass = Class.forName( voiceClassName);
			
			// instantiate the Voice
			
			 helloVoice = (Voice)voiceClass.newInstance();
			
			if (helloVoice instanceof MbrolaVoice) 
			{
				try 
				{
					(new MbrolaVoiceValidator( (MbrolaVoice) helloVoice)).
					validate();
				} 
				catch (ValidationException ve) 
				{
					System.err.println( ve.getMessage());
					throw new IllegalStateException
						( "Problem starting MBROLA voice");
				}
			}
			
			// sets the lexicon to CMU lexicon
			helloVoice.setLexicon( new CMULexicon());
			
			// sets the AudioPlayer to the Java clip player
			helloVoice.setAudioPlayer( new JavaClipAudioPlayer());
			
			helloVoice.setPitch( 150.0f);
			helloVoice.setRate( 120.0f);
			// loads the Voice, which mainly is loading the lexicon
			helloVoice.load();
			

		} 
		
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	
	

}
