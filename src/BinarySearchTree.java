import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


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
				if((newNode.dataString.toUpperCase()).compareTo(temp.dataString.toUpperCase()) < 0){ 
					if(temp.leftBranch == null){
						//add the data to the left branch of the BST if there is no left branch
						temp.leftBranch = newNode;
						newNode.parent = temp;
						newNode.BranchType = "left";
						bRun = false;
					}
					else{
						temp = temp.leftBranch;	
					}
				}
				else if((newNode.dataString.toUpperCase()).compareTo(temp.dataString.toUpperCase()) > 0){ 
					if(temp.rightBranch == null){
						//add to the right branch when it is empty
						temp.rightBranch = newNode;
						newNode.parent = temp;
						newNode.BranchType = "right";
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
	
	public ArrayList getPreorder()
	{
		return preorderList;
	}
	
	public ArrayList getInorder()
	{
		return inorderList;
	}
	
	public ArrayList getPostorder()
	{
		return postorderList;
	}
	public String returnBranch(Node node){
		String output= "";
		if(node.leftBranch != null && node.rightBranch != null){
			//has two branches
			output +="/  \\"; 
		}else if(node.leftBranch != null && node.rightBranch == null){
			//only has a left branch
			//System.out.println();
			output +="/";
		}else if(node.leftBranch == null && node.rightBranch != null){
			//only has a right branch
			
			output += "\\";
		}
		return output;
	}
	public void treeOutput()
	{
		if(root == null)
		{
			return;
		}
		
		Queue<Node> nodesQueue = new LinkedList<Node>();
		int nodesInCurrentLevel = 1;
		int nodesInNextLevel = 0;
		boolean isRoot = true; //need to use this to control the branch display.
		String outputString = "";
		String tmpStringData="";
		String tmpStringBranch="";
		nodesQueue.add(root);
		
		while(!nodesQueue.isEmpty())
		{
			Node currNode = nodesQueue.poll();
			nodesInCurrentLevel--;
			
			if(currNode != null)
			{
				
				if(isRoot){
					outputString += currNode.dataString;
				}else{
						if(currNode.BranchType.equals("left")){
							tmpStringBranch +="/   ";
						}else if(currNode.BranchType.equals("right")){
							tmpStringBranch +=" \\";
							tmpStringData += " ";
						}
					
					tmpStringData += currNode.dataString;
				}
				//System.out.print(currNode.dataString + " ");
				//System.out.print(currNode.BranchType);
				nodesQueue.add(currNode.leftBranch);
				nodesQueue.add(currNode.rightBranch);
				nodesInNextLevel += 2;
				
			}
			
			if(nodesInCurrentLevel == 0)
			{
				if(!isRoot){
					outputString = tmpStringBranch +"\n"+tmpStringData;
					tmpStringData = "";
					tmpStringBranch = "";
				}
				System.out.println(outputString);
				isRoot = false;
				//System.out.println("");
				nodesInCurrentLevel = nodesInNextLevel;
				nodesInNextLevel = 0;
			}
		}
	}
}
