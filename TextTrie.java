package TextTrie;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.io.StreamTokenizer;

import com.thoughtworks.xstream.*;

public class TextTrie implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Trie trie;
	
	public TextTrie(){
		trie = new Trie("");
	}
	
	public static void main(String[] args) throws IOException, ClassNotFoundException{
		
		TextTrie trie = new TextTrie();
		trie.loadText("test.txt");
		trie.saveTrieSerial("trie.out");
		trie.loadTrieSerial("trie.out");
		boolean a = trie.search("Let");
		System.out.println(a);
	}
	
	public void insert(String s) {
		trie.Insert(toArray(s));
	}
	
	public boolean search(String s) {
		return trie.Search(toArray(s));
	}
	
	public void loadText(String filename) throws IOException{
		StreamTokenizer input = new StreamTokenizer(new FileReader(filename));
		input.whitespaceChars('!', '@');
		input.whitespaceChars('[', '`');
		input.whitespaceChars('{', '~');
		while((input.nextToken())!= StreamTokenizer.TT_EOF)
		{
			this.insert(input.sval);
		}
	}
	
	public void saveTrie(String filename) throws IOException{
		OutputStream output = new FileOutputStream(filename);
		XStream xstream = new XStream();
		xstream.toXML(this, output); 
	}
	public void saveTrieSerial(String filename) throws FileNotFoundException, IOException{
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
		out.writeObject(trie);
		out.close();
	}
	public void loadTrieSerial(String filename) throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));
		in.readObject();
		in.close();
	}
	
	public void loadTrie(String filename){
		File file = new File(filename);
		XStream xstream = new XStream();
		xstream.fromXML(file, this);
	}
	
	private String[] toArray(String word){
		 String[] tab = new String[word.length()];
			for(int i=0; i<word.length(); i++)
				tab[i] = Character.toString(word.charAt(i));
			return tab;
	}
}
