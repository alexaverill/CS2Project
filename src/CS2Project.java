//Default Class for the CS project
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CS2Project 
{
	public static void main(String[] args)
	{
		BinarySearchTree test = new BinarySearchTree();
		boolean file = false;
		while(true){
			//loop until q is input.
			Scanner in = new Scanner(System.in);
			System.out.print("Enter a file name (q to quit): ");
			String input = in.next();
			Scanner stringFile = null;
			if(input.equals("q")|| input.equals("Q") || input.equals("quit")){
				break;
			}
			//Open File input stream.
			try 
			{
				stringFile = new Scanner(new File(input));
				file = true;
			} 
			catch (FileNotFoundException e) 
			{
				System.out.print("Invalid File!");
				file = false;
				//e.printStackTrace();
			}
			if(file){
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
				//test.dfsOutput();
				test.treeOutput();
			}
		}
	}
}
