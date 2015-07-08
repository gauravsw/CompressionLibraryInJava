import java.util.*; 
import java.io.*;
public class Compressor
{
public static String wordss;
public static Map<String,String> table = new HashMap<String,String>();

private static void init() throws Exception
{
Properties p = new Properties();
p.load(new FileInputStream("load.properties"));
wordss=p.getProperty("words");
}

private static boolean equals(String a,String b)
{
	return a.equals(b);
}

public static Map<String,Map<String,String>> encode(String input) throws Exception
{
StringBuffer inputtemp=new StringBuffer(input);
init();
String[] words = wordss.split(",");
Arrays.sort(words);
System.out.println(input.length());
System.out.println(words);
table.clear();
for(int i=0;i<words.length;i++)
{
	for(int j=0;j<input.length()-10;j++)
	{
		if(equals(input.substring(j,j+words[i].length()),words[i]))
		{
			char[] chars = new char[words[i].length()];
			Arrays.fill(chars, '|');
			String text = new String(chars);
			inputtemp.replace(j,j+words[i].length(),text);
			if(table.get(words[i])!=null)
			{
				String temp = table.get(words[i]);
				temp=temp+","+j;
				table.remove(words[i]);
				table.put(words[i],temp);
			}
			else
			{
				table.put(words[i],new Integer(j).toString());
			}
		}
	}	
}

	System.out.println(table);
	String temp = inputtemp.toString().replace("|","");
	Map<String,Map<String,String>> m = new HashMap<String,Map<String,String>>();
	Map<String,String> n = new HashMap<String,String>();
	n.put("0",temp);
	m.put("encoded_string",n);
	m.put("table",table);
	return m;	
}

public static String decode(Map<String,Map<String,String>> m) throws Exception 
{
	int len=0;
	Iterator it = m.get("table").entrySet().iterator();
	String input = m.get("encoded_string").get("0");
    while (it.hasNext()) 
	{
        Map.Entry pair = (Map.Entry)it.next();
        String key=(String)pair.getKey();
		String value=(String)pair.getValue();
		String values[]=value.split(",");
		len +=key.length()*values.length;
    }
	StringBuffer decoded = new StringBuffer(len);
	for (int i = 0; i < len+input.length(); i++)	
	{
		decoded.append(" ");
	}

	it = table.entrySet().iterator();
    while (it.hasNext()) 
	{
        Map.Entry pair = (Map.Entry)it.next();
        String key=(String)pair.getKey();
		String value=(String)pair.getValue();
		String values[]=value.split(",");
		for(int i=0;i<values.length;i++)
		{
			decoded.replace(new Integer(values[i]),new Integer(values[i])+key.length(),key);
		}
        it.remove(); // avoids a ConcurrentModificationException
    }
	
	System.out.println(decoded.length());
	for(int i=0,j=0;j<decoded.length();j++)
	{
		if(decoded.charAt(j)==' ' && i<input.length())
		{
			decoded.replace(j,j+1,new Character(input.charAt(i++)).toString());
		}
	}
return decoded.toString();
}

}