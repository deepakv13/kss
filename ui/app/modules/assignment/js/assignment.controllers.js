var assignmentControllers = angular.module('assignmentControllerModule', []);

assignmentControllers.controller('AssignmentCntrl', ['$scope', 'AssignmentService', '$state', function($scope, AssignmentService, $state){
	
	$scope.newAssignment = new Assignment();

	$scope.createNewAssignment = function() {
		var success = function(assignment) {
			$scope.newAssignment = assignment;
			AssignmentService.setNewAssignment(assignment);
			$state.go('edit-assignment');
		};

		var failure = function() {
			alert('problem occurred in creating new assignment');
		};

		AssignmentService.saveAssignment($scope.newAssignment, success, failure);
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

	$scope.getNewAssignment = function() {
		$scope.newAssignment = AssignmentService.getNewAssignment();
	}

	$scope.setAssignment = function(selectedAssignment) {
		$scope.newAssignment = selectedAssignment;
		AssignmentService.setNewAssignment(selectedAssignment);
	}

	$scope.getTotalWeightage = function() {
		var totalWeightage = 0;
		angular.forEach($scope.newAssignment.assignmentItems, function(item, index){
			totalWeightage += item.weightage;
		});
		return totalWeightage;
	}


}]);

assignmentControllers.controller('AssignmentItemCntrl', ['$scope', 'AssignmentService', 'uiGridConstants', function($scope, AssignmentService, uiGridConstants){
	$scope.assignmentItem = new AssignmentItem();

	//TODO: We should not send/receive the full Assignment object from UI to/from server
	$scope.saveAssignmentItem = function() {
		AssignmentService.getNewAssignment().assignmentItems.push($scope.assignmentItem);
		var success = function(assignment) {
			AssignmentService.setNewAssignment(assignment);
			$scope.assignmentItem = new AssignmentItem();
		};

		var failure = function() {
			alert('problem occurred in saving assignment item');
		};
		AssignmentService.saveAssignment(AssignmentService.getNewAssignment(), success, failure);
	}

	$scope.createChoices = function() {
		var totalSelectedChoices = parseInt($scope.assignmentItem.noOfChoices);
		var currentChoices = parseInt($scope.assignmentItem.itemChoices.length);

         for(var i = 0; i < (totalSelectedChoices - currentChoices); i++){
         	$scope.assignmentItem.itemChoices.push(new ItemChoice());
        }
	}

	$scope.getAssignmentItemsForGrid = function() {
		$scope.assignment.assignmentItems.push($scope.assignmentItem);
		var success = function() {
			// add the saved item into list
		};

		var failure = function() {
			alert('problem occurred in saving assignment item');
		};
		AssignmentService.saveAssignment($scope.assignment, success, failure);
	}
}]);

assignmentControllers.controller('AssignmentItemListCntrl', ['$scope', 'AssignmentService', 'uiGridConstants', function($scope, AssignmentService, uiGridConstants){
  $scope.gridOptions = {
  enableFiltering: true,
  columnDefs: [
      // {
      //   name: 'Items',
      //   field: 'selected',
      //   filter: {
      //     type: uiGridConstants.filter.SELECT,
      //     selectOptions: [
      //       {value: false, label: 'Unselect'},
      //       {value: true, label: 'Selected'}
      //     ]
      //   },
      //   cellTemplate: '<input type="checkbox" name="select_item" value="true" ng-model="row.entity.selected"/>'
      // },
      {
        name: 'Description',
        field: 'desc'
      },
      {
        name: 'Weightage',
        field: 'weightage'
      }
    ]
  };

  // $scope.gridOptions.data = [
  //   { name: 'User 1', age: 20,selected:false},
  //   { name: 'User 2', age: 30,selected:false},
  //   { name: 'User 3', age: 40,selected:false}
  // ];
  
  $scope.gridOptions.data = AssignmentService.getNewAssignment().assignmentItems;
	
}])