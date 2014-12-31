
public class BinaryTree {
	
	private Node rootNode = null;
	
	public static void main(String[] args) {
		
		BinaryTree bt = new BinaryTree();
		
		bt.addNode(bt.getRootNode(), 10, "Dan");
		bt.addNode(bt.getRootNode(), 2, "Ashley");
		bt.addNode(bt.getRootNode(), 30, "Mike");
		bt.addNode(bt.getRootNode(), 100, "Frank");
		bt.addNode(bt.getRootNode(), 14, "Tim");
		bt.addNode(bt.getRootNode(), 12, "Megan");
		
		//System.out.println(bt.findNode(2) != null ? "FOUND" : "NOT FOUND"); //FOUND
		//System.out.println(bt.findNode(100) != null ? "FOUND" : "NOT FOUND"); //FOUND
		//System.out.println(bt.findNode(1000) != null ? "FOUND" : "NOT FOUND"); //NOT FOUND
		//System.out.println(bt.findNode(3) != null ? "FOUND" : "NOT FOUND"); //NOT FOUND
		
		
		bt.deleteNode(30);
		
		bt.preorder(bt.getRootNode());
		//bt.inorder(bt.getRootNode());
		//bt.postorder(bt.getRootNode());
	}
	
	public Node findNode(int index) {
		
		Node focusNode = this.getRootNode();
		
		boolean found = false;
		
		while(focusNode != null && !found) {
				
			if(focusNode.getIndex() == index) {
				
				found = true;
				
			} else if(focusNode.getIndex() > index) {
				
				focusNode = focusNode.getLeftNode();
				
			} else {
				
				focusNode = focusNode.getRightNode();
			}	
		}
			
		return focusNode;
	}
	
	public void deleteNode(Node node) {
		
		Node focusNode = this.getRootNode();
		Node parentNode = this.getRootNode();
		
		boolean isItALeftChild = true;
		
		while(focusNode != null && focusNode.getIndex() != node.getIndex()) {
			
			parentNode = focusNode;
			
			if(node.getIndex() < focusNode.getIndex()) {
				
				isItALeftChild = true;
				
				focusNode = focusNode.getLeftNode();
				
			} else {
				
				isItALeftChild = false;
				
				focusNode = focusNode.getRightNode();
				
			}
			
		}
		
		if(focusNode != null) {
		
			if(focusNode.getLeftNode() == null && focusNode.getRightNode() == null) {
				if(focusNode == this.getRootNode()) {
					
					this.setRootNode(null);
					
				} else if(isItALeftChild) {
					
					parentNode.setLeftNode(null);
				
				} else {
					
					parentNode.setRightNode(null);
				
				}
			} else if (focusNode.getLeftNode() == null) {
				
				if(focusNode == this.getRootNode()) {
					
					this.setRootNode(focusNode.getRightNode());
					
				} else if(isItALeftChild) {
					
					parentNode.setLeftNode(focusNode.getRightNode());
					
				} else {
					
					parentNode.setRightNode(focusNode.getRightNode());
					
				}				
				
			} else if (focusNode.getRightNode() == null) {
				
				if(focusNode == this.getRootNode()) {
					
					this.setRootNode(focusNode.getLeftNode());
					
				} else if(isItALeftChild) {
					
					parentNode.setLeftNode(focusNode.getLeftNode());
					
				} else {
					
					parentNode.setRightNode(focusNode.getLeftNode());
					
				}
				
			} else {
				
				Node replacement = getReplacementNode(focusNode);
				
				if(focusNode == this.getRootNode()) {
					
					this.setRootNode(replacement);
					
				} else if (isItALeftChild) {
					
					parentNode.setLeftNode(replacement);
					
				} else {
					
					parentNode.setRightNode(replacement);
					
				}
				
				replacement.setLeftNode(focusNode.getLeftNode());
				
			}
			
		}
		
	}
	
	private Node getReplacementNode(Node replacedNode) {
		
		Node replacementParent = replacedNode;
		Node replacement = replacedNode;
		
		Node focusNode = replacedNode.getRightNode();
		
		while(focusNode != null) {
			
			replacementParent = replacement;
			replacement = focusNode;
			focusNode = focusNode.getLeftNode();
			
		}
		
		if(replacement != replacedNode.getRightNode()) {
			
			replacementParent.setLeftNode(replacement.getRightNode());
			replacement.setRightNode(replacedNode.getRightNode());
			
		}
		
		return replacement;
		
	}

	public void deleteNode(int index) {
		
		Node node = findNode(index);
		
		//while(node != null) {
			
			deleteNode(node);
			//node = findNode(index);
			
		//}
	}
	
	
	
	//insert new node. all insertions will create leaf nodes
	public Node addNode(Node focusNode, int index, String info) {
		
		//if root node is null, first set root.
		if(this.getRootNode() == null) {
			Node newNode = new Node(index, info);
			this.setRootNode(newNode);
			return this.getRootNode();
		}
		
		//if the focusNode is null then create new leaf node.
		if(focusNode == null) {
		
			focusNode = new Node(index, info);
			
		} else {
			
			if(focusNode.getIndex() > index) {
				
				//Recursive call. Keep traversing left until you hit null.
				Node newNode = addNode(focusNode.getLeftNode(), index, info);
				
				//Once the null position is found, create the link between the parent node (focusNode) and the new child leaf node.
				focusNode.setLeftNode(newNode);
				
			} else {
				
				//Recursive call. Keep traversing right until you hit null.
				Node newNode = addNode(focusNode.getRightNode(), index, info);
				
				//Once the null position is found, create the link between the parent node (focusNode) and the new child leaf node.
				focusNode.setRightNode(newNode);
			
			}
		}
		
		return focusNode;
	}
	
	public void setRootNode(Node rootNode) {
		this.rootNode = rootNode;
	}
	
	public Node getRootNode() {
		return this.rootNode;
	}
	
	//preorder traversal
	public void preorder(Node focusNode) {
		if(focusNode != null) {
			System.out.println(focusNode.getIndex());
			preorder(focusNode.getLeftNode());
			preorder(focusNode.getRightNode());
		}
	}
	
	//postorder traversal
	public void postorder(Node focusNode) {
		if(focusNode != null) {
			preorder(focusNode.getLeftNode());
			preorder(focusNode.getRightNode());
			System.out.println(focusNode.getIndex());
		}
	}
	
	//inorder traversal
	public void inorder(Node focusNode) {
		if(focusNode != null) {
			preorder(focusNode.getLeftNode());
			System.out.println(focusNode.getIndex());
			preorder(focusNode.getRightNode());
		}
	}
}


//inner class
class Node {

	private Node rightNode = null;
	private Node leftNode = null;
	
	private int index;
	
	private String info = "";
	
	public Node(int index, String info) {
		this.info = info;
		this.index = index;
	}

	public Node getRightNode() {
		return this.rightNode;
	}

	public Node getLeftNode() {
		return this.leftNode;
	}
	
	public void setRightNode(Node rightNode) {
		this.rightNode = rightNode;
	}

	public void setLeftNode(Node leftNode) {
		this.leftNode = leftNode;
	}
	
	public int getIndex() {
		return this.index;
	}

	public String getInfo() {
		return this.info;
	}
	
	public void setInfo(String info) {
		this.info = info;
	}

	public String toString() {
		return "Node [rightNode=" + rightNode + ", leftNode=" + leftNode
				+ ", index=" + index + ", info=" + info + "]";
	}
}
