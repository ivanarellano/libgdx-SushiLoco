package com.scrappile.sushiloco;

import org.acra.*;
import org.acra.annotation.*;

import android.app.Application;

//@ReportsCrashes(formKey = "dFJHdG1JNExiRFRCS21xOHIxWWFmcnc6MQ") 
@ReportsCrashes(formUri = "http://www.bugsense.com/api/acra?api_key=2de45194", formKey="")
public class AcraActivity extends Application {
    @Override
    public void onCreate() {
        // The following line triggers the initialization of ACRA
        ACRA.init(this);
        super.onCreate();
    }
}
