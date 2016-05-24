var Assignment = function(name, desc, status, owner) {
	this.name = name;
	this.desc = desc;
	this.status = status;
	this.owner = owner;
};

Assignment.prototype.toString = function(){
	return ('Assignment: [Name - ' + this.name + " : Desc - "+ this.desc +" : Status - " + this.status + " : Owner - "+ this.owner + "]");
}

Assignment.prototype.reset = function(){
	this.name = '';
	this.desc = '';
	this.status = '';
	this.owner = '';
}