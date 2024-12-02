package com.tzy.apiclientsdk.utils;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;

public class ApiSignUtils {
    public static String getSign(String secretKey,String body){
        Digester md5 =new Digester(DigestAlgorithm.MD5);
        return md5.digestHex(body+"."+secretKey);
    }
}
