#include "BST.h"

#include <cassert>
#include <iostream>
#include <string>
#include <queue>
#include <math.h>
#include "json.hpp"
using namespace std;

BSTNode::BSTNode(int key) :
	key_(key),
	parent_(std::weak_ptr<BSTNode>()),
	left_(nullptr),
	right_(nullptr),
	height_(0),
	bf_(0){}

BSTNode::BSTNode(int key, std::weak_ptr<BSTNode> parent) :
	key_(key),
	parent_(parent),
	left_(nullptr),
	right_(nullptr),
	height_(0),
	bf_(0) {}

bool BSTNode::IsLeaf() const {
	return left_ == nullptr && right_ == nullptr;
}

bool BSTNode::HasLeftChild() const {
	return left_ != nullptr;
}

bool BSTNode::HasRightChild() const {
	return right_ != nullptr;
}

void BSTNode::DeleteChild(std::shared_ptr<BSTNode> v) {
	if (left_ == v) {
		left_ = nullptr;
	} else if (right_ == v) {
		right_ = nullptr;
	} else {
		std::cerr << "BSTNode::DeleteChild Error: non-child passed as argument\n";
		exit(EXIT_FAILURE);
	}
}

void BSTNode::ReplaceChild(std::shared_ptr<BSTNode> v, std::shared_ptr<BSTNode> u) {
	if (left_ == u || right_ == u) {
		std::cerr << "BSTNode::ReplaceChild Error: child passed as replacement\n";
	}
	if (left_ == v) {
		left_ = u;
		u->parent_ = v->parent_;
	} else if (right_ == v) {
		right_ = u;
		u->parent_ = v->parent_;
	} else {
		std::cerr << "BSTNode::ReplaceChild Error: non-child passed as argument\n";
		exit(EXIT_FAILURE);
	}
}

BST::BST() : root_(nullptr), size_(0) {}

int BST::GetHeight(shared_ptr<BSTNode> lastNode) {
	int h = 0;
	if (lastNode == nullptr) {
		return -1;
	}
	else if (lastNode->IsLeaf()) {
		return 0;	
	}
	else {
		int lh = GetHeight(lastNode->left_);
		int rh = GetHeight(lastNode->right_);		
		h = max(lh, rh) + 1;

	}
	return h;
}

shared_ptr<BSTNode> BST::LeftRotate(shared_ptr<BSTNode> z) {
	shared_ptr<BSTNode> y=z->right_;
	shared_ptr<BSTNode> T2=y->left_;
	y->left_ = z;
	z->parent_ = y;
	z->right_ = T2;
	y->parent_.reset();
	if(T2!=nullptr)
		T2->parent_ = z;
	

	z->height_ = GetHeight(z);	
	y->height_ = GetHeight(y);

	z->bf_ = GetHeight(z->left_) - GetHeight(z->right_);
	y->bf_ = GetHeight(y->left_) - GetHeight(y->right_);
	return y;
}
shared_ptr<BSTNode> BST::RightRotate(shared_ptr<BSTNode> z) {
	shared_ptr<BSTNode> y = z->left_;
	shared_ptr<BSTNode> T3 = y->right_;
	y->right_ = z;
	z->parent_ = y;
	y->parent_.reset();
	z->left_ = T3;
	if (T3 != nullptr)
		T3->parent_ = z;	

	z->height_ = GetHeight(z);
	y->height_ = GetHeight(y);

	z->bf_ = GetHeight(z->left_) - GetHeight(z->right_);
	y->bf_ = GetHeight(y->left_) - GetHeight(y->right_);
	return y;
}
void BST::Update(shared_ptr<BSTNode> lastNode) {	//lastNode is the inserted node

	shared_ptr<BSTNode> parent = lastNode->parent_.lock();
	lastNode->height_ = GetHeight(lastNode);
	lastNode->bf_ = GetHeight(lastNode->left_)-GetHeight(lastNode->right_);
	//cout<<lastNode->bf_<<endl;
	if (lastNode->bf_>1||lastNode->bf_<-1){
		if (lastNode->bf_ > 1 && lastNode->left_->bf_ >= 0) { // LLcase
			if (parent == nullptr) {
				lastNode = RightRotate(lastNode);
				root_ = lastNode;
			}
			else if (parent->right_->key_ > lastNode->key_) {		//lastNode is LeftChild
				parent->left_ = RightRotate(lastNode);
				parent->left_->parent_ = parent;
			}
			else {
				parent->right_ = RightRotate(lastNode);
				parent->right_->parent_ = parent;
			}

		}
		else if (lastNode->bf_ < -1 && lastNode->right_->bf_ <= 0) { // RRcase
			if (parent == nullptr) {
				lastNode = LeftRotate(lastNode);
				root_ = lastNode;
			}
			else if (parent->right_->key_ > lastNode->key_) {	//lastNode is LeftChild
				parent->left_ = LeftRotate(lastNode);
				parent->left_->parent_ = parent;
			}
			else {
				parent->right_ = LeftRotate(lastNode);
				parent->right_->parent_ = parent;
			}
		}
			

		else if (lastNode->bf_ > 1 && lastNode->left_->bf_ < 0) { // LRcase//checked
				lastNode->left_= LeftRotate(lastNode->left_);
				lastNode->left_->parent_ = lastNode;
				if (parent == nullptr) {
					lastNode = RightRotate(lastNode);
					root_ = lastNode;
				}				
				else if (parent->right_->key_ > lastNode->key_) {		//lastNode is LeftChild
					parent->left_ = RightRotate(lastNode);
					parent->left_->parent_ = parent;
				}
				else {
					parent->right_ = RightRotate(lastNode);
					parent->right_->parent_ = parent;
				}
		}
		
		else if (lastNode->bf_ < -1 && lastNode->right_->bf_ >0) {// RLcase
			lastNode->right_= RightRotate(lastNode->right_);
			lastNode->right_->parent_ = lastNode;
			if (parent == nullptr) {
				lastNode = LeftRotate(lastNode);
				root_ = lastNode;
			}
			else if (parent->right_->key_ > lastNode->key_) {	//lastNode is LeftChild
				parent->left_ = LeftRotate(lastNode);
				parent->left_->parent_ = parent;
			}
			else {
				parent->right_ = LeftRotate(lastNode);
				parent->right_->parent_ = parent;
			}
		} 
	}
	if (lastNode != root_) {
		Update(parent);
	}
	return;
}

