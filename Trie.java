package TextTrie;
import java.io.Serializable;

public class Trie implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Node root;
	public Trie(){
		root = null;
	}
	public Trie(String rootValue){
		root = new Node(rootValue);
	}
	
	public void Insert(String[] values){
		Node current = root; // obecny wezel
		if(values != null)
		{
			if(values.length == 0)
				current.setEndMarker(true); // znakuje znacznik konca slowa
		for(int i=0; i<values.length; i++)
			{
				Node sufiks = current.findSufix(values[i]);
				if(sufiks != null)
					current = sufiks;
				else
					current = current.addSufix(values[i]);
				if(i == values.length - 1)
					if(!current.isEndMarker())
						current.setEndMarker(true);
			}
		}
		else System.out.println("Null string given **ERROR*** ");
	}
	public boolean Search(String[] values){
		Node current = root;
		for(int i=0; i<values.length; i++)
			if(current.findSufix(values[i]) == null)
				return false;
			else
				current = current.findSufix(values[i]); // rozpocznie przeszukiwanie od znalezionego wezla
		if(current.isEndMarker())
			return true;
		else
			return false;
	}
}


