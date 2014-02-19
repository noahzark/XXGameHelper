package xxgamehelper.framework.model;

import org.apache.http.HttpHost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieOrigin;
import org.apache.http.cookie.CookieSpec;
import org.apache.http.cookie.CookieSpecFactory;
import org.apache.http.cookie.MalformedCookieException;
import org.apache.http.impl.cookie.BrowserCompatSpec;
import org.apache.http.params.HttpParams;

import xxgamehelper.framework.control.Messenger;

public abstract class WebClient extends WebClientData implements WebClientInterface {

	/***
	 * Constructor with a messenger.
	 * @param messenger
	 */
	public WebClient(Messenger messenger) {
		this.messenger = messenger;
	}
	
	/***
	 * Use a easy cookie policy.
	 */
	public static void useEasyCookiePolicy(WebClient webclient) {
		CookieSpecFactory csf = new CookieSpecFactory(){
			public CookieSpec  newInstance(HttpParams params){
				return new BrowserCompatSpec(){
					@Override
					public void validate(Cookie cookie, CookieOrigin origin)
							throws MalformedCookieException{
								//Oh, I am easy
					}
				};
			}};
		webclient.getCookieSpecs().register("easy", csf);
		webclient.getParams().setParameter(ClientPNames.COOKIE_POLICY, "easy");
	}
	
	/***
	 * User proxy server to net connections.
	 * @param address The proxy server address
	 * @param port The proxy server port
	 */
	public void userProxy(String address, int port) {
		HttpHost proxy = new HttpHost(address, port);
		this.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
	}

}
