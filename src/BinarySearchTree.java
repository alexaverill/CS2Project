
public class BinarySearchTree {
	private Node root;
	
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
			while(temp != null && !((newNode.dataString).equals(temp.dataString)) && bRun == true){
				if((newNode.dataString).compareTo(temp.dataString) < 0){ //fix string comparisons
					if(temp.leftBranch == null){
						temp.leftBranch = newNode;
						newNode.parent = temp;
						bRun = false;
					}
					else{
						temp = temp.leftBranch;	
					}
				}
				else if((newNode.dataString).compareTo(temp.dataString) > 0){ //fix string comparison
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
}
