/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter.Cards;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;


public class StarterApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();

    // Enable Local Datastore.
    //Parse.enableLocalDatastore(this);

    // Add your initialization code here
    Parse.initialize(this,"Un59qWCUETeAIGB0EQvCT2D8ZPhh1EIOeN8XTvfA","KvkS5w0UnoAwNbxJepCZM9Kw3hjn9zfzNoDjVn5h");

    ParseUser.enableAutomaticUser();
    ParseACL defaultACL = new ParseACL();
    // Optionally enable public read access.
    defaultACL.setPublicReadAccess(true);
    defaultACL.setPublicWriteAccess(true);
    ParseACL.setDefaultACL(defaultACL, true);
  }
}