void BST::Insert(int key) {
	if (root_ == nullptr) {
		root_ = std::make_shared<BSTNode>(key);
		size_++;
		return;
	}
	std::shared_ptr<BSTNode> currentNode = root_, lastNode = nullptr;

	while (currentNode != nullptr) {
		lastNode = currentNode;
		if (/**lastNode == root_ && **/lastNode->IsLeaf())
			currentNode = nullptr;
		else //if (key < currentNode->key_ )
			currentNode =(key < currentNode->key_) ? currentNode->left_ : currentNode->right_;

	}
	if (key < lastNode->key_) {
		//shared_ptr<BSTNode> currentNode = make_shared<BSTNode>(key, lastNode);
		lastNode->left_ = make_shared<BSTNode>(key, lastNode);
		Update(lastNode->left_);

	} else {
		lastNode->right_ = std::make_shared<BSTNode>(key, lastNode);
		Update(lastNode->right_);
	}
	size_++;
	return;
	
}
bool BST::Delete(int key) {
	std::shared_ptr<BSTNode> currentNode = root_;
	while (currentNode != nullptr) {
		if (currentNode->key_ == key) {
			if(currentNode == root_){// if delete root
				if (currentNode->IsLeaf()) {
					root_ = nullptr;
					size_--; assert(size_ == 0);
				}
				else {
						root_->key_ = DeleteMin(root_);	//?????!!!
								
				}
			} else if (currentNode->IsLeaf()) {//if delete a leaf
				DeleteLeaf(currentNode);
			} else if (currentNode->left_ == nullptr) {//if delete a node w/ right child
				assert(currentNode->right_ != nullptr);
				std::shared_ptr<BSTNode> parent = currentNode->parent_.lock();
				parent->ReplaceChild(currentNode, currentNode->right_);
				size_--; assert(size_ >= 0);
				Update(parent);
			} else if (currentNode->right_ == nullptr) {//if delete a node w/ left child
				assert(currentNode->left_ != nullptr);
				std::shared_ptr<BSTNode> parent = currentNode->parent_.lock();
				parent->ReplaceChild(currentNode, currentNode->left_);
				size_--; assert(size_ >= 0);
				Update(parent);
			} else {//if delete a node w/ 2 children
				assert(!currentNode->IsLeaf());//currentNode should have 2 children
				currentNode->key_ = DeleteMin(currentNode->right_);//find the min in right child of node
				
			}
		}
		currentNode = (key < currentNode->key_) ?
			currentNode->left_ : currentNode->right_;
	}
	return false;
}

