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

/**
 * Copyright (C) 2013 the original author or authors.
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

package controllers;




import ninja.Context;
import ninja.Result;
import ninja.Results;
import ninja.session.Session;

import com.google.inject.Inject;


import dao.SetupDao;

public class ApplicationController {



    @Inject
    SetupDao setupDao;

    public ApplicationController() {

    }

    /**
     * Method to put initial data in the db...
     * 
     * @return
     */
    public Result setup() {

        setupDao.setup();

        return Results.ok();

    }

    
    public Result getUserNameFromSession(Session session,Context context) {

        String username = session.get("username");

        if (username !=null) {
        	return Results.html().template("views/ApplicationController/yes.html");
        }
        else {
        	context.getFlashScope().success("You need to Login First");
        	
        	return Results.html().template("views/LoginLogoutController/login.ftl.html");
        }

    }


    public Result index() {
    	return Results.html().template("views/ApplicationController/mainpage.html");
       
    }
}
