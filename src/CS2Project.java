/*
 * 
 * This is the CS2 Binary Search Tree project.
 * Written by:
 * Alex Averill
 * James Campbell
 * Travis Wright
 * 
 * 
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class CS2Project 
{
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException
	{
		
		boolean file = false;
		while(true){
			BinarySearchTree test = new BinarySearchTree();
			//loop until q is input.
			Scanner in = new Scanner(System.in);
			System.out.print("Enter a file name (q to quit): ");
			String input = in.next();
			//String input = "string.txt";
			Scanner stringFile = null;
			
			if(input.equals("q")|| input.equals("Q") || input.equals("quit")){
				System.exit(0);
			}
			//Open File input stream.
			try 
			{
				stringFile = new Scanner(new File(input));
				file = true;
			} 
			catch (FileNotFoundException e) 
			{
				System.out.println("Invalid File!");
				file = false;
				//e.printStackTrace();
			}
			if(file){
			//read in string, and then begin to start building BST
				while(stringFile.hasNext())
				{
					test.insert(stringFile.next());
				}
				if(test.isBST){
					test.setPreorder();
					test.setInorder();
					test.setPostorder();
					//write out three orders to files.
					PrintWriter preOut = new PrintWriter("preorder.out", "UTF-8");
					PrintWriter inOut = new PrintWriter("inorder.out", "UTF-8");
					PrintWriter postOut = new PrintWriter("postorder.out", "UTF-8");
					preOut.println(test.getPreorder().toString());
					preOut.close();
					inOut.println(test.getInorder().toString());
					inOut.close();
					postOut.println(test.getPostorder().toString());
					postOut.close();
					System.out.println("PreOrder: "+test.getPreorder().toString());
					System.out.println("InOrder: " +test.getInorder().toString());
					System.out.println("PostOrder: "+test.getPostorder().toString());
					//test.dfsOutput();
					try {
						test.treeOutput();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
}
