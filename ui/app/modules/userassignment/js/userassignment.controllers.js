var userAssignmentControllers = angular.module('userAssignmentControllerModule', []);

userAssignmentControllers.controller('UserAssignmentCntrl', ['$scope','$cookies', '$state', 'UserAssignmentService', function($scope, $cookies, $state, UserAssignmentService) {
	var userObj = $cookies.getObject('user');
	$scope.userId = userObj !== undefined && userObj !== null ? userObj.id.toUpperCase() : null;
	
	$scope.getUserAssignments = function() {
		var success = function(userAssignments) {
			$scope.userAssignments = userAssignments;
		};

		var failure = function() {
			alert('problem occurred in getting user assignments');
		};

		UserAssignmentService.getAssignments($scope.userId, success, failure);
	};

	$scope.setAssignmentInScope = function() {
		$scope.userAssignment = UserAssignmentService.getUserAssignment();
	};

	$scope.getOrTakeAssignment = function() {
		var success = function(userAssignment) {
			UserAssignmentService.setUserAssignment(userAssignment);
			$state.go('user-taking-assignment');
		};
		var failure = function() {
			alert('problem occurred in attempting the assignment');
		};

		UserAssignmentService.getOrTakeAssignment($scope.userId, $scope.userAssignment.id, success, failure);
	};



}]);


userAssignmentControllers.controller('UserTakingAssignmentCntrl', ['$scope', '$state', 'UserAssignmentService', function($scope, $state, UserAssignmentService) {

	
	$scope.initAssignment = function() {
		$scope.assignment = UserAssignmentService.getUserAssignment().assignment;	
		$scope.totalAssignmentItemCount = $scope.assignment.assignmentItems.length;
		$scope.currentAssignmentItem = 0;	


	}

	$scope.showCurrentQues = function(key) {
		if (key === $scope.currentAssignmentItem)
		 return true;
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