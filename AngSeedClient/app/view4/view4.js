'use strict';

angular.module('myApp.view4', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/view4', {
    templateUrl: 'view4/view4.html',
    controller: 'View4Ctrl'
  });
}])


.controller('View4Ctrl', function($http,$scope) {
  $http.get('api/demouser')
            .success(function (data, status, headers, config) {
              $scope.data = data;
      
              $scope.msg = "Du er nu logget ind som USER";
      
            })
            .error(function (data, status, headers, config) {
              
             });
 
});


