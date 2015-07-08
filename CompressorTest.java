import java.util.*; 
import java.io.*;
public class CompressorTest
{
public static void main(String args[]) throws Exception
{
String input ="at times a concern for wasting paper has motivated me to look for methods of fitting a lot of text onto a single sheet of paper. In grade school a friend and I used to annoy teachers by handing in homework assignments hand written with very tiny letters, so when word processors with a visual layout preview came along I lept at the opportunity to improve my abilities.For our purposes today, and in celebration of Einstein's Year of Wonders, I'll direct readers to Project Gutenberg's copy of Mr. Albert Einstein's Relativity: The Special and General Theory. The Word form has images in it which would add another layer of complexity so I'm going to work off of the text version, opened in Word. In it's present form it is 111 pages long. In the examples below I've selected a specific section of the document which will remain consistent throughout the examples to allow the reader to focus on the task at hand. Here is the plan of attack:";
Map<String,Map<String,String>> encoded = Compressor.encode(input);
String decoded = Compressor.decode(encoded);
System.out.println(encoded.get("encoded_string").get("0").length());
System.out.println(encoded.get("encoded_string").get("0"));
System.out.println(input.length());
System.out.println(decoded);
System.out.println(decoded.length());
}
}