package TextTrie;
import java.io.Serializable;
import java.util.*;

public class Node implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String value; // ciag znakowy
	private boolean endMarker; // znacznik konca slowa yes or no
	private ArrayList<Node> sufixList; // lista wezlow dzieci
	
	public Node(String value){
		this.value = value;
		this.endMarker = false;
		this.sufixList = new ArrayList<Node>();
	}
	public Node findSufix(String value){ // przeszukuje liste sufixow w poszukiwaniu podanego
		if(sufixList != null){
			for(Node n: sufixList)
				if(n.getValue().equals(value))
					return n;
			}
		return null;
	}
	public String getValue(){
		return value;
	}
	public void setEndMarker(boolean endMarker){
		this.endMarker = endMarker;
	}
	public boolean isEndMarker(){
		return endMarker;
	}
	public Node addSufix(String value){ // dodaje sufix do listy wezla
		Node n = new Node(value);
		sufixList.add(n);
		return n;
	}
	public ArrayList<Node> getSufixList(){
		return sufixList;
	}
}