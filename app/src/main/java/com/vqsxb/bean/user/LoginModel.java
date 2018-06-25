package com.vqsxb.bean.user;

/**
 * Created by Administrator on 2017/11/15 0015.
 */

public class LoginModel {

    /**
     * data : {"userid":"13","nickname":"qq号","amount":0,"point":"0","avatar":"https://www.appmod.net/uploadfile/avatar/1/13256x256.jpg?1509070962","equipment":"m2","attentionCount":"3","fansCount":"2","CollectionGames":"12","models":"m2","gender":"man","sign":"The man is lazy and has not signed yet!","cardCount":"1","token":"e43717d7d5926ef3c6182d35267c2acf","token_time":1510710379,"crc":"x174_204_188_219_179_208_188_223_182_212_172_157_186_217_177_232_212_198_169_223_216_169_169_223_199_149_144_169_145_153_155_191_149_141_173_195_153_145_155_130_159_162_143_144y"}
     * error : 0
     */

    private UserData data;
    private int error;

    public UserData getData() {
        return data;
    }

    public void setData(UserData data) {
        this.data = data;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

}
