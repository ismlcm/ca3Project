'use strict';

angular.module('myApp.view3', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/view3', {
    templateUrl: 'view3/view3.html',
    controller: 'View3Ctrl'
  });
}])

//.controller('View3Ctrl', function($http,$scope) {
//  $http.get('api/demouser')
//            .success(function (data, status, headers, config) {
//              $scope.data = data;
//              
//              $scope.msg = "Du er nu logget ind som USER";
//              
//            })
//            .error(function (data, status, headers, config) {
//              
//             });
// 
//})

.controller("View3Ctrl", ["$http", "$scope", function ($http, $scope) {

        $http({
            method: 'GET',
            dataType: 'json',
            url: 'http://cvrapi.dk/api?vat=31678021&country=dk'
        }).then(function successCallback(response) {

            $scope.size = response.data.length;

            var companies = [{}];
            $scope.companies = response.data;
        
           console.log(response.data);
           

        }, function errorCallback(response) {

            $scope.error = response.status + ": " + response.statusText;
        });

    }]);