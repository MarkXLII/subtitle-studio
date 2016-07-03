/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.swapnilbhoite.projects.subtitlestudio;

/**
 *
 * @author swap
 */
public class MyServers 
{
    public String APPKEY = "";
    public String APPSECRET = "";
    public String KEYTOKEN = "";
    public String SECRETTOKEN = "";
    public MyServers(String ak, String as, String kt, String st)
    {
        APPKEY = ak;
        APPSECRET = as;
        KEYTOKEN = kt;
        SECRETTOKEN = st;
    }
    @Override
    public String toString()
    {
        return APPKEY+"\n"+APPSECRET+"\n"+KEYTOKEN+"\n"+SECRETTOKEN;
    }
}
