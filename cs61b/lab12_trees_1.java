tree{
	vocabulary{{
		Node single element
		Edge connection between two Nodes(between 1 and 2 true, between 1 and 7 false)
		Paht one or more edges that connects two Nodes
		Leaf a Node without childrem meaning it cannot go down anymore
		Root contrary to Leaf, who does not have a parent
	}}

	relationships{{
		Child below the current Node
		Parent ..
		Siblings Nodes with the same parent
		Ancestor parents, parents of parents..
		Descendant children, children of children..
		Subtree the current Node and all of its Descendantan 
		Height the length of path from the Node to its deepest Descendant, for a leaf, it is 0
		Depth the length between current Node and the Root
	}}

	defination{{
		1.one parent for each Node
		2.atmost one path between any of two Nodes
		3.all Nodes are Descendant of a Root
		4.each parent has a pointer to an array of children pointers
		5.inalbe to have parent's parent is its parent
	}}
}

Amoeba family tree{ //need more practice!
	an Amoeba point to its parent, has a string name, has an ArrayLIst<Amoeba> children
}

Binary Trees{
	Orders(for each sub, also in the <>order){{
		Preorder root-> left sub-> right sub
		Postorder left-> right-> root
		Inorder left-> root-> right
	}}
}

Loose Ends{
	Stack(only operate the top){{
		1.add on top
		2.reach top
		3.pop off top
		4.isEmpty
	}}

	Queue(add to back, but operate the top){{
		1.add to back
		2.reach fromt
		3.remove front
		4.isEmpty
	}}
}