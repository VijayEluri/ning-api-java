package com.ning.api.client.auth;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.ning.http.client.FluentStringsMap;

public class SignatureCalculatorTest
{
    private static final String CONSUMER_KEY = "dpf43f3p2l4k3l03";

    private static final String CONSUMER_SECRET = "kd94hf93k423kf44";

    public static final String TOKEN_KEY = "nnch734d00sl2jdk";

    public static final String TOKEN_SECRET = "pfkkdhi9sl3r4s00";

    public static final String NONCE = "kllo9940pd9333jh";

    final static long TIMESTAMP = 1191242096;
    
    @Test(groups="fast")
    public void test()
    {
        ConsumerKey consumer = new ConsumerKey(CONSUMER_KEY, CONSUMER_SECRET);
        RequestToken user = new RequestToken(TOKEN_KEY, TOKEN_SECRET);
        OAuthSignatureCalculator calc = new OAuthSignatureCalculator(consumer, user);
        FluentStringsMap queryParams = new FluentStringsMap();
        queryParams.add("file", "vacation.jpg");
        queryParams.add("size", "original");
        String url = "http://photos.example.net/photos";
        String sig = calc.calculateSignature("GET", url, TIMESTAMP, NONCE, null, queryParams);

        // based on the reference test case from
        // http://oauth.pbwiki.com/TestCases
        Assert.assertEquals("tR3+Ty81lMeYAr/Fid0kMTYa/WM=", sig);
    }
}
