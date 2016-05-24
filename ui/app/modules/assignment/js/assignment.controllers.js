var assignmentControllers = angular.module('assignmentControllerModule', []);

assignmentControllers.controller('AssignmentCntrl', ['$scope', 'AssignmentService', function($scope, AssignmentService){
	
	$scope.newAssignment = new Assignment();

	$scope.saveNewAssignment = function() {
		alert("Save? " + $scope.newAssignment.toString());
	}

	$scope.getAllAssignments = function() {
		var success = function(assignments) {
			$scope.allAssignments = assignments;
		};

		var failure = function() {
			alert('problem occurred in getting all assignments');
		};

		AssignmentService.getAllAssignments(success, failure);
	}
}]);