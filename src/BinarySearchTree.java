import java.util.ArrayList;


public class BinarySearchTree {
	private Node root;
	private ArrayList preorderList = new ArrayList();
	private ArrayList inorderList = new ArrayList();
	private ArrayList postorderList = new ArrayList();
	
	private static class Node{
		Node leftBranch;
		Node rightBranch;
		String dataString;
		Node parent;
		String BranchType; //is this a left or right branch of parent
		
		Node (String stringInput){
			leftBranch = null;
			rightBranch = null;
			parent = null;
			dataString = stringInput;
			
		}
	}
	public BinarySearchTree(){
		root = null; //Create a blank binary search tree;
	}
	public void insert(String data){
		Node newNode = new Node(data);
		Node temp = root;
		
		boolean bRun = true;
		
		if(root  == null){
			//if the bst is not yet created
			root = newNode; //root
		}
		else{
			//loop through tree
			while(temp != null && !((newNode.dataString.toUpperCase()).equals(temp.dataString.toUpperCase())) && bRun == true){
				if((newNode.dataString.toUpperCase()).compareTo(temp.dataString.toUpperCase()) < 0){ //fix string comparisons
					if(temp.leftBranch == null){
						temp.leftBranch = newNode;
						newNode.parent = temp;
						bRun = false;
					}
					else{
						temp = temp.leftBranch;	
					}
				}
				else if((newNode.dataString.toUpperCase()).compareTo(temp.dataString.toUpperCase()) > 0){ //fix string comparison
					if(temp.rightBranch == null){
						temp.rightBranch = newNode;
						newNode.parent = temp;
						bRun = false;
					}
					else{
						temp = temp.rightBranch;
					}
				}
			}
		}
	}
	
	public void setPreorder()
	{
		preorderRec(this.root);
	}
	
	public void setInorder()
	{
		inorderRec(this.root);
	}
	
	public void setPostorder()
	{
		postorderRec(this.root);
	}
	
	private void preorderRec(Node curr)
	{
		if(curr == null)
		{
			return;
		}
		
		preorderList.add(curr.dataString);
		preorderRec(curr.leftBranch);
		preorderRec(curr.rightBranch);
	}
	
	private void inorderRec(Node curr)
	{
		if(curr == null)
		{
			return;
		}
		
		inorderRec(curr.leftBranch);
		inorderList.add(curr.dataString);
		inorderRec(curr.rightBranch);
	}
	
	private void postorderRec(Node curr)
	{
		if(curr == null)
		{
			return;
		}
		
		postorderRec(curr.leftBranch);
		postorderRec(curr.rightBranch);
		postorderList.add(curr.dataString);
	}
	
	public void getPreorder()
	{
		for(int i = 0; i<(this.preorderList.size()); i++)
		{
			System.out.print(this.preorderList.get(i) + ", ");
		}
		
		System.out.println("");
	}
	
	public void getInorder()
	{
		for(int i = 0; i<(this.inorderList.size()); i++)
		{
			System.out.print(this.inorderList.get(i) + ", ");
		}
		
		System.out.println("");
	}
	
	public void getPostorder()
	{
		for(int i = 0; i<(this.postorderList.size()); i++)
		{
			System.out.print(this.postorderList.get(i) + ", ");
		}
		
		System.out.println("");
	}
}
