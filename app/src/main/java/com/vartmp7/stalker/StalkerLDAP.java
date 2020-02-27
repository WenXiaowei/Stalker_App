package com.vartmp7.stalker;

import android.util.Log;

import com.unboundid.ldap.sdk.BindResult;
import com.unboundid.ldap.sdk.LDAPConnection;
import com.unboundid.ldap.sdk.LDAPException;
import com.unboundid.ldap.sdk.SearchResultEntry;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class StalkerLDAP {
    private static final String TAG="com.vartmp7.stalker.StalkerLDAP";

    private LDAPConnection connection;
    private BindResult result;

    private String bindDN;
    private String bindPassword;
    private String serverAddress;
    private int serverPort;

    private SearchResultEntry entry;

    public StalkerLDAP(String serverAddress, int port, String binDn, String password) throws InterruptedException, LDAPException, ExecutionException {
        this.serverAddress = serverAddress;
        this.serverPort = port;
        this.bindDN= binDn;
        this.bindPassword = password;

    }

    public void connect() throws LDAPException, ExecutionException, InterruptedException {
        this.connection = new LDAPConnection(serverAddress, serverPort);
        FutureTask<BindResult> bindFutureTask = new FutureTask<>(() -> connection.bind(bindDN, bindPassword));
        new Thread(bindFutureTask).start();
        this.result = bindFutureTask.get();
        FutureTask<SearchResultEntry> searchFutureTask = new FutureTask<>(() -> connection.getEntry(bindDN));
        new Thread(searchFutureTask).start();
        this.entry = searchFutureTask.get();


        Log.d(TAG,"uid:"+entry.getAttributeValue("uid"));
        Log.d(TAG,"uidNumber:"+entry.getAttributeValue("uidNumber"));
        Log.d(TAG, "connect: "+entry.getAttributeValue("givenName"));
        this.connection.close();
    }
    public String getSurname(){
        return entry.getAttributeValue("giveName");
    }
    public String getUsername(){
        return entry.getAttributeValue("User Name");
    }




    public String getUid() {
        return entry.getAttributeValue("uid");

    }

    public String getUidNumber() {
        return entry.getAttributeValue("uidNumber");
    }
}
