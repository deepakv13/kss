var assignmentDirectives = angular.module('assignmentDirectiveModule', []);

assignmentDirectives.directive('createNewAssignmentBox', function() {
	return {
		restrict: 'E',
		templateUrl: 'modules/assignment/views/new.assignmentBox.html'
	};
});

assignmentDirectives.directive('availableAssignmentBox', ['AssignmentService', function(AssignmentService) {
	return {
		restrict: 'E',
		scope : {
			assignment : "=",
		},
		link : function(scope) {
			scope.setAssignment = function() {
				AssignmentService.setNewAssignment(scope.assignment);
			};
		},
		templateUrl: 'modules/assignment/views/available.assignmentBox.html'
	};
}]);

assignmentDirectives.directive('assignmentItemOption', function() {
	return {
		restrict: 'E',
		scope : {
			choice : "=",
		},
		templateUrl: 'modules/assignment/views/assignmentitem.option.html'
	};
});

