var sharedModule = angular.module('sharedModule', []);

var constants = {};

constants.APP_BASE_URL = "http://localhost:8080/kss/";
// APP_BASE_URL: "./";

constants.APP_SERVICES_URL = {
	ASSIGNMENT: constants.APP_BASE_URL + "assignment"
};


sharedModule.constant(constants);

