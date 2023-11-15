/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package qa.all.afaanoromoo;

/**
 *
 * @author Nuguse
 */
public class NameNormalizer {
    public String Suffremoved(String name)
	{
		if (name.endsWith("f")||name.endsWith("n"))
		{
			name=name.replace("f", "");
                        name=name.replace("", "");
		}
		return name;
	}
	public String Prefixremoved(String name)
	{
		if (name.startsWith("kan")||name.startsWith("un")||name.startsWith("in")
				||name.startsWith("on"))
		{
			
		}
		return name;
	}
public static void main(String [] args)
{
	NameNormalizer normname=new NameNormalizer();
	System.out.print(normname.Suffremoved("Caalan"));
}
}
    

