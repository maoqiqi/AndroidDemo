// IRemoteService.aidl
package com.software.march.service.remote;

// Declare any non-default types here with import statements
import com.software.march.bean.PersonBean;

interface IRemoteService {

    /**
     * Request the process ID of this service
     */
    int getPid();

    /**
    * Get Person on ID
    */
    PersonBean getPersonById(int id);
}