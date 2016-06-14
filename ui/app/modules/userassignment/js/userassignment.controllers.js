var userAssignmentControllers = angular.module('userAssignmentControllerModule', []);

userAssignmentControllers.controller('UserAssignmentCntrl', ['$scope', '$state', 'UserAssignmentService', function($scope, $state, UserAssignmentService) {
	
	$scope.getUserAssignments = function(userId) {
		var success = function(userAssignments) {
			$scope.userAssignments = userAssignments;
		};

		var failure = function() {
			alert('problem occurred in getting user assignments');
		};

		UserAssignmentService.getAssignments(userId, success, failure);
	};

	$scope.setAssignmentInScope = function() {
		$scope.userAssignment = UserAssignmentService.getUserAssignment();
	};

	$scope.getOrTakeAssignment = function(userId) {
		var success = function(userAssignment) {
			UserAssignmentService.setUserAssignment(userAssignment);
			$state.go('user-taking-assignment');
		};
		var failure = function() {
			alert('problem occurred in attempting the assignment');
		};

		UserAssignmentService.getOrTakeAssignment(userId, $scope.userAssignment.id, success, failure);
	};



}]);


userAssignmentControllers.controller('UserTakingAssignmentCntrl', ['$scope', '$state', 'UserAssignmentService', function($scope, $state, UserAssignmentService) {

	$scope.initAssignment = function() {
		$scope.assignment = UserAssignmentService.getUserAssignment().assignment;		
		$scope.totalAssignmentItemCount = $scope.assignment.assignmentItems.length;
		$scope.currentAssignmentItem = 0;	
	}

	$scope.showCurrentQues = function(key) {
		if (key === $scope.currentAssignmentItem) return true;
		else return false;
	};

	$scope.saveAssignmentItemAnswer = function(itemAnswer) {
	};

	$scope.enablePrev = function(key) {
		if (key > 0) return true;		
		else return false;
	};

	$scope.enableNext = function(key) {		
		if (key < $scope.totalAssignmentItemCount - 1) return true;
		else return false;
	};	

	$scope.enableSubmit = function(key) {
		if (key === $scope.totalAssignmentItemCount - 1) return true;
		else return false;
	};

	$scope.showNextAssignmentItem = function(currentAssignmentItemKey) {
		$scope.currentAssignmentItem = parseInt(currentAssignmentItemKey) + 1;
	};

	$scope.showPrevAssignmentItem = function(currentAssignmentItemKey) {
		$scope.currentAssignmentItem = parseInt(currentAssignmentItemKey) - 1;
	};

	$scope.submitAssignment = function () {

	}
}]);