/**
 * Copyright (C) 2012-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package conf;

import ninja.AssetsController;
import ninja.Router;
import ninja.application.ApplicationRoutes;
import ninja.utils.NinjaProperties;

import com.google.inject.Inject;


import controllers.ApplicationController;

import controllers.LoginLogoutController;

public class Routes implements ApplicationRoutes {
    
    @Inject
    NinjaProperties ninjaProperties;

    /**
     * Using a (almost) nice DSL we can configure the router.
     * 
     * The second argument NinjaModuleDemoRouter contains all routes of a
     * submodule. By simply injecting it we activate the routes.
     * 
     * @param router
     *            The default router of this application
     */
    @Override
    public void init(Router router) {  
        
         //puts test data into db:
        if (!ninjaProperties.isProd()) {
            router.GET().route("/setup").with(ApplicationController::setup);
        }
        router.GET().route("/").with(ApplicationController::index);
        ///////////////////////////////////////////////////////////////////////
        // Login / Logout
        ///////////////////////////////////////////////////////////////////////
        router.GET().route("/login").with(LoginLogoutController::login);
        router.POST().route("/login").with(LoginLogoutController::loginPost);
        router.GET().route("/logout").with(LoginLogoutController::logout);

        
        
      //  router.GET().route("/order").with(ApplicationController::getUserNameFromSession);
        router.POST().route("/order").with(ApplicationController::getUserNameFromSession);
        ///////////////////////////////////////////////////////////////////////
        // Assets (pictures / javascript)
        ///////////////////////////////////////////////////////////////////////    
//        router.GET().route("/assets/webjars/{fileName: .*}").with(AssetsController::serveWebJars);
//        router.GET().route("/assets/{fileName: .*}").with(AssetsController::serveStatic);
        
        router.GET().route("/.*").with(ApplicationController::index);
    }

}
