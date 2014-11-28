import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


public class BinarySearchTree {
	private Node root;
	private ArrayList preorderList = new ArrayList();
	private ArrayList inorderList = new ArrayList();
	private ArrayList postorderList = new ArrayList();
	//Config variables:
	int stringMax = 8; //use this change the max length of strings, CAUTION FORMATTING MAY BE AFFECTED!	
	int stringSpacing = stringMax/6;
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
	public void dfsOutput(){
		ArrayList dfs = this.getPreorder();
		for(int x=0;x<dfs.size(); x++){
			String current = dfs.get(x).toString();
			System.out.println(current);
		}
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
		int wF;//formula for the width, based on trial and error 
		// we need to pad each string to be 10 chars as well
		int strLen;
		int toFull;
		int endW;
		boolean isRoot = true; //need to use this to control the branch display.
		String outputString = "";
		String tmpStringData="";
		String tmpStringBranch="";
		String previousRoot="";
		nodesQueue.add(root);
		boolean hasPartner = false;
		int width = (treeHeight*10)/2 +(numberPairs*10);
		int numLevel = 1;
		String lastParent="";
		while(!nodesQueue.isEmpty())
		{
			
			Node currNode = nodesQueue.poll();
			nodesInCurrentLevel--;
			
			if(currNode != null)
			{
				
				if(isRoot){
					//get length of root. 
					int len = currNode.dataString.length();
					//int spacing = ();
					String spacingString = returnSpacing(width/2);
					outputString += spacingString;
					outputString += currNode.dataString;
					previousRoot = currNode.dataString;
				}else{
						
						if(currNode.dataString.equals("ccc")){
							//System.out.print(numLevel);
							//System.out.print("test l"+currNode.parent.dataString);
						}
						if(currNode.BranchType.equals("left")){
							if(currNode.parent.dataString.equals(previousRoot)){
								wF = width/2; //formula for the width, based on trial and error 
								// we need to pad each string to be 10 chars as well
								if(numLevel >= 2){
									wF = 2;
									strLen = (currNode.dataString.toString()).length();
									endW = wF;
									tmpStringBranch += returnSpacing(endW);
									tmpStringData +=returnSpacing(endW);
									tmpStringBranch +="/"+returnSpacing(this.stringSpacing );
									width -=3;
								}else{
									strLen = (currNode.dataString.toString()).length();
									endW = wF;//-(strLen/2);
									tmpStringBranch += returnSpacing(endW);
									tmpStringData +=returnSpacing(endW-strLen/2);
									tmpStringBranch +="/"+returnSpacing(this.stringSpacing );
									width -=3;
								}
							}else{
								/*tmpStringBranch += returnSpacing(width/2+width/6);
								tmpStringData += returnSpacing(width/2+width/8);*/
								wF = width/2; //+width/6; //formula for the width, based on trial and error 
								// we need to pad each string to be 10 chars as well
								if(numLevel >= 2){
									wF= 2;
									strLen = (currNode.dataString.toString()).length();
									endW = wF;
									tmpStringBranch += returnSpacing(endW);
									tmpStringData +=returnSpacing(endW-(strLen/2));
									tmpStringBranch +="/"+returnSpacing(this.stringSpacing);
									width -=3;
								}else if(numLevel < 2){
										wF = width/2+width/6; //formula for the width, based on trial and error 
										// we need to pad each string to be 10 chars as well
										strLen = (currNode.dataString.toString()).length();
										endW = wF-(strLen/2);
										tmpStringBranch += returnSpacing(endW);
										tmpStringData +=returnSpacing(endW-(strLen/2));
										tmpStringBranch +="/"+returnSpacing(this.stringSpacing);
										//width -=5;
								}else{
									strLen = (currNode.dataString.toString()).length();
									endW = wF;//-(strLen/2);
									tmpStringBranch += returnSpacing(endW);
									tmpStringData +=returnSpacing(endW-strLen/2);
									tmpStringBranch +="/"+returnSpacing(this.stringSpacing);
									width -=3;
								}
							}
						}else if(currNode.BranchType.equals("right")){
								if(currNode.parent.leftBranch !=null){
									tmpStringBranch += returnSpacing(1);
									tmpStringData +=returnSpacing(1);
								}else{
									if(currNode.parent.dataString.equals(previousRoot)){
										wF = width/4; //formula for the width, based on trial and error 
										// we need to pad each string to be 10 chars as well
										strLen = (currNode.dataString.toString()).length();
										endW = wF-(strLen/2);
										if(numLevel < 2){
											tmpStringBranch += returnSpacing(endW);
											tmpStringData +=returnSpacing(endW-(strLen/2));
										}
											//width -=5;
									}else{
										wF = width/2+width/6; //formula for the width, based on trial and error 
										// we need to pad each string to be 10 chars as well
										strLen = (currNode.dataString.toString()).length();
										endW = wF-(strLen/2);
										//tmpStringBranch += returnSpacing(endW);
										//tmpStringData +=returnSpacing(endW);
										if(numLevel < 2){
											tmpStringBranch += returnSpacing(endW);
											tmpStringData +=returnSpacing(endW-(strLen/2));
										}
										//width -=5;
									}
								}
							tmpStringBranch +="\\";
							tmpStringData += "";
						}
						strLen = (currNode.dataString.toString()).length();
						toFull = this.stringMax -strLen;
						
						//System.out.print(numLevel);
						if(numLevel > 1 ){
							tmpStringData += currNode.dataString;
						}else{
							tmpStringData += currNode.dataString+returnSpacing(stringSpacing);
						}
						numLevel++;
				}
				//System.out.print(currNode.dataString + " ");
				//System.out.print(currNode.BranchType);
				nodesQueue.add(currNode.leftBranch);
				nodesQueue.add(currNode.rightBranch);
				//previousRoot = currNode.dataString;
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
				numLevel = 1;
			}
		}
	}
}
