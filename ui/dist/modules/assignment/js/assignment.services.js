var assignmentServices = angular.module('assignmentServiceModule', []);

assignmentServices.service('AssignmentService', [function() {

		this.getAllAssignments = function(success, failure) {

			var assignment1 = new Assignment("First Assignment", "This is first assignment", "SUBMITTED", "DV20572");
			var assignment2 = new Assignment("Second Assignment", "This is second assignment", "SUBMITTED", "GP16816");
			var assignment3 = new Assignment("Third Assignment", "This is third assignment", "SUBMITTED", "RV05411");

			var allAssignments = [assignment1, assignment2, assignment3];
			success(allAssignments);
		};
	
}]);