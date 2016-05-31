var Assignment = function(name, desc, status, owner) {
	this.id = '';
	this.name = name;
	this.desc = desc;
	this.status = "NEW";
	this.owner = "Admin";
	this.assignmentItems = [];
};

Assignment.prototype.toString = function(){
	return ('Assignment: [ID - ' + this.id + 'Name - ' + this.name + " : Desc - "+ this.desc +" : Status - " + this.status + " : Owner - "+ this.owner + "]");
}

Assignment.prototype.reset = function(){
	this.name = '';
	this.desc = '';
	this.status = '';
	this.owner = '';
}

var AssignmentItem = function(desc, itemType, noOfChoices, weightage) {
	this.id = '';
	this.desc = desc;
	this.itemType = itemType;
	this.noOfChoices = noOfChoices;
	this.weightage = weightage;
	this.itemChoices = [];
}

AssignmentItem.prototype.reset = function(){	
	this.id = '';
	this.desc = '';
	this.itemType = '';
	this.noOfChoices = '';
	this.weightage = '';
	this.itemChoices = [];
}

var ItemChoice = function(desc, isCorrect) {
    this.id=''; 	
	this.desc = desc;
	this.isCorrect = isCorrect;	
};

ItemChoice.prototype.toString = function(){
	return ('Choice: [ID - ' + this.id + " : Desc - "+ this.desc + " : isCorrect - "+ this.isCorrect + "]");
}

ItemChoice.prototype.reset = function(){	
	this.desc = '';
	this.isCorrect = '';	
}