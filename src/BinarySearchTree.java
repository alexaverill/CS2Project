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
			while(temp != null && bRun == true){
				if((newNode.dataString).compareTo(temp.dataString) < 0){ 
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
				else if((newNode.dataString).compareTo(temp.dataString) > 0){ 
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
	public String returnSpacing(int number){
		// loops and builds a spacing string.
		String spaces = "";
		for(int x=0; x<=number;x++){
			spaces +=" ";
		}
		return spaces;
	}
	public int[] treeHeight(){
		int treeHeight = 0;
		int[] data = new int[2];
		int numberPairs = 0;
		if(root == null)
		{
			data[0] = treeHeight;
			data[1] = numberPairs;
			return data;
		}
		
		Queue<Node> nodesQueue = new LinkedList<Node>();
		int nodesInCurrentLevel = 1;
		int nodesInNextLevel = 0;
		
		nodesQueue.add(root);
		boolean isRoot = true;
		boolean hasPartner =  false;
		while(!nodesQueue.isEmpty())
				{
					
					Node currNode = nodesQueue.poll();
					nodesInCurrentLevel--;
					
					if(currNode != null)
					{
					if(isRoot){
						treeHeight++;
						isRoot = false;
					}else{
						if(currNode.BranchType.equals("left")){
							treeHeight++;
							hasPartner = true;
						}else if(currNode.BranchType.equals("right")){
							if(hasPartner){
								numberPairs ++;
								hasPartner = false;
							}else{
								treeHeight++;
							}
						}
					}
						nodesQueue.add(currNode.leftBranch);
						nodesQueue.add(currNode.rightBranch);
						nodesInNextLevel += 2;
						//hasPartner = false;
						
					}
					
					if(nodesInCurrentLevel == 0){
						//System.out.println("");
						nodesInCurrentLevel = nodesInNextLevel;
						nodesInNextLevel = 0;
					}
				}
		data[0] = treeHeight;
		data[1] = numberPairs;
		return data;
	}
	public void treeOutput()
	{
		//we are going to assume that we have a 10 character string to start.
				//thus we can assume that the max width per level to be at minimum 20 characters.
				//however we need to have padding so if we assume that we have 10 char padding overall
				// and then a 5 char before element 1 and then 5 after element 1
				//max width then become 30 characters, so if we put the root at 15-1/2(root.length)
				
		if(root == null)
		{
			return;
		}
		
		Queue<Node> nodesQueue = new LinkedList<Node>();
		int nodesInCurrentLevel = 1;
		int nodesInNextLevel = 0;
		int[] treeInfo = treeHeight();
		int treeHeight = treeInfo[0]; // use to calculate width of window;
		int numberPairs = treeInfo[1];
		boolean isRoot = true; //need to use this to control the branch display.
		String outputString = "";
		String tmpStringData="";
		String tmpStringBranch="";
		nodesQueue.add(root);
		boolean hasPartner = false;
		System.out.print(treeHeight);
		System.out.print(numberPairs);
		int width = (treeHeight*10)/2 +(numberPairs*10);
		String currentParent="";
		while(!nodesQueue.isEmpty())
		{
			
			Node currNode = nodesQueue.poll();
			nodesInCurrentLevel--;
			
			if(currNode != null)
			{
				
				if(isRoot){
					//get length of root. 
					int len = currNode.dataString.length();
					int spacing = (width/2) - (len/2)+2;
					String spacingString = returnSpacing(spacing);
					outputString += spacingString;
					outputString += currNode.dataString;
				}else{
						if(currNode.BranchType.equals("left")){
							tmpStringBranch += returnSpacing(width/2);
							tmpStringData += returnSpacing(width/2);
							tmpStringBranch +="/";
							currentParent = currNode.dataString;
						}else if(currNode.BranchType.equals("right")){
							if((currNode.parent.dataString).equals(currentParent)){
								tmpStringBranch += returnSpacing(5);
								tmpStringData +=returnSpacing(5);
							}else{
								tmpStringBranch += returnSpacing(width/4);
								tmpStringData +=returnSpacing(width/4);
							}
							tmpStringBranch +="\\";
							tmpStringData += "";
						}
					
					tmpStringData += currNode.dataString;
				}
				//System.out.print(currNode.dataString + " ");
				//System.out.print(currNode.BranchType);
				nodesQueue.add(currNode.leftBranch);
				nodesQueue.add(currNode.rightBranch);
				nodesInNextLevel += 2;
				//hasPartner = false;
				
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
