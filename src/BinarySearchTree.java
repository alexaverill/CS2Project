import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


public class BinarySearchTree {
	private Node root;
	private ArrayList preorderList = new ArrayList();
	private ArrayList inorderList = new ArrayList();
	private ArrayList postorderList = new ArrayList();
	//Config variables:
	int stringMax = 10; //use this change the max length of strings, CAUTION FORMATTING MAY BE AFFECTED!	
	int stringSpacing = stringMax;
	int levelMax = 0;
	private static class Node{
		Node leftBranch;
		Node rightBranch;
		String dataString;
		Node parent;
		String BranchType; //is this a left or right branch of parent
		int level;
		String overallType;
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
			newNode.level = 0;
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
						newNode.level = (temp.level) + 1; //keep track of level
						if(newNode.level == 1){
							newNode.overallType= "left";
						}else{
							newNode.overallType = newNode.parent.overallType;
						}
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
						newNode.level = (temp.level)+1;
						if(newNode.level == 1){
							newNode.overallType= "right";
						}else{
							newNode.overallType = newNode.parent.overallType;
						}
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
		
		if(this.levelMax <= curr.level)
		{
			levelMax = curr.level;
		}
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
	public int treeHeight(){
		int treeHeight = 0;
		if(root == null)
		{
			return treeHeight;
		}
		
		inorderRec(root);
		treeHeight = levelMax;
		
		return treeHeight;
	}

	public void dfsOutput(){
		ArrayList dfs = this.getPreorder();
		for(int x=0;x<dfs.size(); x++){
			String current = dfs.get(x).toString();
			System.out.println(current);
		}
	}
	public String returnDashes(int number){
		String spaces = "";
		for(int x=0; x<=number;x++){
			spaces +="-";
		}
		return spaces;
	}
	public void treeOutput() throws IOException
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
		int treeHeight = treeHeight(); // use to calculate width of window;
		int wF;//formula for the width, based on trial and error 
		// we need to pad each string to be 10 chars as well
		boolean isRoot = true; //need to use this to control the branch display.
		String outputString = "";
		String tmpStringData="";
		String tmpBranch="";
		String previousRoot="";
		nodesQueue.add(root);
		boolean hasPartner = false;
		int width = (treeHeight*45);
		//System.out.print(width);
		int numLevel = 1;
		int count = 0;
		boolean emptyLeft = false;
		String whichside = "left";
		Writer treeOut = null;
		try {
			treeOut = new FileWriter("treeout.txt");
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(!nodesQueue.isEmpty())
		{
			
			Node currNode = nodesQueue.poll();
			nodesInCurrentLevel--;
			
			if(currNode != null)
			{
				int currentLevel = currNode.level;
				if(isRoot){
					//get length of root. 
					width = width/4;
					int len = currNode.dataString.length();
					outputString += returnSpacing(width/2+width/4);
					treeOut.write(returnSpacing(width/2+width/4));
					outputString += (currNode.dataString);
					treeOut.write(currNode.dataString);
				}else{
					if(currNode.BranchType.equals("right")&& count == 0 && currNode.overallType.equals("left")){
						//System.out.print("lol");
						tmpBranch += (returnSpacing(width/6+2));
						outputString += (returnSpacing(width/6+2));
						treeOut.write(returnSpacing(width/4));
					}
					if(emptyLeft){
						if(currNode.BranchType.equals("left")){
							tmpBranch +=(returnSpacing((width*currentLevel*4-this.stringMax/4)));
							
							outputString += (returnSpacing((width*currentLevel*4)));
							treeOut.write(returnSpacing((width*currentLevel*4)));
							//System.out.print(returnSpacing((width)));
						}
						if(currNode.BranchType.equals("right") && currNode.parent.leftBranch == null){
							tmpBranch +=(returnSpacing((width*currentLevel)*2+this.stringMax));
							//System.out.println(returnSpacing((width*currentLevel)*2+this.stringMax)+"\\");
							outputString += (returnSpacing((width*currentLevel)*2+this.stringMax));
							treeOut.write(returnSpacing((width*currentLevel)*2+this.stringMax));
							
						}
						if(currNode.BranchType.equals("right")&& currNode.parent.BranchType.equals("right") && currNode.parent.leftBranch == null){
							tmpBranch += (returnSpacing((width*currentLevel)*7+this.stringMax));
							outputString += (returnSpacing((width*currentLevel)*7+this.stringMax));
							treeOut.write(returnSpacing((width*currentLevel)*7+this.stringMax));
						}
					}
					if((currNode.BranchType.equals("left") && count == 0 && currNode.overallType.equals("right"))){
						//if we are a left branch on the RIGHT side of the root, and the first one being printed in this level.
						tmpBranch +=(returnSpacing((width*2)*2));
						//tmpBranch += "/";
						emptyLeft = true;
						outputString += (returnSpacing((width*2)*2-this.stringMax));
						treeOut.write(returnSpacing((width*2)*2-this.stringMax));
					}
					
					
					int spacing = (int) Math.pow(2,currentLevel)+1;
					if((currNode.BranchType.equals("left") && currNode.overallType.equals(whichside))&& currNode.level !=1){
						//left branch of tree, and the same side of the tree of the last branch, and its not the first child
						tmpBranch +=(returnSpacing(width/6+2));
						outputString += (returnSpacing(width/6+2));
						treeOut.write(returnSpacing(width/6+2));
					}else{
						//outputString +="/\\"+"\n";
						tmpBranch +=(returnSpacing(width-2));
						outputString += (returnSpacing(width-2));
						treeOut.write(returnSpacing(width-2));
					}
					int side = this.stringMax -currNode.dataString.length();
					int r = side/2;
					int l = side/2;
					//System.out.print(returnSpacing(r)+currNode.dataString+returnSpacing(l));
					if(currNode.BranchType.equals("left")){
						tmpBranch += "/";
					}else{
						tmpBranch += "\\";
					}
					outputString += (currNode.dataString);
					treeOut.write(currNode.dataString);
					if(currNode.BranchType.equals("left")&& currNode.parent.rightBranch == null){
						//left branch with no sibling. add a spacing area after so right overall branch is working.
						tmpBranch += (returnSpacing(width+this.stringMax/6));
						outputString += (returnSpacing(width+this.stringMax/6));
						treeOut.write(returnSpacing(width+this.stringMax/6));
					}
					if(currNode.BranchType.equals("left") && currNode.parent.rightBranch != null){
						tmpBranch += returnSpacing(currentLevel/4);
						//System.out.print(returnSpacing(width/3));
					}else{
						tmpBranch += returnSpacing((width/4));
					}
					count++;
					whichside = currNode.overallType;
				}
				nodesQueue.add(currNode.leftBranch);
				nodesQueue.add(currNode.rightBranch);
				//previousRoot = currNode.dataString;
				//if(currentLevel< (treeHeight+1)/2){
					
				//}
			
				nodesInNextLevel += 2;		
			}
			if(nodesInCurrentLevel == 0)
			{
				System.out.println(tmpBranch);
				System.out.println(outputString);
				outputString = "";
				tmpBranch="";
				treeOut.write("\n");
				isRoot = false;
				//System.out.println("");
				nodesInCurrentLevel = nodesInNextLevel;
				nodesInNextLevel = 0;
				numLevel = 1;
				count =0;
				width = width/2;
			}
		}
		treeOut.close();
	}
}
