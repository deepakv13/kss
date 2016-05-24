var app = angular.module('etsKssApp', ['adminModule', 'ui.router']); 

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

}]);	
