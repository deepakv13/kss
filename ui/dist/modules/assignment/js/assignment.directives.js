var assignmentDirectives = angular.module('assignmentDirectiveModule', []);

assignmentDirectives.directive('createNewAssignmentBox', function() {
	return {
		restrict: 'E',
		templateUrl: 'modules/assignment/views/new.assignmentBox.html'
	};
});

assignmentDirectives.directive('availableAssignmentBox', function() {
	return {
		restrict: 'E',
		scope : {
			assignment : "=",
		},
		templateUrl: 'modules/assignment/views/available.assignmentBox.html'
	};
});

