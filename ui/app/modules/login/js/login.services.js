var loginServices = angular.module('loginServiceModule', ['ngResource']);

loginServices.service('LoginService', ['$resource', 'APP_SERVICES_URL', function($resource, APP_SERVICES_URL) {
		var loginUrl = APP_SERVICES_URL.LOGIN;

		this.login = function(queryObj, success, failure) {
			var login = $resource(loginUrl, queryObj, {query:{method:'GET', isArray:false}});
			login.query({}, success);
		};
}]);