int BST::DeleteMin(shared_ptr<BSTNode> currentNode) {	
	
	if (currentNode->left_ == nullptr && currentNode!=root_) {
		
		std::shared_ptr<BSTNode> parent = currentNode->parent_.lock();
		int r_min = currentNode->key_;
		currentNode->parent_.lock()->right_ = currentNode->right_;
		if(currentNode->right_!=nullptr)
			currentNode->right_->parent_ = parent;
		currentNode = nullptr;
		size_--; assert(size_ >= 0);
		Update(parent);
		return r_min;		
	}

	while (currentNode->left_ != nullptr) {
		currentNode = currentNode->left_;
	}
	
	int r_min = currentNode->key_;
	if (currentNode == root_) {//if currentNode is root???!!!
		if (currentNode->right_ != nullptr) {
			if (currentNode->right_->left_ != nullptr) {//replace root with the min of right child
				root_->key_ = DeleteMin(root_->right_);
			}
			else {//current->right_ is the min
				root_ = currentNode->right_;
				currentNode=nullptr;
				root_->parent_.reset();
				size_--; assert(size_ >= 0);
				Update(root_);
			}
		}else {//currentNode->right_ == nullptr ->root is the only node
			root_ = nullptr;
			size_--; assert(size_ == 0);
		}
	}
	else if (currentNode->right_!=nullptr && currentNode->right_->left_ != nullptr) {
			currentNode->key_ = DeleteMin(currentNode->right_);
		}
	else if(currentNode->right_!=nullptr){// currentNode->right_ is min
		std::shared_ptr<BSTNode> right = currentNode->right_;
		currentNode->key_ = right->key_;
		currentNode->right_=right->right_;
		if (currentNode->right_!= nullptr)
			currentNode->right_->parent_ = currentNode;
		right=nullptr;
		size_--; assert(size_ >= 0);
		Update(currentNode);
	}		
	else {//currentNode  is a leaf
		assert(currentNode->parent_.lock()!=nullptr);
		std::shared_ptr<BSTNode> parent = currentNode->parent_.lock();
		parent->left_ = nullptr;
		currentNode=nullptr;
		size_--; assert(size_ >= 0);
		Update(parent);
	}
	return r_min;
}

int BST::DeleteMin() {
	return DeleteMin(root_);
}

void BST::DeleteLeaf(std::shared_ptr<BSTNode> currentNode) {
	
	if (currentNode==root_) {
		// Delete root
		currentNode = nullptr;
		size_--; assert(size_ == 0);
	} else {
		std::shared_ptr<BSTNode> parent = currentNode->parent_.lock();
		if (parent->right_ == currentNode) {
			parent->right_ = nullptr;
			currentNode=nullptr;
			Update(parent);
		} else if (parent->left_ == currentNode) {
			parent->left_ = nullptr;
			currentNode=nullptr;
			Update(parent);
		} else {
			std::cerr << "BST::DeleteLeaf Error: inconsistent state\n";
		}
		size_--; assert(size_ >= 0);
	}
	return;
}

size_t BST::size() const {
	return size_;
}

bool BST::empty() const {
	return size_ == 0;
}

bool BST::Find(int key) const {
	std::shared_ptr<BSTNode> currentNode = root_;
	while (currentNode != nullptr) {
		if (currentNode->key_ == key) {
			return true;
		}
		currentNode = (key < currentNode->key_) ?
			currentNode->left_ : currentNode->right_;
	}
	return false;
}

std::string BST::JSON() const {
	nlohmann::json result;
	std::queue< std::shared_ptr<BSTNode> > nodes;
	if (root_ != nullptr) {
		result["height"] = root_->height_;
		result["root"] = root_->key_;
		nodes.push(root_);
		while (!nodes.empty()) {
			auto v = nodes.front();
			nodes.pop();
			std::string key = std::to_string(v->key_);
			result[key]["balance factor"] = v->bf_;
			result[key]["height"] = v->height_;

			if (v->left_ != nullptr) {
				result[key]["left"] = v->left_->key_;
				nodes.push(v->left_);
			}
			if (v->right_ != nullptr) {
				result[key]["right"] = v->right_->key_;
				nodes.push(v->right_);
			}
			if (v->parent_.lock() != nullptr) {
				result[key]["parent"] = v->parent_.lock()->key_;
			} else {
				result[key]["root"] = true;
			}
		}
	}
	result["size"] = size_;
	return result.dump(2) + "\n";
}
int  BST::GetBal(shared_ptr<BSTNode> curNode) {
	if(curNode ==nullptr)
		return -1;
	else
		return GetHeight(curNode->left_)- GetHeight(curNode->right_);
}

void BST::PreOrder() {
	 PreOrder(root_);
}
void BST::PreOrder(shared_ptr<BSTNode> curNode) {
	if (curNode != nullptr) {
		unsigned int h = GetHeight(curNode);
		int b = GetBal(curNode);
		assert(h<size_); 
		assert(b <= 1 && b >= -1);
		// b always >=-1 and <=1
		if(curNode->IsLeaf())
			assert(h==0 && b==0);// when node is leaf, h & bf=0
		PreOrder(curNode->left_);
		PreOrder(curNode->right_);
	}
}

