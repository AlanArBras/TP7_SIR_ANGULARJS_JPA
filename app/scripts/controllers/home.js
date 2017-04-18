'use strict';

/**
 * @ngdoc function
 * @name tpangularApp.controller:HomeCtrl
 * @description
 * # HomeCtrl
 * Controller of the tpangularApp
 */
angular.module('tpangularApp')
    //Factory home to use $resource instead $http for the rest request
    .factory('Home', function($resource) {
        return $resource('http://localhost\:8080/homes/:id', { id: '@id' });
    })
    .controller('HomeCtrl', function($scope, Home) {
        this.awesomeThings = [
            'HTML5 Boilerplate',
            'AngularJS',
            'Karma'
        ];
        $scope.homes = Home.query();
    })
    //Controller to delete a home from its id
    .controller('HomeDeleteCtrl', function($scope, $routeParams, $location, Home) {
        $scope.home = Home.get({ id: $routeParams.homeId }, function() {
            $scope.home.$delete();
        });
        $location.path('/home');
    })
    //Controller to add a home
    .controller('HomeAddCtrl', function($scope, $location, Home) {
        $scope.add = function(home) {
            var newHome = new Home;
            newHome.address = home.address;
            newHome.$save();
            $location.path('/home');
        };

        $scope.reset = function() {
            $scope.person = angular.copy({});
        };
    });
