var assignmentServices = angular.module('assignmentServiceModule', ['ngResource']);

assignmentServices.service('AssignmentService', ['$resource', 'APP_SERVICES_URL', function($resource, APP_SERVICES_URL) {
		var assignmentUrl = APP_SERVICES_URL.ASSIGNMENT;
		var newAssignment;

		this.getAllAssignments = function(success, failure) {
			var allAssignments = $resource(assignmentUrl);
			allAssignments.query({}, success, failure);
		};

		this.saveAssignment = function(assignment, success, failure) {
			var assignmentResource = $resource(assignmentUrl);
			assignmentResource.save({}, assignment, success, failure);
		};

		this.setNewAssignment = function(assignment) {
			newAssignment = assignment;
		};

		this.getNewAssignment = function() {
			return newAssignment;
		};

		this.resetNewAssignment = function() {
			newAssignment = '';
		};
	
}]);