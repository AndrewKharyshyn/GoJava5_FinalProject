package com.booking.model;


public class User {
	
	
    private long userId;
    private String userName;
    private String userLastName;
    /**
     * Р”Р°РЅРЅС‹Р№ РџРѕР»СЊР·РѕРІР°С‚РµР»СЊ СЃ РїР°СЂР°Р�?РµС‚СЂР°Р�?Рё:
     * @param userId РЈРЅРёРєР°Р»СЊРЅС‹Р№ РёРґРµРЅС‚РёС„РёРєР°С‚РѕСЂ РїРѕР»СЊР·РѕРІР°С‚РµРґСЏ
     * @param userName Р�Р�?СЏ РїРѕР»СЊР·РѕРІР°С‚РµР»СЏ
     * @param userLastName Р¤Р°Р�?РёР»РёСЏ РїРѕР»СЊР·РѕРІР°С‚РµР»СЏ
     */
    public User(long userId, String userName, String userLastName) {
        this.userId = userId;
        this.userName = userName;
        this.userLastName = userLastName;
    }
    /**
     * РњРµС‚РѕРґ РґР»СЏ РєРѕСЂСЂРµРєС‚РЅРѕРіРѕ РІС‹РІРѕРґР° РёРЅС„РѕСЂР�?Р°С†РёРё РѕР± РїРѕР»СЊР·РѕРІР°С‚РµР»Рµ
     * @return РїР°СЂР°Р�?РµС‚СЂС‹ РїРѕР»СЊР·РѕРІР°С‚РµР»СЏ
     */
    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userLastName='" + userLastName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (userId != user.userId) return false;
        if (!userName.equals(user.userName)) return false;
        return userLastName.equals(user.userLastName);

    }
    /**
     * РњРµС‚РѕРґ РІС‹Р·С‹РІР°РµС‚СЊСЃСЏ РїСЂРё СЃСЂР°РІРЅРёРІР°РЅРёРё СЃРѕРґРµСЂР¶РёР�?РѕРіРѕ РѕР±СЊРµРєС‚РѕРІ (РїРѕР»СЊР·РѕРІР°С‚РµР»РµР№)
     * @param o РґР°РЅРЅС‹Р№ РїРѕР»СЊР·РѕРІР°С‚РµР»СЊ
     * @return СЌРєРІРёРІР°Р»РµРЅС‚РЅРѕСЃС‚СЊ РѕР±СЊРµРєС‚РѕРІ
     */
    @Override
    public int hashCode() {
        int result = (int) (userId ^ (userId >>> 32));
        result = 31 * result + userName.hashCode();
        result = 31 * result + userLastName.hashCode();
        return result;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }
}
