/**
 * @module RHAccess
 * @mail RHAccess
 *
 * The main entry point for the RHAccess module
 *
 */
var RHAccess = (function(RHAccess) {

  /**
   * @property pluginName
   * @type {string}
   *
   * The name of this plugin
   */
  RHAccess.pluginName = 'rhaccess_plugin';

  /**
   * @property log
   * @type {Logging.Logger}
   *
   * This plugin's logger instance
   */
  RHAccess.log = Logger.get('RHAccess');

  /**
   * @property contextPath
   * @type {string}
   *
   * The top level path of this plugin on the server
   *
   */
  RHAccess.contextPath = "/rhaccess-plugin/";



    /**
   * @property templatePath
   * @type {string}
   *
   * The path to this plugin's partials
   */
  RHAccess.templatePath = RHAccess.contextPath + "plugin/html/";

  /**
   * @property module
   * @type {object}
   *
   * This plugin's angularjs module instance.  This plugin only
   * needs hawtioCore to run, which provides services like
   * workspace, viewRegistry and layoutFull used by the
   * run function
   */
  RHAccess.module = angular.module(RHAccess.pluginName, ['hawtioCore', 'hawtio-ui', 'hawtio-forms'])
      .config(function($routeProvider) {
        /**
         * Here we define the route for our plugin.  One note is
         * to avoid using 'otherwise', as hawtio has a handler
         * in place when a route doesn't match any routes that
         * routeProvider has been configured with.
         */
        $routeProvider.
            when('/' + RHAccess.pluginName, {
                templateUrl: RHAccess.templatePath + 'rhaccess.html'
            });

      });

  /**
   * Here we define any initialization to be done when this angular
   * module is bootstrapped.  In here we do a number of things:
   *
   * 1.  We log that we've been loaded (kinda optional)
   * 2.  We load our .css file for our views
   * 3.  We configure the viewRegistry service from hawtio for our
   *     route; in this case we use a pre-defined layout that uses
   *     the full viewing area
   * 4.  We configure our top-level tab and provide a link to our
   *     plugin.  This is just a matter of adding to the workspace's
   *     topLevelTabs array.
   */
  RHAccess.module.run(function(workspace, viewRegistry, layoutFull, NavBarViewCustomLinks, $location, RHAccessSharedProperties) {

    RHAccess.log.info(RHAccess.pluginName, " loaded");

    Core.addCSS(RHAccess.contextPath + "plugin/css/rhaccess.css");

    // tell the app to use the full layout, also could use layoutTree
    // to get the JMX tree or provide a URL to a custom layout
    viewRegistry[RHAccess.pluginName] = RHAccess.templatePath + 'layoutRHAccess.html';

    /* Set up top-level link to our plugin.  Requires an object
       with the following attributes:

         id - the ID of this plugin, used by the perspective plugin
              and by the preferences page
         content - The text or HTML that should be shown in the tab
         title - This will be the tab's tooltip
         isValid - A function that returns whether or not this
                   plugin has functionality that can be used for
                   the current JVM.  The workspace object is passed
                   in by hawtio's navbar controller which lets
                   you inspect the JMX tree, however you can do
                   any checking necessary and return a boolean
         href - a function that returns a link, normally you'd
                return a hash link like #/foo/bar but you can
                also return a full URL to some other site
         isActive - Called by hawtio's navbar to see if the current
                    $location.url() matches up with this plugin.
                    Here we use a helper from workspace that
                    checks if $location.url() starts with our
                    route.
     */
    workspace.topLevelTabs.push({
      id: "rhaccess",
      content: "Red Hat Support",
      title: "RHAccess plugin loaded dynamically",
      isValid: function(workspace) { return true; },
      href: function() { return "#/" + RHAccess.pluginName; },
      isActive: function(workspace) { return workspace.isLinkActive(RHAccess.pluginName); }

    });

    // this is the array of links to be displayed in the main template top right corner

    NavBarViewCustomLinks.dropDownLabel = "Red Hat Access";
    NavBarViewCustomLinks.list = [];

    NavBarViewCustomLinks.list.push( {
      icon: 'icon-warning-sign',
      buttonClass: 'btn-primary',
      title: 'Open New Case',
      href:'/hawtio/rhaccess_plugin',
      action: function() {
        var destination = '/rhaccess_plugin?p=container';
        var iframe = RHAccess.remoteAppEntryPoint + "#case/new";
        Logger.debug("going to: " + destination + ':' + iframe);
        RHAccessSharedProperties.iframeUrl = iframe ;
        $location.url(destination);
      }
    });
    NavBarViewCustomLinks.list.push( {
      icon: 'icon-th-list',
      buttonClass: 'btn-primary',
      title: 'List Cases',
      href:'/hawtio/rhaccess_plugin',
      action: function() {
        var destination = '/rhaccess_plugin?p=container';
        var iframe = RHAccess.remoteAppEntryPoint + "#case/list";
        Logger.debug("going to: " + destination + ':' + iframe);
        RHAccessSharedProperties.iframeUrl = iframe ;
        $location.url(destination);
      }
    });
    NavBarViewCustomLinks.list.push( {
      icon: 'icon-search',
      buttonClass: 'btn-primary',
      title: 'Search Case',
      href:'/hawtio/rhaccess_plugin',
      action: function() {
        var destination = '/rhaccess_plugin?p=container';
        var iframe = RHAccess.remoteAppEntryPoint + "#case/search";
        Logger.debug("going to: " + destination + ':' + iframe);
        RHAccessSharedProperties.iframeUrl = iframe ;
        $location.url(destination);
      }
    });
    NavBarViewCustomLinks.list.push( {
      icon: 'icon-cog',
      buttonClass: 'icon-stethoscope',
      title: 'Diagnose Case',
      href:'/hawtio/rhaccess_plugin',
      action: function() {
        var destination = '/rhaccess_plugin?p=container';
        var iframe = RHAccess.remoteAppEntryPoint + "#logviewer";
        Logger.debug("going to: " + destination + ':' + iframe);
        RHAccessSharedProperties.iframeUrl = iframe ;
        $location.url(destination);
      }
    });
    NavBarViewCustomLinks.list.push( {
      icon: 'icon-book',
      buttonClass: 'btn-primary',
      title: 'Search Articles and Solutions',
      href:'/hawtio/rhaccess_plugin',
      action: function() {
        var destination = '/rhaccess_plugin?p=container';
        var iframe = RHAccess.remoteAppEntryPoint + "#search";
        Logger.debug("going to: " + destination + ':' + iframe);
        RHAccessSharedProperties.iframeUrl = iframe ;
        $location.url(destination);
      }
    });
  });

    RHAccess.module.service('RHAccessSharedProperties', function() {
        return { iframeUrl: null }
    });



    /**
   * @function RHAccessController
   * @param $scope
   * @param jolokia
   *
   * The controller for rhaccess.html, only requires the jolokia
   * service from hawtioCore
   *
   */
  RHAccess.RHAccessController = function($scope, jolokia, RHAccessSharedProperties) {
    $scope.sharedProperties = RHAccessSharedProperties;

    $scope.iframeUrl = RHAccess.rhAccessContextPath + 'support.html#search';

    $scope.$watch("sharedProperties", function(newVal, oldVal) {
        if ( newVal.iframeUrl !== null){
            $scope.iframeUrl = newVal.iframeUrl;
        }
    }, true);
  };


  return RHAccess;

})(RHAccess || {});

// tell the hawtio plugin loader about our plugin so it can be
// bootstrapped with the rest of angular
hawtioPluginLoader.addModule(RHAccess.pluginName);
