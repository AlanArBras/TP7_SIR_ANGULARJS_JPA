'use strict';

/**
 * @ngdoc overview
 * @name tpangularApp
 * @description
 * # tpangularApp
 *
 * Main module of the application.
 */
angular
  .module('tpangularApp', [
    'ngAnimate',
    'ngCookies',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch'
  ])
  .config(function ($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/main.html',
        controller: 'MainCtrl'
      })
      .when('/person', {
        templateUrl: 'views/person.html',
        controller: 'PersonCtrl',
        controllerAs: 'person'
      })
      .when('/person/delete/:personId', {
        templateUrl: 'views/person.html',
        controller: 'PersonDeleteCtrl'
      })
      .when('/person/add', {
        templateUrl: 'views/addPerson.html',
        controller: 'PersonAddCtrl'
      })
      .when('/home', {
        templateUrl: 'views/home.html',
        controller: 'HomeCtrl',
        controllerAs: 'home'
      })
      .when('/home/delete/:homeId', {
        templateUrl: 'views/home.html',
        controller: 'HomeDeleteCtrl'
      })
      .when('/home/add', {
        templateUrl: 'views/addHome.html',
        controller: 'HomeAddCtrl'
      })
      .when('/device', {
        templateUrl: 'views/device.html',
        controller: 'DeviceCtrl',
        controllerAs: 'device'
      })
      .when('/device/delete/:deviceId', {
        templateUrl: 'views/device.html',
        controller: 'DeviceDeleteCtrl'
      })
      .when('/device/add/', {
        templateUrl: 'views/addDevice.html',
        controller: 'DeviceAddCtrl'
      })
      .otherwise({
        redirectTo: '/'
      });
  });
