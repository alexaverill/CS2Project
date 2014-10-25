//Default Class for the CS project
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class CS2Project {
	private static String preOrder(){
		//take in a BST and return the preorder transversal
		String out = "Placeholder"; // this will be the ordered output from BST
		return out;
	}
	private static String inOrder(){
		//take in a BST and return the in order transversal
		String out = "Placeholder"; // this will be the ordered output from BST
		return out;
	}
	private static String postOrder(){
		//take in a BST and return the post order transversal
		String out = "Placeholder"; // this will be the ordered output from BST
		return out;
	}
	public static void main(String[] args){
		//Open File input stream.
		try {
			Scanner stringFile = new Scanner(new File("string.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		//read in string, and then begin to start building BST
	}
}
