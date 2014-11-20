//Default Class for the CS project
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CS2Project 
{
	public static void main(String[] args)
	{
		BinarySearchTree test = new BinarySearchTree();
		
		Scanner stringFile = null;
		//Open File input stream.
		try 
		{
			stringFile = new Scanner(new File("string.txt"));
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		//read in string, and then begin to start building BST
		while(stringFile.hasNext())
		{
			test.insert(stringFile.next());
		}
		
		test.setPreorder();
		test.setInorder();
		test.setPostorder();
		
		System.out.println("PreOrder: "+test.getPreorder().toString());
		System.out.println("InOrder: " +test.getInorder().toString());
		System.out.println("PostOrder: "+test.getPostorder().toString());
		
		test.treeOutput();
	}
}
