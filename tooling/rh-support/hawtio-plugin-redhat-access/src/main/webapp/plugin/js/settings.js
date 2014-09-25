/**
 * @module RHAccess
 */
var RHAccess = (function (RHAccess) {

  /**
   * @method SettingsController
   * @param $scope
   * @param RHAccessServer
   *
   * Controller that handles the RHAccess settings page
   */
  RHAccess.SettingsController = function($scope, RHAccessService, localStorage, $location) {

    $scope.formEntity = angular.fromJson(localStorage[RHAccess.SETTINGS_KEY]) || {};
    $scope.formConfig = {
      properties: {
        username: {
          description: 'RHAccess User Name',
          'type': 'java.lang.String'
        },
        password: {
          description: 'RHAccess Password',
          'type': 'password'
        }
      }
    };

    $scope.$watch('formEntity', function(newValue, oldValue) {
      if (newValue !== oldValue) {
        localStorage[RHAccess.SETTINGS_KEY] = angular.toJson(newValue);
      }
    }, true);

  };

  return RHAccess;
}(RHAccess || {}));
