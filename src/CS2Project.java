//Default Class for the CS project
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
		BinarySearchTree test = new BinarySearchTree();
		boolean file = false;
		while(true){
			//loop until q is input.
			Scanner in = new Scanner(System.in);
			System.out.print("Enter a file name (q to quit): ");
			//String input = in.next();
			String input = "string3.txt";
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
				//write out three orders to files.
				PrintWriter preOut = new PrintWriter("preorder.txt", "UTF-8");
				PrintWriter inOut = new PrintWriter("inorder.txt", "UTF-8");
				PrintWriter postOut = new PrintWriter("postorder.txt", "UTF-8");
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
