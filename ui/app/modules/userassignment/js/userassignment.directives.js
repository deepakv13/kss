var userAssignmentDirectives = angular.module('userAssignmentDirectiveModule', []);

userAssignmentDirectives.directive('userassignmentAssignmentBox', ['UserAssignmentService', '$state', function(UserAssignmentService, $state) {
	return {
		restrict: 'E',
		scope : {
			userassignment : "=",
		},
		link : function(scope) {
			 var getAssignment = function(userId, assignmentId) {
				var success = function(userAssignment) {
					UserAssignmentService.setUserAssignment(userAssignment);
					$state.go('user-taking-assignment');
				};
				var failure = function() {
					alert('problem occurred in attempting the assignment');
				};

				UserAssignmentService.getOrTakeAssignment(userId, assignmentId, success, failure);
			};

			scope.setUserAssignment = function() {
				if (scope.userassignment.status.toUpperCase() === "IN_PROGRESS") {
					getAssignment(scope.userassignment.userId, scope.userassignment.id);
				}
				else {
					UserAssignmentService.setUserAssignment(scope.userassignment);
					$state.go("user-assignment-detail");	
				}
			};
		},		
		templateUrl: 'modules/userassignment/views/userassignment.assignmentBox.html'
	};
}]);


userAssignmentDirectives.directive('assignmentHeader', function() {
	return {
		restrict : 'E',
		scope : {
			items : "=",
			currentItemNum : "@",
			currentItem : "=",
			answeredItems : "="
		},
		templateUrl: 'modules/userassignment/views/userassignment.takingassignment.item.header.html',
		link : function (scope) {
			scope.totalItemsCount = scope.items.length;

            scope.show = function(key) {
                if(key == scope.currentItemNum){
                	return 'current';
                }
               if(scope.items[key-1].answer!=""){
                	return 'answered';
                }

				return '';
			}

            scope.jumpToItem= function(key){
              scope.currentItemNum = key;
              scope.currentItem=scope.items[key];

            }  



		}
	};
});

userAssignmentDirectives.directive('assignmentItem', function() {
	return {
		restrict : 'E',
		scope : {
			item : "=",
			saveAssignmentItemAnswer : "&"
		},
		templateUrl: 'modules/userassignment/views/userassignment.takingassignment.item.content.html',
		link : function (scope) {
           scope.item.answer='';      

		}
	};
});

userAssignmentDirectives.directive('assignmentFooter', function() {
	return {
		restrict : 'E',
		scope : {
			currentAssignmentItemKey : "@",
			enablePrev : "=",
			enableNext : "=",
			enableSubmit : "=",
			showNextAssignmentItem : "&",
			showPrevAssignmentItem : "&",
			submitAssignment : "&"
		},
		templateUrl: 'modules/userassignment/views/userassignment.takingassignment.item.footer.html',
		link : function (scope) {

		}
	};
});