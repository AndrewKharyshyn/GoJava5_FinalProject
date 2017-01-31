package com.booking.model;


public class User {
	
	
    private long userId;
    private String userName;
    private String userLastName;
    /**
     * Р вЂќР В°Р Р…Р Р…РЎвЂ№Р в„– Р СџР С•Р В»РЎРЉР В·Р С•Р Р†Р В°РЎвЂљР ВµР В»РЎРЉ РЎРѓ Р С—Р В°РЎР‚Р В°Р С�Р ВµРЎвЂљРЎР‚Р В°Р С�Р С‘:
     * @param userId Р Р€Р Р…Р С‘Р С”Р В°Р В»РЎРЉР Р…РЎвЂ№Р в„– Р С‘Р Т‘Р ВµР Р…РЎвЂљР С‘РЎвЂћР С‘Р С”Р В°РЎвЂљР С•РЎР‚ Р С—Р С•Р В»РЎРЉР В·Р С•Р Р†Р В°РЎвЂљР ВµР Т‘РЎРЏ
     * @param userName Р пїЅР С�РЎРЏ Р С—Р С•Р В»РЎРЉР В·Р С•Р Р†Р В°РЎвЂљР ВµР В»РЎРЏ
     * @param userLastName Р В¤Р В°Р С�Р С‘Р В»Р С‘РЎРЏ Р С—Р С•Р В»РЎРЉР В·Р С•Р Р†Р В°РЎвЂљР ВµР В»РЎРЏ
     */
    public User(long userId, String userName, String userLastName) {
        this.userId = userId;
        this.userName = userName;
        this.userLastName = userLastName;
    }
    /**
     * Р СљР ВµРЎвЂљР С•Р Т‘ Р Т‘Р В»РЎРЏ Р С”Р С•РЎР‚РЎР‚Р ВµР С”РЎвЂљР Р…Р С•Р С–Р С• Р Р†РЎвЂ№Р Р†Р С•Р Т‘Р В° Р С‘Р Р…РЎвЂћР С•РЎР‚Р С�Р В°РЎвЂ Р С‘Р С‘ Р С•Р В± Р С—Р С•Р В»РЎРЉР В·Р С•Р Р†Р В°РЎвЂљР ВµР В»Р Вµ
     * @return Р С—Р В°РЎР‚Р В°Р С�Р ВµРЎвЂљРЎР‚РЎвЂ№ Р С—Р С•Р В»РЎРЉР В·Р С•Р Р†Р В°РЎвЂљР ВµР В»РЎРЏ
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
     * Р СљР ВµРЎвЂљР С•Р Т‘ Р Р†РЎвЂ№Р В·РЎвЂ№Р Р†Р В°Р ВµРЎвЂљРЎРЉРЎРѓРЎРЏ Р С—РЎР‚Р С‘ РЎРѓРЎР‚Р В°Р Р†Р Р…Р С‘Р Р†Р В°Р Р…Р С‘Р С‘ РЎРѓР С•Р Т‘Р ВµРЎР‚Р В¶Р С‘Р С�Р С•Р С–Р С• Р С•Р В±РЎРЉР ВµР С”РЎвЂљР С•Р Р† (Р С—Р С•Р В»РЎРЉР В·Р С•Р Р†Р В°РЎвЂљР ВµР В»Р ВµР в„–)
     * @param o Р Т‘Р В°Р Р…Р Р…РЎвЂ№Р в„– Р С—Р С•Р В»РЎРЉР В·Р С•Р Р†Р В°РЎвЂљР ВµР В»РЎРЉ
     * @return РЎРЊР С”Р Р†Р С‘Р Р†Р В°Р В»Р ВµР Р…РЎвЂљР Р…Р С•РЎРѓРЎвЂљРЎРЉ Р С•Р В±РЎРЉР ВµР С”РЎвЂљР С•Р Р†
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
>>>>>>> 5baadd0bc6ab23d03ea6f619b564a156e84be8cb:Booking/src/com/booking/model/User.java
