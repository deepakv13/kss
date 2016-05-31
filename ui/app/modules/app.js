var app = angular.module('kssApp', ['adminModule', 'ui.router']); 

app.config(['$stateProvider', '$urlRouterProvider',function($stateProvider, $urlRouterProvider) {

	$urlRouterProvider.otherwise('admin');

	$stateProvider.state('admin', {
      url: '/admin',
      views: {
      	'': {
              templateUrl: 'modules/admin/views/admin.html'
            },

        'header@admin' : {templateUrl: 'modules/header/views/header.html'},

        'assignmentPanel@admin' : {
                                    templateUrl: 'modules/assignment/views/assignmentPanel.html',
                                    controller: 'AssignmentCntrl'}
      }
	});

	$stateProvider.state('new-assignment', {
      url: '/newassignment',
      views: {
      	'': {
              templateUrl: 'modules/assignment/views/new.assignmentDetail.html',
              controller: 'AssignmentCntrl'
            },

      	'header@new-assignment' : {templateUrl: 'modules/header/views/header.html'},
      }
	});

    $stateProvider.state('edit-assignment', {
      url: '/editassignment',
      views: {
        '': {
              templateUrl: 'modules/assignment/views/edit.assignment.html',
              controller: 'AssignmentCntrl'
            },

        'header@edit-assignment' : {templateUrl: 'modules/header/views/header.html'},

        'assignmentItems@edit-assignment' : {
                                              templateUrl: 'modules/assignment/views/listItems.assignment.html',
                                              controller: 'AssignmentItemListCntrl'},

        'addAssignmentItem@edit-assignment' : {
                                                templateUrl: 'modules/assignment/views/addItem.assignment.html',
                                                controller: 'AssignmentItemCntrl'}
      }
  });

}]);	
