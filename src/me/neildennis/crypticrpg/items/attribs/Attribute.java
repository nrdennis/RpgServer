package me.neildennis.crypticrpg.items.attribs;

public enum Attribute {
	
	DAMAGE("DMG: ", ""),
	HEALTH("HEALTH: ", "");
	
	private String prefix, postfix;
	
	Attribute(String prefix, String postfix){
		this.prefix = prefix;
		this.postfix = postfix;
	}
	
	public String getPrefix(){
		return prefix;
	}
	
	public String getPostfix(){
		return postfix;
	}
	
	public static Attribute fromPrefix(String str){
		for (Attribute attr : Attribute.values())
			if (str.startsWith(attr.getPrefix()))
				return attr;
		return null;
	}

}