'use strict';

// Declare app level module which depends on views, and components
angular.module('myApp', [
    'ngRoute',
    'ngAnimate',
    'ui.bootstrap',
    'myApp.security',
    'myApp.view1',
    'myApp.view2',
    'myApp.view3',
    'myApp.view4',
    'myApp.view5',
    'myApp.filters',
    'myApp.directives',
    'myApp.factories',
    'myApp.services',
    'ui.bootstrap'
]).
        config(['$routeProvider', function ($routeProvider) {
                $routeProvider.otherwise({redirectTo: '/view1'});
            }]).
        config(function ($httpProvider) {
            $httpProvider.interceptors.push('authInterceptor');
        }).
                
// BOOTSTRAP MODAL START
        controller('ModalDemoCtrl', function ($scope, $uibModal, $log) {

            $scope.items = ['item1', 'item2', 'item3'];

            $scope.animationsEnabled = true;

            $scope.open = function (size) {

                var modalInstance = $uibModal.open({
                    animation: $scope.animationsEnabled,
                    templateUrl: 'signupmodal.html',
                    controller: 'ModalInstanceCtrl',
                    size: size,
                    resolve: {
                        items: function () {
                            return $scope.items;
                        }
                    }
                });

                modalInstance.result.then(function (selectedItem) {
                    $scope.selected = selectedItem;
                }, function () {
                    $log.info('Modal dismissed at: ' + new Date());
                });
            };

            $scope.toggleAnimation = function () {
                $scope.animationsEnabled = !$scope.animationsEnabled;
            };

        })

// Please note that $modalInstance represents a modal window (instance) dependency.
// It is not the same as the $uibModal service used above.

        .controller('ModalInstanceCtrl', function ($scope, $uibModalInstance, items) {

            $scope.items = items;
            $scope.selected = {
                item: $scope.items[0]
            };

            $scope.ok = function () {
                $uibModalInstance.close($scope.selected.item);
            };

            $scope.cancel = function () {
                $uibModalInstance.dismiss('cancel');
            };
        })

// BOOTSTRAP MODAL END

// SIGNUP
.controller("signupHttp", ["$http", "$scope", function ($http, $scope) {

        // Simple GET request example:
        $http({
            method: 'GET',
            url: 'http://localhost:8383/AngSeedClient/api/users/adduser'
        }).then(function successCallback(response) {

            var list = [];
            $scope.list = response.data;
            
            $scope.msg = ""; // return a message from API (ex: user exist)
            
        }, function errorCallback(response) {

            $scope.error = response.status + ": " + response.statusText;
        });

    }]);