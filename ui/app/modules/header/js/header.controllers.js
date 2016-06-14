var headerControllers = angular.module('headerControllerModule', []);

headerControllers.controller('HeaderCntrl', ['$scope', '$state', '$cookies', function($scope, $state, $cookies) {
	var init = function () {
		$scope.username = $cookies.user !== undefined && $cookies.user !== null ? $cookies.user.id.toUpperCase() : null;
	};

	init();
}]);