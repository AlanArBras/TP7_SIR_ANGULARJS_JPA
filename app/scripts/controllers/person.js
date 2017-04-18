'use strict';

/**
 * @ngdoc function
 * @name tpangularApp.controller:PersonCtrl
 * @description
 * # PersonCtrl
 * Controller of the tpangularApp
 */
angular.module('tpangularApp')
    //Factory person to use $resource instead $http for the rest request
    .factory('Person', function($resource) {
        return $resource('http://localhost\:8080/persons/:id', { id: '@id' });
    })
    //Controller to get all persons in db
    .controller('PersonCtrl', function($scope, Person) {
        this.awesomeThings = [
            'HTML5 Boilerplate',
            'AngularJS',
            'Karma'
        ];
        $scope.persons = Person.query();
    })
    //Controller to delete a person from its id
    .controller('PersonDeleteCtrl', function($scope, $routeParams, $location, Person) {
        console.log($routeParams.personId)
        $scope.person = Person.get({ id: $routeParams.personId }, function() {
            $scope.person.$delete();
        });
        $location.path('/person');
    })
    //Controller to add one person and this home is the checkbox is check
    .controller('PersonAddCtrl', function($scope, $location, Person) {
        $scope.person = {};

        $scope.add = function(person) {
            var newPerson = new Person;
            newPerson.firstName = person.firstName;
            newPerson.lastName = person.lastName;

            //Add house if checkbox is checked
            if (person.home && person.home.create != null && person.home.create) {
                newPerson.homeAddress = person.home.address;
            }
            if ("undefined" === typeof person.home) {
                newPerson.homeCreate = false;
            } else
            //if checkbox is checked, then homeCreate is true
                newPerson.homeCreate = person.home.create;

            newPerson.$save();
            $location.path('/person');
        };

        $scope.reset = function() {
            $scope.person = angular.copy({});
        };
    });
