/**
 * @module RHAccess
 */
var RHAccess = (function (RHAccess) {

    RHAccess.rhAccessContextPath = "/rhaccess-web/";

    RHAccess.remoteAppEntryPoint = RHAccess.rhAccessContextPath + "support.html";

    RHAccess.localAppEntryPoint = "index.html#/rhaccess_plugin";

    /**
   * @property breadcrumbs
   * @type {{content: string, title: string, isValid: isValid, href: string}[]}
   *
   * Data structure that defines the sub-level tabs for
   * our plugin, used by the navbar controller to show
   * or hide tabs based on some criteria
   */
    /**
     * @property breadcrumbs
     * @type {{content: string, title: string, isValid: isValid, href: string}[]}
     *
     * Data structure that defines the sub-level tabs for
     * our plugin, used by the navbar controller to show
     * or hide tabs based on some criteria
     */
    RHAccess.breadcrumbs = [
        //_href is the value used by the ng-click directive to alter the iframe destination

        {
            content: '<i class="icon-warning-sign"></i> Open New Case',
            title: "Open a new Case",
            isValid: function () { return true; },
            href: RHAccess.localAppEntryPoint,
            _href: RHAccess.remoteAppEntryPoint + "#case/new"
        },
        {
            content: '<i class="icon-th-list"></i> List Cases',
            title: "List Cases",
            isValid: function () { return true; },
            href: RHAccess.localAppEntryPoint,
            _href: RHAccess.remoteAppEntryPoint + "#case/list"
        },
        {
            content: '<i class="icon-search"></i> Search Case',
            title: "Search Case",
            isValid: function () { return true; },
            href: RHAccess.localAppEntryPoint,
            _href: RHAccess.remoteAppEntryPoint + "#case/search"
        },
        {
            content: '<i class="icon-stethoscope"></i> Diagnose Log',
            title: "Diagnose Log",
            isValid: function () { return true; },
            href: RHAccess.localAppEntryPoint,
            _href: RHAccess.remoteAppEntryPoint + "#logviewer"
        },
        {
            content: '<i class="icon-book"></i> Search Articles and Solutions',
            title: "Search Articles and Solutions",
            isValid: function () { return true },
            href: RHAccess.localAppEntryPoint,
            _href: RHAccess.remoteAppEntryPoint + "#search"
        }
    ];

    /**
     * @function NavBarController
     *
     * @param $scope
     * @param workspace
     *
     * The controller for this plugin's navigation bar
     *
     */
    RHAccess.NavBarController = function($scope, RHAccessSharedProperties) {

        $scope.sharedProperties = RHAccessSharedProperties;
        $scope.breadcrumbs = RHAccess.breadcrumbs;

        $scope.isValid = function(link) {
            return link.isValid();
        };

        $scope.updateIframe = function(link) {
            $scope.sharedProperties.iframeUrl = link;
            true;
        }

    };

  return RHAccess;

} (RHAccess || {}));
