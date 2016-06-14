var app = angular.module('kssApp', ['loginModule', 'adminModule', 'userAssignmentModule', 'ui.router', 'ngCookies']); 



app.config(['$stateProvider', '$urlRouterProvider',function($stateProvider, $urlRouterProvider) {

	$urlRouterProvider.otherwise('login');

  $stateProvider.state('login', {
      url: '/login',
      views: {
        '': {
              templateUrl: 'modules/login/views/login.html',
              controller: 'LoginCntrl'
            },

        'header@login' : {
                            templateUrl: 'modules/header/views/header.html', 
                            controller: 'HeaderCntrl'
                          },

        'loginBox@login' : {
                              templateUrl: 'modules/login/views/login.box.html',
                              controller: 'LoginCntrl'
                            },
      }
  });  

	$stateProvider.state('admin', {
      url: '/admin',
      views: {
      	'': {
              templateUrl: 'modules/admin/views/admin.html'
            },

        'header@admin' : {templateUrl: 'modules/header/views/header.html',
                            controller: 'HeaderCntrl'
                         },

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

      	'header@new-assignment' : {templateUrl: 'modules/header/views/header.html',
                                    controller: 'HeaderCntrl'
                                    },
      }
	});

  $stateProvider.state('edit-assignment', {
      url: '/editassignment',
      views: {
        '': {
              templateUrl: 'modules/assignment/views/edit.assignment.html',
              controller: 'AssignmentCntrl'
            },

        'header@edit-assignment' : {templateUrl: 'modules/header/views/header.html',
                                     controller: 'HeaderCntrl'
                                    },        

        'assignmentItems@edit-assignment' : {
                                              templateUrl: 'modules/assignment/views/listItems.assignment.html',
                                              controller: 'AssignmentItemListCntrl'},

        'addAssignmentItem@edit-assignment' : {
                                                templateUrl: 'modules/assignment/views/addItem.assignment.html',
                                                controller: 'AssignmentItemCntrl'}
      }
  });

  $stateProvider.state('user-assignment', {
      url: '/userassignment',
      views: {
        '': {
              templateUrl: 'modules/userassignment/views/userassignment.html'
            },

        'header@user-assignment' : {
                                      templateUrl: 'modules/header/views/header.html',
                                      controller: 'HeaderCntrl'
                                    },

        'userAssignmentView@user-assignment' : {
                                              templateUrl: 'modules/userassignment/views/userassignment.dashboard.html',
                                              controller: 'UserAssignmentCntrl'
                                            },
      }
  });  

  $stateProvider.state('user-assignment-detail', {
      url: '/userassignmentdetail',
      views: {
        '': {
              templateUrl: 'modules/userassignment/views/userassignment.detail.html',
              controller: 'UserAssignmentCntrl'
            },
        'header@user-assignment-detail' : {
                                      templateUrl: 'modules/header/views/header.html',
                                      controller: 'HeaderCntrl'
                                        },
                                    
      }
  });   

  $stateProvider.state('user-taking-assignment', {
      url: '/usertakingassignment',
      views: {
        '': {
              templateUrl: 'modules/userassignment/views/userassignment.takingassignment.html',
              controller: 'UserTakingAssignmentCntrl'
            },

        'header@user-taking-assignment' : {
                                      templateUrl: 'modules/header/views/header.html',
                                      controller: 'HeaderCntrl'
                                        },

        'userAssignmentItemView@user-taking-assignment' : {
                                              templateUrl: 'modules/userassignment/views/userassignment.takingassignment.item.html'
                                            },
      }
  });  

}]);	

// app.run([ '$rootScope', '$state', '$stateParams', '$cookies' , '$location', '$window', 
//   function ($rootScope, $state, $stateParams, $cookies, $location, $window) {
//     $rootScope.$on( '$stateChangeStart', 
//       function(e, toState, toParams, fromState, fromParams) {
//         var loginStateStr = "login";
//         var isLogin = toState.name === loginStateStr;
//         var userInfo = $cookies.user;

//         if(isLogin) {
//           $cookies.user = undefined;
//           return;          
//         }

//         if(userInfo === undefined) {
//             e.preventDefault(); // stop current execution
//             $state.go(loginStateStr); // go to login
//         }
//       }
//     );
//   }
// ]);

app.factory('httpInterceptor' , ['$q', '$location', '$rootScope', '$injector',
  function ($q , $location , $rootScope , $injector) {    
  
  var httpInterceptor = {
    request : function (config) {
      return config;
    },

    responseError : function (rejection) {      
      switch(rejection.status) {                  
        default : alert('Server is facing some issue. Please get back later!'); break;
      }
      return $q.reject(rejection); 
    },  

    response : function (response) {        
      var requestUrl = response.config.url;
      var requestUrlSplit = requestUrl.split("/");
      var path = requestUrlSplit[requestUrlSplit.length - 1];
      var escapedFileExtensions = ['.html' , '.css' , '.js'];

      return response;      
      // if (!new RegExp(escapedFileExtensions.join("|")).test(path)) {  // excludes html, css and js pages
      //   var loginStateStr = "login";
      //   var userInfo = $cookies.user;

      //   if(userInfo === undefined) {            
      //     $state.go("login"); // go to login
      //   }
      // }
      // else {         
      //   return response;
      // }        
    }
  };
  return httpInterceptor;    
}]);
